package com.api.rms.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends BaseEntity {
    private String name;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phone;
    private String password;
    private String passwordEncoded;
    private int userType;
    private String address;
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Dhaka")
    private Date signupDate;
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Dhaka")
    private Date dateOfBirth;
    private int status;
}
