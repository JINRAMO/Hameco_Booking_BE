package com.example.hamecobooking.service;

import com.example.hamecobooking.dto.reservation.ReservationDto;
import com.example.hamecobooking.dto.review.ReviewDto;
import com.example.hamecobooking.dto.user.CreateUser;
import com.example.hamecobooking.dto.user.UserDto;
import com.example.hamecobooking.entity.AuthenticationEntity;
import com.example.hamecobooking.entity.ReservationEntity;
import com.example.hamecobooking.entity.ReviewEntity;
import com.example.hamecobooking.entity.UserEntity;
import com.example.hamecobooking.repository.AuthenticationRepository;
import com.example.hamecobooking.repository.ReservationRepository;
import com.example.hamecobooking.repository.ReviewRepository;
import com.example.hamecobooking.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final AuthenticationRepository authenticationRepository;

    public UserService(ReviewRepository reviewRepository,
                       UserRepository userRepository,
                       ReservationRepository reservationRepository,
                       AuthenticationRepository authenticationRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
        this.authenticationRepository = authenticationRepository;
    }

    // 유저의 리뷰 목록 조회
    public List<ReviewDto> getReviews(AuthenticationEntity authenticationEntity) {
        List<ReviewEntity> reviews = reviewRepository.findByUser_Login_email(authenticationEntity.getEmail());

        return reviews.stream()
                .map(ReviewDto::fromEntity)
                .collect(Collectors.toList());
    }

    // 유저의 예약 목록 조회
    public List<ReservationDto> getReservations(AuthenticationEntity authenticationEntity) {
        List<ReservationEntity> reservations = reservationRepository.findByUser_Login_email(authenticationEntity.getEmail());

        return reservations.stream()
                .map(ReservationDto::fromEntity)
                .collect(Collectors.toList());
    }

    // 유저의 정보 조회
    public UserDto getUserInfo(AuthenticationEntity authenticationEntity) {
        UserEntity user = userRepository.findByLogin_email(authenticationEntity.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        AuthenticationEntity login = authenticationRepository.findByEmail(authenticationEntity.getEmail())
                .orElseThrow(() -> new RuntimeException("Login not found"));

        return UserDto.fromEntity(user, login.getEmail());
    }

    // 유저의 정보 수정
    public UserDto updateUserInfo(AuthenticationEntity authenticationEntity, CreateUser.Request user) {;
        UserEntity userToUpdate = userRepository.findByLogin_email(authenticationEntity.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        AuthenticationEntity login = authenticationRepository.findByEmail(authenticationEntity.getEmail())
                .orElseThrow(() -> new RuntimeException("Login not found")) ;

        return UserDto.fromEntity(userRepository.save(
                        userToUpdate.builder()
                                .username(user.getUsername())
                                .phoneNumber(user.getPhoneNumber())
                                .build()),
                authenticationRepository.save(
                        login.builder()
                                .email(user.getEmail())
                                .build()).getEmail()
        );
    }
}
