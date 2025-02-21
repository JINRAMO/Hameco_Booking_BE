package com.example.hamecobooking.service;

import com.example.hamecobooking.dto.review.CreateReview;
import com.example.hamecobooking.dto.review.ReviewDto;
import com.example.hamecobooking.dto.review.UpdateReview;
import com.example.hamecobooking.entity.*;
import com.example.hamecobooking.repository.ProcedureRepository;
import com.example.hamecobooking.repository.ReviewRepository;
import com.example.hamecobooking.repository.UserRepository;
import com.example.hamecobooking.service.login.JwtService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProcedureRepository procedureRepository;
    private final JwtService jwtService;

    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, ProcedureRepository procedureRepository, JwtService jwtService) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.procedureRepository = procedureRepository;
        this.jwtService = jwtService;
    }

    public ReviewDto createReview(String token, CreateReview.Request review) {

        Long Id = jwtService.tokenPacingId(token);

        UserEntity user = userRepository.findById(Id)
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

    public ReviewDto updateReview(String token, UpdateReview.Request review) {

        Long Id = jwtService.tokenPacingId(token);

        // 받은 Id 값으로 유저인지, 디자이너인지, 점장인지 구분하는 로직 필요 (유저가 존재하는지 유무 체크위한 용도이기도 함)

        ReviewEntity reviewToUpdate = reviewRepository.findByReviewIdAndUser_UserId(review.getReviewId(),Id)
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

    public void deleteReview(String token, Long reviewId) {

        Long Id = jwtService.tokenPacingId(token);

        // 받은 Id 값으로 유저인지, 디자이너인지, 점장인지 구분하는 로직 필요 (유저가 존재하는지 유무 체크위한 용도이기도 함)

        ReviewEntity review =  reviewRepository.findByReviewIdAndUser_UserId(reviewId, Id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        reviewRepository.delete(review);
    }
}
