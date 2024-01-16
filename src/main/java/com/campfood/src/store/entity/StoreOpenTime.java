package com.campfood.src.store.entity;

import com.campfood.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "StoreOpenTime")
public class StoreOpenTime extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_open_time_id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "dayOfWeek" , nullable = false)
    private DayOfWeek dayOfWeek;

    @Column(name = "openTime" , nullable = false)
    private String openTime;

    @Column(name = "breakTime" , nullable = false)
    private String breakTime;

    @Column(name = "lastOrder" , nullable = false)
    private String lastOrder;

}
