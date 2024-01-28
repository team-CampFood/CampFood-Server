package com.campfood.src.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class ChangePasswordRequestDto {

    private String curPassword;

    @NotBlank(message = "비밀번호는 필수입력값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{6,16}", message = "비밀번호는 8~16자 대문자, 소문자, 숫자, 특수문자를 허용합니다.")
    private String newPassword;
}
