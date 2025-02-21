package com.example.hamecobooking.dto.procedure;

import com.example.hamecobooking.dto.partner.PartnerDto;
import com.example.hamecobooking.entity.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcedureDto {
    private Long procedureId;
    private DesignerEntity designer;
    private List<ReviewEntity> reviews = new ArrayList<>();
    private List<ReservationEntity> reservations = new ArrayList<>();
    private String procedureName;
    private int duration;
    private String description;
    private int price;

    public static ProcedureDto fromEntity(ProcedureEntity procedure) {
        return ProcedureDto.builder()
                .procedureId(procedure.getProcedureId())
                .designer(procedure.getDesigner())
                .reviews(procedure.getReviews())
                .reservations(procedure.getReservations())
                .procedureName(procedure.getProcedureName())
                .duration(procedure.getDuration())
                .description(procedure.getDescription())
                .price(procedure.getPrice())
                .build();
    }
}
