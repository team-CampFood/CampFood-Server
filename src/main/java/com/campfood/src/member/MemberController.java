package com.campfood.src.member;

import com.campfood.common.result.ResultCode;
import com.campfood.common.result.ResultResponse;
import com.campfood.src.member.dto.*;
import com.campfood.src.member.response.MemberInfoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="Member")
@RequiredArgsConstructor
@RestController
@Log4j2
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "닉네임 중복 확인")
    @PostMapping("/nickname")
    public ResponseEntity<ResultResponse> nicknameDuplicationCheck(@RequestBody CheckDuplicateNicknameDto checkDuplicateNicknameDto){
        boolean result = memberService.nicknameDuplicationCheck(checkDuplicateNicknameDto);
        ResultCode resultCode = (result ? ResultCode.INVALID_NICKNAME : ResultCode.VALID_NICKNAME);
        return ResponseEntity.ok(ResultResponse.of(resultCode, !result));
    }

    @Operation(summary = "로그인 id 중복 확인")
    @PostMapping("/loginid")
    public ResponseEntity<ResultResponse> loginIdDuplicationCheck(@RequestBody CheckDuplicateLoginidDto checkDuplicateLoginidDto){
        boolean result = memberService.loginIdDuplicationCheck(checkDuplicateLoginidDto);
        ResultCode resultCode = (result ? ResultCode.INVALID_LOGIN_ID : ResultCode.VALID_LOGIN_ID);
        return ResponseEntity.ok(ResultResponse.of(resultCode, !result));
    }

    @Operation(summary = "회원정보 불러오기")
    @GetMapping()
    public ResponseEntity<MemberInfoResponse> getMemberInfo(){
        MemberInfoDto memberInfoDto = memberService.getMemberInfo();
        return ResponseEntity.ok(MemberInfoResponse.of(ResultCode.GET_MEMBER_INFO_SUCCESS, memberInfoDto));
    }

    @Operation(summary = "닉네임 수정하기")
    @PatchMapping("/nickname")
    public ResponseEntity<ResultResponse> changeNickname(@RequestBody ChangeNicknameRequestDto memberInfoRequestDto){
        memberService.changeNickname(memberInfoRequestDto);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.CHANGE_NICKNAME_SUCCESS));
    }

    @Operation(summary = "비밀번호 변경(로그인유저용)")
    @PatchMapping("/password")
    public ResponseEntity<ResultResponse> changePassword(@RequestBody ChangePasswordRequestDto changePasswordRequestDto){
        memberService.changePassword(changePasswordRequestDto);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.CHANGE_PASSWORD_SUCCESS));
    }

    @Operation(summary = "프로필사진 등록 및 수정")
    @PatchMapping("/profile")
    public ResponseEntity<ResultResponse> changeProfile(@RequestBody ChangeProfileRequestDto changeProfileRequestDto){
        memberService.changeProfile(changeProfileRequestDto);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.CHANGE_PROFILE_SUCCESS));
    }

}
