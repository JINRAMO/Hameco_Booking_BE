package com.example.hamecobooking.service;

import com.example.hamecobooking.dto.reservation.CreateReservation;
import com.example.hamecobooking.dto.reservation.ReservationDto;
import com.example.hamecobooking.entity.*;
import com.example.hamecobooking.enums.Role;
import com.example.hamecobooking.enums.Status;
import com.example.hamecobooking.repository.DesignerRepository;
import com.example.hamecobooking.repository.ProcedureRepository;
import com.example.hamecobooking.repository.ReservationRepository;
import com.example.hamecobooking.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.example.hamecobooking.enums.Status.PENDING;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final DesignerRepository designerRepository;
    private final ProcedureRepository procedureRepository;
    public ReservationService(ReservationRepository reservationRepository, UserRepository userRepository, DesignerRepository designerRepository, ProcedureRepository procedureRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.designerRepository = designerRepository;
        this.procedureRepository = procedureRepository;
    }

    // 예약 등록
    public ReservationDto createReservation(AuthenticationEntity authenticationEntity, CreateReservation.Request reservation) {;
        UserEntity user = userRepository.findByLogin_email(authenticationEntity.getEmail())
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

    // 예약 상태 변경
    public ReservationDto updateReservationStatus(AuthenticationEntity authenticationEntity, Long reservationId, Status status) {
        Role role = authenticationEntity.getRole();

        ReservationEntity reservation = reservationRepository.findByReservationId(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        if ("DESIGNER".equals(role)) {
            if (!reservation.getDesigner().getDesignerId().equals(authenticationEntity.getEmail())) {
                throw new RuntimeException("You can only update your own reservations.");
            }
            if (status != Status.APPROVED && status != Status.REJECTED) {
                throw new RuntimeException("Designers can only change status to CONFIRMED or REJECTED.");
            }
            reservation.setStatus(status);
        } else if ("USER".equals(role)) {
            if (!reservation.getUser().getUserId().equals(authenticationEntity.getEmail())) {
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

    // 예약 삭제
    public void deleteReservation(AuthenticationEntity authenticationEntity, Long reservationId) {
        ReservationEntity reservation= reservationRepository.findByReservationIdAndUser_Login_email(reservationId,authenticationEntity.getEmail())
                .orElseThrow(() -> new RuntimeException("Reservation and User not found"));

        reservationRepository.delete(reservation);
    }
}