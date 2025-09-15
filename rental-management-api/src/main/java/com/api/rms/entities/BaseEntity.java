package com.api.rms.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", timezone = "Asia/Dhaka")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", timezone = "Asia/Dhaka")
    private Timestamp updatedAt;
}
