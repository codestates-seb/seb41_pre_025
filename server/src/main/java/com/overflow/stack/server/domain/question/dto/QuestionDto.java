package com.overflow.stack.server.domain.question.dto;

import com.overflow.stack.server.domain.answer.dto.AnswerDto;
import com.overflow.stack.server.domain.member.entity.Member;
import com.overflow.stack.server.domain.question.entity.Question_Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class QuestionDto {
    @AllArgsConstructor
    @Getter
    public static class Post{
        @NotBlank
        private String title;
        @NotBlank
        private String content;
        private Set<String> tag;
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class Patch{
        private Long questionId;
        @NotBlank
        private String title;
        @NotBlank
        private String content;
        private Set<String> tag;
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class response{
        private Long questionId;
        @NotBlank
        private String title;
        @NotBlank
        private String content;
        private Long voteResult;
        private String displayName;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private Set<String> tag;
        private List<AnswerDto.Response> answers;
        private Long answerCount;
    }

}
