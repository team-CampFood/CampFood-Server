package com.campfood.src.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class FindIdDto {
    @NotBlank(message = "이메일은 필수입력값입니다.")
    private String email;
}
