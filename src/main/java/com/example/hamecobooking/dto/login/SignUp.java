package com.example.hamecobooking.dto.login;

import com.example.hamecobooking.enums.Gender;
import com.example.hamecobooking.enums.Role;
import lombok.*;

public class SignUp {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        private String email;
        private String password;
        private String name;
        private String phoneNumber;
        private Gender gender;
        private Role role;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private Long id;
        private String email;
        private String name;
        private String phoneNumber;
        private Gender gender;

        public static Response from(LoginDto loginDto) {
            return Response.builder()
                    .id(loginDto.getId())
                    .email(loginDto.getEmail())
                    .name(loginDto.getName())
                    .phoneNumber(loginDto.getPhoneNumber())
                    .gender(loginDto.getGender())
                    .build();
        }
    }

}
