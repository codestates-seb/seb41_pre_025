package com.overflow.stack.server.domain.member.factory;

import com.overflow.stack.server.domain.member.dto.MemberDto;

public class MemberFactory {
    public static MemberDto.Post createMemberPostDto() {

        return MemberDto.Post.builder()
                .email("email@test.com")
                .password("password")
                .fullName("fullName")
                .displayName("displayName")
                .aboutMe("aboutMe")
                .aboutMeTitle("aboutMeTitle")
                .twitterLink("https://twitter.com")
                .githubLink("https://github.com")
                .websiteLink("https://website.com")
                .location("location")
                .imgUrl("https://img.com")
                .build();
    }


}
