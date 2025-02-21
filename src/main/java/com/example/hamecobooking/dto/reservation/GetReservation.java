package com.example.hamecobooking.dto.reservation;

import com.example.hamecobooking.enums.Status;
import lombok.*;

import java.time.LocalDateTime;

public class GetReservation {

    public static class Request {
    }
    // {"designerId":1,"procedureId":1,"notes":"노트","reservationDateTime":"2021-08-01T00:00:00"}

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private Long userId;
        private String username; // 디자이너 이름
        private String procedureName;
        private String notes;
        private LocalDateTime reservationDateTime;
        private Status status;

        public static GetReservation.Response from(ReservationDto reservationDto){
            return GetReservation.Response.builder()
                    .userId(reservationDto.getUser().getUserId())
                    .username(reservationDto.getDesigner().getUsername())
                    .procedureName(reservationDto.getProcedure().getProcedureName())
                    .notes(reservationDto.getNotes())
                    .reservationDateTime(reservationDto.getReservationDateTime())
                    .status(reservationDto.getStatus())
                    .build();
        }
    }
}
