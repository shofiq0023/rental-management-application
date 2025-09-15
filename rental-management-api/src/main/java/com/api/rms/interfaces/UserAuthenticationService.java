package com.api.rms.interfaces;

import com.api.rms.dtos.PasswordChangeReqDto;
import com.api.rms.dtos.ResponseDto;
import com.api.rms.dtos.UserAuthReq;
import com.api.rms.dtos.UserSignupReqDto;
import org.springframework.http.ResponseEntity;

public interface UserAuthenticationService {
    ResponseEntity<ResponseDto> userAuthenticate(UserAuthReq req);

    ResponseEntity<ResponseDto> userSignup(UserSignupReqDto reqDto, int userType, int userStatus);

    ResponseEntity<ResponseDto> getInactiveUsers();

    ResponseEntity<ResponseDto> activeUser(Long userId);

    ResponseEntity<ResponseDto> getAllUsers();

    ResponseEntity<ResponseDto> createAdminUser();

    ResponseEntity<ResponseDto> deleteUser(Long userId);

    ResponseEntity<ResponseDto> updateUserInfo(UserSignupReqDto reqDto, Long userId);

    ResponseEntity<ResponseDto> updateUserPassword(PasswordChangeReqDto reqDto, Long userId);

    ResponseEntity<ResponseDto> getUserDetailsFromToken(String token);
}
