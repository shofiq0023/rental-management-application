package com.api.rms.controllers;

import com.api.rms.dtos.ResponseDto;
import com.api.rms.dtos.UserAuthReq;
import com.api.rms.dtos.UserSignupReqDto;
import com.api.rms.interfaces.UserAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserAuthenticationController {
    private final UserAuthenticationService service;

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseDto> userAuthenticate(@RequestBody UserAuthReq req) {
        return service.userAuthenticate(req);
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> userSignup(@RequestBody UserSignupReqDto reqDto) {
        return service.userSignup(reqDto, 2, 0);
    }

    // FOR ADMINS (LAND-LORD) ONLY
    @PostMapping("/create/user")
    public ResponseEntity<ResponseDto> createUser(@RequestBody UserSignupReqDto reqDto) {
        return service.userSignup(reqDto, reqDto.getUserType(), 1);
    }
}
