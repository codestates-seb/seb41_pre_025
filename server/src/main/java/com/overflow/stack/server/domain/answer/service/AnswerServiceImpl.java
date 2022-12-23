package com.overflow.stack.server.domain.answer.service;

import com.overflow.stack.server.domain.answer.entity.Answer;
import com.overflow.stack.server.domain.answer.repository.AnswerRepository;
import org.springframework.stereotype.Service;


@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public Answer createAnswer(Answer answer) {
        return answerRepository.save(answer);
    }
}
