package com.example.hamecobooking.controller;

import com.example.hamecobooking.dto.reservation.GetReservation;
import com.example.hamecobooking.dto.review.GetReview;
import com.example.hamecobooking.dto.user.CreateUser;
import com.example.hamecobooking.dto.user.GetUser;
import com.example.hamecobooking.service.UserService;
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
    @GetMapping("/reviews")
    public List<GetReview.Response> getReviews(@RequestHeader("Authorization") String token) {
        token = token.replace("Bearer ", "");
        return userService.getReviews(token).stream()
                .map(GetReview.Response::from)
                .collect(Collectors.toList());
    }

    // 유저의 예약 목록 조회
    @GetMapping("/reservations")
    public List<GetReservation.Response> getReservations(@RequestHeader("Authorization") String token) {
        token = token.replace("Bearer ", "");
        return userService.getReservations(token).stream()
                .map(GetReservation.Response::from)
                .collect(Collectors.toList());
    }

    // 유저의 정보 조회
    @GetMapping("/info")
    public GetUser.Response getUserInfo(@RequestHeader("Authorization") String token) {
        token = token.replace("Bearer ", "");
        return GetUser.Response.from(userService.getUserInfo(token));
    }

    // 유저의 정보 수정
    @PutMapping
    public CreateUser.Response updateUserInfo(@RequestHeader("Authorization") String token, @RequestBody CreateUser.Request user) {
        token = token.replace("Bearer ", "");
        return CreateUser.Response.from(userService.updateUserInfo(token, user));
    }
}
