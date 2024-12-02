package com.api.rms.dtos;

import lombok.Data;

@Data
public class UserAuthReq {
    private String username;
    private String password;
}
