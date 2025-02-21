package com.example.hamecobooking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long reviewId;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private UserEntity user;

        @ManyToOne
        @JoinColumn(name = "procedure_id")
        private ProcedureEntity procedure;

        @ManyToOne
        @JoinColumn(name = "store_id", nullable = false)
        private StoreEntity store;

        private int rating;

        @Column(columnDefinition = "TEXT")
        private String content;

        @Column(nullable = false)
        private LocalDateTime createdAt = LocalDateTime.now();
}
