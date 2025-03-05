package com.example.hamecobooking.controller;

import com.example.hamecobooking.dto.login.CustomUserDetails;
import com.example.hamecobooking.dto.procedure.CreateProcedure;
import com.example.hamecobooking.dto.procedure.GetProcedure;
import com.example.hamecobooking.service.ProcedureService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/procedure")
public class ProcedureController {
    private final ProcedureService procedureService;
    public ProcedureController(ProcedureService procedureService) {
        this.procedureService = procedureService;
    }

    // 시술 등록
    @PreAuthorize("hasAuthority('DESIGNER')")
    @PostMapping
    public CreateProcedure.Response createPartner(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody CreateProcedure.Request request) {
        return CreateProcedure.Response.from(procedureService.createProcedure(userDetails.getAuthenticationEntity(),request));
    }

    // 시술 조회
    @GetMapping("/{designerId}")
    public List<GetProcedure.Response> getProcedures(@PathVariable Long designerId){
        return procedureService.getProcedures(designerId).stream()
                .map(GetProcedure.Response::from)
                .collect(Collectors.toList());
    }

    // 시술 삭제
    @PreAuthorize("hasAuthority('DESIGNER')")
    @DeleteMapping("/{procedureId}")
    public void deleteProcedure(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long procedureId) {
        procedureService.deleteProcedure(userDetails.getAuthenticationEntity(), procedureId);
    }


}
