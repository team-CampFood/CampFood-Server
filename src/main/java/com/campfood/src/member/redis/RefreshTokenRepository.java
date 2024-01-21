package com.campfood.src.member.redis;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
