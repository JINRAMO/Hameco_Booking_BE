package com.example.hamecobooking.dto.review;

import com.example.hamecobooking.entity.ProcedureEntity;
import com.example.hamecobooking.entity.ReviewEntity;
import com.example.hamecobooking.entity.StoreEntity;
import com.example.hamecobooking.entity.UserEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {
    private Long reviewId;
    private UserEntity user;
    private ProcedureEntity procedure;
    private StoreEntity store;
    private int rating;
    private String content;
    private LocalDateTime createdAt;

    public static ReviewDto fromEntity(ReviewEntity review) {
        return ReviewDto.builder()
                .reviewId(review.getReviewId())
                .user(review.getUser())
                .procedure(review.getProcedure())
                .store(review.getStore())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
