package com.campfood.src.member.redis;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RequiredArgsConstructor
@RedisHash(value="emailauthcode", timeToLive = 60*5)
public class EmailAuthCode {
    @Id
    private final String Id;

    private final String emailAuthCode;
}
