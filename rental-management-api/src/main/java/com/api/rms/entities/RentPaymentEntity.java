package com.api.rms.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "rent_payment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentPaymentEntity extends BaseEntity {
    private double amount;
    private double utilityBill;
    private double othersBill;
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Dhaka")
    private Date paymentDate;
    private String flatNo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "renter_id", referencedColumnName = "id")
    private RentersEntity renter;
}
