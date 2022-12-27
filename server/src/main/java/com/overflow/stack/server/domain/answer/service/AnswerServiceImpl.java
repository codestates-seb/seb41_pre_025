package com.overflow.stack.server.domain.answer.service;

import com.overflow.stack.server.domain.answer.entity.Answer;
import com.overflow.stack.server.domain.answer.repository.AnswerRepository;
import com.overflow.stack.server.domain.member.service.MemberService;
import com.overflow.stack.server.domain.question.service.QuestionService;
import com.overflow.stack.server.global.exception.CustomLogicException;
import com.overflow.stack.server.global.exception.ExceptionCode;
import com.overflow.stack.server.global.utils.CustomBeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionService questionService;
    private final CustomBeanUtils<Answer> beanUtils;
    private final MemberService memberService;
    public AnswerServiceImpl(AnswerRepository answerRepository, QuestionService questionService, CustomBeanUtils<Answer> beanUtils, MemberService memberService) {
        this.answerRepository = answerRepository;
        this.questionService = questionService;
        this.beanUtils = beanUtils;
        this.memberService = memberService;
    }

    @Override
    public Answer createAnswer(Answer answer, String userName, long questionId) {
        answer.setMember(memberService.findMember(userName));
        answer.setQuestion(questionService.findQuestion(questionId));
        return answerRepository.save(answer);
    }

    @Override
    public Answer updateAnswer(Answer answer, String userName) {
        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());
        beanUtils.copyNonNullProperties(answer, findAnswer);

        if(this.findAnswer(answer.getAnswerId()).getMember().getMemberId()
                == memberService.findMember(userName).getMemberId()){
            beanUtils.copyNonNullProperties(answer, findAnswer);
        }
        else{
            throw new CustomLogicException(ExceptionCode.ANSWER_WRITER_DUPLICATE);
        }

        return answerRepository.save(findAnswer);
    }

    @Override
    public Answer findAnswer(long answerId) {
        return findVerifiedAnswer(answerId);
    }

    @Override
    public List<Answer> findAnswers() {
        return answerRepository.findAll();
    }


    @Override
    public Answer findVerifiedAnswer(long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
        Answer findAnswer = optionalAnswer.orElseThrow(() ->
                new CustomLogicException(ExceptionCode.ANSWER_NOT_FOUND));
        return findAnswer;
    }


}
