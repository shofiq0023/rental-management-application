package com.api.rms.services;

import com.api.rms.configs.JwtService;
import com.api.rms.dtos.*;
import com.api.rms.entities.RentersBuildingEntity;
import com.api.rms.entities.UserEntity;
import com.api.rms.interfaces.EncoderDecoder;
import com.api.rms.interfaces.UserAuthenticationService;
import com.api.rms.repository.RentersBuildingRepo;
import com.api.rms.repository.UserRepo;
import com.api.rms.utilities.GenericResponseUtil;
import com.api.rms.utilities.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserAuthenticationServiceImpl implements UserAuthenticationService {
    private final UserRepo userRepo;
    private final EncoderDecoder encoderDecoder;
    private final AuthenticationManager authManager;
    private final GenericResponseUtil resUtil;
    private final JwtService jwtService;
    private final PasswordEncoder passEncoder;
    private final RentersBuildingRepo rentersBuildingRepo;

    @Value("${password.salt}")
    private String salt;

    @Override
    public ResponseEntity<ResponseDto> userAuthenticate(UserAuthReq req) {
        try {
            String encodedPass = encoderDecoder.encodeString(req.getPassword(), salt);
            Optional<UserEntity> userOpt = userRepo.findByUsernameAndPassword(req.getUsername(), encodedPass);

            if (userOpt.isEmpty())
                return resUtil.createErrorResponse("No user found with this username and password", "404");

            if (userOpt.get().getStatus() == 0)
                return resUtil.createErrorResponse("Your account is inactive. Please contact the landlord for account activation", "403");

            UserEntity user = userOpt.get();
            Map<String, Object> claims = new HashMap<>();
            authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), req.getPassword()));
            claims.put("sub", user.getUsername());
            claims.put("userId", user.getId());
            claims.put("email", user.getEmail());
            claims.put("userType", user.getUserType() == 1 ? "ROLE_ADMIN" : "ROLE_USER");

            String token = jwtService.generateToken(claims, user.getUsername());

            UserDto userDto = Utility.copyProperties(user, UserDto.class);
            AuthenticationResDto resDto = new AuthenticationResDto(token, userDto);

            return resUtil.createSuccessResponse(resDto);
        } catch (Exception e) {
            return resUtil.createErrorResponse();
        }
    }

    @Override
    public ResponseEntity<ResponseDto> userSignup(UserSignupReqDto reqDto, int userType, int userStatus) {
        try {
            UserEntity userEntity = new UserEntity();
            userEntity.setName(reqDto.getName());
            userEntity.setUsername(reqDto.getUsername());
            userEntity.setEmail(reqDto.getEmail());
            userEntity.setPhone(reqDto.getPhone());
            userEntity.setAddress(reqDto.getAddress());
            userEntity.setDateOfBirth(reqDto.getDateOfBirth());

            // Encoding the origin password
            // This will help in retrieving the user's password later
            String encodedPass = encoderDecoder.encodeString(reqDto.getPassword(), salt);
            userEntity.setPassword(encodedPass);
            userEntity.setPasswordEncoded(passEncoder.encode(reqDto.getPassword()));

            // Default user is "Renter"
            userEntity.setUserType(userType);

            userEntity.setSignupDate(new Date(System.currentTimeMillis()));
            userEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            userEntity.setStatus(userStatus);

            UserEntity savedEntity = userRepo.save(userEntity);

            return resUtil.createSuccessResponse(savedEntity, "User signup successful");
        } catch (DataIntegrityViolationException e) {
            return resUtil.createErrorResponse("Duplicate data is not allowed");
        } catch (Exception e) {
            return resUtil.createErrorResponse();
        }
    }

    @Override
    public ResponseEntity<ResponseDto> getInactiveUsers() {

        try {
            List<UserEntity> userList = new ArrayList<>(userRepo.findByStatusOrderByCreatedAtDesc(0));
            List<UserDto> userDtos = new ArrayList<>();

            for (UserEntity entity : userList) {
                UserDto dto = new UserDto();
                dto.setId(entity.getId());
                dto.setName(entity.getName());
                dto.setUsername(entity.getUsername());
                dto.setEmail(entity.getEmail());
                dto.setPhone(entity.getPhone());
                dto.setUserType(entity.getUserType() == 1 ? "ADMIN" : "RENTER");
                dto.setAddress(entity.getAddress());
                dto.setDateOfBirth(entity.getDateOfBirth());
                dto.setSignupDate(entity.getSignupDate());

                userDtos.add(dto);
            }

            if (userDtos.isEmpty()) {
                return resUtil.createSuccessResponse("No inactive users found");
            } else {
                return resUtil.createSuccessResponse(userDtos, "Inactive users found");
            }
        } catch (Exception e) {
            return resUtil.createErrorResponse();
        }
    }

    @Override
    public ResponseEntity<ResponseDto> activeUser(Long userId) {
        try {
            Optional<UserEntity> userOpt = userRepo.findById(userId);

            if (userOpt.isEmpty()) {
                return resUtil.createSuccessResponse("No user found with the given id");
            }

            userRepo.updateUserStatus(1, userId);

            return resUtil.createSuccessResponse("User activated");
        } catch (Exception e) {
            return resUtil.createErrorResponse();
        }
    }

    @Override
    public ResponseEntity<ResponseDto> getAllUsers() {
        try {
            List<UserEntity> entities = new ArrayList<>(userRepo.findAllByStatus(1));

            if (entities.isEmpty())
                return resUtil.createSuccessResponse("No Data found!");

            List<UserDto> dtos = new ArrayList<>();
            for (UserEntity entity : entities) {
                UserDto dto = new UserDto();
                dto.setId(entity.getId());
                dto.setName(entity.getName());
                dto.setUsername(entity.getUsername());
                dto.setEmail(entity.getEmail());
                dto.setPhone(entity.getPhone());
                dto.setUserType(entity.getUserType() == 1 ? "ADMIN" : "RENTER");
                dto.setAddress(entity.getAddress());
                dto.setDateOfBirth(entity.getDateOfBirth());
                dto.setSignupDate(entity.getSignupDate());

                dtos.add(dto);
            }

            return resUtil.createSuccessResponse(dtos, "Users found");
        } catch (Exception e) {
            return resUtil.createErrorResponse();
        }
    }

    @Override
    public ResponseEntity<ResponseDto> createAdminUser() {
        try {
            UserEntity userEntity = new UserEntity();
            userEntity.setName("System User");
            userEntity.setUsername("sysadmin");
            userEntity.setEmail("system.user@gmail.com");
            userEntity.setPhone("017181818686");
            userEntity.setAddress("System User Address");
            userEntity.setDateOfBirth(new Date(System.currentTimeMillis()));

            // Encoding the origin password
            // This will help in retrieving the user's password later
            String encodedPass = encoderDecoder.encodeString("123456", salt);
            userEntity.setPassword(encodedPass);
            userEntity.setPasswordEncoded(passEncoder.encode("123456"));

            // Default user is "Renter"
            userEntity.setUserType(1);

            userEntity.setSignupDate(new Date(System.currentTimeMillis()));
            userEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            userEntity.setStatus(1);

            UserEntity savedEntity = userRepo.save(userEntity);

            return resUtil.createSuccessResponse(savedEntity, "User creation successful");
        } catch (DataIntegrityViolationException e) {
            return resUtil.createDuplicateKeyResponse(e);
        } catch (Exception e) {
            return resUtil.createErrorResponse();
        }
    }

    @Override
    public ResponseEntity<ResponseDto> deleteUser(Long userId) {
        try {
            Optional<UserEntity> userOpt = userRepo.findById(userId);

            if (userOpt.isEmpty())
                return resUtil.createErrorResponse("No user found with the given id!");

            int flatRentedByUser = rentersBuildingRepo.findCountByUserId(userId);

            if (flatRentedByUser > 0)
                return resUtil.createErrorResponse("This user is assigned as a renter. Please remove this user from renter first!");

            userRepo.deleteById(userId);

            return resUtil.createSuccessResponse(true, "Users deleted successfully!");
        } catch (Exception e) {
            return resUtil.createErrorResponse();
        }
    }

    @Override
    public ResponseEntity<ResponseDto> getUserDetailsFromToken(String token) {
        try {
            String jwt = token.substring(7);
            Long userId = jwtService.extractUserId(jwt);

            Optional<UserEntity> userEntityOpt = userRepo.findById(userId);
            if (userEntityOpt.isEmpty())
                return resUtil.createErrorResponse("No user found with the given id!");

            return resUtil.createSuccessResponse(userEntityOpt.get(), "User information updated successfully!");
        } catch (Exception e) {
            return resUtil.createErrorResponse();
        }
    }

    @Override
    public ResponseEntity<ResponseDto> updateUserInfo(UserSignupReqDto reqDto, Long userId) {
        try {
            Optional<UserEntity> userOpt = userRepo.findById(userId);
            if (userOpt.isEmpty())
                return resUtil.createErrorResponse("No user found with the given id!");

            UserEntity existingEntity = userOpt.get();
            existingEntity.setName(reqDto.getName());
            existingEntity.setAddress(reqDto.getAddress());
            existingEntity.setDateOfBirth(reqDto.getDateOfBirth());
            existingEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

            UserEntity updatedEntity = userRepo.save(existingEntity);

            return resUtil.createSuccessResponse(updatedEntity, "User information updated successfully!");
        } catch (Exception e) {
            return resUtil.createErrorResponse();
        }
    }

    @Override
    public ResponseEntity<ResponseDto> updateUserPassword(PasswordChangeReqDto reqDto, Long userId) {
        return null;
    }
}
