package com.example.hamecobooking.repository;

import com.example.hamecobooking.entity.ManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<ManagerEntity, Long> {

    boolean existsById(Long userId);

}
