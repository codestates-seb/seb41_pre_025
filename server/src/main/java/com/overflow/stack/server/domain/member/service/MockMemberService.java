package com.overflow.stack.server.domain.member.service;

import com.overflow.stack.server.domain.member.repository.MemberRepository;

public class MockMemberService implements MemberService {

   private final MemberRepository memberRepository;

    public MockMemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
}
