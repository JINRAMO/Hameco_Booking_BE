package com.example.hamecobooking.repository;

import com.example.hamecobooking.entity.DesignerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DesignerRepository extends JpaRepository<DesignerEntity,Long> {
    boolean existsById(Long id);
    Optional<DesignerEntity> findByEmailAndPassword(String email, String password);
}
