package com.campfood.src.member.Auth;

import com.campfood.src.member.entity.Member;
import com.campfood.src.member.redis.RefreshToken;
import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.*;

@Slf4j
@Log4j2
@Component
public class TokenProvider {
    private static String secretKey;

    @Value("${jwt.token.key}")
    public void setSecretKey(String secretKey) {
        TokenProvider.secretKey = secretKey;
    }

    public static String generateJwtToken(Member member) {
        JwtBuilder builder = Jwts.builder()
                .setSubject(member.getLoginId())
                .setHeader(createHeader())
                .setClaims(createClaims(member))
                .setExpiration(setExpireTime())
                .signWith(SignatureAlgorithm.HS256, createSigningKey());
        return builder.compact();
    }

    public static RefreshToken generateJwtRefreshToken(Member member) {
        return new RefreshToken(UUID.randomUUID().toString(), member.getId());
    }

    private static Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        header.put("regDate", System.currentTimeMillis());
        return header;
    }

    private static Date setExpireTime() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR, 2);
        return c.getTime();
    }

    private static Map<String, Object> createClaims(Member member) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("loginId", member.getLoginId());
//        claims.put("role", member.getRole());
        return claims;
    }

    private static Key createSigningKey() {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    public static Claims getClaimsFormToken(String token) {
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                .parseClaimsJws(token).getBody();
    }


    public static String getLoginIdFromToken(String token) {
        Claims claims = getClaimsFormToken(token);
        return (String) claims.get("loginId");
    }
}
