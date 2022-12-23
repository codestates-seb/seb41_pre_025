package com.overflow.stack.server.domain.member.mapper;

import com.overflow.stack.server.domain.member.dto.MemberDto;
import com.overflow.stack.server.domain.member.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member postMemberDtoToMember(MemberDto.Post memberDto);
    MemberDto.Response memberToResponseMemberDto(Member member);
}
