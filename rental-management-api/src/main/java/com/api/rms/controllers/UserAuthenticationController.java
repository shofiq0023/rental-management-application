package com.api.rms.controllers;

import com.api.rms.dtos.ResponseDto;
import com.api.rms.dtos.UserAuthReq;
import com.api.rms.dtos.UserSignupReqDto;
import com.api.rms.interfaces.UserAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserAuthenticationController {
    private final UserAuthenticationService service;

    // Api for logging in
    @PostMapping("/authenticate")
    public ResponseEntity<ResponseDto> userAuthenticate(@RequestBody UserAuthReq req) {
        return service.userAuthenticate(req);
    }

    // User signup api
    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> userSignup(@RequestBody UserSignupReqDto reqDto) {
        return service.userSignup(reqDto, 2, 0);
    }

    // FOR ADMINS (LAND-LORD) ONLY
    @PostMapping("/create/user")
    public ResponseEntity<ResponseDto> createUser(@RequestBody UserSignupReqDto reqDto) {
        return service.userSignup(reqDto, reqDto.getUserType(), 1);
    }

    // Get Inactive User's List
    @GetMapping("/inactive/users")
    public ResponseEntity<ResponseDto> getInactiveUsers() {
        return service.getInactiveUsers();
    }

    @PostMapping("/active/user/{userId}")
    public ResponseEntity<ResponseDto> activeUser(@PathVariable("userId") Long userId) {
        return service.activeUser(userId);
    }

    @GetMapping("/users")
    public ResponseEntity<ResponseDto> getAllUsers() {
        return service.getAllUsers();
    }
}
