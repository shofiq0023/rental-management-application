package com.api.rms.repository;

import com.api.rms.entities.RentPaymentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface RentPaymentRepo extends JpaRepository<RentPaymentEntity, Long> {
}
