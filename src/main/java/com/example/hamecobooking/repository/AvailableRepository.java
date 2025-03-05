package com.example.hamecobooking.repository;

import com.example.hamecobooking.entity.AvailableHourEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvailableRepository extends JpaRepository<AvailableHourEntity, Long> {
    Optional<AvailableHourEntity>findByIdAndDesigner_Login_email(Long availableHourId, String email);
//    Optional<AvailableHourEntity> findByDesignerId(Long designerId);
}
