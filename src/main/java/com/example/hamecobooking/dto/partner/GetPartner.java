package com.example.hamecobooking.dto.partner;

import com.example.hamecobooking.enums.Status;
import lombok.*;

import java.time.LocalDateTime;

public class GetPartner {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Request {
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private Long partnerId;
        private String businessName;
        private String businessAddress;
        private String businessPhone;
        private String businessNumber;
        private Status status;
        private LocalDateTime createdAt;

        public static GetPartner.Response from(PartnerDto partnerDto){
            return GetPartner.Response.builder()
                    .partnerId(partnerDto.getPartnerId())
                    .businessName(partnerDto.getBusinessName())
                    .businessAddress(partnerDto.getBusinessAddress())
                    .businessPhone(partnerDto.getBusinessPhone())
                    .businessNumber(partnerDto.getBusinessNumber())
                    .status(partnerDto.getStatus())
                    .createdAt(partnerDto.getCreatedAt())
                    .build();
        }
    }
}
