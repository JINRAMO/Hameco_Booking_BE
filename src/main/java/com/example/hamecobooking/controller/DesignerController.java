package com.example.hamecobooking.controller;

import com.example.hamecobooking.dto.designer.CreateAvailableHour;
import com.example.hamecobooking.dto.designer.GetAvailableHour;
import com.example.hamecobooking.dto.login.CustomUserDetails;
import com.example.hamecobooking.dto.reservation.GetReservation;
import com.example.hamecobooking.entity.AuthenticationEntity;
import com.example.hamecobooking.service.DesignerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    // 디자이너의 가능 시간 등록
    @PreAuthorize("hasAuthority('DESIGNER')")
    @PostMapping("/available")
    public void createAvailableHours(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody CreateAvailableHour.Request request) {
        designerService.createAvailableHours(userDetails.getAuthenticationEntity(), request);
    }

    // 디자이너의 예약 목록 조회
    @PreAuthorize("hasAuthority('DESIGNER')")
    @GetMapping
    public List<GetReservation.Response> getReservations(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return designerService.getReservations(userDetails.getAuthenticationEntity()).stream()
                .map(GetReservation.Response::from)
                .collect(Collectors.toList());
    }

    // 디자이너의 가능 시간 조회
    @GetMapping("/available/{designerId}")
    public GetAvailableHour.Response getAvailableHours(@PathVariable Long designerId) {
        return designerService.getAvailableHours(designerId);
    }

    // 디자이너의 가능 시간 삭제
    //TODO: 가능시간이 한 곳에 모이므로 다시 생각해서 작성하기
    @PreAuthorize("hasAuthority('DESIGNER')")
    @DeleteMapping("/available/{availableHourId}")
    public void deleteAvailableHours(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long availableHourId) {
        designerService.deleteAvailableHours(userDetails.getAuthenticationEntity(), availableHourId);
    }
}
