package com.campfood.src.store.entity;

import com.campfood.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.DayOfWeek;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StoreOpenTime extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Store store;

    @Column(nullable = false)
    private DayOfWeek dayOfWeek;

    private String openTime;

    private String breakTime;

    private String lastOrder;

}
