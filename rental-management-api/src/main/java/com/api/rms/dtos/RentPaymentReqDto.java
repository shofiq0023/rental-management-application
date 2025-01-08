package com.api.rms.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
