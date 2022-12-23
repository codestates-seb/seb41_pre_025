package com.overflow.stack.server.domain.answer.repository;

import com.overflow.stack.server.domain.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
