package com.example.hamecobooking.dto.store;

import lombok.*;

import java.time.LocalDateTime;

public class UpdateStore {
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request {
        private Long storeId;
        private String storeName;
        private String storeAddress;
        private String storePhone;
        private String storeType;
        private LocalDateTime createdAt;
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
        private float rating;
        private LocalDateTime createdAt;

        public static UpdateStore.Response from(StoreDto storeDto){
            return UpdateStore.Response.builder()
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
