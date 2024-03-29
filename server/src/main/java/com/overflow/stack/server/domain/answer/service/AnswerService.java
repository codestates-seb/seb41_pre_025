package com.overflow.stack.server.domain.answer.service;

import com.overflow.stack.server.domain.answer.entity.Answer;

import java.util.List;

public interface AnswerService {
    Answer createAnswer(Answer answer, String userName, long questionId);

    Answer updateAnswer(Answer answer, String userName);

    Answer findAnswer(long answerId);

    List<Answer> findMyAnswers(String userName);

    List<Answer> findAnswers();

    Answer findVerifiedAnswer(long answerId);

    List<Answer> findVerifiedAnswers(String userName);

    void deleteAnswer(long answerId, String userName);

    Answer voteAnswer(long answerId, String userName, boolean voteUp);


}
