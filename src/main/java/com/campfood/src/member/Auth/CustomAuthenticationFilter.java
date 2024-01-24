package com.campfood.src.member.Auth;

import com.campfood.common.error.ErrorCode;
import com.campfood.common.exception.InputNotFoundException;
import com.campfood.src.member.entity.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    public CustomAuthenticationFilter(final AuthenticationManager authenticationManager){
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response) throws AuthenticationException {
        final UsernamePasswordAuthenticationToken authRequest;
        try{
            final Member member = new ObjectMapper().readValue(request.getInputStream(), Member.class);
            authRequest = new UsernamePasswordAuthenticationToken(member.getLoginId(), member.getPassword());
        } catch (IOException exception){
            throw new InputNotFoundException("input not found error", ErrorCode.INPUT_NOT_FOUND);
        }
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

}
