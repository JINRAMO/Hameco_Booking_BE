package com.example.hamecobooking.service;

import com.example.hamecobooking.dto.partner.PartnerDto;
import com.example.hamecobooking.dto.store.StoreDto;
import com.example.hamecobooking.entity.AuthenticationEntity;
import com.example.hamecobooking.entity.PartnerEntity;
import com.example.hamecobooking.entity.StoreEntity;
import com.example.hamecobooking.repository.PartnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManagerService {
    private final PartnerRepository partnerRepository;
    public ManagerService(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    // 점장의 사업체 조회
    public List<PartnerDto> getPartners(AuthenticationEntity authenticationEntity) {
        List<PartnerEntity> partners = partnerRepository.findByManager_Login_email(authenticationEntity.getEmail());

        return partners.stream()
                .map(PartnerDto::fromEntity)
                .collect(Collectors.toList());
    }

    // 점장의 매장 조회
    public List<StoreDto> getStores(AuthenticationEntity authenticationEntity) {
        List<PartnerEntity> partners = partnerRepository.findByManager_Login_email(authenticationEntity.getEmail());

        List<StoreEntity> stores = partners.stream()
                .flatMap(partner -> partner.getStores().stream())
                .collect(Collectors.toList());

        return stores.stream()
                .map(StoreDto::fromEntity)
                .collect(Collectors.toList());
    }

}
