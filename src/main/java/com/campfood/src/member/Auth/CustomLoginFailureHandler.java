package com.campfood.src.member.Auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomLoginFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        String exceptionMessage = "";

        if (exception instanceof AuthenticationServiceException) {
            exceptionMessage = "존재하지 않는 사용자입니다.";

        } else if(exception instanceof BadCredentialsException) {
            exceptionMessage = "아이디 또는 비밀번호가 틀립니다.";

        } else if(exception instanceof LockedException) {
            exceptionMessage = "잠긴 계정입니다.";

        } else if(exception instanceof DisabledException) {
            exceptionMessage = "비활성화된 계정입니다.";

        } else if(exception instanceof CredentialsExpiredException) {
            exceptionMessage = "비밀번호가 만료되었습니다";
        }

        Map<String, String> map = new HashMap<>();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        map.put("status", "404");
        map.put("code", "Login fail");
        map.put("message", exceptionMessage);
        response.getWriter().write(objectMapper.writeValueAsString(map));
        response.setStatus(404);

    }

}
