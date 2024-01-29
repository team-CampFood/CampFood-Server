package com.campfood.src.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class ChangeProfileRequestDto {
    @NotBlank(message = "사진은 필수입력값입니다.")
    private String url;
}
