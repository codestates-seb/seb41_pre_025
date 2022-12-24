package com.overflow.stack.server.domain.answer.service;

import com.overflow.stack.server.domain.answer.entity.Answer;

public interface AnswerService {
    Answer createAnswer(Answer answer);

    Answer updateAnswer(Answer answer);

    Answer findVerifiedAnswer(long answerId);
}
