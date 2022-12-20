package com.overflow.stack.server.domain.question.service;

import com.overflow.stack.server.domain.question.repository.QuestionRepository;

public class MockQuestionService implements QuestionService {

   private final QuestionRepository questionRepository;

    public MockQuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }
}
