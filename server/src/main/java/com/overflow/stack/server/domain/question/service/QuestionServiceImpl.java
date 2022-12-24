package com.overflow.stack.server.domain.question.service;

import com.overflow.stack.server.domain.member.entity.Member;
import com.overflow.stack.server.domain.member.service.MemberService;
import com.overflow.stack.server.domain.question.entity.Question;
import com.overflow.stack.server.domain.question.repository.QuestionRepository;
import com.overflow.stack.server.global.exception.CustomLogicException;
import com.overflow.stack.server.global.exception.ExceptionCode;
import com.overflow.stack.server.global.utils.CustomBeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final CustomBeanUtils<Question> beanUtils;
    private final MemberService memberService;

    public QuestionServiceImpl(QuestionRepository questionRepository, CustomBeanUtils<Question> beanUtils, MemberService memberService) {
        this.questionRepository = questionRepository;
        this.beanUtils = beanUtils;
        this.memberService = memberService;
    }

    @Override
    public Question createQuestion(Question question, String userName) {
        question.setMember(memberService.findMember(userName));
        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Question question) {
        Question findQuestion =findVerifiedQuestion(question.getQuestionId());
        beanUtils.copyNonNullProperties(question, findQuestion);
        return questionRepository.save(findQuestion);
    }

    @Override
    public Question findQuestion(long questionId) {
        return findVerifiedQuestion(questionId);
    }

    @Override
    public List<Question> findQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public void deleteQuestion(long questionId) {
        Question question= findVerifiedQuestion(questionId);
        questionRepository.delete(question);
    }

    @Override
    public Question findVerifiedQuestion(long questionId) {
        Optional<Question>optionalQuestion = questionRepository.findById(questionId);
        Question findQuestion =
                optionalQuestion.orElseThrow(()->
                        new CustomLogicException(ExceptionCode.QUESTION_NOT_FOUND));
        return findQuestion;
    }
}
