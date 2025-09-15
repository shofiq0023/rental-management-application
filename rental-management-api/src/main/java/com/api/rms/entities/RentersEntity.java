package com.api.rms.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "renters")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentersEntity extends BaseEntity {
    private String nidNo;
    private String deal;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "building_flat_id", nullable = false)
    private BuildingFlatEntity buildingFlat;
}
