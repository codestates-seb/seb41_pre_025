package com.overflow.stack.server.domain.member.factory;

import com.overflow.stack.server.domain.member.dto.MemberDto;
import com.overflow.stack.server.domain.member.entity.Member;
import org.springframework.security.crypto.password.PasswordEncoder;

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


    public static Member createMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email("test1@gmail.com")
                .memberStatus(Member.MemberStatus.MEMBER_ACTIVE)
                .password(passwordEncoder.encode("password"))
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
