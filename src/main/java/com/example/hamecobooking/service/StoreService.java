package com.example.hamecobooking.service;

import com.example.hamecobooking.dto.store.CreateStore;
import com.example.hamecobooking.dto.review.ReviewDto;
import com.example.hamecobooking.dto.store.StoreDto;
import com.example.hamecobooking.dto.store.UpdateStore;
import com.example.hamecobooking.entity.PartnerEntity;
import com.example.hamecobooking.entity.ReviewEntity;
import com.example.hamecobooking.entity.StoreEntity;
import com.example.hamecobooking.repository.ManagerRepository;
import com.example.hamecobooking.repository.PartnerRepository;
import com.example.hamecobooking.repository.ReviewRepository;
import com.example.hamecobooking.repository.StoreRepository;
import com.example.hamecobooking.service.login.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.rmi.server.LogStream.log;

@Service
@Slf4j
public class StoreService {
    private final StoreRepository storeRepository;
    private final ManagerRepository managerRepository;
    private final PartnerRepository partnerRepository;
    private final ReviewRepository reviewRepository;
    private final JwtService jwtService;
    public StoreService(StoreRepository storeRepository, ManagerRepository managerRepository, PartnerRepository partnerRepository, ReviewRepository reviewRepository, JwtService jwtService) {
        this.storeRepository = storeRepository;
        this.managerRepository = managerRepository;
        this.partnerRepository = partnerRepository;
        this.reviewRepository = reviewRepository;
        this.jwtService = jwtService;
    }

    public StoreDto createStore(String token, CreateStore.Request store) {

        // 토큰 검증
        Long Id = jwtService.tokenPacingId(token);
        if(!managerRepository.existsById(Id)){
            log("에러");
        }

       PartnerEntity partner = partnerRepository.findById(store.getPartnerId())
                .orElseThrow(() -> new IllegalArgumentException("Partner not found"));

        return StoreDto.fromEntity(storeRepository.save(
                StoreEntity.builder()
                        .partner(partner)
                        .storeName(store.getStoreName())
                        .storeAddress(store.getStoreAddress())
                        .storePhone(store.getStorePhone())
                        .storeType(store.getStoreType())
                        .createdAt(LocalDateTime.now())
                        .rating(0.0f)
                        .build())
        );
    }

    public StoreDto updateStore(String token, UpdateStore.Request store) {

        // 토큰 검증 필요
        StoreEntity storeToUpdate = storeRepository.findById(store.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found"));

        return StoreDto.fromEntity(storeRepository.save(
                storeToUpdate.builder()
                        .storeId(storeToUpdate.getStoreId())
                        .partner(storeToUpdate.getPartner())
                        .storeName(store.getStoreName())
                        .storeAddress(store.getStoreAddress())
                        .storePhone(store.getStorePhone())
                        .storeType(store.getStoreType())
                        .rating(storeToUpdate.getRating())
                        .createdAt(storeToUpdate.getCreatedAt())
                        .build())
        );
    }

    public void deleteStore(String token, Long storeId) {

        // 토큰 검증 필요
        StoreEntity storeEntity = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Store not found"));

        storeRepository.delete(storeEntity);
    }

    public List<StoreDto> getAllStores() {

        return storeRepository.findAll().stream()
                .map(StoreDto::fromEntity)
                .toList();
    }

    public StoreDto getStoreDetail(Long storeId) {
        StoreEntity storeEntity = storeRepository.findById(storeId)
            .orElseThrow(() -> new RuntimeException("Store not found"));

        return StoreDto.fromEntity(storeEntity);
    }

    public List<ReviewDto> getStoreReviews(Long storeId) {
        List<ReviewEntity> reviews = reviewRepository.findByStore_StoreId(storeId);

        return reviews.stream()
                .map(ReviewDto::fromEntity)
                .collect(Collectors.toList());
    }
}
