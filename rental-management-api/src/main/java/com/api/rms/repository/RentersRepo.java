package com.api.rms.repository;

import com.api.rms.entities.RentersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentersRepo extends JpaRepository<RentersEntity, Long> {
}
