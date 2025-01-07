package com.api.rms.repository;

import com.api.rms.entities.BuildingFlatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BuildingFlatRepo extends JpaRepository<BuildingFlatEntity, Long> {
    List<BuildingFlatEntity> findByBuildingId(Long buildingId);

    @Modifying
    @Query("UPDATE BuildingFlatEntity SET isRented=true WHERE id = :id")
    void changeFlatStatus(Long id);

    @Query("SELECT bf.isRented FROM BuildingFlatEntity bf WHERE bf.id = :id")
    Boolean findIsRentedStatus(Long id);

    List<BuildingFlatEntity> findByBuildingIdOrderByFlatNo(Long buildingId);
}
