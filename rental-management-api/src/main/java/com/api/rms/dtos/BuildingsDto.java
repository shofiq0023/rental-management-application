package com.api.rms.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuildingsDto {
    private Long id;
    private String name;
    private String address;
    private List<FlatDto> flats;
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", timezone = "Asia/Dhaka")
    private Timestamp createdAt;
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", timezone = "Asia/Dhaka")
    private Timestamp updatedAt;
}
