package com.api.rms.repository;

import com.api.rms.entities.BuildingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingsRepo extends JpaRepository<BuildingsEntity, Long> {
}
