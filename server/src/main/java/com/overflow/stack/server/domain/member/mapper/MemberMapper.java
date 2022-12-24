package com.overflow.stack.server.domain.member.mapper;

import com.overflow.stack.server.domain.member.dto.MemberDto;
import com.overflow.stack.server.domain.member.entity.Member;
import com.overflow.stack.server.domain.member.entity.Member_Tag;
import com.overflow.stack.server.domain.tag.entity.Tag;
import org.mapstruct.Mapper;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    default Member postMemberDtoToMember(MemberDto.Post memberDto){
        Member member = Member.builder()
                .email(memberDto.getEmail())
                .displayName(memberDto.getDisplayName())
                .fullName(memberDto.getFullName())
                .password(memberDto.getPassword())
                .build();
        member.setTags(
                memberDto.getTags().stream()
                        .map(tag -> new Member_Tag(new Tag(tag.toLowerCase())))
                        .collect(Collectors.toList())
        );
        return member;
    }
    default MemberDto.Response memberToResponseMemberDto(Member member){
        return MemberDto.Response.builder()
                .memberId(member.getMemberId())
                .email(member.getEmail())
                .displayName(member.getDisplayName())
                .fullName(member.getFullName())
                .tags(member.getTags().stream().map(Member_Tag::getTag).map(Tag::getTagName).collect(Collectors.toList()))
                .aboutMe(member.getAboutMe())
                .aboutMeTitle(member.getAboutMeTitle())
                .githubLink(member.getGithubLink())
                .twitterLink(member.getTwitterLink())
                .websiteLink(member.getWebsiteLink())
                .imgUrl(member.getImgUrl())
                .build();
    }
}
