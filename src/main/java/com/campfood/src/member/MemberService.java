package com.campfood.src.member;

import com.campfood.src.member.dto.SignUpDto;
import com.campfood.src.member.entity.Member;
import com.campfood.src.member.entity.MemberRole;
import com.campfood.src.member.redis.RefreshToken;
import com.campfood.src.member.redis.RefreshTokenRepository;
import com.campfood.src.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void signUp(SignUpDto signUpDto){
        final Member member= Member.builder()
                .email(signUpDto.getEmail())
                .loginId(signUpDto.getLoginId())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .nickname(signUpDto.getNickname())
                .role(MemberRole.ROLE_USER)
                .build();
        memberRepository.save(member);
    }

}
