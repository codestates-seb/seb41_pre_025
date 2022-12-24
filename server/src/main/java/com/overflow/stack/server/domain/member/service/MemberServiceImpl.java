package com.overflow.stack.server.domain.member.service;

import com.overflow.stack.server.domain.member.entity.Member;
import com.overflow.stack.server.domain.member.entity.Member_Tag;
import com.overflow.stack.server.domain.member.repository.JpaMemberRepository;
import com.overflow.stack.server.domain.tag.entity.Tag;
import com.overflow.stack.server.domain.tag.service.TagService;
import com.overflow.stack.server.global.exception.CustomLogicException;
import com.overflow.stack.server.global.exception.ExceptionCode;
import com.overflow.stack.server.global.utils.CustomBeanUtils;
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
    private final CustomBeanUtils<Member> customBeanUtils;
    private final PasswordEncoder passwordEncoder;

    public MemberServiceImpl(JpaMemberRepository memberRepository, TagService tagService, CustomBeanUtils<Member> customBeanUtils, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.tagService = tagService;
        this.customBeanUtils = customBeanUtils;
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
        List<Member_Tag> memberTags = member.getTags().stream().peek(mTag -> {
            mTag.setMember(member);
            Tag tag = tagService.findTagByTagName(mTag.getTag().getTagName()).orElseGet(() -> tagService.saveTag(mTag.getTag()));
            mTag.setTag(tag);
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

    @Override
    public Member updateMember(Member member, Member patchMember) {
        return customBeanUtils.copyNonNullProperties(patchMember, member);
    }

    @Override
    public Member updateMemberTags(Member member, String tag, boolean isFollow) {
        // follow 를 할 때 tag 가 없으면 tag 를 생성하고, 있으면 tag 를 찾아서 member_tag 에 추가한다.
        // follow 할 때 unfollow 한 목록에 해당 tag 가 있다면 unfollow 목록에서 제거한다.
// unfollow 를 할 때 tag 가 없으면 예외를 발생시킨다.
// unfollow 를 할 때 follow 한 목록에 해당 tag 가 있다면 follow 목록에서 제거한다.

        Tag tagEntity = tagService.findTagByTagName(tag).orElseGet(() -> tagService.saveTag(new Tag(tag)));
        Member_Tag memberTag = Member_Tag.builder().member(member).tag(tagEntity).isFollowed(isFollow).build();
        member.getTags().removeIf(mTag -> mTag.getTag().getTagName().equals(tag));
        member.getTags().add(memberTag);


        return member;
    }

}
