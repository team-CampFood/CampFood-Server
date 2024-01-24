package com.campfood.src.member.Auth;

import com.campfood.src.member.entity.Member;
import com.campfood.src.member.entity.MemberDetails;
import com.campfood.src.member.redis.RefreshToken;
import com.campfood.src.member.redis.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Log4j2
@RequiredArgsConstructor
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
                                        final Authentication authentication) throws IOException {
        final Member member = ((MemberDetails) authentication.getPrincipal()).getMember();
        final String accessToken = TokenProvider.generateJwtToken(member);
        final RefreshToken refreshToken = TokenProvider.generateJwtRefreshToken(member);
        refreshTokenRepository.save(refreshToken);
        response.addHeader(AuthConstants.AUTH_HEADER_ACCESS, accessToken);
        response.addHeader(AuthConstants.AUTH_HEADER_REFRESH, refreshToken.getRefreshToken());
    }
}
