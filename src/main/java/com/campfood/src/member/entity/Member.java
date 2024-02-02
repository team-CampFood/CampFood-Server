package com.campfood.src.member.entity;

import com.campfood.src.university.entity.University;
import lombok.*;

import javax.persistence.*;
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
    @ManyToOne
    @JoinColumn(name = "university_id", nullable = false)
    private University university;
*/

    @Column(name = "email")
    private String email;

    @Column(name = "loginId")
    private String loginId;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    public void updatePassword(String password){
        this.password = password;
    }

    public void updateNickname(String nickname){
        this.nickname = nickname;
    }

    public void withdrawal(){
        //this.university = null;
        this.email = null;
        this.loginId = null;
        this.password = null;
        this.nickname = null;
    }

}