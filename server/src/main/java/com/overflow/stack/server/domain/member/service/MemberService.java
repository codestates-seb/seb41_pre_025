package com.overflow.stack.server.domain.member.service;

import com.overflow.stack.server.domain.member.entity.Member;

public interface MemberService {

    Member createMember(Member member);
    Member findMember(String email);
    Member findMember(Long id);


}
