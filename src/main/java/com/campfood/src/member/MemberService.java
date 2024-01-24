package com.campfood.src.member;

import com.campfood.src.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.campfood.src.member.dto.MemberInfoDto;
import com.campfood.src.member.dto.MemberInfoRequestDto;
import com.campfood.src.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public MemberInfoDto getMemberInfo() {
        //get로직
        return new MemberInfoDto();
    }

    @Transactional
    public MemberInfoDto putMemberInfo(MemberInfoRequestDto memberInfoRequestDto) {
        //수정로직
        return new MemberInfoDto();
    }
}
