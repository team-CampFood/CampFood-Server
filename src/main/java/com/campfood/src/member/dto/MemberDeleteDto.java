package com.campfood.src.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class MemberDeleteDto {
    @NotBlank(message = "비밀번호는 필수입력값입니다.")
    String password;
}
