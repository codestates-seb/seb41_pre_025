package com.overflow.stack.server.domain.member.service;

import com.overflow.stack.server.domain.member.entity.Member;
import com.overflow.stack.server.domain.member.repository.JpaMemberRepository;
import com.overflow.stack.server.global.exception.CustomLogicException;
import com.overflow.stack.server.global.exception.ExceptionCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static com.overflow.stack.server.domain.member.utils.AuthoritiesUtils.createRoles;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {
    private final JpaMemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    public MemberServiceImpl(JpaMemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Member createMember(Member member) {
        findByEmail(member.getEmail()).ifPresent(m -> {
            throw new CustomLogicException(ExceptionCode.MEMBER_DUPLICATE);
        });
        member.setMemberStatus(Member.MemberStatus.MEMBER_ACTIVE);
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        member.setRoles(createRoles(member.getEmail()));
        return memberRepository.save(member);
    }
    // read only
    private Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
    public Member findMember(String email) {
      return findByEmail(email).orElseThrow(() -> new CustomLogicException(ExceptionCode.MEMBER_NONE));
    }
    public Member findMember(Long id) {
      return memberRepository.findById(id).orElseThrow(() -> new CustomLogicException(ExceptionCode.MEMBER_NONE));
    }
}
