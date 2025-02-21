package com.example.hamecobooking.dto.manager;

import com.example.hamecobooking.dto.partner.PartnerDto;
import com.example.hamecobooking.entity.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ManagerDto {
    private Long managerId;
    private List<PartnerEntity> partners;
    private DesignerEntity designer;
    private LocalDateTime createdAt;

    public static ManagerDto fromEntity(ManagerEntity manager) {
        return ManagerDto.builder()
                .managerId(manager.getManagerId())
                .partners(manager.getPartners())
                .designer(manager.getDesigner())
                .createdAt(manager.getCreatedAt())
                .build();
    }
}
