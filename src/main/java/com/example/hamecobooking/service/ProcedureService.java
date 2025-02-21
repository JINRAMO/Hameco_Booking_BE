package com.example.hamecobooking.service;

import com.example.hamecobooking.dto.procedure.CreateProcedure;
import com.example.hamecobooking.dto.procedure.ProcedureDto;
import com.example.hamecobooking.entity.*;
import com.example.hamecobooking.repository.DesignerRepository;
import com.example.hamecobooking.repository.ProcedureRepository;
import com.example.hamecobooking.service.login.JwtService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcedureService {
    private final ProcedureRepository procedureRepository;
    private final DesignerRepository designerRepository;
    private final JwtService jwtService;
    public ProcedureService(ProcedureRepository procedureRepository, DesignerRepository designerRepository, JwtService jwtService) {
        this.procedureRepository = procedureRepository;
        this.designerRepository = designerRepository;
        this.jwtService = jwtService;
    }

    public ProcedureDto createProcedure(String token, CreateProcedure.Request procedure) {

        // 토큰 검증 필요
        Long Id = jwtService.tokenPacingId(token);

        DesignerEntity designer = designerRepository.findById(Id)
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

    public List<ProcedureDto> getProcedures(String token) {

        // 토큰 검증 필요
        Long Id = jwtService.tokenPacingId(token);

        return procedureRepository.findByDesigner_DesignerId(Id).stream()
                .map(ProcedureDto::fromEntity)
                .toList();
    }

    public void deleteProcedure(String token, Long procedureId) {

        // 토큰 검증 필요
        Long Id = jwtService.tokenPacingId(token);

        ProcedureEntity procedure = procedureRepository.findById(procedureId)
                .orElseThrow(() -> new RuntimeException("Procedure not found"));

        procedureRepository.delete(procedure);
    }
}
