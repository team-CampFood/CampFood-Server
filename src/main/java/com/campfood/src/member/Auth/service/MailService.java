package com.campfood.src.member.Auth.service;

import com.campfood.common.error.ErrorCode;
import com.campfood.common.exception.CreateAuthCodeException;
import com.campfood.common.exception.DuplicatedEmailException;
import com.campfood.common.exception.EmailException;
import com.campfood.src.member.entity.Member;
import com.campfood.src.member.redis.EmailAuthCode;
import com.campfood.src.member.redis.EmailAuthCodeRepository;
import com.campfood.src.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender emailSender;
    private final MemberRepository memberRepository;
    private final EmailAuthCodeRepository emailAuthCodeRepository;

    public void sendEmail(String toEmail,
                          String title,
                          String text) {
        SimpleMailMessage emailForm = createEmailForm(toEmail, title, text);
        try {
            emailSender.send(emailForm);
        }catch (RuntimeException e) {
            throw new EmailException("이메일 전송에 실패하였습니다.", ErrorCode.UNABLE_TO_SEND_EMAIL);
        }
    }


    private SimpleMailMessage createEmailForm(String toEmail, String title, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(title);
        message.setText(text);

        return message;
    }


    //이메일로 인증번호 전송
    public void sendCodeToEmail(String toEmail) {
        this.checkDuplicatedEmail(toEmail);
        String title = "CampFood 이메일 인증 번호";
        String authCode = this.createCode();
        sendEmail(toEmail, title, authCode);
        EmailAuthCode emailAuthCode = new EmailAuthCode("AuthCode " + toEmail, authCode);
        emailAuthCodeRepository.save(emailAuthCode);
    }

    private String createCode() {
        int length = 6;
        try {
            Random random = SecureRandom.getInstanceStrong();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < length; i++) {
                builder.append(random.nextInt(10));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new CreateAuthCodeException("이메일 인증코드 생성에 실패하였습니다.", ErrorCode.CREATE_AUTHCODE_FAILED);
        }
    }

    //인증번호 검증
    public boolean verifiedCode(String email, String authCode) {
        this.checkDuplicatedEmail(email);
        Optional<EmailAuthCode> emailAuthCode = emailAuthCodeRepository.findById("AuthCode " + email);
        return authCode.equals(emailAuthCode.get().getEmailAuthCode());
    }

    private void checkDuplicatedEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent()) {
            throw new DuplicatedEmailException("이미 등록된 이메일입니다.",ErrorCode.ALREADY_REGISTERED_EMAIL);
        }
    }
}
