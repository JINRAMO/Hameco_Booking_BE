package com.example.hamecobooking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProcedureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long procedureId;

    @ManyToOne
    @JoinColumn(name = "designer_id")
    private DesignerEntity designer;

    @OneToMany(mappedBy = "procedure", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewEntity> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "procedure",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservationEntity> reservations = new ArrayList<>();

    @Column(nullable = false, length = 255)
    private String procedureName;

    private int duration;

    @Column(columnDefinition = "TEXT")
    private String description;

    private int price;
}

