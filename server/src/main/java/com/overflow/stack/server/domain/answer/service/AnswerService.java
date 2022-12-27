package com.overflow.stack.server.domain.answer.service;

import com.overflow.stack.server.domain.answer.entity.Answer;

import java.util.List;

public interface AnswerService {
    Answer createAnswer(Answer answer, String userName, long questionId);

    Answer updateAnswer(Answer answer, String userName);

    Answer findAnswer(long answerId);

    List<Answer> findAnswers();

    Answer findVerifiedAnswer(long answerId);


}
