package com.example.hamecobooking.service.login;

import org.springframework.stereotype.Service;

@Service
public class JwtService {
    public Long tokenPacingId(String token) {
        if (token.equals("1")) {
            return 1L;
        } else if (token.equals("2")) {
            return 2L;
        } else {
            return 3L;
        }
    }

    public String getUserRole(String token) {
        return "USER";
    }
}
