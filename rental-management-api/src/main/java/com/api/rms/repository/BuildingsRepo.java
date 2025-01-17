package com.api.rms.repository;

import com.api.rms.entities.BuildingsEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface BuildingsRepo extends JpaRepository<BuildingsEntity, Long> {
}
