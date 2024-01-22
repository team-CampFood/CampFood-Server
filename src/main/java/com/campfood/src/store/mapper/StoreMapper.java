package com.campfood.src.store.mapper;

import com.campfood.src.member.entity.Member;
import com.campfood.src.store.entity.Store;
import com.campfood.src.store.entity.StoreHeart;
import org.springframework.stereotype.Component;

@Component
public class StoreMapper {
    public Store toStore() {
        return Store.builder().build();
    }

    public StoreHeart toStoreHeart(Member member, Store store) {
        return StoreHeart.builder()
                .store(store)
                .member(member)
                .isChecked(false)
                .build();
    }
}
