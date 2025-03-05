package com.example.hamecobooking.service.login;

import com.example.hamecobooking.dto.login.LoginDto;
import com.example.hamecobooking.dto.login.SignUp;
import com.example.hamecobooking.entity.DesignerEntity;
import com.example.hamecobooking.entity.AuthenticationEntity;
import com.example.hamecobooking.entity.ManagerEntity;
import com.example.hamecobooking.entity.UserEntity;
import com.example.hamecobooking.enums.Role;
import com.example.hamecobooking.repository.DesignerRepository;
import com.example.hamecobooking.repository.AuthenticationRepository;
import com.example.hamecobooking.repository.ManagerRepository;
import com.example.hamecobooking.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SignService {
    private final UserRepository userRepository;
    private final DesignerRepository designerRepository;
    private final ManagerRepository managerRepository;
    private final AuthenticationRepository loginRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public SignService(UserRepository userRepository,
                       DesignerRepository designerRepository,
                       ManagerRepository managerRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       AuthenticationRepository loginRepository){
        this.userRepository = userRepository;
        this.designerRepository = designerRepository;
        this.managerRepository = managerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.loginRepository = loginRepository;
    }

    public LoginDto signUp(SignUp.Request request) {
        AuthenticationEntity login = loginRepository.save(
                AuthenticationEntity.builder()
                        .email(request.getEmail())
                        .password(bCryptPasswordEncoder.encode(request.getPassword()))
                        .role(request.getRole())
                        .build()
        );
        if (request.getRole() == Role.DESIGNER) {
            DesignerEntity designer = designerRepository.save(
                    DesignerEntity.builder()
                            .login(login)
                            .username(request.getName())
                            .phoneNumber(request.getPhoneNumber())
                            .gender(request.getGender())
                            .createdAt(LocalDateTime.now())
                            .careerYears(0)
                            .build()
            );
            return LoginDto.fromDesignerEntity(designer, login.getEmail());
        }

        if (request.getRole() == Role.USER) {
            UserEntity user = userRepository.save(
                    UserEntity.builder()
                            .login(login)
                            .username(request.getName())
                            .phoneNumber(request.getPhoneNumber())
                            .gender(request.getGender())
                            .createdAt(LocalDateTime.now())
                            .build()
            );
            return LoginDto.fromUserEntity(user, login.getEmail());
        }

        if (request.getRole() == Role.MANAGER) {
            DesignerEntity designer = designerRepository.save(
                    DesignerEntity.builder()
                            .login(login)
                            .username(request.getName())
                            .phoneNumber(request.getPhoneNumber())
                            .gender(request.getGender())
                            .createdAt(LocalDateTime.now())
                            .careerYears(0)
                            .build()
            );
            ManagerEntity manager = managerRepository.save(
                    ManagerEntity.builder()
                            .login(login)
                            .designer(designer)
                            .username(request.getName())
                            .phoneNumber(request.getPhoneNumber())
                            .gender(request.getGender())
                            .createdAt(LocalDateTime.now())
                            .build()
            );
            return LoginDto.fromManagerEntity(manager, login.getEmail());
        }

        throw new RuntimeException("Role not found");
    }
}
