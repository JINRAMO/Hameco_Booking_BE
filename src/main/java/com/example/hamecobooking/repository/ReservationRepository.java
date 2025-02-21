package com.example.hamecobooking.repository;

import com.example.hamecobooking.entity.DesignerEntity;
import com.example.hamecobooking.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long>{
    Optional<ReservationEntity> findByReservationIdAndUser_UserId(Long reservationId, Long id);
    boolean existsByDesignerAndReservationDateTime(DesignerEntity designer, LocalDateTime reservationDateTime);
    List<ReservationEntity> findByUser_UserId(Long id);
    List<ReservationEntity> findByDesigner_DesignerId(Long id);
}

