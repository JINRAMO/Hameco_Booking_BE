package com.example.hamecobooking.dto.partner;

import lombok.*;

public class CreatePartner {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Request {
        private String businessName;
        private String businessAddress;
        private String businessPhone;
        private String businessNumber;

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private String businessName;
        private String businessAddress;
        private String businessPhone;
        private String businessNumber;

        public static CreatePartner.Response from(PartnerDto partnerDto){
            return Response.builder()
                    .businessName(partnerDto.getBusinessName())
                    .businessAddress(partnerDto.getBusinessAddress())
                    .businessPhone(partnerDto.getBusinessPhone())
                    .businessNumber(partnerDto.getBusinessNumber())
                    .build();
        }
    }
}
