package com.overflow.stack.server.domain.question.repository;

import com.overflow.stack.server.domain.question.entity.Question;
import com.overflow.stack.server.domain.question.entity.Question_Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionVoteRepository extends JpaRepository<Question_Vote, Long> {
}
