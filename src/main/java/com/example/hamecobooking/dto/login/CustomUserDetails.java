package com.example.hamecobooking.dto.login;

import com.example.hamecobooking.entity.AuthenticationEntity;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
public class CustomUserDetails implements UserDetails {

    private final AuthenticationEntity authenticationEntity;
    public CustomUserDetails(AuthenticationEntity userEntity) {
        this.authenticationEntity = userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.authenticationEntity.getRole().toString())); // 기본 권한 설정
    }

    @Override
    public String getPassword() {
        return authenticationEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return authenticationEntity.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
