package com.campfood.src.member;

import com.campfood.common.error.ErrorCode;
import com.campfood.common.exception.ExampleException;
import com.campfood.src.member.entity.MemberDetails;
import com.campfood.src.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class MemberDetailsServiceImpl implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public MemberDetails loadUserByUsername(String loginId) {
        return memberRepository.findByLoginId(loginId)
                .map(m -> new MemberDetails(m, Collections.singleton(new SimpleGrantedAuthority(m.getRole().getValue()))))
                .orElseThrow(() -> new ExampleException("member not exist", ErrorCode.MEMBER_NOT_EXIST));
    }
}
