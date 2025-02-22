package com.example.hamecobooking.service.login;

import com.example.hamecobooking.dto.login.LoginDto;
import com.example.hamecobooking.dto.login.SignIn;
import com.example.hamecobooking.dto.login.SignUp;
import com.example.hamecobooking.dto.user.CreateUser;
import com.example.hamecobooking.dto.user.GetUser;
import com.example.hamecobooking.dto.user.UserDto;
import com.example.hamecobooking.entity.DesignerEntity;
import com.example.hamecobooking.entity.ManagerEntity;
import com.example.hamecobooking.entity.UserEntity;
import com.example.hamecobooking.enums.Role;
import com.example.hamecobooking.repository.DesignerRepository;
import com.example.hamecobooking.repository.ManagerRepository;
import com.example.hamecobooking.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SignService {
    private final UserRepository userRepository;
    private final DesignerRepository designerRepository;
    private final ManagerRepository managerRepository;
    public SignService(UserRepository userRepository, DesignerRepository designerRepository, ManagerRepository managerRepository) {
        this.userRepository = userRepository;
        this.designerRepository = designerRepository;
        this.managerRepository = managerRepository;
    }

    public LoginDto signUp(SignUp.Request request) {
        if (request.getRole() == Role.DESIGNER) {
            DesignerEntity designer = designerRepository.save(
                    DesignerEntity.builder()
                            .email(request.getEmail())
                            .password(request.getPassword())
                            .username(request.getName())
                            .phoneNumber(request.getPhoneNumber())
                            .gender(request.getGender())
                            .createdAt(LocalDateTime.now())
                            .careerYears(0)
                            .build()
            );
            return LoginDto.fromDesignerEntity(designer);
        }

        if (request.getRole() == Role.USER) {
            UserEntity user = userRepository.save(
                    UserEntity.builder()
                            .email(request.getEmail())
                            .password(request.getPassword())
                            .username(request.getName())
                            .phoneNumber(request.getPhoneNumber())
                            .gender(request.getGender())
                            .createdAt(LocalDateTime.now())
                            .build()
            );
            return LoginDto.fromUserEntity(user);
        }

        if (request.getRole() == Role.MANAGER) {
            ManagerEntity manager = managerRepository.save(
                    ManagerEntity.builder()
                            .email(request.getEmail())
                            .password(request.getPassword())
                            .username(request.getName())
                            .phoneNumber(request.getPhoneNumber())
                            .createdAt(LocalDateTime.now())
                            .build()
            );
            return LoginDto.fromManagerEntity(manager);
        }

        throw new RuntimeException("Role not found");
    }


    public LoginDto signIn(SignIn.Request request) {
        if (request.getRole() == Role.DESIGNER) {
            DesignerEntity designer = designerRepository.findByEmailAndPassword(request.getEmail(), request.getPassword())
                    .orElseThrow(() -> new RuntimeException("Designer not found"));
            return LoginDto.fromDesignerEntity(designer);
        }

        if (request.getRole() == Role.USER) {
            UserEntity user = userRepository.findByEmailAndPassword(request.getEmail(), request.getPassword())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            return LoginDto.fromUserEntity(user);
        }

        if (request.getRole() == Role.MANAGER) {
            ManagerEntity manager = managerRepository.findByEmailAndPassword(request.getEmail(), request.getPassword())
                    .orElseThrow(() -> new RuntimeException("Manager not found"));
            return LoginDto.fromManagerEntity(manager);
        }

        throw new RuntimeException("Role not found");
    }

}
