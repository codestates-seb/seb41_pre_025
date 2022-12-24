package com.overflow.stack.server.domain.question.dto;

import com.overflow.stack.server.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class QuestionDto {
    @AllArgsConstructor
    @Getter
    public static class Post{
        @NotBlank
        private String title;
        @NotBlank
        private String content;
        private Long voteResult;
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
        private Long voteResult;
    }

    @AllArgsConstructor
    @Getter
    public static class response{
        private Long questionId;
        @NotBlank
        private String title;
        @NotBlank
        private String content;
        private Long voteResult;
        private String displayName;
    }

}
