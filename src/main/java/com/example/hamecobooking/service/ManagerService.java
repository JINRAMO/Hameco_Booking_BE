package com.example.hamecobooking.service;

import com.example.hamecobooking.dto.partner.PartnerDto;
import com.example.hamecobooking.dto.store.StoreDto;
import com.example.hamecobooking.entity.PartnerEntity;
import com.example.hamecobooking.entity.StoreEntity;
import com.example.hamecobooking.repository.PartnerRepository;
import com.example.hamecobooking.service.login.JwtService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManagerService {
    private final PartnerRepository partnerRepository;
    private final JwtService jwtService;
    public ManagerService(PartnerRepository partnerRepository, JwtService jwtService) {
        this.partnerRepository = partnerRepository;
        this.jwtService = jwtService;
    }

    public List<PartnerDto> getPartners(String token) {

        Long Id = jwtService.tokenPacingId(token);

        List<PartnerEntity> partners = partnerRepository.findByManager_ManagerId(Id);

        return partners.stream()
                .map(PartnerDto::fromEntity)
                .collect(Collectors.toList());
    }

    public List<StoreDto> getStores(String token) {

        Long Id = jwtService.tokenPacingId(token);

        List<PartnerEntity> partners = partnerRepository.findByManager_ManagerId(Id);

        List<StoreEntity> stores = partners.stream()
                .flatMap(partner -> partner.getStores().stream())
                .collect(Collectors.toList());

        return stores.stream()
                .map(StoreDto::fromEntity)
                .collect(Collectors.toList());
    }

}
