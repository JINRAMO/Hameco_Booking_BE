package com.example.hamecobooking.dto.user;

import com.example.hamecobooking.enums.Gender;
import com.example.hamecobooking.enums.Role;
import lombok.*;

public class CreateUser {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request {
        private String email;
        private String password;
        private String username;
        private String phoneNumber;
        private Gender gender;
        private Role role;
    }
    //json
    // {
    //     "email": "
    //     "password": "
    //     "username":
    //     "phoneNumber
    //     "gender":

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

        public static CreateUser.Response from(UserDto userDto) {
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
