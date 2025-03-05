package com.example.hamecobooking.service.login;

import com.example.hamecobooking.dto.login.CustomUserDetails;
import com.example.hamecobooking.entity.AuthenticationEntity;
import com.example.hamecobooking.repository.AuthenticationRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final AuthenticationRepository loginRepository;
    private final PasswordEncoder passwordEncoder;
    public CustomUserDetailService(AuthenticationRepository loginRepository, PasswordEncoder passwordEncoder) {
        this.loginRepository = loginRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        AuthenticationEntity userData = loginRepository.findByEmail(email).get();
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
