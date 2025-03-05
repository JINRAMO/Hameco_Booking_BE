package com.example.hamecobooking.entity;

import com.example.hamecobooking.enums.Gender;
import com.example.hamecobooking.enums.StoreAcceptStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DesignerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long designerId;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private StoreEntity store;

    @OneToMany(mappedBy = "designer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProcedureEntity> procedures = new ArrayList<>();

    @OneToOne(mappedBy = "designer", cascade = CascadeType.ALL, orphanRemoval = true)
    private AvailableHourEntity availableHours;

    @OneToMany(mappedBy = "designer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservationEntity> reservations = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "login_id", nullable = false)
    private AuthenticationEntity login;

    @Column(nullable = false, length = 255)
    private String username;

    @Column(length = 20)
    private String phoneNumber;
    private Gender gender;
    private int careerYears;

    @Enumerated(EnumType.STRING)
    private StoreAcceptStatus storeAcceptStatus;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

}

