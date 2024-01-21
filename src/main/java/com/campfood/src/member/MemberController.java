package com.campfood.src.member;

import com.campfood.common.result.ResultCode;
import com.campfood.common.result.ResultResponse;
import com.campfood.src.member.dto.LoginDto;
import com.campfood.src.member.dto.SignUpDto;
import com.campfood.src.store.dto.StoreInfoDTO;
import com.campfood.src.store.response.StoreInfoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="User")
@RequiredArgsConstructor
@RestController
@Log4j2
public class MemberController {
    private final MemberService memberService;

    @ApiOperation(value = "로그인")
    @PostMapping("/login")
    public ResponseEntity<ResultResponse> login(@RequestBody LoginDto loginDto){
        System.out.println("success");
        return ResponseEntity.ok(ResultResponse.of(ResultCode.Example));
    }

    @ApiOperation(value = "회원가입")
    @PostMapping("/sign-up")
    public ResponseEntity<ResultResponse> signUp(@RequestBody SignUpDto signUpDto){
        memberService.signUp(signUpDto);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.Example));
    }

}
