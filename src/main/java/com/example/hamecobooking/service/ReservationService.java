package com.example.hamecobooking.service;

import com.example.hamecobooking.dto.reservation.CreateReservation;
import com.example.hamecobooking.dto.reservation.ReservationDto;
import com.example.hamecobooking.entity.*;
import com.example.hamecobooking.enums.Status;
import com.example.hamecobooking.repository.DesignerRepository;
import com.example.hamecobooking.repository.ProcedureRepository;
import com.example.hamecobooking.repository.ReservationRepository;
import com.example.hamecobooking.repository.UserRepository;
import com.example.hamecobooking.service.login.JwtService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.example.hamecobooking.enums.Status.PENDING;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final DesignerRepository designerRepository;
    private final ProcedureRepository procedureRepository;
    private final JwtService jwtService;
    public ReservationService(ReservationRepository reservationRepository, UserRepository userRepository, DesignerRepository designerRepository, ProcedureRepository procedureRepository, JwtService jwtService) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.designerRepository = designerRepository;
        this.procedureRepository = procedureRepository;
        this.jwtService = jwtService;
    }

    public ReservationDto createReservation(String token, CreateReservation.Request reservation) {
        Long Id = jwtService.tokenPacingId(token);

        UserEntity user = userRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        DesignerEntity designer = designerRepository.findById(reservation.getDesignerId())
                .orElseThrow(() -> new RuntimeException("Designer not found"));
       ProcedureEntity procedure = procedureRepository.findById(reservation.getProcedureId())
                .orElseThrow(() -> new RuntimeException("Procedure not found"));

        LocalDateTime reservationDateTime = reservation.getReservationDateTime();
        boolean isTimeSlotTaken = reservationRepository.existsByDesignerAndReservationDateTime(designer, reservationDateTime);

        if (isTimeSlotTaken) {
            throw new RuntimeException("해당 시간대에 이미 예약이 있습니다.");
        }

        return ReservationDto.fromEntity(reservationRepository.save(
                ReservationEntity.builder()
                        .user(user)
                        .designer(designer)
                        .procedure(procedure)
                        .notes(reservation.getNotes())
                        .reservationDateTime(reservationDateTime)
                        .status(PENDING)
                        .createdAt(LocalDateTime.now())
                        .build())
        );
    }

    public ReservationDto updateReservationStatus(String token, Long reservationId, Status status) {
        Long Id = jwtService.tokenPacingId(token);
        String role = jwtService.getUserRole(token);
        role = "DESIGNER";
        ReservationEntity reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        if ("DESIGNER".equals(role)) {
            if (!reservation.getDesigner().getDesignerId().equals(Id)) {
                throw new RuntimeException("You can only update your own reservations.");
            }
            if (status != Status.APPROVED && status != Status.REJECTED) {
                throw new RuntimeException("Designers can only change status to CONFIRMED or REJECTED.");
            }
            reservation.setStatus(status);
        } else if ("USER".equals(role)) {
            if (!reservation.getUser().getUserId().equals(Id)) {
                throw new RuntimeException("You can only update your own reservations.");
            }
            if (reservation.getStatus() != Status.APPROVED) {
                throw new RuntimeException("You can only complete a reservation that has been confirmed.");
            }
            if (status != Status.COMPLETED) {
                throw new RuntimeException("Users can only change status to COMPLETED.");
            }
            reservation.setStatus(status);
        }
            reservationRepository.save(reservation);
            return ReservationDto.fromEntity(reservation);
    }

    public void deleteReservation(String token, Long reservationId) {
        Long Id = jwtService.tokenPacingId(token);

        if (!userRepository.existsById(Id)) {
            throw new RuntimeException("User not found");
        }

        ReservationEntity reservation= reservationRepository.findByReservationIdAndUser_UserId(reservationId,Id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        reservationRepository.delete(reservation);
    }
}