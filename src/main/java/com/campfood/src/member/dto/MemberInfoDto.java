package com.campfood.src.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberInfoDto {
    private String nickname;
    private String loginId;
    private String email;
    private String university;
    private int myReviewCount;
}
