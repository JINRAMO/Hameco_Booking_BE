package com.example.hamecobooking.controller;

import com.example.hamecobooking.dto.partner.GetPartner;
import com.example.hamecobooking.dto.partner.PartnerDto;
import com.example.hamecobooking.dto.store.GetStore;
import com.example.hamecobooking.dto.store.StoreDto;
import com.example.hamecobooking.service.ManagerService;
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

    //점장의 사업체 조회
    @GetMapping("/partners")
    public List<GetPartner.Response> getPartners(@RequestHeader("Authorization") String token) {
        token = token.replace("Bearer ", "");
        return managerService.getPartners(token).stream()
                .map(GetPartner.Response::from)
                .collect(Collectors.toList());
    }

    // 점장의 매장 조회
    @GetMapping("/stores")
    public List<GetStore.Response> getStores(@RequestHeader("Authorization") String token) {
        token = token.replace("Bearer ", "");
        return managerService.getStores(token).stream()
                .map(GetStore.Response::from)
                .collect(Collectors.toList());
    }
}
