package com.example.hamecobooking.repository;

import com.example.hamecobooking.entity.ProcedureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcedureRepository extends JpaRepository<ProcedureEntity,Long> {
    List<ProcedureEntity> findByDesigner_DesignerId(Long designerId);

}
