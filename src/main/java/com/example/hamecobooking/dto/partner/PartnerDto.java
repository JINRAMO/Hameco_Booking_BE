package com.example.hamecobooking.dto.partner;

import com.example.hamecobooking.entity.ManagerEntity;
import com.example.hamecobooking.entity.PartnerEntity;
import com.example.hamecobooking.entity.StoreEntity;
import com.example.hamecobooking.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PartnerDto {
    private Long partnerId;
    private ManagerEntity manager;
    private List<StoreEntity> stores;
    private String businessName;
    private String businessAddress;
    private String businessPhone;
    private String businessNumber;
    private Status status;
    private LocalDateTime createdAt;

    public static PartnerDto fromEntity(PartnerEntity partner) {
        return PartnerDto.builder()
                .partnerId(partner.getPartnerId())
                .manager(partner.getManager())
                .stores(partner.getStores())
                .businessName(partner.getBusinessName())
                .businessAddress(partner.getBusinessAddress())
                .businessPhone(partner.getBusinessPhone())
                .businessNumber(partner.getBusinessNumber())
                .status(partner.getStatus())
                .createdAt(partner.getCreatedAt())
                .build();
    }
}
