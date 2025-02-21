package com.example.hamecobooking.controller;

import com.example.hamecobooking.dto.review.GetReview;
import com.example.hamecobooking.dto.store.CreateStore;
import com.example.hamecobooking.dto.store.GetStore;
import com.example.hamecobooking.dto.store.StoreDto;
import com.example.hamecobooking.dto.store.UpdateStore;
import com.example.hamecobooking.service.StoreService;
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
    @PostMapping
    public CreateStore.Response createStore(@RequestHeader("Authorization") String token, @RequestBody CreateStore.Request request) {
        token = token.replace("Bearer ", "");
        return CreateStore.Response.from(storeService.createStore(token,request));
    }

    // 매장 수정
    @PutMapping
    public UpdateStore.Response updateStore(@RequestHeader("Authorization") String token, @RequestBody UpdateStore.Request request) {
        token = token.replace("Bearer ", "");
        return UpdateStore.Response.from(storeService.updateStore(token,request));
    }

    // 매장 삭제
    @DeleteMapping("/{store_id}")
    public void deleteStore(@RequestHeader("Authorization") String token, @PathVariable("store_id") Long storeId) {
        token = token.replace("Bearer ", "");
        storeService.deleteStore(token, storeId);
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