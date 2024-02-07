package com.campfood.src.member;

import com.campfood.common.error.ErrorCode;
import com.campfood.common.exception.PasswordMismatchException;
import com.campfood.common.exception.RestApiException;
import com.campfood.common.service.EntityLoader;
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
public class MemberService implements EntityLoader<Member, Long> {
    private final MemberRepository memberRepository;
    private final AuthUtils authUtils;
    private final PasswordEncoder passwordEncoder;
    private final ProfileImageRepository profileImageRepository;

    //닉네임 중복 확인
    @Transactional
    public boolean nicknameDuplicationCheck(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    //로그인id 중복 확인
    @Transactional
    public boolean loginIdDuplicationCheck(String loginId) {
        return memberRepository.existsByLoginId(loginId);
    }

    //나의 정보 불러오기
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

    //닉네임 변경
    @Transactional
    public void changeNickname(ChangeNicknameRequestDto memberInfoRequestDto) {
        Member member = authUtils.getMemberByAuthentication();
        member.updateNickname(memberInfoRequestDto.getNickname());
    }

    //비밀번호 변경 (로그인유저용)
    @Transactional
    public void changePassword(ChangePasswordRequestDto changePasswordRequestDto) {
        Member member = authUtils.getMemberByAuthentication();
        if(passwordEncoder.matches(changePasswordRequestDto.getCurPassword(), member.getPassword())) {
            member.updatePassword(passwordEncoder.encode(changePasswordRequestDto.getNewPassword()));
        }
        else throw new PasswordMismatchException("password mismatched", ErrorCode.PASSWORD_MISMATCH);
    }

    //프로필사진 변경 및 수정
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

    // 멤버 프로필 사진 조회
    public String findProfileImage(Member member) {
        Optional<ProfileImage> profileImage = profileImageRepository.findByMember(member);

        return profileImage.map(ProfileImage::getUrl).orElse(null);
    }

    // averageRate 업데이트 함수
    public void updateAverageRate(Member member, double reviewAverageRate, int reviewCnt) {
        double memberAverageRate = member.getAverageRate();

        memberAverageRate = memberAverageRate * (reviewCnt - 1) + reviewAverageRate / reviewCnt;

        String formatted = String.format("%.1f", memberAverageRate);
        member.updateAverageRate(Double.parseDouble(formatted));
    }

    @Override
    public Member loadEntity(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new RestApiException(ErrorCode.MEMBER_NOT_EXIST));
    }
}
