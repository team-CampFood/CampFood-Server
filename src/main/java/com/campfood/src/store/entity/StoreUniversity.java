package com.campfood.src.store.entity;

import com.campfood.common.entity.BaseEntity;
import com.campfood.src.university.entity.University;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "is_deleted = false")
public class StoreUniversity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn()
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn()
    private University university;

    private int distance;
}
