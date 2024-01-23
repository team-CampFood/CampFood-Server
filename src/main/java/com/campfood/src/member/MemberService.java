package com.campfood.src.member;

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
public class MemberService {
    private final MemberRepository memberRepository;

    public boolean nicknameDuplicationCheck(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    public boolean loginIdDuplicationCheck(String loginId) {
        return memberRepository.existsByLoginId(loginId);
    }

}
