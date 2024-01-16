package com.campfood.src.review.entity;

import com.campfood.common.entity.BaseEntity;
import com.campfood.src.member.entity.Member;
import com.campfood.src.store.entity.Store;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Review")
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "taste_rate", nullable = false)
    private Double taste_rate;

    @Column(name = "cost_effectiveness_rate", nullable = false)
    private Double cost_effectiveness_rate;

    @Column(name = "service_rate", nullable = false)
    private Double service_rate;

    @Column(name = "clean_rate", nullable = false)
    private Double clean_rate;

}
