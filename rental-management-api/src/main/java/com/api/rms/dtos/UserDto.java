package com.api.rms.dtos;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String dateOfBirth;
}
