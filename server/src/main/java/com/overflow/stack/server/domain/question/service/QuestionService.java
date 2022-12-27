package com.overflow.stack.server.domain.question.service;

import com.overflow.stack.server.domain.member.entity.Member;
import com.overflow.stack.server.domain.question.entity.Question;
import com.overflow.stack.server.domain.question.entity.Question_Vote;

import java.util.List;

public interface QuestionService {
    Question createQuestion(Question question, String userName);
    Question updateQuestion(Question question, String userName);
    Question voteQuestion(long questionId, String userName, boolean voteUp);
    Question findQuestion(long questionId);
    List<Question> findQuestions();
    void deleteQuestion(long questionId);

    Question findVerifiedQuestion(long questionId);
}
