package com.campfood.src.store;

import com.campfood.common.result.ResultCode;
import com.campfood.common.result.ResultResponse;
import com.campfood.src.store.dto.StoreInfoDTO;
import com.campfood.src.store.response.StoreInfoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Store")
@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;
    @ApiOperation(value = "예시1")
    @PostMapping()
    public ResponseEntity<ResultResponse> example1(){
        storeService.example1();
        return ResponseEntity.ok(ResultResponse.of(ResultCode.Example));
    }

    @ApiOperation(value = "예시2")
    @PostMapping()
    public ResponseEntity<StoreInfoResponse> example2(){
        StoreInfoDTO storeInfoDTO = storeService.example2();
        return ResponseEntity.ok(StoreInfoResponse.of(ResultCode.Example, storeInfoDTO));
    }

}
