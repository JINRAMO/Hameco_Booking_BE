package com.example.hamecobooking.service;

import com.example.hamecobooking.dto.procedure.CreateProcedure;
import com.example.hamecobooking.dto.procedure.ProcedureDto;
import com.example.hamecobooking.entity.*;
import com.example.hamecobooking.repository.DesignerRepository;
import com.example.hamecobooking.repository.ProcedureRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcedureService {
    private final ProcedureRepository procedureRepository;
    private final DesignerRepository designerRepository;
    public ProcedureService(ProcedureRepository procedureRepository, DesignerRepository designerRepository) {
        this.procedureRepository = procedureRepository;
        this.designerRepository = designerRepository;
    }

    // 시술 등록
    public ProcedureDto createProcedure(AuthenticationEntity authenticationEntity, CreateProcedure.Request procedure) {
        DesignerEntity designer = designerRepository.findByLogin_Email(authenticationEntity.getEmail())
                .orElseThrow(() -> new RuntimeException("Designer not found"));

        return ProcedureDto.fromEntity(procedureRepository.save(
                ProcedureEntity.builder()
                        .designer(designer)
                        .procedureName(procedure.getProcedureName())
                        .duration(procedure.getDuration())
                        .description(procedure.getDescription())
                        .price(procedure.getPrice())
                        .build())
        );
    }

    // 시술 조회
    public List<ProcedureDto> getProcedures(Long designerId) {
        return procedureRepository.findByDesigner_DesignerId(designerId).stream()
                .map(ProcedureDto::fromEntity)
                .toList();
    }

    // 시술 삭제
    public void deleteProcedure(AuthenticationEntity authenticationEntity, Long procedureId) {
        ProcedureEntity procedure = procedureRepository.findByProcedureIdAndDesigner_Login_email(procedureId,authenticationEntity.getEmail())
                .orElseThrow(() -> new RuntimeException("Procedure and Designer not found"));

        procedureRepository.delete(procedure);
    }
}
