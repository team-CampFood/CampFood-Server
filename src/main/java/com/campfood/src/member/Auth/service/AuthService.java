package com.campfood.src.member.Auth.service;

import com.campfood.common.error.ErrorCode;
import com.campfood.common.exception.MemberNotExistException;
import com.campfood.common.exception.PasswordMismatchException;
import com.campfood.common.exception.RefreshTokenExpiredException;
import com.campfood.src.member.Auth.AuthConstants;
import com.campfood.src.member.Auth.AuthUtils;
import com.campfood.src.member.Auth.TokenProvider;
import com.campfood.src.member.dto.MemberDeleteDto;
import com.campfood.src.member.dto.SignUpDto;
import com.campfood.src.member.entity.Member;
import com.campfood.src.member.entity.MemberRole;
import com.campfood.src.member.redis.RefreshToken;
import com.campfood.src.member.redis.RefreshTokenRepository;
import com.campfood.src.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthUtils authUtils;
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

    @Transactional
    public HttpHeaders generateNewAccessToken(String refreshToken) {
        Optional<RefreshToken> getRefreshToken= refreshTokenRepository.findById(refreshToken);
        if(getRefreshToken.isPresent()){
            HttpHeaders headers = new HttpHeaders();
            Member member = memberRepository.findById(getRefreshToken.get().getMemberId())
                    .orElseThrow(()-> new MemberNotExistException("member not exist", ErrorCode.MEMBER_NOT_EXIST));
            String newAccessToken = TokenProvider.generateJwtToken(member);
            headers.add(AuthConstants.AUTH_HEADER_ACCESS, newAccessToken);
            return headers;
        }
        else{
            throw new RefreshTokenExpiredException("refresh token expired",ErrorCode.REFRESH_TOKEN_EXPIRED);
        }

    }
    public void deleteMember(MemberDeleteDto memberDeleteDto) {
        Member member = authUtils.getMemberByAuthentication();
        if(passwordEncoder.matches(memberDeleteDto.getPassword(), member.getPassword())){

            //이미지, 댓글 등 삭제
            SecurityContextHolder.getContext().setAuthentication(null);
            SecurityContextHolder.clearContext();
        }
        else throw new PasswordMismatchException("password mismatched", ErrorCode.PASSWORD_MISMATCH);
    }
}
