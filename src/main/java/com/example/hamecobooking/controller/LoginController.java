package com.example.hamecobooking.controller;

import com.example.hamecobooking.dto.login.SignIn;
import com.example.hamecobooking.dto.login.SignUp;
import com.example.hamecobooking.dto.user.CreateUser;
import com.example.hamecobooking.dto.user.GetUser;
import com.example.hamecobooking.service.login.SignService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final SignService signService;
    public LoginController(SignService signService) {
        this.signService = signService;
    }

    // 로그인
    @PostMapping("/signIn")
    public SignIn.Response signIn(@RequestBody SignIn.Request request) {
        return SignIn.Response.from(signService.signIn(request));

    }

    // 회원가입
    @PostMapping("/signUp")
    public SignUp.Response signUp(@RequestBody SignUp.Request request) {
        return SignUp.Response.from(signService.signUp(request));
    }
}
