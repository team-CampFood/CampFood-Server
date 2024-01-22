package com.campfood.src.store.service;

import com.campfood.common.error.ErrorCode;
import com.campfood.common.exception.RestApiException;
import com.campfood.src.member.entity.Member;
import com.campfood.src.store.dto.StoreInfoDTO;
import com.campfood.src.store.entity.Store;
import com.campfood.src.store.entity.StoreHeart;
import com.campfood.src.store.mapper.StoreMapper;
import com.campfood.src.store.repository.StoreHeartRepository;
import com.campfood.src.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreHeartRepository storeHeartRepository;

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

    public void example1() {
    }

    public StoreInfoDTO example2() {
        return new StoreInfoDTO();
    }
}
