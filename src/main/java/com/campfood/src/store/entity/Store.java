package com.campfood.src.store.entity;

import com.campfood.common.entity.BaseEntity;
import com.campfood.src.university.entity.University;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Store")
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id", nullable = false)
    private University university;

    @Column(name = "identification_id", nullable = false)
    private String identificationId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "naver_rate", nullable = false)
    private Double naverRate;

    @Column(name = "visitedReview_cnt", nullable = false)
    private int visitedReviewCnt;

    @Column(name = "blogReview_cnt", nullable = false)
    private int blogReviewCnt;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "storeNumber")
    private String storeNumber;

    @Column(name = "storeTag")
    private  StoreTag storeTag;

}
