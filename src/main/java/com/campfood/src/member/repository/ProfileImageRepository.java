package com.campfood.src.member.repository;

import com.campfood.src.member.entity.Member;
import com.campfood.src.member.entity.ProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileImageRepository extends JpaRepository<ProfileImage,Long> {
    Optional<ProfileImage> findByMember(Member member);
}
