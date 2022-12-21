package com.overflow.stack.server.domain.question.service;

import com.overflow.stack.server.domain.question.entity.Question;
import com.overflow.stack.server.domain.question.repository.MockQuestionRepository;
import com.overflow.stack.server.domain.question.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;

public class MockQuestionService implements QuestionService {

   private final QuestionRepository mockQuestionRepository;

    public MockQuestionService(QuestionRepository mockQuestionRepository) {
        this.mockQuestionRepository = mockQuestionRepository;
    }


    @Override
    public Question createQuestion(Question question) {
        return null;
    }

    @Override
    public Question updateQuestion(Question question) {
        return null;
    }

    @Override
    public Question findQuestion(long questionId) {
        return null;
    }

    @Override
    public List<Question> findQuestions() {
        return null;
    }

    @Override
    public void deleteQuestion(long questionId) {
    }

    @Override
    public Question findVerifiedQuestion(long questionId) {
        return null;
    }
}
