package com.overflow.stack.server.domain.member.controller;

import com.google.gson.Gson;
import com.overflow.stack.server.auth.token.AuthTokenProvider;
import com.overflow.stack.server.common.abstractControllerTest;
import com.overflow.stack.server.domain.member.dto.MemberDto;
import com.overflow.stack.server.domain.member.entity.Member;
import com.overflow.stack.server.domain.member.factory.MemberFactory;
import com.overflow.stack.server.domain.member.mapper.MemberMapper;
import com.overflow.stack.server.domain.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;

import static com.overflow.stack.server.domain.member.factory.MemberFactory.createMemberPostDto;
import static com.overflow.stack.server.util.ApiDocumentUtils.getRequestPreProcessor;
import static com.overflow.stack.server.util.ApiDocumentUtils.getResponsePreProcessor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
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
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("data.email").value(response.getEmail()))
                .andExpect(jsonPath("data.fullName").value(response.getFullName()))
                .andExpect(jsonPath("data.displayName").value(response.getDisplayName()))
                .andExpect(jsonPath("data.aboutMe").value(response.getAboutMe()))
                .andExpect(jsonPath("data.aboutMeTitle").value(response.getAboutMeTitle()))
                .andExpect(jsonPath("data.twitterLink").value(response.getTwitterLink()))
                .andExpect(jsonPath("data.githubLink").value(response.getGithubLink()))
                .andExpect(jsonPath("data.websiteLink").value(response.getWebsiteLink()))
                .andExpect(jsonPath("data.location").value(response.getLocation()))
                .andExpect(jsonPath("data.imgUrl").value(response.getImgUrl()));
        // then
        resultActions.andDo(document("member-get",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                getResponseFieldsSnippet()
        ));


    }

    private static ResponseFieldsSnippet getResponseFieldsSnippet() {
        return responseFields(
                fieldWithPath("data").type(JsonFieldType.OBJECT).description("회원 정보"),
                fieldWithPath("data.email").type(JsonFieldType.STRING).description("이메일"),
                fieldWithPath("data.fullName").type(JsonFieldType.STRING).description("이름"),
                fieldWithPath("data.displayName").type(JsonFieldType.STRING).description("닉네임"),
                fieldWithPath("data.aboutMe").type(JsonFieldType.STRING).description("자기소개"),
                fieldWithPath("data.aboutMeTitle").type(JsonFieldType.STRING).description("자기소개 제목"),
                fieldWithPath("data.twitterLink").type(JsonFieldType.STRING).description("트위터 링크"),
                fieldWithPath("data.githubLink").type(JsonFieldType.STRING).description("깃허브 링크"),
                fieldWithPath("data.websiteLink").type(JsonFieldType.STRING).description("웹사이트 링크"),
                fieldWithPath("data.location").type(JsonFieldType.STRING).description("지역"),
                fieldWithPath("data.imgUrl").type(JsonFieldType.STRING).description("이미지 링크")
        );
    }

    private static RequestFieldsSnippet getRequestFieldsSnippet() {
        return requestFields(
                fieldWithPath("email").type(JsonFieldType.STRING).description("이메일 / not null"),
                fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호 / not null"),
                fieldWithPath("fullName").type(JsonFieldType.STRING).description("이름 / not null"),
                fieldWithPath("displayName").type(JsonFieldType.STRING).description("닉네임 / not null"),
                fieldWithPath("aboutMe").type(JsonFieldType.STRING).description("자기소개 / nullable"),
                fieldWithPath("aboutMeTitle").type(JsonFieldType.STRING).description("자기소개 제목 / nullable"),
                fieldWithPath("twitterLink").type(JsonFieldType.STRING).description("트위터 링크 / nullable"),
                fieldWithPath("githubLink").type(JsonFieldType.STRING).description("깃허브 링크 / nullable"),
                fieldWithPath("websiteLink").type(JsonFieldType.STRING).description("웹사이트 링크 / nullable"),
                fieldWithPath("location").type(JsonFieldType.STRING).description("지역 / nullable"),
                fieldWithPath("imgUrl").type(JsonFieldType.STRING).description("이미지 링크 /  nullable")
        );
    }



}