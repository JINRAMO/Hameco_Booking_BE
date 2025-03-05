package com.example.hamecobooking.repository;

import com.example.hamecobooking.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByLogin_email(String email);
//    boolean existsByUsername(String email);
//    Optional<UserEntity> findByEmailAndPassword(String email, String password);
//    UserEntity findByEmail(String email);
}
