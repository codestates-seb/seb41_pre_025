package com.overflow.stack.server.domain.question.mapper;

import com.overflow.stack.server.domain.answer.dto.AnswerDto;
import com.overflow.stack.server.domain.answer.mapper.AnswerMapper;
import com.overflow.stack.server.domain.answer.mapper.AnswerMapperImpl;
import com.overflow.stack.server.domain.question.dto.QuestionDto;
import com.overflow.stack.server.domain.question.entity.Question;
import com.overflow.stack.server.domain.question.entity.Question_Tag;
import com.overflow.stack.server.domain.tag.entity.Tag;
import org.mapstruct.Mapper;

import java.util.*;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    AnswerMapper answerMapper = new AnswerMapperImpl();
    default Question questionPostDtoToQuestion(QuestionDto.Post questionPostDto){
        if (questionPostDto == null) {
            return null;
        } else {
            Question question = new Question();
            question.setTitle(questionPostDto.getTitle());
            question.setContent(questionPostDto.getContent());
            if(questionPostDto.getTag()!=null) {
                question.setTags(questionPostDto.getTag().stream()
                        .map(tag -> new Question_Tag(question, new Tag(tag.toLowerCase())))
                        .collect(Collectors.toSet()));
            }
            return question;
        }
    }
    default Question questionPatchDtoToQuestion(QuestionDto.Patch questionPatchDto){
        if (questionPatchDto == null) {
            return null;
        } else {
            Question question = new Question();
            question.setQuestionId(questionPatchDto.getQuestionId());
            question.setTitle(questionPatchDto.getTitle());
            question.setContent(questionPatchDto.getContent());
            question.setTags(questionPatchDto.getTag().stream()
                    .map(tag->new Question_Tag(question,new Tag(tag.toLowerCase())))
                    .collect(Collectors.toSet()));
            return question;
        }
    }
    default QuestionDto.response questionToQuestionResponseDto(Question question){
        if (question == null) {
            return null;
        } else {
            Long questionId = null;
            String title = null;
            String content = null;
            Long voteResult = null;
            String displayName=null;
            Set<String> tags = null;
            List<AnswerDto.Response> answerResponseDtos=null;
            questionId = question.getQuestionId();
            title = question.getTitle();
            content = question.getContent();
            voteResult = question.getVoteResult();
            displayName= question.getMember().getDisplayName();
            answerResponseDtos=question.getAnswers().stream()
                    .map(answer -> answerMapper.answerToAnswerResponseDto(answer))
                    .collect(Collectors.toList());
            if(question.getTags()!=null) {
                tags = question.getTags().stream()
                        .map(tag -> tag.getTag().getTagName())
                        .collect(Collectors.toSet());
            }
            QuestionDto.response response = new QuestionDto.response(questionId, title, content, voteResult,displayName,tags,answerResponseDtos);
            return response;
        }
    }
    default List<QuestionDto.response> questionsToQuestionResponseDtos(List<Question> questions){
        if (questions == null) {
            return null;
        } else {
            List<QuestionDto.response> list = new ArrayList(questions.size());
            Iterator var3 = questions.iterator();

            while(var3.hasNext()) {
                Question question = (Question)var3.next();
                QuestionDto.response qDtoResponse=this.questionToQuestionResponseDto(question);
                qDtoResponse.setAnswers(null);
                list.add(qDtoResponse);
            }

            return list;
        }
    }
}
