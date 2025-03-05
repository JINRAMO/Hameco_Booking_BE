package com.example.hamecobooking.controller;

import com.example.hamecobooking.dto.login.CustomUserDetails;
import com.example.hamecobooking.dto.review.CreateReview;
import com.example.hamecobooking.dto.review.UpdateReview;
import com.example.hamecobooking.service.ReviewService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // 리뷰 등록
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping
    public CreateReview.Response createReview(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody CreateReview.Request request) {
        return CreateReview.Response.from(reviewService.createReview(userDetails.getAuthenticationEntity(),request));
    }

    // 리뷰 수정
    @PreAuthorize("hasAuthority('USER')")
    @PutMapping
    public UpdateReview.Response updateStore(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody UpdateReview.Request request) {
        return UpdateReview.Response.from(reviewService.updateReview(userDetails.getAuthenticationEntity(),request));
    }

    // 리뷰 삭제
    @PreAuthorize("hasAuthority('USER')")
    @DeleteMapping("/{storeId}")
    public void deleteReview(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long storeId) {
        reviewService.deleteReview(userDetails.getAuthenticationEntity(), storeId);
    }
}
