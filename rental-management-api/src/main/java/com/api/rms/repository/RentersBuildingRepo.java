package com.api.rms.repository;

import com.api.rms.entities.RentersBuildingEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

@Transactional
public interface RentersBuildingRepo extends JpaRepository<RentersBuildingEntity, Long> {
    Optional<RentersBuildingEntity> findByUserId(Long userId);

    @Query("SELECT COUNT(*) FROM RentersBuildingEntity WHERE user.id=:userId")
    int findCountByUserId(Long userId);

    @Modifying
    @Query("DELETE FROM RentersBuildingEntity WHERE buildingFlat.id=:buildingFlatId")
    void deleteByBuildingFlatId(Long buildingFlatId);
}
