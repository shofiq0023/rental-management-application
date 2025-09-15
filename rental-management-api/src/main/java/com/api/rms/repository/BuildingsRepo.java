package com.api.rms.repository;

import com.api.rms.entities.BuildingsEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Transactional
public interface BuildingsRepo extends JpaRepository<BuildingsEntity, Long> {
    @Query("FROM BuildingsEntity ORDER BY createdAt DESC")
    List<BuildingsEntity> findAllOrderByCreatedAtDesc();
}
