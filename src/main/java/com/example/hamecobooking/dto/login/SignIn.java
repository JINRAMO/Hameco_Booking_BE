package com.example.hamecobooking.dto.login;

import com.example.hamecobooking.enums.Gender;
import com.example.hamecobooking.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class SignIn {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private String email;
        private String password;
        private Role role;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private Long id;
        private String email;
        private String username;
        private String phoneNumber;
        private Gender gender;

        public static Response from(LoginDto loginDto) {
            return Response.builder()
                    .id(loginDto.getId())
                    .email(loginDto.getEmail())
                    .username(loginDto.getName())
                    .phoneNumber(loginDto.getPhoneNumber())
                    .gender(loginDto.getGender())
                    .build();
        }
    }
}
