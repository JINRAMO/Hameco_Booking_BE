package com.example.hamecobooking.dto.login;

import com.example.hamecobooking.entity.DesignerEntity;
import com.example.hamecobooking.entity.ManagerEntity;
import com.example.hamecobooking.entity.UserEntity;
import com.example.hamecobooking.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private Gender gender;
    private LocalDateTime createdAt;

    public static LoginDto fromUserEntity(UserEntity user) {
        return LoginDto.builder()
                .id(user.getUserId())
                .name(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .gender(user.getGender())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public static LoginDto fromDesignerEntity(DesignerEntity designer) {
        return LoginDto.builder()
                .id(designer.getDesignerId())
                .name(designer.getUsername())
                .email(designer.getEmail())
                .password(designer.getPassword())
                .phoneNumber(designer.getPhoneNumber())
                .gender(designer.getGender())
                .build();
    }

    public static LoginDto fromManagerEntity(ManagerEntity manager) {
        return LoginDto.builder()
                .id(manager.getManagerId())
                .name(manager.getUsername())
                .email(manager.getEmail())
                .password(manager.getPassword())
                .phoneNumber(manager.getPhoneNumber())
                .build();
    }
}
