package com.overflow.stack.server.domain.answer.service;

import com.overflow.stack.server.domain.answer.entity.Answer;
import com.overflow.stack.server.domain.answer.entity.Answer_Vote;
import com.overflow.stack.server.domain.answer.repository.AnswerRepository;
import com.overflow.stack.server.domain.answer.repository.AnswerVoteRepository;
import com.overflow.stack.server.domain.member.entity.Member;
import com.overflow.stack.server.domain.member.service.MemberService;
import com.overflow.stack.server.domain.question.service.QuestionService;
import com.overflow.stack.server.global.exception.CustomLogicException;
import com.overflow.stack.server.global.exception.ExceptionCode;
import com.overflow.stack.server.global.utils.CustomBeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    private final AnswerVoteRepository answerVoteRepository;
    private final QuestionService questionService;
    private final CustomBeanUtils<Answer> beanUtils;
    private final MemberService memberService;
    public AnswerServiceImpl(AnswerRepository answerRepository, AnswerVoteRepository answerVoteRepository, QuestionService questionService, CustomBeanUtils<Answer> beanUtils, MemberService memberService) {
        this.answerRepository = answerRepository;
        this.answerVoteRepository = answerVoteRepository;
        this.questionService = questionService;
        this.beanUtils = beanUtils;
        this.memberService = memberService;
    }

    @Override
    public Answer createAnswer(Answer answer, String userName, long questionId) {
        answer.setVoteResult(0L);
        answer.setMember(memberService.findMember(userName));
        answer.setQuestion(questionService.findQuestion(questionId));
        answer.setQuestionId(questionId);
        return answerRepository.save(answer);
    }

    @Override
    public Answer updateAnswer(Answer answer, String userName) {
        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());

        if(findAnswer.getMember().getMemberId()
                == memberService.findMember(userName).getMemberId()){
            beanUtils.copyNonNullProperties(answer, findAnswer);
            return answerRepository.save(findAnswer);
        }
        throw new CustomLogicException(ExceptionCode.ANSWER_WRITER_NOT_MATCH);
    }

    @Override
    public Answer findAnswer(long answerId) {
        return findVerifiedAnswer(answerId);
    }

    @Override
    public List<Answer> findMyAnswers(String userName) {
        List<Answer> myAnswers = findVerifiedAnswers(userName);
        return myAnswers;
    }

    @Override
    public List<Answer> findAnswers() {
        Sort lateSort = Sort.by("createdAt").descending();
        return answerRepository.findAll(lateSort);
    }


    @Override
    public Answer findVerifiedAnswer(long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
        Answer findAnswer = optionalAnswer.orElseThrow(() ->
                new CustomLogicException(ExceptionCode.ANSWER_NOT_FOUND));
        return findAnswer;

    }

    @Override
    public List<Answer> findVerifiedAnswers(String userName) {
        Member member = memberService.findMember(userName);
        List<Answer> findAnswers = answerRepository.findAllByMember(member);
        return findAnswers;
    }

    @Override
    public void deleteAnswer(long answerId, String userName) {
        Answer findAnswer = findVerifiedAnswer(answerId);
        if (this.findAnswer(findAnswer.getAnswerId()).getMember().getMemberId() == memberService.findMember(userName).getMemberId()) {
            answerRepository.delete(findAnswer);
            return;
        }
        throw new CustomLogicException(ExceptionCode.ANSWER_WRITER_NOT_MATCH);
    }

    @Override
    public Answer voteAnswer(long answerId, String userName, boolean voteUp) {
        Member member = memberService.findMember(userName);
        Answer answer = findVerifiedAnswer(answerId);
        Optional<Answer_Vote> vote = answerVoteRepository.findByMemberAndAnswer(member, answer);
        if (!vote.isPresent()) {
            answer.setVoteResult(answer.getVoteResult() + (voteUp ? 1 : -1));
            answerVoteRepository.save(new Answer_Vote(voteUp, answer, member));
            return answer;
        }
        if (vote.get().isVoteUp() != voteUp) {
            answer.setVoteResult(answer.getVoteResult() + (voteUp ? 2 : -2));
            vote.get().setVoteUp(voteUp);
            return answer;
        }
        answer.setVoteResult(answer.getVoteResult() + (voteUp ? -1 : 1));
        answerVoteRepository.delete(vote.get());
        return answer;
    }


}
