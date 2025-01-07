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
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "building_flat_id", nullable = false)
    private BuildingFlatEntity buildingFlat;
}
