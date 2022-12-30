package com.overflow.stack.server.domain.answer.mapper;

import com.overflow.stack.server.domain.answer.dto.AnswerDto;
import com.overflow.stack.server.domain.answer.entity.Answer;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    default Answer answerPostDtoToAnswer(AnswerDto.Post answerPostDto){
        if ( answerPostDto == null ) {
            return null;
        }

        Answer answer = new Answer();

        answer.setContent( answerPostDto.getContent() );

        return answer;
    }

    default Answer answerPatchDtoToAnswer(AnswerDto.Patch answerPatchDto){
        if ( answerPatchDto == null ) {
            return null;
        }

        Answer answer = new Answer();

        answer.setAnswerId( answerPatchDto.getAnswerId() );
        answer.setContent( answerPatchDto.getContent() );

        return answer;
    }

    default AnswerDto.Response answerToAnswerResponseDto(Answer answer) {
        if ( answer == null ) {
            return null;
        }

        Long answerId = null;
        String content = null;
        Long voteResult = null;
        Long questionId = null;
        String displayName = null;
        LocalDateTime createdAt= null;
        LocalDateTime modifiedAt = null;

        answerId = answer.getAnswerId();
        content = answer.getContent();
        voteResult = answer.getVoteResult();
        questionId = answer.getQuestion().getQuestionId();
        displayName = answer.getMember().getDisplayName();
        createdAt = answer.getCreatedAt();
        modifiedAt = answer.getModifiedAt();


        AnswerDto.Response response = new AnswerDto.Response( answerId, content, voteResult, questionId, displayName, createdAt,modifiedAt);

        return response;
    }
    List<AnswerDto.Response> answersToAnswerResponseDtos(List<Answer> answers);
}
