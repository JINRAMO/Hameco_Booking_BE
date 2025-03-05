package com.example.hamecobooking.controller;

import com.example.hamecobooking.dto.login.CustomUserDetails;
import com.example.hamecobooking.dto.reservation.CreateReservation;
import com.example.hamecobooking.enums.Status;
import com.example.hamecobooking.service.ReservationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // 예약 등록
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping
    public CreateReservation.Response createReservation(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody CreateReservation.Request request) {
        return CreateReservation.Response.from(reservationService.createReservation(userDetails.getAuthenticationEntity(),request));
    }

    // 예약 상태 변경
    @PreAuthorize("hasAnyAuthority('USER', 'DESIGNER')")
    @PatchMapping("/{reservationId}")
    public CreateReservation.Response updateReservationStatus(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long reservationId, @RequestParam Status status) {
        return CreateReservation.Response.from(reservationService.updateReservationStatus(userDetails.getAuthenticationEntity(), reservationId, status));
    }

    // 예약 삭제
    @PreAuthorize("hasAuthority('USER')")
    @DeleteMapping("/{reservationId}")
    public void deleteReservation(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long reservationId) {
        reservationService.deleteReservation(userDetails.getAuthenticationEntity(), reservationId);
    }
}
