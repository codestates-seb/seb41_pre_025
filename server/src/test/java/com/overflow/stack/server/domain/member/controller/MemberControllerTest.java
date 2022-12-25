package com.overflow.stack.server.domain.member.controller;

import com.google.gson.Gson;
import com.overflow.stack.server.common.abstractControllerTest;
import com.overflow.stack.server.common.token.GeneratedToken;
import com.overflow.stack.server.domain.member.dto.MemberDto;
import com.overflow.stack.server.domain.member.entity.Member;
import com.overflow.stack.server.domain.member.factory.MemberFactory;
import com.overflow.stack.server.domain.member.mapper.MemberMapper;
import com.overflow.stack.server.domain.member.service.MemberService;
import com.overflow.stack.server.domain.member.utils.MemberExpectedAction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static com.overflow.stack.server.domain.member.factory.MemberFactory.createMemberPatchDto;
import static com.overflow.stack.server.domain.member.factory.MemberFactory.createMemberPostDto;
import static com.overflow.stack.server.domain.member.utils.MemberExpectedAction.expectedResponse;
import static com.overflow.stack.server.util.ApiDocumentUtils.getRequestPreProcessor;
import static com.overflow.stack.server.util.ApiDocumentUtils.getResponsePreProcessor;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
class MemberControllerTest extends abstractControllerTest {

    private final String BASE_URL = "/api/v1/members";
    @MockBean
    private MemberService memberService;
    @MockBean
    private MemberMapper mapper;

    @Autowired
    private Gson gson;

    @Test
    @DisplayName("회원가입")
    @WithMockUser(username = "test@gmail.com", roles = "USER")
    void createMember() throws Exception {
        // given
        MemberDto.Post post = createMemberPostDto();
        String json = gson.toJson(post);
        // when
        ResultActions resultActions = mockMvc.perform(post(BASE_URL)
                        .contentType("application/json")
                        .with(csrf())
                        .content(json))
                .andExpect(status().isCreated());
        // then
        resultActions.andDo(document("member-create",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                getRequestFieldsSnippet()
        ));
    }

    @Test
    @DisplayName("회원 가입 실패 - 데이터 양식 오류")
    @WithMockUser(username = "test@gmail.com", roles = "USER")
    void createMemberFail() throws Exception {
        // given
        MemberDto.Post post = createMemberPostDto();
        post.setEmail("test");
        String json = gson.toJson(post);
        // when
        ResultActions resultActions = mockMvc.perform(post(BASE_URL)
                        .contentType("application/json")
                        .content(json)
                        .with(csrf()))
                .andExpect(jsonPath("message").value("bad-request"))
                .andExpect(jsonPath("status").value(400))
                .andExpect(jsonPath("fieldErrors").isArray())
                .andExpect(jsonPath("fieldErrors[0].field").value("email"))
                .andExpect(jsonPath("fieldErrors[0].rejectedValue").value("test"))
                .andExpect(jsonPath("fieldErrors[0].reason").value("must match \"^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$\""))
                .andExpect(jsonPath("violationErrors").doesNotExist())
                .andExpect(status().isBadRequest());
        // then
        resultActions.andDo(document("member-create-fail",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                getRequestFieldsSnippet(),
                responseFields(
                        fieldWithPath("status").type(JsonFieldType.NUMBER).description("응답 상태"),
                        fieldWithPath("message").type(JsonFieldType.STRING).description("에러 메시지"),
                        fieldWithPath("fieldErrors").type(JsonFieldType.ARRAY).description("필드 에러"),
                        fieldWithPath("fieldErrors[].field").type(JsonFieldType.STRING).description("필드"),
                        fieldWithPath("fieldErrors[].rejectedValue").type(JsonFieldType.STRING).description("필드 값"),
                        fieldWithPath("fieldErrors[].reason").type(JsonFieldType.STRING).description("필드 에러 사유"),
                        fieldWithPath("violationErrors").type(JsonFieldType.NULL).description("바디 에러")
                )
        ));
    }

    @Test
    @DisplayName("회원 정보 GET")
    @WithMockUser(username = "test@gmail.com", roles = "USER")
    void getMember() throws Exception {
        // given
        MemberDto.Response response = MemberFactory.createMemberResponseDto();
        given(memberService.findMember(anyString())).willReturn(new Member());
        given(mapper.memberToResponseMemberDto(any(Member.class))).willReturn(response);
        // when

        ResultActions resultActions = mockMvc.perform(get(BASE_URL)
                        .contentType("application/json")
                        .headers(GeneratedToken.getMockHeaderToken())
                        .with(csrf()));
        resultActions = expectedResponse(resultActions , response);
        // then
        resultActions.andDo(document("member-get",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                requestHeaders(
                        headerWithName("Authorization").description("JWT 토큰")
                ),
                getResponseFieldsSnippet()
        ));


    }
    @Test
    @DisplayName("회원 정보 수정")
    @WithMockUser
    void updateMember() throws Exception {
        // given
        MemberDto.Patch put = createMemberPatchDto();
        String json = gson.toJson(put);
        // when
        ResultActions resultActions = mockMvc.perform(patch(BASE_URL)
                        .contentType("application/json")
                        .headers(GeneratedToken.getMockHeaderToken())
                        .with(csrf())
                        .content(json))
                .andExpect(status().isNoContent());
        // then
        resultActions.andDo(document("member-update",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                requestHeaders(
                        headerWithName("Authorization").description("JWT 토큰")
                ),
                requestFields(
                        fieldWithPath("fullName").type(JsonFieldType.STRING).description("이름"),
                        fieldWithPath("displayName").type(JsonFieldType.STRING).description("닉네임"),
                        fieldWithPath("aboutMe").type(JsonFieldType.STRING).description("자기소개"),
                        fieldWithPath("aboutMeTitle").type(JsonFieldType.STRING).description("자기소개 제목"),
                        fieldWithPath("twitterLink").type(JsonFieldType.STRING).description("트위터 링크"),
                        fieldWithPath("githubLink").type(JsonFieldType.STRING).description("깃허브 링크"),
                        fieldWithPath("websiteLink").type(JsonFieldType.STRING).description("웹사이트 링크"),
                        fieldWithPath("location").type(JsonFieldType.STRING).description("지역"),
                        fieldWithPath("imgUrl").type(JsonFieldType.STRING).description("이미지 링크")
                )
        ));
    }
    @Test
    @DisplayName("TAG 팔로우")
    @WithMockUser
    void followTag() throws Exception {
        // given
        String tag = "java";
        MemberDto.Response response = MemberFactory.createMemberResponseDto();
        response.setIsFollowingTags(List.of(tag));
        given(memberService.findMember(anyString())).willReturn(new Member());
        given(memberService.updateMemberTags(any(Member.class), anyString(), anyBoolean())).willReturn(new Member());
        given(mapper.memberToResponseMemberDto(any(Member.class))).willReturn(response);
        // when
        ResultActions resultActions = mockMvc.perform(patch(BASE_URL + "/tags/{tag}/{isFollow}", tag, true)
                        .contentType("application/json")
                        .headers(GeneratedToken.getMockHeaderToken())
                        .with(csrf()));
        resultActions = expectedResponse(resultActions, response);

        // then
        resultActions.andDo(document("member-follow-tag",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                requestHeaders(
                        headerWithName("Authorization").description("JWT 토큰")
                ),
                pathParameters(
                        parameterWithName("tag").description("팔로우할 태그"),
                        parameterWithName("isFollow").description("팔로우 여부")
                ),
                getResponseFieldsSnippet()
        ));
    }

    private static ResponseFieldsSnippet getResponseFieldsSnippet() {
        return responseFields(
                fieldWithPath("data").type(JsonFieldType.OBJECT).description("회원 정보"),
                fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("회원 ID"),
                fieldWithPath("data.email").type(JsonFieldType.STRING).description("이메일"),
                fieldWithPath("data.fullName").type(JsonFieldType.STRING).description("이름"),
                fieldWithPath("data.displayName").type(JsonFieldType.STRING).description("닉네임"),
                fieldWithPath("data.aboutMe").type(JsonFieldType.STRING).description("자기소개"),
                fieldWithPath("data.aboutMeTitle").type(JsonFieldType.STRING).description("자기소개 제목"),
                fieldWithPath("data.twitterLink").type(JsonFieldType.STRING).description("트위터 링크"),
                fieldWithPath("data.githubLink").type(JsonFieldType.STRING).description("깃허브 링크"),
                fieldWithPath("data.websiteLink").type(JsonFieldType.STRING).description("웹사이트 링크"),
                fieldWithPath("data.location").type(JsonFieldType.STRING).description("지역"),
                fieldWithPath("data.imgUrl").type(JsonFieldType.STRING).description("이미지 링크"),
                fieldWithPath("data.isFollowingTags").type(JsonFieldType.ARRAY).description("팔로우 태그"),
                fieldWithPath("data.isUnFollowingTags").type(JsonFieldType.ARRAY).description("언팔로우 태그")
        );
    }

    private static RequestFieldsSnippet getRequestFieldsSnippet() {
        return requestFields(
                fieldWithPath("email").type(JsonFieldType.STRING).description("이메일 / not null"),
                fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호 / not null"),
                fieldWithPath("fullName").type(JsonFieldType.STRING).description("이름 / not null"),
                fieldWithPath("displayName").type(JsonFieldType.STRING).description("닉네임 / not null"),
                fieldWithPath("tags").type(JsonFieldType.ARRAY).description("태그 / nullable")
        );
    }


    @Test
    @DisplayName("회원 Follow Tag , UnFollow Tag 삭제")
    @WithMockUser
    void deleteMemberTags() throws Exception {
        // given
        String tag = "java";
        MemberDto.Response response = MemberFactory.createMemberResponseDto();
        response.setIsFollowingTags(List.of("js"));
        given(memberService.findMember(anyString())).willReturn(new Member());
        given(memberService.deleteMemberTags(any(Member.class), anyString())).willReturn(new Member());
        given(mapper.memberToResponseMemberDto(any(Member.class))).willReturn(response);
        // when
        ResultActions resultActions = mockMvc.perform(delete(BASE_URL + "/tags/{tag}", tag)
                        .contentType("application/json")
                        .headers(GeneratedToken.getMockHeaderToken())
                        .with(csrf()))
                .andExpect(status().isNoContent());

        // then
        resultActions.andDo(document("member-delete-tag",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                requestHeaders(
                        headerWithName("Authorization").description("JWT 토큰")
                ),
                pathParameters(
                        parameterWithName("tag").description("팔로우할 태그")
                )
        ));
    }
}