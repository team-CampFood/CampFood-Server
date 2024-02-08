package com.campfood.src.store.dto.request;

import com.campfood.src.store.entity.Category;
import com.campfood.src.store.entity.OpenDay;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreUpdateDTO {
    private String identificationId;
    private String name;
    private List<Category> categories;
    private double rate;
    private int visitedReview;
    private int blogReview;
    private String address;
    private String storeNumber;
    private String latitude;
    private String longitude;
    private List<OpeningTime> openingTimes;
    private String universityName;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static  class OpeningTime {
        private OpenDay dayOfWeek;
        private String content;
    }
}
