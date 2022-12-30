package com.overflow.stack.server.domain.answer.repository;

import com.overflow.stack.server.domain.answer.entity.Answer;
import com.overflow.stack.server.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findAllByMember(Member member);
}
