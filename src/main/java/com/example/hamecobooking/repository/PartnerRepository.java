package com.example.hamecobooking.repository;

import com.example.hamecobooking.entity.PartnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartnerRepository extends JpaRepository<PartnerEntity, Long> {
    List<PartnerEntity> findByManager_Login_email(String email);
    Optional<PartnerEntity> findByPartnerIdAndManager_Login_email(Long partnerId, String email);
    Optional<PartnerEntity> findByPartnerId(Long partnerId);
}
