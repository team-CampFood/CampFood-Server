package com.campfood.src.store.service;

import com.campfood.common.error.ErrorCode;
import com.campfood.common.exception.RestApiException;
import com.campfood.src.member.entity.Member;
import com.campfood.src.store.dto.StoreInfoDTO;
import com.campfood.src.store.dto.StoreInquiryAllDTO;
import com.campfood.src.store.entity.Store;
import com.campfood.src.store.entity.StoreHeart;
import com.campfood.src.store.entity.Tag;
import com.campfood.src.store.mapper.StoreMapper;
import com.campfood.src.store.repository.StoreHeartRepository;
import com.campfood.src.store.repository.StoreRepository;
import com.campfood.src.store.repository.StoreTagRepository;
import com.campfood.src.university.entity.University;
import com.campfood.src.university.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreHeartRepository storeHeartRepository;
    private final StoreTagRepository storeTagRepository;

    private final UniversityService universityService;

    private final StoreMapper storeMapper;

    @Transactional
    public boolean toggleStoreHeart(Long storeId) {
        // 로그인 유저 -> 받아오는 로직 필요
        Member member = Member.builder().build();

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RestApiException(ErrorCode.STORE_NOT_EXIST));

        StoreHeart storeHeart = storeHeartRepository.findByMemberAndStore(member, store)
                .orElseGet(() -> {
                    StoreHeart newStoreHeart = storeMapper.toStoreHeart(member, store);
                    return storeHeartRepository.save(newStoreHeart);
                });

        storeHeart.toggleStoreHeart();

        return storeHeart.isChecked();
    }

    public List<StoreInquiryAllDTO> inquiryStoresByTag(Tag tag, Pageable pageable) {

        Page<Store> savedStores = storeTagRepository.findAllByTag(tag, pageable);

        return savedStores.map(storeMapper::toInquiryByTagDTO).stream().toList();
    }

    public List<StoreInquiryAllDTO> inquiryStoresByUniversity(String name, Pageable pageable) {
        University university = universityService.findUniversityByName(name);

        Page<Store> savedStores = storeRepository.findAllByUniversity(university, pageable);

        return savedStores.map(storeMapper::toInquiryByTagDTO).stream().toList();
    }

    public void example1() {
    }

    public StoreInfoDTO example2() {
        return new StoreInfoDTO();
    }
}
