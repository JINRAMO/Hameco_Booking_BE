package com.example.hamecobooking.controller;

import com.example.hamecobooking.dto.reservation.GetReservation;
import com.example.hamecobooking.service.DesignerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/designer")
public class DesignerController {
    private final DesignerService designerService;
    public DesignerController(DesignerService designerService) {
        this.designerService = designerService;
    }

    // 디자이너의 가능 시간 수정

    // 디자이너의 예약 목록 조회
    @GetMapping
    public List<GetReservation.Response> getReservations(@RequestHeader("Authorization") String token) {
        token = token.replace("Bearer ", "");
        return designerService.getReservations(token).stream()
                .map(GetReservation.Response::from)
                .collect(Collectors.toList());
    }

    // 디자이너의 가능 시간 조회
}
