package com.example.hamecobooking.service;

import com.example.hamecobooking.dto.store.CreateStore;
import com.example.hamecobooking.dto.review.ReviewDto;
import com.example.hamecobooking.dto.store.StoreDto;
import com.example.hamecobooking.dto.store.UpdateStore;
import com.example.hamecobooking.entity.AuthenticationEntity;
import com.example.hamecobooking.entity.PartnerEntity;
import com.example.hamecobooking.entity.ReviewEntity;
import com.example.hamecobooking.entity.StoreEntity;
import com.example.hamecobooking.repository.ManagerRepository;
import com.example.hamecobooking.repository.PartnerRepository;
import com.example.hamecobooking.repository.ReviewRepository;
import com.example.hamecobooking.repository.StoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StoreService {
    private final StoreRepository storeRepository;
    private final ManagerRepository managerRepository;
    private final PartnerRepository partnerRepository;
    private final ReviewRepository reviewRepository;
    public StoreService(StoreRepository storeRepository, ManagerRepository managerRepository, PartnerRepository partnerRepository, ReviewRepository reviewRepository) {
        this.storeRepository = storeRepository;
        this.managerRepository = managerRepository;
        this.partnerRepository = partnerRepository;
        this.reviewRepository = reviewRepository;
    }

    // 매장 등록
    public StoreDto createStore(AuthenticationEntity authenticationEntity, CreateStore.Request store) {
        managerRepository.findByLogin_email(authenticationEntity.getEmail()).orElseThrow(() -> new RuntimeException("Manager not found"));

        PartnerEntity partner = partnerRepository.findByPartnerId(store.getPartnerId())
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

    // 매장 수정
    public StoreDto updateStore(AuthenticationEntity authenticationEntity, UpdateStore.Request store) {
        StoreEntity storeToUpdate = storeRepository.findByStoreIdAndPartner_Manager_Login_email(store.getStoreId(),authenticationEntity.getEmail())
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

    // 매장 삭제
    public void deleteStore(AuthenticationEntity authenticationEntity, Long storeId) {
        StoreEntity storeEntity = storeRepository.findByStoreIdAndPartner_Manager_Login_email(storeId,authenticationEntity.getEmail())
                .orElseThrow(() -> new RuntimeException("Store not found"));
        storeRepository.delete(storeEntity);
    }

    // 전체 매장 목록 조회
    public List<StoreDto> getAllStores() {

        return storeRepository.findAll().stream()
                .map(StoreDto::fromEntity)
                .toList();
    }

    // 선택한 매장 조회
    public StoreDto getStoreDetail(Long storeId) {
        StoreEntity storeEntity = storeRepository.findByStoreId(storeId)
            .orElseThrow(() -> new RuntimeException("Store not found"));

        return StoreDto.fromEntity(storeEntity);
    }

    // 해당 매장의 전체 리뷰 조회
    public List<ReviewDto> getStoreReviews(Long storeId) {
        List<ReviewEntity> reviews = reviewRepository.findByStore_StoreId(storeId);

        return reviews.stream()
                .map(ReviewDto::fromEntity)
                .collect(Collectors.toList());
    }
}
