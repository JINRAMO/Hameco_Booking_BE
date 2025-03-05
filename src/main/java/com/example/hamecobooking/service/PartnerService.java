package com.example.hamecobooking.service;

import com.example.hamecobooking.dto.partner.CreatePartner;
import com.example.hamecobooking.dto.partner.PartnerDto;
import com.example.hamecobooking.entity.AuthenticationEntity;
import com.example.hamecobooking.entity.ManagerEntity;
import com.example.hamecobooking.entity.PartnerEntity;
import com.example.hamecobooking.repository.ManagerRepository;
import com.example.hamecobooking.repository.PartnerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.example.hamecobooking.enums.Status.PENDING;

@Service
public class PartnerService {
    private final PartnerRepository partnerRepository;
    private final ManagerRepository managerRepository;
    public PartnerService(PartnerRepository partnerRepository, ManagerRepository managerRepository) {
        this.partnerRepository = partnerRepository;
        this.managerRepository = managerRepository;
    }

    // 사업체 등록
    public PartnerDto createPartner(AuthenticationEntity authenticationEntity, CreatePartner.Request partner) {
        ManagerEntity manager = managerRepository.findByLogin_email(authenticationEntity.getEmail())
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

    // 사업체 삭제
    public void deletePartner(AuthenticationEntity authenticationEntity, Long partnerId) {
        PartnerEntity partnerEntity = partnerRepository.findByPartnerIdAndManager_Login_email(partnerId,authenticationEntity.getEmail())
               .orElseThrow(() -> new RuntimeException("Partner and Manage not found"));

        partnerRepository.delete(partnerEntity);
    }
}
