package com.api.rms.services;

import com.api.rms.configs.JwtService;
import com.api.rms.dtos.*;
import com.api.rms.entities.UserEntity;
import com.api.rms.interfaces.EncoderDecoder;
import com.api.rms.interfaces.UserAuthenticationService;
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

    @Value("${password.salt}")
    private String salt;

    @Override
    public ResponseEntity<ResponseDto> userAuthenticate(UserAuthReq req) {
        try {
            String encodedPass = encoderDecoder.encodeString(req.getPassword(), salt);
            Optional<UserEntity> userOpt = userRepo.findByUsernameAndPassword(req.getUsername(), encodedPass);

            if (userOpt.isEmpty())
                return resUtil.createErrorResponse("No user found with this username and password", "404");

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
            return resUtil.createDuplicateKeyResponse(e);
        } catch (Exception e) {
            return resUtil.createErrorResponse();
        }
    }

    @Override
    public ResponseEntity<ResponseDto> getInactiveUsers() {

        try {
            List<UserEntity> userList = new ArrayList<>(userRepo.findByStatusOrderByCreatedAtDesc(0));
            List<UserDto> userDtos = userList.stream().map(u -> Utility.copyProperties(u, UserDto.class)).toList();

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
            List<UserEntity> entities = new ArrayList<>(userRepo.findAll());

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
                dto.setUserType(entity.getUserType() == 1 ? "ROLE_ADMIN" : "ROLE_USER");
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
}
