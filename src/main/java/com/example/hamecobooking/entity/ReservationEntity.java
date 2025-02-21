package com.example.hamecobooking.entity;

import com.example.hamecobooking.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "designer_id")
    private DesignerEntity designer;

    @ManyToOne
    @JoinColumn(name = "procedure_id")
    private ProcedureEntity procedure;

    @Column(columnDefinition = "TEXT")
    private String notes;

    private LocalDateTime reservationDateTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
