package com.example.hamecobooking.controller;

import com.example.hamecobooking.dto.partner.CreatePartner;
import com.example.hamecobooking.service.PartnerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/partner")
public class PartnerController {
    private final PartnerService partnerService;
    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    // 사업체 등록
    @PostMapping
    public CreatePartner.Response createPartner(@RequestHeader("Authorization") String token, @RequestBody CreatePartner.Request request) {
        token = token.replace("Bearer ", "");
        return CreatePartner.Response.from(partnerService.createPartner(token,request));
    }

    // 사업체 삭제
    @DeleteMapping("/{partner_id}")
    public void deletePartner(@RequestHeader("Authorization") String token, @PathVariable("partner_id") Long partnerId) {
        token = token.replace("Bearer ", "");
        partnerService.deletePartner(token, partnerId);
    }
}
