package com.overflow.stack.server.domain.question.service;

import com.overflow.stack.server.domain.member.entity.Member;
import com.overflow.stack.server.domain.member.service.MemberService;
import com.overflow.stack.server.domain.question.entity.Question;
import com.overflow.stack.server.domain.question.entity.Question_Vote;
import com.overflow.stack.server.domain.question.repository.QuestionRepository;
import com.overflow.stack.server.domain.question.repository.QuestionVoteRepository;
import com.overflow.stack.server.domain.tag.entity.Tag;
import com.overflow.stack.server.domain.tag.service.TagService;
import com.overflow.stack.server.global.exception.CustomLogicException;
import com.overflow.stack.server.global.exception.ExceptionCode;
import com.overflow.stack.server.global.utils.CustomBeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionVoteRepository questionVoteRepository;
    private final CustomBeanUtils<Question> beanUtils;
    private final MemberService memberService;
    private final TagService tagService;

    public QuestionServiceImpl(QuestionRepository questionRepository, QuestionVoteRepository questionVoteRepository, CustomBeanUtils<Question> beanUtils, MemberService memberService, TagService tagService) {
        this.questionRepository = questionRepository;
        this.questionVoteRepository = questionVoteRepository;
        this.beanUtils = beanUtils;
        this.memberService = memberService;
        this.tagService = tagService;
    }

    @Override
    public Question createQuestion(Question question, String userName) {
        question.setMember(memberService.findMember(userName));
        question.setVoteResult(0L);
        if(question.getTags()!=null) {
            question.getTags().stream()
                    .forEach(qtag -> {
                        Tag tag = tagService.findTagByTagName(qtag.getTag().getTagName()).orElseGet(() -> tagService.saveTag(qtag.getTag()));
                        qtag.setTag(tag);
                    });
        }
        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Question question, String userName) {
        Question findQuestion =findVerifiedQuestion(question.getQuestionId());

        if(this.findQuestion(question.getQuestionId()).getMember().getMemberId()
                == memberService.findMember(userName).getMemberId()){
            beanUtils.copyNonNullProperties(question, findQuestion);
            return questionRepository.save(findQuestion);
        }
        throw new CustomLogicException(ExceptionCode.QUESTION_WRITER_NOT_MATCH);
    }

    @Override
    public Question voteQuestion(long questionId, String userName, boolean voteUp) {
        Member member = memberService.findMember(userName);
        Question findQ = findVerifiedQuestion(questionId);
        Optional<Question_Vote> findVote=questionVoteRepository.findByMember(member);
        if(findVote.isPresent()) {
            if (findVote.get().isVoteUp() == voteUp) {
                findQ.setVoteResult(findQ.getVoteResult() + (voteUp ? -1 : 1));
                questionVoteRepository.delete(findVote.get());
                return findQ;
            }
            findQ.setVoteResult(findQ.getVoteResult() + (voteUp ? 2 : -2));
            findVote.get().setVoteUp(voteUp);
            return findQ;
        }
        findQ.setVoteResult(findQ.getVoteResult() + (voteUp ? 1 : -1));
        Question_Vote qVote= new Question_Vote(voteUp,findQ, member);
        questionVoteRepository.save(qVote);
        return findQ;
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
    public void deleteQuestion(long questionId, String userName) {
        Question question= findVerifiedQuestion(questionId);
        if(this.findQuestion(question.getQuestionId()).getMember().getMemberId()
                == memberService.findMember(userName).getMemberId()){
            questionRepository.delete(question);
            return;
        }
            throw new CustomLogicException(ExceptionCode.QUESTION_WRITER_NOT_MATCH);
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
