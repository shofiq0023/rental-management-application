package com.api.rms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

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
