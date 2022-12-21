package com.overflow.stack.server.domain.question.service;

import com.overflow.stack.server.domain.question.entity.Question;
import com.overflow.stack.server.domain.question.repository.JpaQuestionRepository;
import com.overflow.stack.server.domain.question.repository.QuestionRepository;
import com.overflow.stack.server.global.exception.CustomLogicException;
import com.overflow.stack.server.global.exception.ExceptionCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Question question) {
        Question findQuestion =findVerifiedQuestion(question.getQuestionId());

        Optional.ofNullable(question.getTitle())
                .ifPresent(title -> findQuestion.setTitle(title));
        Optional.ofNullable(question.getContent())
                .ifPresent(content -> findQuestion.setContent(content));
        Optional.ofNullable(question.getVoteResult())
                .ifPresent(vote -> findQuestion.setVoteResult(vote));
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
