package com.campfood.src.store.controller;

import com.campfood.common.result.ResultCode;
import com.campfood.common.result.ResultResponse;
import com.campfood.src.store.service.StoreService;
import com.campfood.src.store.dto.StoreInfoDTO;
import com.campfood.src.store.response.StoreResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "예시1")
    @GetMapping("/example1")
    public ResponseEntity<ResultResponse> example1(){
        storeService.example1();
        return ResponseEntity.ok(ResultResponse.of(ResultCode.Example));
    }

    @ApiOperation(value = "예시2")
    @PostMapping("/example2")
    public ResponseEntity<StoreResponse> example2(){
        StoreInfoDTO storeInfoDTO = storeService.example2();
        return ResponseEntity.ok(StoreResponse.of(ResultCode.Example, storeInfoDTO));
    }

}
