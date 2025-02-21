package com.example.hamecobooking.dto.designer;

import com.example.hamecobooking.entity.*;
import com.example.hamecobooking.enums.StoreAcceptStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DesignerDto {
    private Long designerId;
    private StoreEntity store;
    private List<ProcedureEntity> procedures;
    private List<AvailableHourEntity> availableHours = new ArrayList<>();
    private List<ReservationEntity> reservations = new ArrayList<>();
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private int careerYears;
    private StoreAcceptStatus storeAcceptStatus;
    private LocalDateTime createdAt;

    public static DesignerDto fromEntity(DesignerEntity designer) {
        return DesignerDto.builder()
                .designerId(designer.getDesignerId())
                .store(designer.getStore())
                .procedures(designer.getProcedures())
                .availableHours(designer.getAvailableHours())
                .reservations(designer.getReservations())
                .username(designer.getUsername())
                .email(designer.getEmail())
                .password(designer.getPassword())
                .phoneNumber(designer.getPhoneNumber())
                .careerYears(designer.getCareerYears())
                .storeAcceptStatus(designer.getStoreAcceptStatus())
                .createdAt(designer.getCreatedAt())
                .build();
    }
}
