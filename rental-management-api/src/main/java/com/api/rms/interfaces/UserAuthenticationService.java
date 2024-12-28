package com.api.rms.interfaces;

import com.api.rms.dtos.ResponseDto;
import com.api.rms.dtos.UserAuthReq;
import com.api.rms.dtos.UserSignupReqDto;
import org.springframework.http.ResponseEntity;

public interface UserAuthenticationService {
    ResponseEntity<ResponseDto> userAuthenticate(UserAuthReq req);

    ResponseEntity<ResponseDto> userSignup(UserSignupReqDto reqDto, int userType, int userStatus);

    ResponseEntity<ResponseDto> getInactiveUsers();

    ResponseEntity<ResponseDto> activeUser(Long userId);
}
