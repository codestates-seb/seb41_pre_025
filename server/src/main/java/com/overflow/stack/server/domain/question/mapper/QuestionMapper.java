package com.overflow.stack.server.domain.question.mapper;

import com.overflow.stack.server.domain.question.dto.QuestionDto;
import com.overflow.stack.server.domain.question.entity.Question;
import com.overflow.stack.server.domain.question.entity.Question_Tag;
import com.overflow.stack.server.domain.tag.entity.Tag;
import org.mapstruct.Mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
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
            questionId = question.getQuestionId();
            title = question.getTitle();
            content = question.getContent();
            voteResult = question.getVoteResult();
            displayName= question.getMember().getDisplayName();
            if(question.getTags()!=null) {
                tags = question.getTags().stream()
                        .map(tag -> tag.getTag().getTagName())
                        .collect(Collectors.toSet());
            }
            QuestionDto.response response = new QuestionDto.response(questionId, title, content, voteResult,displayName,tags);
            return response;
        }
    }
    List<QuestionDto.response> questionsToQuestionResponseDtos(List<Question> questions);
}
