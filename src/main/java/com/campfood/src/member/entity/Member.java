package com.campfood.src.member.entity;

import com.campfood.common.entity.BaseEntity;
import com.campfood.src.university.entity.University;
import javax.persistence.*;

import lombok.*;

import java.io.Serializable;

@ToString
@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Member")
public class Member extends Common implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    private Long id;
/*
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id", nullable = false)
    private University university;
*/
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "loginId", nullable = false)
    private String loginId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

}