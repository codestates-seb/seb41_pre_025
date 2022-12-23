package com.overflow.stack.server.domain.member.controller;

import com.overflow.stack.server.domain.member.dto.MemberDto;
import com.overflow.stack.server.domain.member.entity.Member;
import com.overflow.stack.server.domain.member.mapper.MemberMapper;
import com.overflow.stack.server.domain.member.service.MemberService;
import com.overflow.stack.server.global.response.SingleResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@SuppressWarnings("rawtypes")
@RestController
@Validated
@RequestMapping("/api/v1/members")
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;
    private static final String BASE_URL = "/api/v1/members";

    public MemberController(MemberService memberService, MemberMapper memberMapper) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }

    @PostMapping
    public ResponseEntity createMember(@Valid @RequestBody MemberDto.Post memberDto) throws URISyntaxException {
        memberService.createMember(
                memberMapper.postMemberDtoToMember(memberDto));
        return ResponseEntity.created(new URI(BASE_URL)).build();
    }

    /**
     * @param members email
     *
     * @return
     */
    @GetMapping
    public ResponseEntity getMember(@AuthenticationPrincipal User members){
        Member member = memberService.findMember(members.getUsername());
        return ResponseEntity.ok(new SingleResponse<>(memberMapper.memberToResponseMemberDto(member)));
    }

}
