package com.campfood.src.member.Auth.controller;

import com.campfood.common.result.ResultCode;
import com.campfood.common.result.ResultResponse;
import com.campfood.src.member.Auth.service.MailService;
import com.campfood.src.member.dto.MemberDeleteDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Email;

import static com.campfood.common.result.ResultCode.EMAIL_VERIFIED_FAILED;
import static com.campfood.common.result.ResultCode.EMAIL_VERIFIED_SUCCESS;

@Tag( name = "Mail")
@RequiredArgsConstructor
@RestController
@Log4j2
@RequestMapping("/mail")
public class MailController {

    private final MailService mailService;

    @Operation(summary = "이메일 인증번호 전송(회원가입용)")
    @PostMapping()
    public ResponseEntity<ResultResponse> sendCodeToEmail(@RequestParam("email") @Valid String email){
        mailService.sendCodeToEmail(email);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.EMAIL_SEND_SUCCESS));
    }

    @Operation(summary = "이메일 인증번호 전송(비밀번호 변경용)")
    @PostMapping("/password")
    public ResponseEntity<ResultResponse> sendCodeToEmailForMember(@RequestParam("email") @Valid String email){
        mailService.sendCodeToEmailForMember(email);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.EMAIL_SEND_SUCCESS));
    }

    @Operation(summary = "인증번호 검증(회원가입용)")
    @PostMapping("/verify")
    public ResponseEntity<ResultResponse> verifiedCode(@RequestParam("email") @Valid String email,
                                                       @RequestParam("code") String authCode){
        boolean result = mailService.verifiedCode(email, authCode);
        ResultCode resultCode = (result ? EMAIL_VERIFIED_SUCCESS : EMAIL_VERIFIED_FAILED);
        return ResponseEntity.ok(ResultResponse.of(resultCode,result));
    }

    @Operation(summary = "인증번호 검증(비밀번호 변경용)")
    @PostMapping("/verify/password")
    public ResponseEntity<ResultResponse> verifiedMember(@RequestParam("email") @Valid String email,
                                                         @RequestParam("code") String authCode){
        boolean result = mailService.verifiedMember(email, authCode);
        ResultCode resultCode = (result ? EMAIL_VERIFIED_SUCCESS : EMAIL_VERIFIED_FAILED);
        return ResponseEntity.ok(ResultResponse.of(resultCode,result));
    }

}
