package com.example.hamecobooking.dto.login;

import com.example.hamecobooking.enums.Gender;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpDto {

    private Long Id;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private Gender gender;
    private int careerYears;
    private LocalDateTime createdAt;

}

