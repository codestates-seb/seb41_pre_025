package com.overflow.stack.server.domain.answer.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AnswerDto {

    @NoArgsConstructor
    @Getter
    public static class Post {

        public Post(String content) {
            this.content = content;
        }

        @NotBlank
        @Lob
        private String content;
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class Patch{
        private Long answerId;
        @NotBlank
        @Lob
        private String content;
    }

    @AllArgsConstructor
    @Getter
    public static class Response {
        private Long answerId;
        @NotBlank
        @Lob
        private String content;
        private Long voteResult;
        private String displayName;
    }
}
