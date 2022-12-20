package com.overflow.stack.server.domain.answer.service;

import com.overflow.stack.server.domain.answer.repository.AnswerRepository;

public class MockAnswerService implements AnswerService {
    private final AnswerRepository answerRepository;

    public MockAnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }
}
