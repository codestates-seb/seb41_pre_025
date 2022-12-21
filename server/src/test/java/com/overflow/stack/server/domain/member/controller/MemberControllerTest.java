package com.overflow.stack.server.domain.member.controller;

import com.google.gson.Gson;
import com.overflow.stack.server.common.abstractControllerTest;
import com.overflow.stack.server.domain.member.dto.MemberDto;
import com.overflow.stack.server.domain.member.mapper.MemberMapper;
import com.overflow.stack.server.domain.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.test.web.servlet.ResultActions;

import static com.overflow.stack.server.domain.member.factory.MemberFactory.createMemberPostDto;
import static com.overflow.stack.server.util.ApiDocumentUtils.getRequestPreProcessor;
import static com.overflow.stack.server.util.ApiDocumentUtils.getResponsePreProcessor;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
class MemberControllerTest extends abstractControllerTest {

    private final String BASE_URL = "/api/v1/members";
    @MockBean
    private  MemberService memberService;
    @MockBean
    private MemberMapper mapper;

    @Autowired
    private Gson gson;

    @Test
    @DisplayName("회원가입")
    void createMember() throws Exception {
        // given
        MemberDto.Post post = createMemberPostDto();
        String json = gson.toJson(post);
        // when
        ResultActions resultActions = mockMvc.perform(post(BASE_URL)
                .contentType("application/json")
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
    void createMemberFail() throws Exception {
        // given
        MemberDto.Post post = createMemberPostDto();
        post.setEmail("test");
        String json = gson.toJson(post);
        // when
        ResultActions resultActions = mockMvc.perform(post(BASE_URL)
                .contentType("application/json")
                .content(json))
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