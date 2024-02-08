package com.campfood.src.review.controller;

import com.campfood.common.result.ResultCode;
import com.campfood.common.result.ResultResponse;
import com.campfood.src.review.dto.request.ReviewCreateDTO;
import com.campfood.src.review.dto.request.ReviewUpdateDTO;
import com.campfood.src.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Review")
@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "리뷰 생성하기")
    @PostMapping(value = "/store/{storeId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResultResponse> createReview(@PathVariable Long storeId,
                                                       @RequestPart ReviewCreateDTO request,
                                                       @RequestPart(required = false) List<MultipartFile> reviewImages) {
        return ResponseEntity.ok(ResultResponse.of(ResultCode.CREATE_REVIEW_SUCCESS,
                reviewService.createReview(storeId, request, reviewImages)));
    }

    @Operation(summary = "리뷰 수정하기")
    @PostMapping(value = "/{reviewId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
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

    @Operation(summary = "리뷰 좋아요 활성화/비활성화하기")
    @PostMapping("/{reviewId}/heart")
    public ResponseEntity<ResultResponse> toggleReviewHeart(@PathVariable Long reviewId) {
        if (reviewService.toggleReviewHeart(reviewId))
            return ResponseEntity.ok(ResultResponse.of(ResultCode.ACTIVE_REVIEW_HEART_SUCCESS));
        return ResponseEntity.ok(ResultResponse.of(ResultCode.INACTIVE_REVIEW_HEART_SUCCESS));
    }

    @Operation(summary = "특정 가게의 모든 리뷰 조회하기")
    @Parameters(value = {
            @Parameter(name = "page", description = "페이지 지정")
    })
    @GetMapping("/store/{storeId}")
    public ResponseEntity<ResultResponse> inquiryReviewsByStore(
            @PathVariable Long storeId,
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC)
            @Parameter(hidden = true) Pageable pageable)  {
        return ResponseEntity.ok(ResultResponse.of(ResultCode.INQUIRY_REVIEWS_BY_STORE_SUCCESS,
                reviewService.inquiryReviewsByStore(storeId, pageable)));
    }

    @Operation(summary = "특정 멤버의 모든 리뷰 조회하기")
    @Parameters(value = {
            @Parameter(name = "page", description = "페이지 지정"),
            @Parameter(name = "memberId", in = ParameterIn.PATH, description = "미입력 시 본인 리뷰 조회")
    })    @GetMapping(value = {"/member", "/member/{memberId}"})
    public ResponseEntity<ResultResponse> inquiryReviewsByMember(
            @PathVariable(required = false) Long memberId,
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC)
            @Parameter(hidden = true) Pageable pageable) {
        return ResponseEntity.ok(ResultResponse.of(ResultCode.INQUIRY_REVIEWS_BY_MEMBER_SUCCESS,
                reviewService.inquiryReviewsByMember(memberId, pageable)));
    }
}
