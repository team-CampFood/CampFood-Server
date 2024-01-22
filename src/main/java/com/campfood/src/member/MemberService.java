package com.campfood.src.member;

import com.campfood.common.error.ErrorCode;
import com.campfood.common.exception.MemberNotExistException;
import com.campfood.common.exception.RefreshTokenExpiredException;
import com.campfood.src.member.Auth.AuthConstants;
import com.campfood.src.member.Auth.TokenProvider;
import com.campfood.src.member.dto.SignUpDto;
import com.campfood.src.member.entity.Member;
import com.campfood.src.member.entity.MemberRole;
import com.campfood.src.member.redis.RefreshToken;
import com.campfood.src.member.redis.RefreshTokenRepository;
import com.campfood.src.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
}
