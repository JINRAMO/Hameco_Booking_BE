package com.example.hamecobooking.entity;

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
public class StoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    @ManyToOne
    @JoinColumn(name = "partner_id")
    private PartnerEntity partner;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DesignerEntity> designers = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewEntity> reviews = new ArrayList<>();

    @Column(nullable = false, length = 255)
    private String storeName;

    @Column(columnDefinition = "TEXT")
    private String storeAddress;

    @Column(length = 20)
    private String storePhone;

    @Column(length = 50)
    private String storeType;

    private float rating;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

}
