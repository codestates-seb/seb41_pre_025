package com.overflow.stack.server.domain.answer.repository;

import com.overflow.stack.server.domain.answer.entity.Answer;
import com.overflow.stack.server.domain.answer.entity.Answer_Vote;
import com.overflow.stack.server.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnswerVoteRepository extends JpaRepository<Answer_Vote, Long> {
    Optional<Answer_Vote> findByMemberAndAnswer(Member member,Answer answer);
}
