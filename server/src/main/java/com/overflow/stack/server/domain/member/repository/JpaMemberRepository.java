package com.overflow.stack.server.domain.member.repository;

import com.overflow.stack.server.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface JpaMemberRepository extends JpaRepository<Member, Long> , MemberRepository {

    Optional<Member> findByEmail(String email);
}

