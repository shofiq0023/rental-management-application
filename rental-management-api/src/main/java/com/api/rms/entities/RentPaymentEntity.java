package com.api.rms.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "rent_payment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentPaymentEntity extends BaseEntity {
    private double amount;
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Dhaka")
    private Date paymentDate;
    private String monthName;
    private String yearStr;
    private double utilityBill;
    private double othersBill;

    @OneToOne
    @JoinColumn(name = "renter_id", referencedColumnName = "id")
    private RentersEntity renter;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;
}
