package com.api.rms.interfaces;

import com.api.rms.dtos.ResponseDto;
import com.api.rms.dtos.UserAuthReq;
import org.springframework.http.ResponseEntity;

public interface UserAuthenticationService {
    ResponseEntity<ResponseDto> userAuthenticate(UserAuthReq req);
}
