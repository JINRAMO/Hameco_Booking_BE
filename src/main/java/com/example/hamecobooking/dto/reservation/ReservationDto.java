package com.example.hamecobooking.dto.reservation;

import com.example.hamecobooking.entity.DesignerEntity;
import com.example.hamecobooking.entity.ProcedureEntity;
import com.example.hamecobooking.entity.ReservationEntity;
import com.example.hamecobooking.entity.UserEntity;
import com.example.hamecobooking.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationDto {
    private Long reservationId;
    private UserEntity user;
    private DesignerEntity designer;
    private ProcedureEntity procedure;
    private String notes;
    private LocalDateTime reservationDateTime;
    private Status status;
    private LocalDateTime createdAt;

    public static ReservationDto fromEntity(ReservationEntity reservation) {
        return ReservationDto.builder()
                .reservationId(reservation.getReservationId())
                .user(reservation.getUser())
                .designer(reservation.getDesigner())
                .procedure(reservation.getProcedure())
                .notes(reservation.getNotes())
                .reservationDateTime(reservation.getReservationDateTime())
                .status(reservation.getStatus())
                .createdAt(reservation.getCreatedAt())
                .build();
    }
}
