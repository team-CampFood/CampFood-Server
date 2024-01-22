package com.campfood.src.member;

import com.campfood.common.result.ResultCode;
import com.campfood.common.result.ResultResponse;
import com.campfood.src.member.dto.LoginDto;
import com.campfood.src.member.dto.SignUpDto;
import com.campfood.src.store.dto.StoreInfoDTO;
import com.campfood.src.store.response.StoreInfoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags="User")
@RequiredArgsConstructor
@RestController
@Log4j2
@RequestMapping("/auth")
public class MemberController {
    private final MemberService memberService;

    @ApiOperation(value = "로그인")
    @PostMapping("/login")
    public ResponseEntity<ResultResponse> login(@RequestBody LoginDto loginDto){
        System.out.println("success");
        return ResponseEntity.ok(ResultResponse.of(ResultCode.LOGIN_SUCCESS));
    }

    @ApiOperation(value = "회원가입")
    @PostMapping("/sign-up")
    public ResponseEntity<ResultResponse> signUp(@RequestBody SignUpDto signUpDto){
        memberService.signUp(signUpDto);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.SIGNIN_SUCCESS));
    }

    @ApiOperation(value = "엑세스토큰 재발급")
    @PostMapping("/newAccessToken")
    @ApiImplicitParam(name = "AuthorizationRefresh", value = "AuthorizationRefresh", required = true, dataType = "String", paramType = "header")
    public ResponseEntity<ResultResponse> generateNewAccessToken(@RequestHeader(value="AuthorizationRefresh") String refreshToken){
        HttpHeaders headers = memberService.generateNewAccessToken(refreshToken);
        return ResponseEntity.ok().headers(headers).body(ResultResponse.of(ResultCode.NEW_ACCESS_TOKEN_SUCCESS));
    }

}
