package com.campfood.src.review.controller;

import com.campfood.common.result.ResultCode;
import com.campfood.common.result.ResultResponse;
import com.campfood.src.review.dto.request.ReviewCreateDTO;
import com.campfood.src.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Tag(name = "Review")
@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "리뷰 생성하기")
    @PostMapping("/store/{storeId}")
    public ResponseEntity<ResultResponse> createReview(@PathVariable Long storeId,
                                                       @RequestPart ReviewCreateDTO request,
                                                       @RequestPart(required = false) List<MultipartFile> reviewImages) {
        return ResponseEntity.ok(ResultResponse.of(ResultCode.CREATE_REVIEW_SUCCESS,
                reviewService.createReview(storeId, request, reviewImages)));
    }
}
