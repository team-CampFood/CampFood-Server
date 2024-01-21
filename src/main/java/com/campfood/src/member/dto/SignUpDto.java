package com.campfood.src.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpDto {
    private String email;
    private String loginId;
    private String password;
    private String nickname;
}
