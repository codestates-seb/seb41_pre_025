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
import java.util.List;

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
    @PatchMapping
    public ResponseEntity patchMember(@AuthenticationPrincipal User members, @Valid @RequestBody MemberDto.Patch memberDto){
        Member member = memberService.findMember(members.getUsername());
        memberService.updateMember(member, memberMapper.patchMemberDtoToMember(memberDto));
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/tags/{tag}/{isFollow}")
    public ResponseEntity patchMemberTags(@AuthenticationPrincipal User members, @PathVariable String tag , @PathVariable boolean isFollow){
        Member member = memberService.findMember(members.getUsername());
        member = memberService.updateMemberTags(member, tag, isFollow);
        return ResponseEntity.ok(new SingleResponse<>(memberMapper.memberToResponseMemberDto(member)));
    }
    @DeleteMapping("/tags/{tag}")
    public ResponseEntity deleteMemberTags(@AuthenticationPrincipal User members, @PathVariable String tag){
        Member member = memberService.findMember(members.getUsername());
        member = memberService.deleteMemberTags(member, tag);
        return ResponseEntity.noContent().build();
    }

}
