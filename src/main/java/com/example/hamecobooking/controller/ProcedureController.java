package com.example.hamecobooking.controller;

import com.example.hamecobooking.dto.partner.CreatePartner;
import com.example.hamecobooking.dto.procedure.CreateProcedure;
import com.example.hamecobooking.dto.procedure.GetProcedure;
import com.example.hamecobooking.dto.procedure.ProcedureDto;
import com.example.hamecobooking.service.ProcedureService;
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
    @PostMapping
    public CreateProcedure.Response createPartner(@RequestHeader("Authorization") String token, @RequestBody CreateProcedure.Request request) {
        token = token.replace("Bearer ", "");
        return CreateProcedure.Response.from(procedureService.createProcedure(token,request));
    }

    // 디자이너 시술 조회 TODO: 삭제 상태시 조회 X
    @GetMapping
    public List<GetProcedure.Response> getProcedures(@RequestHeader("Authorization") String token){
        token = token.replace("Bearer ", "");
        return procedureService.getProcedures(token).stream()
                .map(GetProcedure.Response::from)
                .collect(Collectors.toList());
    }

    // 시술 삭제 TODO: 상태 달아서 삭제하는 것으로 변경
    @DeleteMapping
    public void deleteProcedure(@RequestHeader("Authorization") String token, @PathVariable("procedure_id") Long procedureId) {
        token = token.replace("Bearer ", "");
        procedureService.deleteProcedure(token, procedureId);
    }


}
