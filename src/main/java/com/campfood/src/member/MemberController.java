package com.campfood.src.member;

import com.campfood.common.result.ResultCode;

import com.campfood.src.member.dto.*;
import com.campfood.src.member.response.LoginIdCheckResponse;
import com.campfood.src.member.response.MemberInfoResponse;
import com.campfood.src.member.response.NicknameCheckResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.campfood.common.result.ResultCode.*;

@Api(tags="Member")
@RequiredArgsConstructor
@RestController
@Log4j2
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @ApiOperation(value = "닉네임 중복 확인")
    @PostMapping("/nickname/{nickname}")
    public ResponseEntity<NicknameCheckResponse> nicknameDuplicationCheck(@PathVariable(value = "nickname") String nickname){
        boolean isDuplicated = memberService.nicknameDuplicationCheck(nickname);
        ResultCode result = (isDuplicated ? INVALID_NICKNAME : VALID_NICKNAME);
        return ResponseEntity.ok(NicknameCheckResponse.of(result, isDuplicated));
    }

    @ApiOperation(value = "로그인 id 중복 확인")
    @PostMapping("/login-id/{loginId}")
    public ResponseEntity<LoginIdCheckResponse> loginIdDuplicationCheck(@PathVariable(value = "loginId") String loginId){
        boolean isDuplicated = memberService.loginIdDuplicationCheck(loginId);
        ResultCode result = (isDuplicated ? INVALID_LOGIN_ID : VALID_LOGIN_ID);
        return ResponseEntity.ok(LoginIdCheckResponse.of(result, isDuplicated));
    }

    @ApiOperation(value = "회원정보 불러오기")
    @GetMapping()
    public ResponseEntity<MemberInfoResponse> getMemberInfo(){
        MemberInfoDto memberInfoDto = memberService.getMemberInfo();
        return ResponseEntity.ok(MemberInfoResponse.of(ResultCode.GET_MEMBER_INFO_SUCCESS, memberInfoDto));
    }

    @ApiOperation(value = "회원정보 수정하기")
    @PutMapping()
    public ResponseEntity<MemberInfoResponse> putMemberInfo(@RequestBody MemberInfoRequestDto memberInfoRequestDto){
        MemberInfoDto memberInfoDto = memberService.putMemberInfo(memberInfoRequestDto);
        return ResponseEntity.ok(MemberInfoResponse.of(ResultCode.GET_MEMBER_INFO_SUCCESS, memberInfoDto));
    }

}
