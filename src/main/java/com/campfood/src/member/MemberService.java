package com.campfood.src.member;

import com.campfood.common.error.ErrorCode;
import com.campfood.common.exception.DuplicatedLoginIdException;
import com.campfood.common.exception.PasswordMismatchException;
import com.campfood.src.member.Auth.AuthUtils;
import com.campfood.src.member.dto.ChangePasswordRequestDto;
import com.campfood.src.member.dto.ChangeProfileRequestDto;
import com.campfood.src.member.entity.Member;
import com.campfood.src.member.entity.ProfileImage;
import com.campfood.src.member.repository.MemberRepository;
import com.campfood.src.member.repository.ProfileImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.campfood.src.member.dto.MemberInfoDto;
import com.campfood.src.member.dto.ChangeNicknameRequestDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final AuthUtils authUtils;
    private final PasswordEncoder passwordEncoder;
    private final ProfileImageRepository profileImageRepository;


    @Transactional
    public void nicknameDuplicationCheck(String nickname) {
        if(memberRepository.existsByNickname(nickname)) {
            throw new DuplicatedLoginIdException("이미 존재하는 닉네임입니다.", ErrorCode.INVALID_NICKNAME);
        }
    }

    @Transactional
    public void loginIdDuplicationCheck(String loginId) {
        if(memberRepository.existsByLoginId(loginId)) {
            throw new DuplicatedLoginIdException("이미 존재하는 로그인id입니다.", ErrorCode.INVALID_LOGIN_ID);
        }
    }

    @Transactional(readOnly = true)
    public MemberInfoDto getMemberInfo() {
        Member member = authUtils.getMemberByAuthentication();
        MemberInfoDto memberInfoDto =
                MemberInfoDto.builder().
                        nickname(member.getNickname()).
                        loginId(member.getLoginId()).
                        email(member.getEmail()).
                       // university(member.getUniversity().getName()).
                        myReviewCount(1).build(); // review repository 생성이후 작성
        return memberInfoDto;
    }

    @Transactional
    public void changeNickname(ChangeNicknameRequestDto memberInfoRequestDto) {
        Member member = authUtils.getMemberByAuthentication();
        member.updateNickname(memberInfoRequestDto.getNickname());
    }


    @Transactional
    public void changePassword(ChangePasswordRequestDto changePasswordRequestDto) {
        Member member = authUtils.getMemberByAuthentication();
        if(passwordEncoder.matches(changePasswordRequestDto.getCurPassword(), member.getPassword())) {
            member.updatePassword(passwordEncoder.encode(changePasswordRequestDto.getNewPassword()));
        }
        else throw new PasswordMismatchException("password mismatched", ErrorCode.PASSWORD_MISMATCH);
    }

    @Transactional
    public void changeProfile(ChangeProfileRequestDto changeProfileRequestDto) {
        Member member = authUtils.getMemberByAuthentication();
        Optional<ProfileImage> curProfileImage = profileImageRepository.findByMember(member);
        if(curProfileImage.isPresent()) {
            curProfileImage.get().updateUrl(changeProfileRequestDto.getUrl());
        }
        else {
            ProfileImage profileImage = ProfileImage.builder()
                    .member(member)
                    .url(changeProfileRequestDto.getUrl())
                    .build();
            profileImageRepository.save(profileImage);
        }
    }
}
