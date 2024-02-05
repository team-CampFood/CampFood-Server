package com.campfood.src.review.controller;

import com.campfood.common.result.ResultCode;
import com.campfood.common.result.ResultResponse;
import com.campfood.src.member.dto.SignUpDto;
import com.campfood.src.review.dto.request.ReviewCreateDTO;
import com.campfood.src.review.dto.request.ReviewUpdateDTO;
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

    @Operation(summary = "리뷰 수정하기")
    @PostMapping("/{reviewId}")
    public ResponseEntity<ResultResponse> updateReview(@PathVariable Long reviewId,
                                                       @RequestPart ReviewUpdateDTO request,
                                                       @RequestPart(required = false) List<MultipartFile> reviewImages) {
        return ResponseEntity.ok(ResultResponse.of(ResultCode.UPDATE_REVIEW_SUCCESS,
                reviewService.updateReview(reviewId, request, reviewImages)));
    }

    @Operation(summary = "리뷰 삭제하기")
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<ResultResponse> deleteReview(@PathVariable Long reviewId) {
        return ResponseEntity.ok(ResultResponse.of(ResultCode.DELETE_REVIEW_SUCCESS,
                reviewService.deleteReview(reviewId)));
    }

    @Operation(summary = "리뷰 좋아요 활성화/비활성화")
    @PostMapping("/{reviewId}/heart")
    public ResponseEntity<ResultResponse> toggleReviewHeart(@PathVariable Long reviewId) {
        if (reviewService.toggleReviewHeart(reviewId))
            return ResponseEntity.ok(ResultResponse.of(ResultCode.ACTIVE_REVIEW_HEART_SUCCESS));
        return ResponseEntity.ok(ResultResponse.of(ResultCode.INACTIVE_REVIEW_HEART_SUCCESS));
    }
}
