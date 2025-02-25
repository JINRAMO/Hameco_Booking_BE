package com.example.hamecobooking.service.login;

import com.example.hamecobooking.dto.login.CustomUserDetails;
import com.example.hamecobooking.entity.UserEntity;
import com.example.hamecobooking.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public CustomUserDetailService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity userData = userRepository.findByEmail(email);
        System.out.println("userData = " + userData);
        System.out.println("email = " + userData.getEmail());
        System.out.println("password = " + userData.getPassword());


        if (userData == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        System.out.println(new CustomUserDetails(userData));
        return new CustomUserDetails(userData);
    }
}
