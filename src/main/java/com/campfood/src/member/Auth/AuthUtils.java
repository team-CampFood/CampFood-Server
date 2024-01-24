package com.campfood.src.member.Auth;

import com.campfood.common.error.ErrorCode;
import com.campfood.common.exception.MemberNotExistException;
import com.campfood.src.member.entity.Member;
import com.campfood.src.member.entity.MemberDetails;
import com.campfood.src.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthUtils {
    private final MemberRepository memberRepository;
    public Member getMemberByAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return memberRepository.findByLoginId(authentication.getName())
               .orElseThrow(()-> new MemberNotExistException("login member not exist", ErrorCode.MEMBER_NOT_EXIST));
    }
}
