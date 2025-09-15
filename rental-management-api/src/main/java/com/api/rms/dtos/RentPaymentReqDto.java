package com.api.rms.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentPaymentReqDto {
    private double amount;
    private double utilityBill;
    private double othersBill;
    private Long renterId;
    private Long userId;
    private int paymentStatus;
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Dhaka")
    private Date rentPaymentDate;
}
