package com.overflow.stack.server.domain.answer;

import com.google.gson.Gson;
import com.overflow.stack.server.common.abstractControllerTest;
import com.overflow.stack.server.common.token.GeneratedToken;
import com.overflow.stack.server.domain.answer.controller.AnswerController;
import com.overflow.stack.server.domain.answer.dto.AnswerDto;
import com.overflow.stack.server.domain.answer.entity.Answer;
import com.overflow.stack.server.domain.answer.mapper.AnswerMapper;
import com.overflow.stack.server.domain.answer.service.AnswerServiceImpl;
import com.overflow.stack.server.domain.member.entity.Member;
import com.overflow.stack.server.domain.question.entity.Question;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.overflow.stack.server.util.ApiDocumentUtils.getRequestPreProcessor;
import static com.overflow.stack.server.util.ApiDocumentUtils.getResponsePreProcessor;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AnswerController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class AnswerControllerRestDocsTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnswerServiceImpl answerService;

    @MockBean
    private AnswerMapper answerMapper;

    @Autowired
    private Gson gson;

    private static final String BASE_URL = "/api/v1/answers";

    @Test
    @DisplayName("답변 생성")
    @WithMockUser
    public void createAnswerTest() throws Exception {
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime modifiedAt = createdAt;
        AnswerDto.Post post = new AnswerDto.Post("content");
        String content = gson.toJson(post);
        AnswerDto.Response responseDto =
                new AnswerDto.Response(1L,
                        "content",
                        0L,
                        1L,
                        "displayName",
                        "email",
                        createdAt,
                        modifiedAt);


        given(answerMapper.answerPostDtoToAnswer(Mockito.any(AnswerDto.Post.class))).willReturn(new Answer());

        given(answerService.createAnswer(Mockito.any(Answer.class), Mockito.anyString(), Mockito.anyLong())).willReturn(new Answer());

        given(answerMapper.answerToAnswerResponseDto(Mockito.any(Answer.class))).willReturn(responseDto);

        ResultActions actions =
                mockMvc.perform(
                        post(BASE_URL)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("questionId", "1")
                                .with(csrf())
                                .content(content)
                                .headers(GeneratedToken.getMockHeaderToken()));

        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.content").value(post.getContent()))
                .andDo(document("post-answer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestParameters(
                                parameterWithName("questionId").description("질문 식별자"),
                                parameterWithName("_csrf").description("csrf")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("내용")
                                )
                        ),

                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.answerId").type(JsonFieldType.NUMBER).description("답변 식별자"),
                                        fieldWithPath("data.content").type(JsonFieldType.STRING).description("내용"),
                                        fieldWithPath("data.voteResult").type(JsonFieldType.NUMBER).description("투표 결과"),
                                        fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
                                        fieldWithPath("data.displayName").type(JsonFieldType.STRING).description("작성자"),
                                        fieldWithPath("data.email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("답변 생성 일자"),
                                        fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("답변 수정 일자")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("답변 수정")
    @WithMockUser
    public void patchAnswerTest() throws Exception {
        long answerId = 1L;
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime modifiedAt = createdAt;
        AnswerDto.Patch patch = new AnswerDto.Patch(answerId, "content");
        String content = gson.toJson(patch);

        AnswerDto.Response responseDto =
                new AnswerDto.Response(1L, "content", 0L, 1L,"displayName","email", createdAt, modifiedAt);

        given(answerMapper.answerPatchDtoToAnswer(Mockito.any(AnswerDto.Patch.class))).willReturn(new Answer());
        given(answerService.updateAnswer(Mockito.any(Answer.class), Mockito.anyString())).willReturn(new Answer());
        given(answerMapper.answerToAnswerResponseDto(Mockito.any(Answer.class))).willReturn(responseDto);

        ResultActions actions =
                mockMvc.perform(
                        patch(BASE_URL + "/{answer-id}", answerId)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                                .headers(GeneratedToken.getMockHeaderToken())
                );

        actions
                .andExpect((status().isOk()))
                .andExpect(jsonPath("$.data.answerId").value(patch.getAnswerId()))
                .andExpect(jsonPath("$.data.content").value(patch.getContent()))
                .andDo(document("patch-answer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestHeaders(
                                headerWithName("Authorization").description("Bearer Token")
                        ),
                        pathParameters(
                                parameterWithName("answer-id").description("답변 식별자")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("답변 식별자").ignored(),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("내용").optional()
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.answerId").type(JsonFieldType.NUMBER).description("답변 식별자"),
                                        fieldWithPath("data.content").type(JsonFieldType.STRING).description("내용"),
                                        fieldWithPath("data.voteResult").type(JsonFieldType.NUMBER).description("투표 결과"),
                                        fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
                                        fieldWithPath("data.displayName").type(JsonFieldType.STRING).description("작성자"),
                                        fieldWithPath("data.email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("답변 생성 일자"),
                                        fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("답변 수정 일자")
                                )
                        )
                ));
    }



    @Test
    @DisplayName("답변 검색")
    @WithMockUser
    public void getAnswerTest() throws Exception {
        long answerId = 1L;
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime modifiedAt = createdAt;
        AnswerDto.Response responseDto = new AnswerDto.Response(1L,
                "content1", 0L, 1L,"displayName", "email", createdAt, modifiedAt);

        given(answerService.findAnswer(Mockito.anyLong())).willReturn(new Answer());
        given(answerMapper.answerToAnswerResponseDto(Mockito.any(Answer.class))).willReturn(responseDto);

        ResultActions actions =
                mockMvc.perform(
                        get(BASE_URL + "/{answer-id}", answerId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON));

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.answerId").value(responseDto.getAnswerId()))
                .andExpect(jsonPath("$.data.content").value(responseDto.getContent()))
                .andDo(document("get-answer",
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("answer-id").description("답변 식별자")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.answerId").type(JsonFieldType.NUMBER).description("답변 식별자"),
                                        fieldWithPath("data.content").type(JsonFieldType.STRING).description("답변"),
                                        fieldWithPath("data.voteResult").type(JsonFieldType.NUMBER).description("투표 결과"),
                                        fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
                                        fieldWithPath("data.displayName").type(JsonFieldType.STRING).description("작성자"),
                                        fieldWithPath("data.email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("답변 생성 일자"),
                                        fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("답변 수정 일자")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("나의 답변 검색")
    @WithMockUser
    public void getMyAnswerTest() throws Exception {
        Member member = new Member();
        member.setDisplayName("displayName1");
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime modifiedAt = createdAt;
        AnswerDto.Response responseDto1 =
                new AnswerDto.Response(1L, "content1", 2L, 1L, "displayName1","email", createdAt, modifiedAt);

        AnswerDto.Response responseDto2 =
                new AnswerDto.Response(2L, "content2", 2L, 1L, "displayName2","email", createdAt, modifiedAt);

        List<AnswerDto.Response> responseList = new ArrayList<>();
        responseList.add(responseDto1);
        responseList.add(responseDto2);

        List<Answer> answerList = new ArrayList<>();
        answerList.add(new Answer());
        answerList.add(new Answer());

        given(answerService.findMyAnswers(Mockito.anyString())).willReturn(answerList);
        given(answerMapper.answersToAnswerResponseDtos(Mockito.anyList())).willReturn(responseList);

        ResultActions actions =
                mockMvc.perform(
                        get(BASE_URL +"/myanswers")
                                .accept(MediaType.APPLICATION_JSON)
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                .headers(GeneratedToken.getMockHeaderToken())
                );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andDo(document("get-myanswers",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestHeaders(
                                headerWithName("Authorization").description("Bearer Token")
                        ),
                responseFields(
                        List.of(
                                fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터"),
                                fieldWithPath("data.[].answerId").type(JsonFieldType.NUMBER).description("답변 식별자"),
                                fieldWithPath("data.[].content").type(JsonFieldType.STRING).description("내용"),
                                fieldWithPath("data.[].voteResult").type(JsonFieldType.NUMBER).description("투표 결과"),
                                fieldWithPath("data.[].questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
                                fieldWithPath("data.[].displayName").type(JsonFieldType.STRING).description("작성자"),
                                fieldWithPath("data.[].email").type(JsonFieldType.STRING).description("이메일"),
                                fieldWithPath("data.[].createdAt").type(JsonFieldType.STRING).description("답변 생성 일자"),
                                fieldWithPath("data.[].modifiedAt").type(JsonFieldType.STRING).description("답변 수정 일자")
                        )
                )
        ));

    }

    @Test
    @DisplayName("전체 답변 검색")
    @WithMockUser
    public void getAnswersTest() throws Exception {
        Member member = new Member();
        member.setDisplayName("displayName1");
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime modifiedAt = createdAt;
        AnswerDto.Response responseDto1 =
                new AnswerDto.Response(
                        1L,
                        "content1",
                        2L,
                        1L,
                        "displayName1",
                        "email",
                        createdAt,
                        modifiedAt);

        AnswerDto.Response responseDto2 =
                new AnswerDto.Response(
                        2L,
                        "content2",
                        2L,
                        1L,
                        "displayName2",
                        "email",
                        createdAt,
                        modifiedAt);

        List<AnswerDto.Response> responseList = new ArrayList<>();
        responseList.add(responseDto1);
        responseList.add(responseDto2);

        List<Answer> answerList = new ArrayList<>();
        answerList.add(new Answer());
        answerList.add(new Answer());

        given(answerService.findAnswers()).willReturn(answerList);
        given(answerMapper.answersToAnswerResponseDtos(Mockito.anyList())).willReturn(responseList);

        ResultActions actions =
                mockMvc.perform(
                        get(BASE_URL)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andDo(document("get-answers",
                        getResponsePreProcessor(),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터"),
                                        fieldWithPath("data.[].answerId").type(JsonFieldType.NUMBER).description("답변 식별자"),
                                        fieldWithPath("data.[].content").type(JsonFieldType.STRING).description("내용"),
                                        fieldWithPath("data.[].voteResult").type(JsonFieldType.NUMBER).description("투표 결과"),
                                        fieldWithPath("data.[].questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
                                        fieldWithPath("data.[].displayName").type(JsonFieldType.STRING).description("작성자"),
                                        fieldWithPath("data.[].email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("data.[].createdAt").type(JsonFieldType.STRING).description("답변 생성 일자"),
                                        fieldWithPath("data.[].modifiedAt").type(JsonFieldType.STRING).description("답변 수정 일자")

                                )
                        )
                ));


    }

    @Test
    @WithMockUser
    @DisplayName("답변 삭제")
    public void deleteAnswerTest() throws Exception {
        long answerId = 1L;
        doNothing().when(answerService).deleteAnswer(Mockito.anyLong(), Mockito.anyString());

        ResultActions actions = mockMvc.perform(
                delete(BASE_URL + "/{answer-id}", answerId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .headers(GeneratedToken.getMockHeaderToken()));

        actions.andExpect(status().isNoContent())
                .andDo(document(
                                "delete-answer",
                                requestHeaders(
                                        headerWithName("Authorization").description("JWT 토큰")
                                ),
                                pathParameters(
                                        parameterWithName("answer-id").description("답변 식별자"))
                        )
                );

    }

    @Test
    @WithMockUser
    @DisplayName("답변 투표")
    public void voteAnswerTest() throws Exception {
        long answerId = 1L;
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime modifiedAt = createdAt;
        AnswerDto.Response responseDto = new AnswerDto.Response(answerId, "content", 0L, 1L,"displayName","email", createdAt, modifiedAt);
        given(answerService.voteAnswer(Mockito.anyLong(), Mockito.anyString(), Mockito.anyBoolean())).willReturn(new Answer());
        given(answerMapper.answerToAnswerResponseDto(Mockito.any(Answer.class))).willReturn(responseDto);

        ResultActions actions =
                mockMvc.perform(
                        patch(BASE_URL + "/votes/{answer-id}", answerId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("voteUp", "true")
                                .with(csrf())
                                .headers(GeneratedToken.getMockHeaderToken()));

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.answerId").value(1L))
                .andExpect(jsonPath("$.data.content").value("content"))
                .andDo(document("vote-answer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestHeaders(
                                headerWithName("Authorization").description("Bearer Token")
                        ),
                        requestParameters(
                                parameterWithName("voteUp").description("투표 종류"),
                                parameterWithName("_csrf").description("csrf")
                        ),
                        pathParameters(
                                parameterWithName("answer-id").description("답변 식별자")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.answerId").type(JsonFieldType.NUMBER).description("답변 식별자"),
                                        fieldWithPath("data.content").type(JsonFieldType.STRING).description("내용"),
                                        fieldWithPath("data.voteResult").type(JsonFieldType.NUMBER).description("투표 결과"),
                                        fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
                                        fieldWithPath("data.displayName").type(JsonFieldType.STRING).description("답변 작성자"),
                                        fieldWithPath("data.email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("답변 작성 일자"),
                                        fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("답변 수정 일자")
                                )
                        )));
    }
}
