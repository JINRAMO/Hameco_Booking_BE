package com.example.hamecobooking.controller;

import com.example.hamecobooking.dto.designer.CreateAvailableHour;
import com.example.hamecobooking.dto.login.CustomUserDetails;
import com.example.hamecobooking.dto.partner.GetPartner;
import com.example.hamecobooking.dto.partner.PartnerDto;
import com.example.hamecobooking.dto.store.GetStore;
import com.example.hamecobooking.dto.store.StoreDto;
import com.example.hamecobooking.entity.AuthenticationEntity;
import com.example.hamecobooking.service.ManagerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    private final ManagerService managerService;
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    // 점장의 사업체 조회
    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/partners")
    public List<GetPartner.Response> getPartners(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return managerService.getPartners(userDetails.getAuthenticationEntity()).stream()
                .map(GetPartner.Response::from)
                .collect(Collectors.toList());
    }

    // 점장의 매장 조회
    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/stores")
    public List<GetStore.Response> getStores(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return managerService.getStores(userDetails.getAuthenticationEntity()).stream()
                .map(GetStore.Response::from)
                .collect(Collectors.toList());
    }
}
