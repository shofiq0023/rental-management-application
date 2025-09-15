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
@Table(name = "building_flat")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuildingFlatEntity extends BaseEntity{
    private String flatNo;
    private boolean isRented;

    @ManyToOne
    @JoinColumn(name = "building_id", nullable = false)
    private BuildingsEntity building;
}
