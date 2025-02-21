package com.example.hamecobooking.dto.store;

import com.example.hamecobooking.entity.DesignerEntity;
import com.example.hamecobooking.entity.PartnerEntity;
import com.example.hamecobooking.entity.StoreEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreDto {
    private Long storeId;
    private PartnerEntity partner;
    private List<DesignerEntity> designers;
    private String storeName;
    private String storeAddress;
    private String storePhone;
    private String storeType;
    private float rating;
    private LocalDateTime createdAt;

    public static StoreDto fromEntity(StoreEntity store) {
        return StoreDto.builder()
                .storeId(store.getStoreId())
                .partner(store.getPartner())
                .designers(store.getDesigners())
                .storeName(store.getStoreName())
                .storeAddress(store.getStoreAddress())
                .storePhone(store.getStorePhone())
                .storeType(store.getStoreType())
                .createdAt(store.getCreatedAt())
                .rating(store.getRating())
                .build();
    }
}
