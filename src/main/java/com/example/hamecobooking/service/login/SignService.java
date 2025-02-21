package com.example.hamecobooking.service.login;

import com.example.hamecobooking.dto.user.CreateUser;
import com.example.hamecobooking.dto.user.GetUser;
import com.example.hamecobooking.dto.user.UserDto;
import com.example.hamecobooking.entity.DesignerEntity;
import com.example.hamecobooking.entity.UserEntity;
import com.example.hamecobooking.enums.Role;
import com.example.hamecobooking.repository.DesignerRepository;
import com.example.hamecobooking.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SignService {
    private final UserRepository userRepository;
    private final DesignerRepository designerRepository;
    public SignService(UserRepository userRepository, DesignerRepository designerRepository) {
        this.userRepository = userRepository;
        this.designerRepository = designerRepository;
    }

    public UserDto signUp(CreateUser.Request request) {
        if (request.getRole() == Role.DESIGNER) {
            // 디자이너 계정 생성
            DesignerEntity designer = designerRepository.save(
                    DesignerEntity.builder()
                            .email(request.getEmail())
                            .password(request.getPassword())
                            .username(request.getUsername())
                            .phoneNumber(request.getPhoneNumber())
                            .role(Role.DESIGNER)
                            .gender(request.getGender())
                            .createdAt(LocalDateTime.now())
                            .careerYears(0)
                            .build()
            );
            return UserDto.fromDesignerEntity(designer);  // 디자이너 DTO 변환
        } else {
            // 일반 사용자 계정 생성
            UserEntity user = userRepository.save(
                    UserEntity.builder()
                            .email(request.getEmail())
                            .password(request.getPassword())
                            .username(request.getUsername())
                            .phoneNumber(request.getPhoneNumber())
                            .gender(request.getGender())
                            .role(Role.USER)
                            .createdAt(LocalDateTime.now())
                            .build()
            );
            return UserDto.fromEntity(user);  // 일반 사용자 DTO 변환
        }
    }

    public UserDto signIn(GetUser.Request request) {
        if (request.getRole() == Role.DESIGNER) {
            // 디자이너 로그인 처리
            DesignerEntity designer = designerRepository.findByEmailAndPassword(request.getEmail(), request.getPassword())
                    .orElseThrow(() -> new RuntimeException("Designer not found"));
            return UserDto.fromDesignerEntity(designer);
        } else {
            // 일반 사용자 로그인 처리
            UserEntity user = userRepository.findByEmailAndPassword(request.getEmail(), request.getPassword())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            return UserDto.fromEntity(user);
        }
    }
}
