package com.overflow.stack.server.domain.member.factory;

import com.overflow.stack.server.domain.member.dto.MemberDto;
import com.overflow.stack.server.domain.member.entity.Member;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class MemberFactory {
    public static MemberDto.Post createMemberPostDto() {

        return MemberDto.Post.builder()
                .email("email@test.com")
                .password("password")
                .fullName("fullName")
                .displayName("displayName")
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
    public static Member createMember() {
        return Member.builder()
                .email("test1@gmail.com")
                .memberStatus(Member.MemberStatus.MEMBER_ACTIVE)
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
    public static MemberDto.Response createMemberResponseDto() {
        return MemberDto.Response.builder()
                .memberId(1L)
                .email("test1@gmail.com")
                .fullName("fullName")
                .displayName("displayName")
                .aboutMe("aboutMe")
                .aboutMeTitle("aboutMeTitle")
                .twitterLink("https://twitter.com")
                .githubLink("https://github.com")
                .websiteLink("https://website.com")
                .location("location")
                .imgUrl("https://img.com")
                .isFollowingTags(List.of("js" , "java"))
                .isUnFollowingTags(List.of("python" , "c++"))
                .build();
    }
    public static MemberDto.Patch createMemberPatchDto() {
        return MemberDto.Patch.builder()
                .imgUrl("https://img.com")
                .fullName("fullName")
                .displayName("displayName")
                .aboutMe("aboutMe")
                .aboutMeTitle("aboutMeTitle")
                .twitterLink("https://twitter.com")
                .githubLink("https://github.com")
                .websiteLink("https://website.com")
                .location("location")
                .build();
    }
}
