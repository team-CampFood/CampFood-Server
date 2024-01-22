package com.campfood.src.store.controller;

import com.campfood.common.result.ResultCode;
import com.campfood.common.result.ResultResponse;
import com.campfood.src.store.dto.StoreInquiryAllDTO;
import com.campfood.src.store.entity.Tag;
import com.campfood.src.store.service.StoreService;
import com.campfood.src.store.dto.StoreInquiryDetailDTO;
import com.campfood.src.store.response.StoreResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Store")
@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @ApiOperation(value = "가게 좋아요 활성화/비활성화")
    @PostMapping("/{storeId}/heart")
    public ResponseEntity<ResultResponse> toggleStoreHeart(@PathVariable Long storeId) {
        if (storeService.toggleStoreHeart(storeId))
            return ResponseEntity.ok(ResultResponse.of(ResultCode.ACTIVE_STORE_HAERT_SUCCESS));
        return ResponseEntity.ok(ResultResponse.of(ResultCode.INACTIVE_STORE_HEART_SUCCESS));
    }

    @ApiOperation(value = "특정 태그 가게 조회")
    @GetMapping
    public ResponseEntity<StoreResponse<List<StoreInquiryAllDTO>>> inquiryStoresByTag(@RequestParam Tag tag,
                                                                                      @RequestParam String sort,
                                                                                      @RequestParam Sort.Direction direction,
                                                                                      @PageableDefault(page = 1) Pageable pageable) {
        Sort sorting = Sort.by(direction, sort);
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sorting);

        List<StoreInquiryAllDTO> responseDTO = storeService.inquiryStoresByTag(tag, pageable);
        return ResponseEntity.ok(StoreResponse.of(ResultCode.INQUIRY_STORES_BY_TAG_SUCCESS, responseDTO));
    }

    @ApiOperation(value = "특정 학교 가게 조회")
    @GetMapping("/university")
    public ResponseEntity<StoreResponse<List<StoreInquiryAllDTO>>> inquiryStoresByUniversity(@RequestParam String name,
                                                                                             @RequestParam String sort,
                                                                                             @RequestParam Sort.Direction direction,
                                                                                             @PageableDefault(page = 1) Pageable pageable) {
        Sort sorting = Sort.by(direction, sort);
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sorting);

        List<StoreInquiryAllDTO> responseDTO = storeService.inquiryStoresByUniversity(name, pageable);
        return ResponseEntity.ok(StoreResponse.of(ResultCode.INQUIRY_STORES_BY_UNIVERSITY_SUCCESS, responseDTO));
    }

    @ApiOperation(value = "특정 가게 상세 조회")
    @GetMapping("/{storeId}")
    public ResponseEntity<StoreResponse<StoreInquiryDetailDTO>> inquiryStoreDetail(@PathVariable Long storeId) {
        StoreInquiryDetailDTO responseDTO = storeService.inquiryStoreDetail(storeId);
        return ResponseEntity.ok(StoreResponse.of(ResultCode.INQUIRY_STORE_DETAIL_SUCCESS, responseDTO));
    }
}
