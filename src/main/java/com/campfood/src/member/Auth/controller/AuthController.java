package com.campfood.src.member.Auth.controller;

import com.campfood.common.result.ResultCode;
import com.campfood.common.result.ResultResponse;
import com.campfood.src.member.Auth.service.AuthService;
import com.campfood.src.member.response.FindIdResponse;
import com.campfood.src.member.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Tag( name = "Auth")
@RequiredArgsConstructor
@RestController
@Log4j2
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    @Operation(summary = "로그인")
    @PostMapping("/login")
    public void login(@RequestBody LoginDto loginDto){
    }

    @Operation(summary = "로그아웃")
    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response){
    }


    @Operation(summary = "회원가입")
    @PostMapping("/sign-up")
    public ResponseEntity<ResultResponse> signUp(@RequestBody SignUpDto signUpDto){
        authService.signUp(signUpDto);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.SIGNIN_SUCCESS));
    }

    @Operation(summary = "엑세스토큰 재발급")
    @PostMapping("/accesstoken")
    @Parameter(name = "AuthorizationRefresh", description = "AuthorizationRefresh", required = true, in = ParameterIn.HEADER)
    public ResponseEntity<ResultResponse> generateNewAccessToken(@RequestHeader(value="AuthorizationRefresh") String refreshToken){
        HttpHeaders headers = authService.generateNewAccessToken(refreshToken);
        return ResponseEntity.ok().headers(headers).body(ResultResponse.of(ResultCode.NEW_ACCESS_TOKEN_SUCCESS));
    }

    @Operation(summary = "회원탈퇴")
    @DeleteMapping("/member")
    public ResponseEntity<ResultResponse> deleteMember(@RequestBody MemberDeleteDto memberDeleteDto, HttpServletRequest httpServletRequest){
        authService.deleteMember(memberDeleteDto);
        httpServletRequest.getSession().invalidate();
        return ResponseEntity.ok(ResultResponse.of(ResultCode.WITHDRAWAL_SUCCESS));
    }

    @Operation(summary = "아이디 찾기")
    @PostMapping("/find-id")
    public ResponseEntity<FindIdResponse> findLoginId(@RequestBody FindIdDto findIdDto){
        String loginId = authService.findLoginId(findIdDto);
        return ResponseEntity.ok(FindIdResponse.of(ResultCode.FIND_LOGINID_SUCCESS, loginId));
    }

    @Operation(summary = "비밀번호찾기(비로그인유저용)")
    @PostMapping("/change-password")
    public ResponseEntity<ResultResponse> changePassword(@RequestBody ChangePasswordForUnauthenticatedRequestDto changePasswordRequest){
        authService.changePassword(changePasswordRequest);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.CHANGE_PASSWORD_SUCCESS,"비밀번호 변경에 성공하였습니다."));
    }


}
