package com.api.rms.repository;

import com.api.rms.entities.RentersBuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentersBuildingRepo extends JpaRepository<RentersBuildingEntity, Long> {
}
