package com.campfood.src.member.entity;

import com.campfood.common.entity.BaseEntity;
import com.campfood.src.university.entity.University;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@ToString
@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "is_enable = true")
@Table(name = "Member")
@DynamicInsert

public class Member extends Common implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "university_id", nullable = false)
    private University university;

    @Column(name = "email")
    private String email;

    @Column(name = "loginId")
    private String loginId;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ColumnDefault("0")
    private double averageRate;

    @Enumerated(EnumType.STRING)
    private MemberRole role;



    public void updatePassword(String password){
        this.password = password;
    }

    public void updateNickname(String nickname){
        this.nickname = nickname;
    }

    public void withdrawal(){
        this.setIsEnable(false);
    }

    public void updateAverageRate(double averageRate) {
        this.averageRate = averageRate;
    }
}