package com.campfood.src.store.controller;

import com.campfood.common.result.ResultCode;
import com.campfood.common.result.ResultResponse;
import com.campfood.src.store.dto.request.StoreUpdateDTO;
import com.campfood.src.store.dto.response.*;
import com.campfood.src.store.entity.Category;
import com.campfood.src.store.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Store")
@Log4j2
@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @Operation(summary = "가게 업데이트")
    @PostMapping
    public ResponseEntity<ResultResponse> updateStore(@RequestBody StoreUpdateDTO request) {
        storeService.updateStore(request);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.UPDATE_STORE_SUCCESS));
    }

    @Operation(summary = "가게 좋아요 활성화/비활성화")
    @PostMapping("/{storeId}/heart")
    public ResponseEntity<ResultResponse> toggleStoreHeart(@PathVariable Long storeId) {
        if (storeService.toggleStoreHeart(storeId))
            return ResponseEntity.ok(ResultResponse.of(ResultCode.ACTIVE_STORE_HAERT_SUCCESS));
        return ResponseEntity.ok(ResultResponse.of(ResultCode.INACTIVE_STORE_HEART_SUCCESS));
    }

    @Operation(summary = "특정 태그 가게 조회")
    @Parameters(value = {
            @Parameter(name = "pageable", hidden = true),
            @Parameter(name = "page", required = true, description = "페이지 지정")
    })
    @GetMapping
    public ResponseEntity<ResultResponse> inquiryStoresByTag(
            @RequestParam Category category,
            @PageableDefault(page = 1, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        StorePageResponse<StoreInquiryAllDTO> responseDTO = storeService.inquiryStoresByTag(category, pageable);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.INQUIRY_STORES_BY_TAG_SUCCESS, responseDTO));
    }

    @Operation(summary = "특정 학교 가게 조회")
    @Parameters(value = {
            @Parameter(name = "pageable", hidden = true),
            @Parameter(name = "page", required = true, description = "페이지 지정"),
    })
    @GetMapping("/university")
    public ResponseEntity<ResultResponse> inquiryStoresByUniversity(
            @RequestParam String name,
            @PageableDefault(page = 1, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        StorePageResponse<StoreInquiryAllDTO> responseDTO = storeService.inquiryStoresByUniversity(name, pageable);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.INQUIRY_STORES_BY_UNIVERSITY_SUCCESS, responseDTO));
    }

    @Operation(summary = "특정 가게 상세 조회")
    @GetMapping("/{storeId}")
    public ResponseEntity<ResultResponse> inquiryStoreDetail(@PathVariable Long storeId) {
        StoreInquiryDetailDTO responseDTO = storeService.inquiryStoreDetail(storeId);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.INQUIRY_STORE_DETAIL_SUCCESS, responseDTO));
    }

    @Operation(summary = "가게 검색")
    @Parameters(value = {
            @Parameter(name = "pageable", hidden = true),
            @Parameter(name = "page", required = true, description = "페이지 지정"),
    })
    @GetMapping("/search")
    public ResponseEntity<ResultResponse> searchStoresByKeyword(
            @RequestParam String keyword,
            @PageableDefault(page = 1, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        StorePageResponse<StoreSearchByKeywordDTO> responseDTO = storeService.searchStoresByKeyword(keyword, pageable);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.SEARCH_STORES_BY_KEYWORD_SUCCESS, responseDTO));
    }

    @Operation(summary = "인기 가게 조회")
    @GetMapping("/popular")
    public ResponseEntity<ResultResponse> inquiryStoresByRate(@RequestParam(required = false) String university) {
        List<StoreInquiryPopularDTO> responseDTO = storeService.inquiryStoresByPopular(university);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.INQUIRY_STORES_BY_POPULAR, responseDTO));
    }
}
