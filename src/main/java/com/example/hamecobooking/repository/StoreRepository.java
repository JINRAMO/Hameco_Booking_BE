package com.example.hamecobooking.repository;

import com.example.hamecobooking.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Long> {
    Optional<StoreEntity> findByStoreIdAndPartner_Manager_Login_email(Long storeId, String email);
    Optional<StoreEntity> findByStoreId(Long storeId);
}
