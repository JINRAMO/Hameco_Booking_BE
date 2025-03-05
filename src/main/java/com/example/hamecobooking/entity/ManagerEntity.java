package com.example.hamecobooking.entity;

import com.example.hamecobooking.enums.Gender;
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
public class ManagerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long managerId;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PartnerEntity> partners = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "designer_id", nullable = true)
    private DesignerEntity designer;

    @OneToOne
    @JoinColumn(name = "login_id", nullable = false)
    private AuthenticationEntity login;

    @Column(nullable = false, length = 255)
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 255)
    private Gender gender;

    @Column(length = 20)
    private String phoneNumber;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
