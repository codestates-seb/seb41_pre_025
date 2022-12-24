package com.overflow.stack.server.domain.answer.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AnswerDto {
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Post {
        @NotBlank
        private String content;
        private Long voteResult;
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class Patch{
        private Long answerId;
        @NotBlank
        private String content;
        private Long voteResult;
    }

    @AllArgsConstructor
    @Getter
    public static class Response {
        private Long answerId;
        @NotBlank
        private String content;
        private Long voteResult;
    }
}
