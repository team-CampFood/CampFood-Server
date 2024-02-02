package com.campfood.src.review.service;

import com.campfood.src.review.dto.request.ReviewCreateDTO;
import com.campfood.src.review.entity.Review;
import com.campfood.src.review.entity.ReviewImage;
import com.campfood.src.review.mapper.ReviewImageMapper;
import com.campfood.src.review.mapper.ReviewMapper;
import com.campfood.src.review.repository.ReviewImageRepository;
import com.campfood.src.review.repository.ReviewRepository;
import com.campfood.src.store.entity.Store;
import com.campfood.src.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final ReviewMapper reviewMapper;
    private final ReviewImageMapper reviewImageMapper;

    private final StoreService storeService;

    // 리뷰 생성 함수
    @Transactional
    public Long createReview(final Long storeId, ReviewCreateDTO request, List<MultipartFile> reviewImages) {

        // 가게 찾기
        Store store = storeService.loadEntity(storeId);

        Review savedReview = reviewRepository.save(reviewMapper.toReview(store, request));

        // 리뷰 이미지 저장
        if (reviewImages != null) {
            saveReviewImages(savedReview, uploadReviewImages(reviewImages));
        }

        return savedReview.getId();
    }

    // reviewImage 저장 함수
    private void saveReviewImages(Review review, List<String> imageUrls) {
        List<ReviewImage> reviewImages = imageUrls.stream()
                .map(imageUrl -> reviewImageMapper.toReviewImage(review, imageUrl))
                .collect(Collectors.toList());
        reviewImageRepository.saveAll(reviewImages);
    }

    // s3 업로드 함수
    private List<String> uploadReviewImages(List<MultipartFile> reviewImages) {
        //TODO: s3 사진 업로드 구현 후 개발
        return List.of();
    }
}
