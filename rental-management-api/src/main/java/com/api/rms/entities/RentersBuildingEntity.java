package com.api.rms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "renters_building")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentersBuildingEntity extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "renter_id", nullable = false)
    private RentersEntity rentersEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_flat_id", nullable = false)
    private BuildingFlatEntity buildingFlatEntity;
}
