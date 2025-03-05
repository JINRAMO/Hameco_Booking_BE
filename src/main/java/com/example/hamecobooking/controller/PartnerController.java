package com.example.hamecobooking.controller;

import com.example.hamecobooking.dto.designer.CreateAvailableHour;
import com.example.hamecobooking.dto.login.CustomUserDetails;
import com.example.hamecobooking.dto.partner.CreatePartner;
import com.example.hamecobooking.service.PartnerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/partner")
public class PartnerController {
    private final PartnerService partnerService;
    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    // 사업체 등록
    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping
    public CreatePartner.Response createPartner(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody CreatePartner.Request request) {
        return CreatePartner.Response.from(partnerService.createPartner(userDetails.getAuthenticationEntity(),request));
    }

    // 사업체 삭제
    @PreAuthorize("hasAuthority('MANAGER')")
    @DeleteMapping("/{partnerId}")
    public void deletePartner(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long partnerId) {
        partnerService.deletePartner(userDetails.getAuthenticationEntity(), partnerId);
    }
}
