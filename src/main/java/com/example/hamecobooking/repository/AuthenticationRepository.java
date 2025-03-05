package com.example.hamecobooking.repository;

import com.example.hamecobooking.entity.AuthenticationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticationRepository extends JpaRepository<AuthenticationEntity, Long> {
    Optional< AuthenticationEntity> findByEmail(String email);
}
