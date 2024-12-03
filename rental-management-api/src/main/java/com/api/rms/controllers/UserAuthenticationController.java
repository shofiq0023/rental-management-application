package com.api.rms.controllers;

import com.api.rms.dtos.ResponseDto;
import com.api.rms.dtos.UserAuthReq;
import com.api.rms.interfaces.UserAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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


}
