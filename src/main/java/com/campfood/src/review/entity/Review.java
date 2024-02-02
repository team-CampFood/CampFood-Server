package com.campfood.src.review.entity;

import com.campfood.common.entity.BaseEntity;
import com.campfood.src.member.entity.Member;
import com.campfood.src.store.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Where;

import javax.persistence.*;


@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "is_deleted = false")
@DynamicInsert
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Store store;

    @Column(nullable = false)
    private String content;

    @ColumnDefault("0")
    private Double taste_rate;

    @ColumnDefault("0")
    private Double cost_effectiveness_rate;

    @ColumnDefault("0")
    private Double service_rate;

    @ColumnDefault("0")
    private Double clean_rate;

}
