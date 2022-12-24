package com.overflow.stack.server.domain.answer.service;

import com.overflow.stack.server.domain.answer.entity.Answer;
import com.overflow.stack.server.domain.answer.repository.AnswerRepository;
import com.overflow.stack.server.global.exception.CustomLogicException;
import com.overflow.stack.server.global.exception.ExceptionCode;
import com.overflow.stack.server.global.utils.CustomBeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final CustomBeanUtils<Answer> beanUtils;

    public AnswerServiceImpl(AnswerRepository answerRepository, CustomBeanUtils<Answer> beanUtils) {
        this.answerRepository = answerRepository;
        this.beanUtils = beanUtils;
    }



    @Override
    public Answer createAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public Answer updateAnswer(Answer answer) {
        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());
        beanUtils.copyNonNullProperties(answer, findAnswer);
        return answerRepository.save(findAnswer);
    }

    @Override
    public Answer findVerifiedAnswer(long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
        Answer findAnswer = optionalAnswer.orElseThrow(() ->
                new CustomLogicException(ExceptionCode.ANSWER_NOT_FOUND));
        return findAnswer;
    }


}
