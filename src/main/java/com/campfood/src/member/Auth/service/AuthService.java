package com.campfood.src.member.Auth.service;

import com.campfood.common.error.ErrorCode;
import com.campfood.common.exception.AlreadyExistMemberException;
import com.campfood.common.exception.MemberNotExistException;
import com.campfood.common.exception.PasswordMismatchException;
import com.campfood.common.exception.RefreshTokenExpiredException;
import com.campfood.src.member.Auth.AuthConstants;
import com.campfood.src.member.Auth.AuthUtils;
import com.campfood.src.member.Auth.TokenProvider;
import com.campfood.src.member.dto.ChangePasswordForUnauthenticatedRequestDto;
import com.campfood.src.member.dto.FindIdDto;
import com.campfood.src.member.dto.MemberDeleteDto;
import com.campfood.src.member.dto.SignUpDto;
import com.campfood.src.member.entity.Gender;
import com.campfood.src.member.entity.Member;
import com.campfood.src.member.entity.MemberRole;
import com.campfood.src.member.entity.ProfileImage;
import com.campfood.src.member.redis.RefreshToken;
import com.campfood.src.member.redis.RefreshTokenRepository;
import com.campfood.src.member.repository.MemberRepository;
import com.campfood.src.member.repository.ProfileImageRepository;
import com.campfood.src.university.entity.University;
import com.campfood.src.university.repository.UniversityRepository;
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
    private final ProfileImageRepository profileImageRepository;
    private final UniversityRepository universityRepository;

    //회원가입
    @Transactional
    public void signUp(SignUpDto signUpDto){
        if(memberRepository.existsByEmail(signUpDto.getEmail())){
            throw new AlreadyExistMemberException("이미 존재하는 유저입니다.", ErrorCode.ALREADY_EXIST_MEMBER);
        }

        final Member member= Member.builder()
                .email(signUpDto.getEmail())
                .loginId(signUpDto.getLoginId())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .nickname(signUpDto.getNickname())
                .role(MemberRole.ROLE_USER)
                .university(universityRepository.findByName(signUpDto.getUniversityName()).get())
                .gender(signUpDto.getGender().equals("MALE")? Gender.MALE: Gender.FEMALE)
                .build();
        memberRepository.save(member);
    }

    //엑세스토큰 재발급
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

    //회원탈퇴
    @Transactional
    public void deleteMember(MemberDeleteDto memberDeleteDto) {
        Member member = authUtils.getMemberByAuthentication();
        if(passwordEncoder.matches(memberDeleteDto.getPassword(), member.getPassword())){

            Optional<ProfileImage> profileImage = profileImageRepository.findByMember(member);
            if(profileImage.isPresent()){
                profileImageRepository.deleteById(profileImage.get().getId());
            }
            member.withdrawal();
            SecurityContextHolder.getContext().setAuthentication(null);
            SecurityContextHolder.clearContext();
        }
        else throw new PasswordMismatchException("password mismatched", ErrorCode.PASSWORD_MISMATCH);
    }

    //아이디찾기
    @Transactional
    public String findLoginId(FindIdDto findIdDto) {
        Member member = memberRepository.findByEmail(findIdDto.getEmail())
                .orElseThrow(() -> new MemberNotExistException("member not exist", ErrorCode.MEMBER_NOT_EXIST));
        return member.getLoginId();
    }


    //비밀번호변경(비로그인용)
    @Transactional
    public void changePassword(ChangePasswordForUnauthenticatedRequestDto changePasswordRequest) {
        String email = changePasswordRequest.getEmail();
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberNotExistException("member not exist", ErrorCode.MEMBER_NOT_EXIST));
        String password = passwordEncoder.encode(changePasswordRequest.getPassword());
        member.updatePassword(password);
    }
}
