package com.campfood.src.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class SignUpDto {
    @NotBlank(message = "이메일은 필수입력값입니다.")
    private String email;
    @NotBlank(message = "로그인id는 필수입력값입니다.")
    private String loginId;
    @NotBlank(message = "비밀번호는 필수입력값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{6,16}", message = "비밀번호는 8~16자 대문자, 소문자, 숫자, 특수문자를 허용합니다.")
    private String password;
    @NotBlank(message = "닉네임은 필수입력값입니다.")
    private String nickname;
    @NotBlank(message = "대학교명은 필수입력값입니다.")
    private String universityName;
}
