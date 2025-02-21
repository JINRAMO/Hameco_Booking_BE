package com.example.hamecobooking.repository;

import com.example.hamecobooking.entity.PartnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartnerRepository extends JpaRepository<PartnerEntity, Long> {
    List<PartnerEntity> findByManager_ManagerId(Long managerId);

}
