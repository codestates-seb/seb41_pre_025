package com.overflow.stack.server.domain.question.mapper;

import com.overflow.stack.server.domain.question.dto.QuestionDto;
import com.overflow.stack.server.domain.question.entity.Question;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    Question questionPostDtoToQuestion(QuestionDto.Post questionPostDto);
    Question questionPatchDtoToQuestion(QuestionDto.Patch questionPatchDto);
    QuestionDto.response questionToQuestionResponseDto(Question question);
    List<QuestionDto.response> questionsToQuestionResponseDtos(List<Question> questions);
}
