package com.overflow.stack.server.domain.question.service;

import com.overflow.stack.server.domain.question.entity.Question;

import java.util.List;

public interface QuestionService {
    Question createQuestion(Question question);
    Question updateQuestion(Question question);
    Question findQuestion(long questionId);
    List<Question> findQuestions();
    void deleteQuestion(long questionId);

    Question findVerifiedQuestion(long questionId);
}
