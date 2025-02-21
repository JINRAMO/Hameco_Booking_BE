package com.example.hamecobooking.dto.user;

import com.example.hamecobooking.enums.Gender;
import com.example.hamecobooking.enums.Role;
import lombok.*;

public class GetUser {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request {
        private String email;
        private String password;
        private Role role;

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private Long userId;
        private String email;
        private String username;
        private String phoneNumber;
        private Gender gender;

        public static GetUser.Response from(UserDto userDto) {
            return Response.builder()
                    .userId(userDto.getUserId())
                    .email(userDto.getEmail())
                    .username(userDto.getUsername())
                    .phoneNumber(userDto.getPhoneNumber())
                    .gender(userDto.getGender())
                    .build();
        }
    }
}
