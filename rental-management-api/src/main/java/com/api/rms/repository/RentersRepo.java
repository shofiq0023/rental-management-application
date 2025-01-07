package com.api.rms.repository;

import com.api.rms.dtos.RentersQueryResDto;
import com.api.rms.entities.RentersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RentersRepo extends JpaRepository<RentersEntity, Long> {
    @Query(value = "SELECT new com.api.rms.dtos.RentersQueryResDto(" +
            " r.id AS renterId, " +
            " r.nidNo AS nidNo, " +
            " r.deal AS deal, " +
            " u.id AS userId, " +
            " u.name AS renterName, " +
            " u.username AS renterUsername, " +
            " u.email AS renterEmail, " +
            " u.phone AS renterPhone, " +
            " u.address AS renterAddress, " +
            " u.dateOfBirth AS renterDateOfBirth, " +
            " bf.id AS buildingFlatId, " +
            " bf.flatNo AS flatNo, " +
            " b.id AS buildingId, " +
            " b.name AS buildingName, " +
            " b.address AS buildingAddress) " +
            "FROM RentersEntity r " +
            "LEFT JOIN UserEntity u ON r.user.id = u.id " +
            "LEFT JOIN BuildingFlatEntity bf ON r.buildingFlat.id = bf.id " +
            "LEFT JOIN BuildingsEntity b ON bf.building.id = b.id")
    List<RentersQueryResDto> findAllRenters();

    @Query(value = "SELECT new com.api.rms.dtos.RentersQueryResDto(" +
            " r.id AS renterId, " +
            " r.nidNo AS nidNo, " +
            " r.deal AS deal, " +
            " u.id AS userId, " +
            " u.name AS renterName, " +
            " u.username AS renterUsername, " +
            " u.email AS renterEmail, " +
            " u.phone AS renterPhone, " +
            " u.address AS renterAddress, " +
            " u.dateOfBirth AS renterDateOfBirth, " +
            " bf.id AS buildingFlatId, " +
            " bf.flatNo AS flatNo, " +
            " b.id AS buildingId, " +
            " b.name AS buildingName, " +
            " b.address AS buildingAddress) " +
            "FROM RentersEntity r " +
            "LEFT JOIN UserEntity u ON r.user.id = u.id " +
            "LEFT JOIN BuildingFlatEntity bf ON r.buildingFlat.id = bf.id " +
            "LEFT JOIN BuildingsEntity b ON bf.building.id = b.id " +
            "WHERE r.id=:renterId")
    List<RentersQueryResDto> findRenter(Long renterId);
}
