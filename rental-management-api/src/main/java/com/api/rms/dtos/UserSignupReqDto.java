package com.api.rms.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;

@Data
public class UserSignupReqDto {
    private String name;
    private String username;
    private String email;
    private String phone;
    private String password;
    private String address;
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Dhaka")
    private Date dateOfBirth;
    private int userType;
}
