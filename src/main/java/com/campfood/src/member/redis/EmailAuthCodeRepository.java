package com.campfood.src.member.redis;

import org.springframework.data.repository.CrudRepository;

public interface EmailAuthCodeRepository extends CrudRepository<EmailAuthCode, String> {
}
