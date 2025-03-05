package com.example.hamecobooking.service;

import com.example.hamecobooking.dto.review.CreateReview;
import com.example.hamecobooking.dto.review.ReviewDto;
import com.example.hamecobooking.dto.review.UpdateReview;
import com.example.hamecobooking.entity.*;
import com.example.hamecobooking.repository.ProcedureRepository;
import com.example.hamecobooking.repository.ReviewRepository;
import com.example.hamecobooking.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProcedureRepository procedureRepository;
    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, ProcedureRepository procedureRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.procedureRepository = procedureRepository;
    }

    // 리뷰 등록
    public ReviewDto createReview(AuthenticationEntity authenticationEntity, CreateReview.Request review) {
        UserEntity user = userRepository.findByLogin_email(authenticationEntity.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        ProcedureEntity procedure = procedureRepository.findById(review.getProcedureId())
                .orElseThrow(() -> new RuntimeException("Procedure not found"));

        return ReviewDto.fromEntity(reviewRepository.save(
                ReviewEntity.builder()
                        .user(user)
                        .procedure(procedure)
                        .store(procedure.getDesigner().getStore())
                        .content(review.getContent())
                        .rating(review.getRating())
                        .createdAt(LocalDateTime.now())
                        .build())
        );
    }

    // 리뷰 수정
    public ReviewDto updateReview(AuthenticationEntity authenticationEntity, UpdateReview.Request review) {
        ReviewEntity reviewToUpdate = reviewRepository.findByReviewIdAndUser_Login_email(review.getReviewId(),authenticationEntity.getEmail())
                .orElseThrow(() -> new RuntimeException("Review not found"));

        return ReviewDto.fromEntity(reviewRepository.save(
                reviewToUpdate.builder()
                        .reviewId(reviewToUpdate.getReviewId())
                        .store(reviewToUpdate.getStore())
                        .procedure(reviewToUpdate.getProcedure())
                        .user(reviewToUpdate.getUser())
                        .content(review.getContent())
                        .rating(review.getRating())
                        .createdAt(LocalDateTime.now())
                        .build())
        );
    }

    // 리뷰 삭제
    public void deleteReview(AuthenticationEntity authenticationEntity, Long reviewId) {
        ReviewEntity review =  reviewRepository.findByReviewIdAndUser_Login_email(reviewId,authenticationEntity.getEmail())
                .orElseThrow(() -> new RuntimeException("Review not found"));

        reviewRepository.delete(review);
    }
}
