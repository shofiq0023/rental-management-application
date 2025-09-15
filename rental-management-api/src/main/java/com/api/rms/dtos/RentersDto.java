package com.api.rms.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentersDto {
    private String nidNo;
    private String deal;
    private Long userId;
    private String renterName;
    private String renterUsername;
    private String renterEmail;
    private String renterPhone;
    private String renterAddress;
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Dhaka")
    private Date renterDateOfBirth;
    private Long buildingFlatId;
    private List<Long> renterIds;
    private List<RentedBuildingDto> rentedBuildings;
}
