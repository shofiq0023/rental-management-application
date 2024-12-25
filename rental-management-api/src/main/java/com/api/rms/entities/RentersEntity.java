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
@Table(name = "renters")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentersEntity extends BaseEntity {
    private String nidNo;
    private String deal;
    private Long buildingId;
    private String flats;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;
}
