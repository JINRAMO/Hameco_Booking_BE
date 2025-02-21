package com.example.hamecobooking.controller;

import com.example.hamecobooking.dto.reservation.CreateReservation;
import com.example.hamecobooking.enums.Status;
import com.example.hamecobooking.service.ReservationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // 예약 등록
    @PostMapping
    public CreateReservation.Response createReservation(@RequestHeader("Authorization") String token, @RequestBody CreateReservation.Request request) {
        token = token.replace("Bearer ", "");
        return CreateReservation.Response.from(reservationService.createReservation(token,request));
    }

    // 예약 상태 변경
    @PatchMapping("/{reservation_id}")
    public CreateReservation.Response updateReservationStatus(@RequestHeader("Authorization") String token, @PathVariable("reservation_id") Long reservationId, @RequestParam Status status) {
        token = token.replace("Bearer ", "");
        return CreateReservation.Response.from(reservationService.updateReservationStatus(token, reservationId, status));
    }

    // 예약 삭제
    @DeleteMapping("/{reservation_id}")
    public void deleteReservation(@RequestHeader("Authorization") String token, @PathVariable("reservation_id") Long reservationId) {
        token = token.replace("Bearer ", "");
        reservationService.deleteReservation(token, reservationId);
    }
}
