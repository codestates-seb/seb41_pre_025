package com.overflow.stack.server.domain.answer.mapper;

import com.overflow.stack.server.domain.answer.dto.AnswerDto;
import com.overflow.stack.server.domain.answer.entity.Answer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    Answer answerPostDtoToAnswer(AnswerDto.Post answerPostDto);
    Answer answerPatchDtoToAnswer(AnswerDto.Patch answerPatchDto);
    AnswerDto.Response answerToAnswerResponseDto(Answer answer);
}
