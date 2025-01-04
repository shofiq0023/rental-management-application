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

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Set<BuildingFlatEntity> buildingFlat;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;
}
