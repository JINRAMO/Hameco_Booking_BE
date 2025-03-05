package com.example.hamecobooking.service;

import com.example.hamecobooking.dto.designer.CreateAvailableHour;
import com.example.hamecobooking.dto.designer.GetAvailableHour;
import com.example.hamecobooking.dto.reservation.ReservationDto;
import com.example.hamecobooking.entity.AuthenticationEntity;
import com.example.hamecobooking.entity.AvailableHourEntity;
import com.example.hamecobooking.entity.DesignerEntity;
import com.example.hamecobooking.entity.ReservationEntity;
import com.example.hamecobooking.repository.AvailableRepository;
import com.example.hamecobooking.repository.DesignerRepository;
import com.example.hamecobooking.repository.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DesignerService {
    private final ReservationRepository reservationRepository;
    private final DesignerRepository designerRepository;
    private final AvailableRepository availableHourRepository;
    public DesignerService(ReservationRepository reservationRepository, DesignerRepository designerRepository, AvailableRepository availableHourRepository) {
        this.reservationRepository = reservationRepository;
        this.designerRepository = designerRepository;
        this.availableHourRepository = availableHourRepository;
    }

    // 디자이너의 가능 시간 등록
    public void createAvailableHours(AuthenticationEntity authenticationEntity, CreateAvailableHour.Request request) {
        DesignerEntity designer = designerRepository.findByLogin_Email(authenticationEntity.getEmail())
                .orElseThrow(() -> new RuntimeException("디자이너 없음"));

        AvailableHourEntity availableHour = new AvailableHourEntity();
        availableHour.setDesigner(designer);
        availableHour.setAvailableHours(request.getAvailableHours());

        availableHourRepository.save(availableHour);
    }

    // 디자이너의 예약 목록 조회
    public List<ReservationDto> getReservations(AuthenticationEntity authenticationEntity) {
        List<ReservationEntity> reservations = reservationRepository.findByDesigner_Login_email(authenticationEntity.getEmail());

        return reservations.stream()
                .map(ReservationDto::fromEntity)
                .collect(Collectors.toList());
    }

    // 디자이너의 가능 시간 조회
    public GetAvailableHour.Response getAvailableHours(Long designerId) {
        DesignerEntity designer = designerRepository.findById(designerId)
                .orElseThrow(() -> new EntityNotFoundException("디자이너를 찾을 수 없습니다."));

       return GetAvailableHour.Response.from(designer);
    }

    // 디자이너의 가능 시간 삭제
    public void deleteAvailableHours(AuthenticationEntity authenticationEntity, Long availableHourId) {
        AvailableHourEntity availableHour = availableHourRepository.findByIdAndDesigner_Login_email(availableHourId,authenticationEntity.getEmail())
                .orElseThrow(()-> new RuntimeException(""));
        availableHourRepository.delete(availableHour);
    }
}
