package com.example.hamecobooking.service;

import com.example.hamecobooking.dto.reservation.ReservationDto;
import com.example.hamecobooking.entity.ReservationEntity;
import com.example.hamecobooking.repository.ReservationRepository;
import com.example.hamecobooking.service.login.JwtService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DesignerService {
    private final JwtService jwtService;
    private final ReservationRepository reservationRepository;
    public DesignerService(JwtService jwtService, ReservationRepository reservationRepository) {
        this.jwtService = jwtService;
        this.reservationRepository = reservationRepository;
    }

    public List<ReservationDto> getReservations(String token) {
        Long Id = jwtService.tokenPacingId(token);

        List<ReservationEntity> reservations = reservationRepository.findByDesigner_DesignerId(Id);

        return reservations.stream()
                .map(ReservationDto::fromEntity)
                .collect(Collectors.toList());
    }
}
