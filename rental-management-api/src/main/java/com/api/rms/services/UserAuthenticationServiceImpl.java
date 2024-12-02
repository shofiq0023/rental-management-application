package com.api.rms.services;

import com.api.rms.configs.JwtService;
import com.api.rms.dtos.AuthenticationResDto;
import com.api.rms.dtos.ResponseDto;
import com.api.rms.dtos.UserAuthReq;
import com.api.rms.dtos.UserDto;
import com.api.rms.entities.UserEntity;
import com.api.rms.interfaces.EncoderDecoder;
import com.api.rms.interfaces.UserAuthenticationService;
import com.api.rms.repository.UserRepo;
import com.api.rms.utilities.GenericResponseUtil;
import com.api.rms.utilities.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAuthenticationServiceImpl implements UserAuthenticationService {
    private final UserRepo userRepo;
    private final EncoderDecoder encoderDecoder;
    private final AuthenticationManager authManager;
    private final GenericResponseUtil resUtil;
    private final JwtService jwtService;

    @Value("${password.salt}")
    private String salt;

    @Override
    public ResponseEntity<ResponseDto> userAuthenticate(UserAuthReq req) {
        try {
            String encodedPass = encoderDecoder.encodeString(req.getPassword(), salt);
            Optional<UserEntity> userOpt = userRepo.findByUsernameAndPassword(req.getUsername(), encodedPass);

            if (userOpt.isEmpty())
                return resUtil.createErrorResponse("User not found", "404");

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
            return resUtil.createErrorResponse("Something Went Wrong!");
        }
    }
}
