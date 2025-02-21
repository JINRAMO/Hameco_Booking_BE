package com.example.hamecobooking.entity;

import com.example.hamecobooking.enums.Status;
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
public class PartnerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partnerId;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private ManagerEntity manager;

    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StoreEntity> stores = new ArrayList<>();

    @Column(nullable = false, length = 255)
    private String businessName;

    @Column(columnDefinition = "TEXT")
    private String businessAddress;

    @Column(length = 20)
    private String businessPhone;

    @Column(nullable = false, unique = true, length = 50)
    private String businessNumber;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}

