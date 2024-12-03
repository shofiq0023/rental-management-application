package com.api.rms.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "buildings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuildingsEntity extends BaseEntity {
    private String name;
    private String address;
}
