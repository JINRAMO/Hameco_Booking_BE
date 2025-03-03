package com.example.hamecobooking.dto.user;

import com.example.hamecobooking.entity.DesignerEntity;
import com.example.hamecobooking.entity.ReservationEntity;
import com.example.hamecobooking.entity.ReviewEntity;
import com.example.hamecobooking.entity.UserEntity;
import com.example.hamecobooking.enums.Gender;
import com.example.hamecobooking.enums.Role;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long userId;
    private List<ReservationEntity> reservations = new ArrayList<>();
    private List<ReviewEntity> reviews = new ArrayList<>();
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private Gender gender;
    private LocalDateTime createdAt;

    // 유저 변환
    public static UserDto fromEntity(UserEntity user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .reviews(user.getReviews())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .gender(user.getGender())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
