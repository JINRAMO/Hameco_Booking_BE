package com.example.hamecobooking.service;

import com.example.hamecobooking.dto.reservation.ReservationDto;
import com.example.hamecobooking.dto.review.ReviewDto;
import com.example.hamecobooking.dto.store.StoreDto;
import com.example.hamecobooking.dto.user.CreateUser;
import com.example.hamecobooking.dto.user.GetUser;
import com.example.hamecobooking.dto.user.UserDto;
import com.example.hamecobooking.entity.ReservationEntity;
import com.example.hamecobooking.entity.ReviewEntity;
import com.example.hamecobooking.entity.UserEntity;
import com.example.hamecobooking.enums.Gender;
import com.example.hamecobooking.enums.Role;
import com.example.hamecobooking.repository.ReservationRepository;
import com.example.hamecobooking.repository.ReviewRepository;
import com.example.hamecobooking.repository.UserRepository;
import com.example.hamecobooking.service.login.JwtService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final JwtService jwtService;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    public UserService(JwtService jwtService, ReviewRepository reviewRepository, UserRepository userRepository, ReservationRepository reservationRepository) {
        this.jwtService = jwtService;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<ReviewDto> getReviews(String token) {
        Long Id = jwtService.tokenPacingId(token);

        List<ReviewEntity> reviews = reviewRepository.findByUser_UserId(Id);

        return reviews.stream()
                .map(ReviewDto::fromEntity)
                .collect(Collectors.toList());
    }

    public List<ReservationDto> getReservations(String token) {
        Long Id = jwtService.tokenPacingId(token);

        List<ReservationEntity> reservations = reservationRepository.findByUser_UserId(Id);

        return reservations.stream()
                .map(ReservationDto::fromEntity)
                .collect(Collectors.toList());
    }

    public UserDto updateUserInfo(String token, CreateUser.Request user) {
        // 토큰 검증 필요
        Long Id = jwtService.tokenPacingId(token);

        UserEntity userToUpdate = userRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserDto.fromEntity(userRepository.save(
                userToUpdate.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .email(user.getEmail())
                        .phoneNumber(user.getPhoneNumber())
                        .build())
        );
    }

    public UserDto getUserInfo(String token) {
        // 토큰 검증 필요
        Long Id = jwtService.tokenPacingId(token);

        UserEntity user = userRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserDto.fromEntity(user);
    }
}
