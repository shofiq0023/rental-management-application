package com.api.rms.repository;

import com.api.rms.entities.RentPaymentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Transactional
public interface RentPaymentRepo extends JpaRepository<RentPaymentEntity, Long> {
    @Modifying
    @Query("DELETE FROM RentPaymentEntity WHERE renter.id=:renterId")
    void deleteByRenterId(Long renterId);
}
