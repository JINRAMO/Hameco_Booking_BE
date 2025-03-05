package com.example.hamecobooking.controller;

import com.example.hamecobooking.dto.login.CustomUserDetails;
import com.example.hamecobooking.dto.reservation.GetReservation;
import com.example.hamecobooking.dto.review.GetReview;
import com.example.hamecobooking.dto.user.CreateUser;
import com.example.hamecobooking.dto.user.GetUser;
import com.example.hamecobooking.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 유저의 리뷰 목록 조회
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/reviews")
    public List<GetReview.Response> getReviews(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return userService.getReviews(userDetails.getAuthenticationEntity()).stream()
                .map(GetReview.Response::from)
                .collect(Collectors.toList());
    }

    // 유저의 예약 목록 조회
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/reservations")
    public List<GetReservation.Response> getReservations(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return userService.getReservations(userDetails.getAuthenticationEntity()).stream()
                .map(GetReservation.Response::from)
                .collect(Collectors.toList());
    }

    // 유저의 정보 조회
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/info")
    public GetUser.Response getUserInfo(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return GetUser.Response.from(userService.getUserInfo(userDetails.getAuthenticationEntity()));
    }

    // TODO: 변경사항 논의
    // 유저의 정보 수정
    @PreAuthorize("hasAuthority('USER')")
    @PutMapping
    public CreateUser.Response updateUserInfo(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody CreateUser.Request user) {
        return CreateUser.Response.from(userService.updateUserInfo(userDetails.getAuthenticationEntity(), user));
    }
}
