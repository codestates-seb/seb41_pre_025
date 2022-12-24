package com.overflow.stack.server.domain.member.service;

import com.overflow.stack.server.domain.member.entity.Member;
import com.overflow.stack.server.domain.member.entity.Member_Tag;
import com.overflow.stack.server.domain.member.repository.JpaMemberRepository;
import com.overflow.stack.server.domain.tag.entity.Tag;
import com.overflow.stack.server.domain.tag.repository.JpaTagRepository;
import com.overflow.stack.server.domain.tag.service.TagService;
import com.overflow.stack.server.global.exception.CustomLogicException;
import com.overflow.stack.server.global.exception.ExceptionCode;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.overflow.stack.server.domain.member.utils.AuthoritiesUtils.createRoles;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {
    private final JpaMemberRepository memberRepository;
    private final TagService tagService;
    private final PasswordEncoder passwordEncoder;
    public MemberServiceImpl(JpaMemberRepository memberRepository,  TagService tagService, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.tagService = tagService;
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
        List<Member_Tag> memberTags = member.getTags().stream().map(mTag -> {
            Member_Tag memberTag = new Member_Tag();
            memberTag.setMember(member);
            Tag tag = tagService.findTagByTagName(mTag.getTag().getTagName()).orElseGet(() -> tagService.saveTag(mTag.getTag()));
            memberTag.setTag(tag);
            return memberTag;
        }).collect(Collectors.toList());
        member.setTags(memberTags);
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
