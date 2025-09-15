package com.api.rms.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class RentersQueryResDto {
    private Long renterId;
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
    private String flatNo;
    private Long buildingId;
    private String buildingName;
    private String buildingAddress;

    public RentersQueryResDto(
            Long renterId, String nidNo, String deal, Long userId, String renterName,
            String renterUsername, String renterEmail, String renterPhone,
            String renterAddress, Date renterDateOfBirth, Long buildingFlatId,
            String flatNo, Long buildingId, String buildingName, String buildingAddress
    ) {
        this.renterId = renterId;
        this.nidNo = nidNo;
        this.deal = deal;
        this.userId = userId;
        this.renterName = renterName;
        this.renterUsername = renterUsername;
        this.renterEmail = renterEmail;
        this.renterPhone = renterPhone;
        this.renterAddress = renterAddress;
        this.renterDateOfBirth = renterDateOfBirth;
        this.buildingFlatId = buildingFlatId;
        this.flatNo = flatNo;
        this.buildingId = buildingId;
        this.buildingName = buildingName;
        this.buildingAddress = buildingAddress;
    }
}
