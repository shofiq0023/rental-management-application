package com.api.rms.repository;

import com.api.rms.entities.BuildingFlatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuildingFlatRepo extends JpaRepository<BuildingFlatEntity, Long> {
    List<BuildingFlatEntity> findByBuildingId(Long buildingId);
}
