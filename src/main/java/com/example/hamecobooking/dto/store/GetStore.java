package com.example.hamecobooking.dto.store;


import lombok.*;

import java.time.LocalDateTime;

public class GetStore {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request {
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private Long storeId;
        private String storeName;
        private String storeAddress;
        private String storePhone;
        private String storeType;
        private LocalDateTime createdAt;
        private float rating;

        public static GetStore.Response from(StoreDto storeDto){
            return GetStore.Response.builder()
                    .storeId(storeDto.getStoreId())
                    .storeName(storeDto.getStoreName())
                    .storeAddress(storeDto.getStoreAddress())
                    .storePhone(storeDto.getStorePhone())
                    .storeType(storeDto.getStoreType())
                    .createdAt(storeDto.getCreatedAt())
                    .rating(storeDto.getRating())
                    .build();
        }
    }
}
