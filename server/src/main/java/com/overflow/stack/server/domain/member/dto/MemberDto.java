package com.overflow.stack.server.domain.member.dto;

import lombok.*;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
public class MemberDto {

    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    @Builder
    public static class Post{
        @NonNull
        @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
        private String email;
        @NonNull

        private String password;
        @NonNull
        @Pattern(regexp = "^[a-zA-Z0-9가-힣+_.-]+$")
        private String fullName;
        @NonNull
        @Pattern(regexp = "^[a-zA-Z0-9가-힣+_.-]+$")
        private String displayName;
        private String location;
        private String aboutMeTitle;
        private String aboutMe;
        //weblink Pattern
        @Pattern(regexp = "^(http|https)://[a-zA-Z0-9+_.-]+.[a-zA-Z0-9+_.-]+.[a-zA-Z0-9+_.-]+$")
        private String githubLink;
        @Pattern(regexp = "^(http|https)://[a-zA-Z0-9+_.-]+.[a-zA-Z0-9+_.-]+.[a-zA-Z0-9+_.-]+$")
        private String twitterLink;
        @Pattern(regexp = "^(http|https)://[a-zA-Z0-9+_.-]+.[a-zA-Z0-9+_.-]+.[a-zA-Z0-9+_.-]+$")
        private String websiteLink;

        private String imgUrl;
    }
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private Long memberId;
        private String email;
        private String fullName;
        private String displayName;
        private String location;
        private String aboutMeTitle;
        private String aboutMe;
        private String githubLink;
        private String twitterLink;
        private String websiteLink;
        private String imgUrl;

    }

}
