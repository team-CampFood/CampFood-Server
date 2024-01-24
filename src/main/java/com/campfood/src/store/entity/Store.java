package com.campfood.src.store.entity;

import com.campfood.common.entity.BaseEntity;
import com.campfood.src.university.entity.University;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn()
    private University university;

    @Column(nullable = false)
    private String identificationId;

    @Column(nullable = false)
    private String name;

    private String image;

    private String description;

    @ColumnDefault("0.0")
    private Double naverRate;

    @ColumnDefault("0")
    private int naverVisitedReviewCnt;

    @ColumnDefault("0")
    private int naverBlogReviewCnt;

    @ColumnDefault("0.0")
    private Double campFoodRate;

    @ColumnDefault("0")
    private int campFoodReviewCnt;

    @Column(nullable = false)
    private String address;

    private String storeNumber;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<StoreTag> storeTags;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<StoreOpenTime> storeOpenTimes;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<StoreUniversity> universities;
}
