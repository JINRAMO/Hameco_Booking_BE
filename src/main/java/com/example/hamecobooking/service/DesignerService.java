package com.example.hamecobooking.service;

import com.example.hamecobooking.dto.designer.CreateAvailableHour;
import com.example.hamecobooking.dto.designer.GetAvailableHour;
import com.example.hamecobooking.dto.reservation.ReservationDto;
import com.example.hamecobooking.entity.AvailableHourEntity;
import com.example.hamecobooking.entity.DesignerEntity;
import com.example.hamecobooking.entity.ReservationEntity;
import com.example.hamecobooking.repository.AvailableRepository;
import com.example.hamecobooking.repository.DesignerRepository;
import com.example.hamecobooking.repository.ReservationRepository;
import com.example.hamecobooking.service.login.JwtService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DesignerService {
    private final JwtService jwtService;
    private final ReservationRepository reservationRepository;
    private final DesignerRepository designerRepository;
    private final AvailableRepository availableHourRepository;
    public DesignerService(JwtService jwtService, ReservationRepository reservationRepository, DesignerRepository designerRepository, AvailableRepository availableHourRepository) {
        this.jwtService = jwtService;
        this.reservationRepository = reservationRepository;
        this.designerRepository = designerRepository;
        this.availableHourRepository = availableHourRepository;
    }

    public void createAvailableHours(String token, CreateAvailableHour.Request request) {
        // token 검증 필요
        Long id = jwtService.tokenPacingId(token);

        AvailableHourEntity availableHour = new AvailableHourEntity();

        // 디자이너 정보 설정
        DesignerEntity designer = designerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("디자이너 없음"));
        availableHour.setDesigner(designer);

        // 디자이너가 가능한 예약 시간 저장
        availableHour.setAvailableHours(request.getAvailableHours());

        availableHourRepository.save(availableHour);
    }

    public List<ReservationDto> getReservations(String token) {
        Long Id = jwtService.tokenPacingId(token);

        List<ReservationEntity> reservations = reservationRepository.findByDesigner_DesignerId(Id);

        return reservations.stream()
                .map(ReservationDto::fromEntity)
                .collect(Collectors.toList());
    }

    public void deleteAvailableHours(String token, Long availableHourId) {
        Long Id = jwtService.tokenPacingId(token);

        AvailableHourEntity availableHour = availableHourRepository.findById(availableHourId)
                .orElseThrow(() -> new RuntimeException("예약 시간 없음"));

        if (!availableHour.getDesigner().getDesignerId().equals(Id)) {
            throw new RuntimeException("예약 시간 삭제 권한 없음");
        }

        availableHourRepository.delete(availableHour);
    }

    public GetAvailableHour.Response getAvailableHours(String token) {
        // 토큰 검증 필요
        Long id = jwtService.tokenPacingId(token);

        // 해당 디자이너의 예약 가능 시간 조회
        DesignerEntity designer = designerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("디자이너를 찾을 수 없습니다."));

       return GetAvailableHour.Response.from(designer);
    }
}
