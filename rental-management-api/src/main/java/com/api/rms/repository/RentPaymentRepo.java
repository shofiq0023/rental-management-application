package com.api.rms.repository;

import com.api.rms.entities.RentPaymentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Transactional
public interface RentPaymentRepo extends JpaRepository<RentPaymentEntity, Long> {
    @Modifying
    @Query("DELETE FROM RentPaymentEntity WHERE renter.id=:renterId")
    void deleteByRenterId(Long renterId);

    List<RentPaymentEntity> findAllByUserId(Long userId);

    @Query("FROM RentPaymentEntity ORDER BY createdAt DESC")
    List<RentPaymentEntity> findAllOrderByCreatedAtDesc();
}
