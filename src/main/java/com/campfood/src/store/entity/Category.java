package com.campfood.src.store.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    KOREAN_FOOD("한식"),
    ASIAN_FOOD("아시안"),
    CHINESE_FOOD("중식"),
    WESTERN_FOOD("양식"),
    SCHOOL_FOOD("분식"),
    CAFE("카페"),
    DESSERT("디저트"),
    BAR("술집/바"),
    IZAKAYA("이자카야"),
    CHICKEN("치킨"),
    BURGER("버거"),
    PIZZA("피자"),
    SUSHI("초밥"),
    JOKBAL_BOSSAM("족발/보쌈"),
    JAPANESE_FOOD("돈까스/회/일식"),
    MEAT_GRILLED("고기/구이"),
    SOUP("찜/탕/찌개"),
    MEAL("백반/죽/국수"),
    TRIPE("곱창/막창/대창"),
    SEAFOOD("해산물"),
    CHICKENS("닭갈비/찜닭/닭발"),
    ;

    private final String toKorean;
}

