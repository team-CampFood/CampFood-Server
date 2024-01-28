package com.campfood.src.member;

import com.campfood.common.result.ResultCode;

import com.campfood.common.result.ResultResponse;
import com.campfood.src.member.dto.*;
import com.campfood.src.member.response.LoginIdCheckResponse;
import com.campfood.src.member.response.MemberInfoResponse;
import com.campfood.src.member.response.NicknameCheckResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.campfood.common.result.ResultCode.*;

@Tag(name="Member")
@RequiredArgsConstructor
@RestController
@Log4j2
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "닉네임 중복 확인")
    @PostMapping("/nickname/{nickname}")
    public ResponseEntity<NicknameCheckResponse> nicknameDuplicationCheck(@PathVariable(value = "nickname") String nickname){
        boolean isDuplicated = memberService.nicknameDuplicationCheck(nickname);
        ResultCode result = (isDuplicated ? INVALID_NICKNAME : VALID_NICKNAME);
        return ResponseEntity.ok(NicknameCheckResponse.of(result, isDuplicated));
    }

    @Operation(summary = "로그인 id 중복 확인")
    @PostMapping("/login-id/{loginId}")
    public ResponseEntity<LoginIdCheckResponse> loginIdDuplicationCheck(@PathVariable(value = "loginId") String loginId){
        boolean isDuplicated = memberService.loginIdDuplicationCheck(loginId);
        ResultCode result = (isDuplicated ? INVALID_LOGIN_ID : VALID_LOGIN_ID);
        return ResponseEntity.ok(LoginIdCheckResponse.of(result, isDuplicated));
    }

    @Operation(summary = "회원정보 불러오기")
    @GetMapping()
    public ResponseEntity<MemberInfoResponse> getMemberInfo(){
        MemberInfoDto memberInfoDto = memberService.getMemberInfo();
        return ResponseEntity.ok(MemberInfoResponse.of(ResultCode.GET_MEMBER_INFO_SUCCESS, memberInfoDto));
    }

    @Operation(summary = "닉네임 수정하기")
    @PutMapping("/nickname")
    public ResponseEntity<MemberInfoResponse> changeNickname(@RequestBody ChangeNicknameRequestDto memberInfoRequestDto){
        MemberInfoDto memberInfoDto = memberService.changeNickname(memberInfoRequestDto);
        return ResponseEntity.ok(MemberInfoResponse.of(ResultCode.CHANGE_NICKNAME_SUCCESS, memberInfoDto));
    }

    @Operation(summary = "비밀번호 변경(로그인유저용)")
    @PutMapping("/password")
    public ResponseEntity<ResultResponse> changePassword(@RequestBody ChangePasswordRequestDto changePasswordRequestDto){
        memberService.changePassword(changePasswordRequestDto);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.CHANGE_PASSWORD_SUCCESS, "비밀번호 변경에 성공하였습니다."));
    }

    @Operation(summary = "프로필사진 등록 및 수정")
    @PutMapping("/profile")
    public ResponseEntity<ResultResponse> changeProfile(@RequestBody ChangeProfileRequestDto changeProfileRequestDto){
        memberService.changeProfile(changeProfileRequestDto);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.CHANGE_PROFILE_SUCCESS, "프로필사진 변경에 성공하였습니다."));
    }

}
