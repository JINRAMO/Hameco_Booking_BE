package com.example.hamecobooking.dto.review;

import lombok.*;

import java.time.LocalDateTime;

public class UpdateReview {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request {
        private Long reviewId;
        private int rating;
        private String content;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private Long reviewId;
        private String username;
        private String procedureName;
        private int rating;
        private String content;
        private LocalDateTime createdAt;

        public static UpdateReview.Response from(ReviewDto reviewDto){
            return UpdateReview.Response.builder()
                    .reviewId(reviewDto.getReviewId())
                    .username(reviewDto.getUser().getUsername())
                    .procedureName(reviewDto.getProcedure().getProcedureName())
                    .rating(reviewDto.getRating())
                    .content(reviewDto.getContent())
                    .createdAt(reviewDto.getCreatedAt())
                    .build();
        }
    }
}
