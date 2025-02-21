package com.example.hamecobooking.service;

import com.example.hamecobooking.dto.partner.CreatePartner;
import com.example.hamecobooking.dto.partner.PartnerDto;
import com.example.hamecobooking.entity.ManagerEntity;
import com.example.hamecobooking.entity.PartnerEntity;
import com.example.hamecobooking.repository.ManagerRepository;
import com.example.hamecobooking.repository.PartnerRepository;
import com.example.hamecobooking.service.login.JwtService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.example.hamecobooking.enums.Status.PENDING;
import static java.rmi.server.LogStream.log;

@Service
public class PartnerService {
    private final PartnerRepository partnerRepository;
    private final ManagerRepository managerRepository;
    private final JwtService jwtService;
    public PartnerService(PartnerRepository partnerRepository, ManagerRepository managerRepository, JwtService jwtService) {
        this.partnerRepository = partnerRepository;
        this.managerRepository = managerRepository;
        this.jwtService = jwtService;
    }

    public PartnerDto createPartner(String token, CreatePartner.Request partner) {

        // 토큰 검증
        Long Id = jwtService.tokenPacingId(token);

        ManagerEntity manager = managerRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("Manager not found"));

        return PartnerDto.fromEntity(partnerRepository.save(
                PartnerEntity.builder()
                        .manager(manager)
                        .businessName(partner.getBusinessName())
                        .businessAddress(partner.getBusinessAddress())
                        .businessPhone(partner.getBusinessPhone())
                        .businessNumber(partner.getBusinessNumber())
                        .status(PENDING)
                        .createdAt(LocalDateTime.now())
                        .build())
        );
    }

    public void deletePartner(String token, Long partnerId) {

        // 토큰 검증
        Long Id = jwtService.tokenPacingId(token);
        if (!managerRepository.existsById(Id)) {
            log("에러");
        }

        PartnerEntity partnerEntity = partnerRepository.findById(partnerId)
               .orElseThrow(() -> new RuntimeException("Partner not found"));

        partnerRepository.delete(partnerEntity);
    }
}
