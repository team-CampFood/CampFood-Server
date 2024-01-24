package com.campfood.src.member;

import com.campfood.common.result.ResultCode;
import com.campfood.src.member.response.LoginIdCheckResponse;
import com.campfood.src.member.response.NicknameCheckResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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




}
