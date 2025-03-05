package com.example.hamecobooking.controller;

import com.example.hamecobooking.dto.login.CustomUserDetails;
import com.example.hamecobooking.dto.review.CreateReview;
import com.example.hamecobooking.dto.review.GetReview;
import com.example.hamecobooking.dto.store.CreateStore;
import com.example.hamecobooking.dto.store.GetStore;
import com.example.hamecobooking.dto.store.StoreDto;
import com.example.hamecobooking.dto.store.UpdateStore;
import com.example.hamecobooking.service.StoreService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/store")
public class StoreController {
    private final StoreService storeService;
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    // 매장 등록
    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping
    public CreateStore.Response createStore(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody CreateStore.Request request) {
        return CreateStore.Response.from(storeService.createStore(userDetails.getAuthenticationEntity(),request));
    }

    // 매장 수정
    @PreAuthorize("hasAuthority('MANAGER')")
    @PutMapping
    public UpdateStore.Response updateStore(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody UpdateStore.Request request) {
        return UpdateStore.Response.from(storeService.updateStore(userDetails.getAuthenticationEntity(),request));
    }

    // 매장 삭제
    @PreAuthorize("hasAuthority('MANAGER')")
    @DeleteMapping("/{storeId}")
    public void deleteStore(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long storeId) {
        storeService.deleteStore(userDetails.getAuthenticationEntity(),storeId);
    }

    // 전체 매장 목록 조회
    @GetMapping
    public List<GetStore.Response> getAllStores() {
        return storeService.getAllStores().stream()
                .map(GetStore.Response::from)
                .collect(Collectors.toList());
    }

    // 선택한 매장 조회
    @GetMapping("/{storeId}")
    public GetStore.Response getStoreDetail(@PathVariable Long storeId){
        return GetStore.Response.from(storeService.getStoreDetail(storeId));
    }

    // 해당 매장의 전체 리뷰 조회
    @GetMapping("/{storeId}/reviews")
    public List<GetReview.Response> getStoreReviews(@PathVariable Long storeId) {
        return storeService.getStoreReviews(storeId).stream()
                .map(GetReview.Response::from)
                .collect(Collectors.toList());
    }
}