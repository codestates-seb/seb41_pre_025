package com.overflow.stack.server.domain.question.mapper;

import com.overflow.stack.server.domain.question.dto.QuestionDto;
import com.overflow.stack.server.domain.question.entity.Question;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    Question questionPostDtoToQuestion(QuestionDto.Post questionPostDto);
    Question questionPatchDtoToQuestion(QuestionDto.Patch questionPatchDto);
    default QuestionDto.response questionToQuestionResponseDto(Question question){
        if (question == null) {
            return null;
        } else {
            Long questionId = null;
            String title = null;
            String content = null;
            Long voteResult = null;
            String displayName=null;
            questionId = question.getQuestionId();
            title = question.getTitle();
            content = question.getContent();
            voteResult = question.getVoteResult();
            displayName= question.getMember().getDisplayName();
            QuestionDto.response response = new QuestionDto.response(questionId, title, content, voteResult,displayName);
            return response;
        }
    }
    List<QuestionDto.response> questionsToQuestionResponseDtos(List<Question> questions);
}
