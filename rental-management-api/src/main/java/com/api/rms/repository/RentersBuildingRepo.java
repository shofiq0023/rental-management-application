package com.api.rms.repository;

import com.api.rms.entities.RentersBuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RentersBuildingRepo extends JpaRepository<RentersBuildingEntity, Long> {
    Optional<RentersBuildingEntity> findByUserId(Long userId);

    @Query("SELECT COUNT(*) FROM RentersBuildingEntity WHERE user.id=:userId")
    int findCountByUserId(Long userId);
}
