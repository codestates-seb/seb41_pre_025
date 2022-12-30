package com.overflow.stack.server.domain.question.repository;

import com.overflow.stack.server.domain.member.entity.Member;
import com.overflow.stack.server.domain.question.entity.Question;
import com.overflow.stack.server.domain.question.entity.Question_Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionVoteRepository extends JpaRepository<Question_Vote, Long> {
    Optional<Question_Vote> findByMemberAndQuestion(Member member, Question question);
    void deleteAllByQuestion(Question question);
}
