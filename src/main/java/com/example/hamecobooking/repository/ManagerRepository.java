package com.example.hamecobooking.repository;

import com.example.hamecobooking.entity.ManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<ManagerEntity, Long> {

    boolean existsById(Long userId);
    Optional<ManagerEntity> findByEmailAndPassword(String email, String password);
}
