package com.campfood.src.member.Auth.controller;

import com.campfood.common.result.ResultCode;
import com.campfood.common.result.ResultResponse;
import com.campfood.src.member.Auth.service.AuthService;
import com.campfood.src.member.dto.LoginDto;
import com.campfood.src.member.dto.MemberDeleteDto;
import com.campfood.src.member.dto.SignUpDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(tags="Auth")
@RequiredArgsConstructor
@RestController
@Log4j2
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    @ApiOperation(value = "로그인")
    @PostMapping("/login")
    public void login(@RequestBody LoginDto loginDto){
    }

    @ApiOperation(value = "로그아웃")
    @PostMapping("/logout")
    public ResponseEntity<ResultResponse> logout(HttpServletRequest request, HttpServletResponse response){
        return ResponseEntity.ok(ResultResponse.of(ResultCode.LOGOUT_SUCCESS));
    }


    @ApiOperation(value = "회원가입")
    @PostMapping("/sign-up")
    public ResponseEntity<ResultResponse> signUp(@RequestBody SignUpDto signUpDto){
        authService.signUp(signUpDto);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.SIGNIN_SUCCESS));
    }

    @ApiOperation(value = "엑세스토큰 재발급")
    @PostMapping("/accesstoken")
    @ApiImplicitParam(name = "AuthorizationRefresh", value = "AuthorizationRefresh", required = true, dataType = "String", paramType = "header")
    public ResponseEntity<ResultResponse> generateNewAccessToken(@RequestHeader(value="AuthorizationRefresh") String refreshToken){
        HttpHeaders headers = authService.generateNewAccessToken(refreshToken);
        return ResponseEntity.ok().headers(headers).body(ResultResponse.of(ResultCode.NEW_ACCESS_TOKEN_SUCCESS));
    }

    @ApiOperation(value = "회원탈퇴")
    @DeleteMapping("/member")
    public ResponseEntity<ResultResponse> deleteMember(@RequestBody MemberDeleteDto memberDeleteDto, HttpServletRequest httpServletRequest){
        authService.deleteMember(memberDeleteDto);
        httpServletRequest.getSession().invalidate();
        return ResponseEntity.ok(ResultResponse.of(ResultCode.WITHDRAWAL_SUCCESS));
    }
}
