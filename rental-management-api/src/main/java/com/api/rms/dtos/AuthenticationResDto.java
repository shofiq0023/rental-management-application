package com.api.rms.dtos;

import lombok.Data;

@Data
public class AuthenticationResDto {
    private String token;
    private UserDto userInfo;

    public AuthenticationResDto(String token, UserDto userDto) {
        this.token = token;
        this.userInfo = userDto;
    }
}
