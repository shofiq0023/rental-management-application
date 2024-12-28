package com.api.rms.repository;

import com.api.rms.entities.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Transactional
public interface UserRepo extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByUsernameAndPassword(String username, String encodedPass);

    List<UserEntity> findByStatusOrderByCreatedAtDesc(int status);

    @Modifying
    @Query("UPDATE UserEntity SET status = :status WHERE id = :userId")
    void updateUserStatus(int status, Long userId);
}
