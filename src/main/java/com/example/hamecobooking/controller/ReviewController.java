package com.example.hamecobooking.controller;

import com.example.hamecobooking.dto.review.CreateReview;
import com.example.hamecobooking.dto.review.UpdateReview;
import com.example.hamecobooking.dto.store.CreateStore;
import com.example.hamecobooking.dto.store.UpdateStore;
import com.example.hamecobooking.service.ReviewService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // 리뷰 등록
    @PostMapping
    public CreateReview.Response createReview(@RequestHeader("Authorization") String token, @RequestBody CreateReview.Request request) {
        token = token.replace("Bearer ", "");
        return CreateReview.Response.from(reviewService.createReview(token,request));
    }

    // 리뷰 수정
    @PutMapping
    public UpdateReview.Response updateStore(@RequestHeader("Authorization") String token, @RequestBody UpdateReview.Request request) {
        token = token.replace("Bearer ", "");
        return UpdateReview.Response.from(reviewService.updateReview(token,request));
    }

    // 리뷰 삭제
    @DeleteMapping("/{store_id}")
    public void deleteReview(@RequestHeader("Authorization") String token, @PathVariable("store_id") Long storeId) {
        token = token.replace("Bearer ", "");
        reviewService.deleteReview(token, storeId);
    }
}
