package com.overflow.stack.server.question;

import com.overflow.stack.server.common.token.GeneratedToken;
import com.overflow.stack.server.domain.member.entity.Member;
import com.overflow.stack.server.domain.question.controller.QuestionController;
import com.overflow.stack.server.domain.question.dto.QuestionDto;
import com.overflow.stack.server.domain.question.entity.Question;
import com.overflow.stack.server.domain.question.mapper.QuestionMapper;
import com.overflow.stack.server.domain.question.service.QuestionServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.google.gson.Gson;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.overflow.stack.server.util.ApiDocumentUtils.getRequestPreProcessor;
import static com.overflow.stack.server.util.ApiDocumentUtils.getResponsePreProcessor;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(QuestionController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class QuestionControllerRestDocsTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionServiceImpl questionService;

    @MockBean
    private QuestionMapper mapper;

    @Autowired
    private Gson gson;
    private static final String url = "/api/v1/questions";

    @Test
    @DisplayName("질문 등록")
    @WithMockUser
    public void postQuestionTest() throws Exception {
        QuestionDto.Post post = new QuestionDto.Post("title1", "content1", Set.of("tag1, tag2"));
        String content = gson.toJson(post);

        QuestionDto.response responseDto =
                new QuestionDto.response(1L,
                        "title1",
                        "content1",
                        0L,
                        "displayName1",
                        Set.of("tag1, tag2"));


        given(mapper.questionPostDtoToQuestion(Mockito.any(QuestionDto.Post.class))).willReturn(new Question());

        given(questionService.createQuestion(Mockito.any(Question.class),Mockito.anyString())).willReturn(new Question());

        given(mapper.questionToQuestionResponseDto(Mockito.any(Question.class))).willReturn(responseDto);


        ResultActions actions =
                mockMvc.perform(
                        post(url)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(csrf())
                                .content(content)
                                .headers(GeneratedToken.getMockHeaderToken())
                );

        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.title").value(post.getTitle()))
                .andExpect(jsonPath("$.data.content").value(post.getContent()))
                .andDo(document("post-question",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestHeaders(
                                headerWithName("Authorization").description("Bearer Token")
                        ),
                        // request body
                        requestFields(
                                List.of(
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("내용"),
                                        fieldWithPath("tag").type(JsonFieldType.ARRAY).description("태그")
                                )
                        ),
                        // response body
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("data.title").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("data.content").type(JsonFieldType.STRING).description("내용"),
                                        fieldWithPath("data.voteResult").type(JsonFieldType.NUMBER).description("투표 결과"),
                                        fieldWithPath("data.displayName").type(JsonFieldType.STRING).description("작성자"),
                                        fieldWithPath("data.tag").type(JsonFieldType.ARRAY).description("태그")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("질문 수정")
    @WithMockUser
    public void patchQuestionTest() throws Exception {
        long questionId=1L;
        QuestionDto.Patch patch = new QuestionDto.Patch(questionId, "title1", "content1",Set.of("tag1, tag2"));
        String content = gson.toJson(patch);
        QuestionDto.response responseDto =
                new QuestionDto.response(1L,
                        "title1",
                        "content1",
                        0L,
                        "displayName1",
                        Set.of("tag1, tag2"));


        given(mapper.questionPatchDtoToQuestion(Mockito.any(QuestionDto.Patch.class))).willReturn(new Question());

        given(questionService.updateQuestion(Mockito.any(Question.class))).willReturn(new Question());

        given(mapper.questionToQuestionResponseDto(Mockito.any(Question.class))).willReturn(responseDto);


        ResultActions actions =
                mockMvc.perform(
                        patch(url+"/{question-id}", questionId)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                                .headers(GeneratedToken.getMockHeaderToken())
                );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.questionId").value(patch.getQuestionId()))
                .andExpect(jsonPath("$.data.title").value(patch.getTitle()))
                .andExpect(jsonPath("$.data.content").value(patch.getContent()))
                .andDo(document("patch-question",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestHeaders(
                                headerWithName("Authorization").description("Bearer Token")
                        ),
                        pathParameters(
                                parameterWithName("question-id").description("질문 식별자")
                        ),
                        // request body
                        requestFields(
                                List.of(
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("질문 식별자").ignored(),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("제목").optional(),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("내용").optional(),
                                        fieldWithPath("tag").type(JsonFieldType.ARRAY).description("태그").optional()
                                )
                        ),
                        // response body
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
                                        fieldWithPath("data.title").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("data.content").type(JsonFieldType.STRING).description("내용"),
                                        fieldWithPath("data.voteResult").type(JsonFieldType.NUMBER).description("투표 결과"),
                                        fieldWithPath("data.displayName").type(JsonFieldType.STRING).description("작성자"),
                                        fieldWithPath("data.tag").type(JsonFieldType.ARRAY).description("태그")
                                )
                        )
                ));
    }
    @Test
    @DisplayName("질문 투표")
    @WithMockUser
    public void voteQuestionTest() throws Exception {
        long questionId=1L;
        QuestionDto.response responseDto =
                new QuestionDto.response(1L,
                        "title1",
                        "content1",
                        0L,
                        "displayName1",
                        Set.of("tag1, tag2"));

        given(questionService.voteQuestion(Mockito.anyLong(), Mockito.anyString(), Mockito.anyBoolean())).willReturn(new Question());

        given(mapper.questionToQuestionResponseDto(Mockito.any(Question.class))).willReturn(responseDto);


        ResultActions actions =
                mockMvc.perform(
                        patch(url+"/votes/{question-id}", questionId)
                                .param("voteUp","true")
                                .accept(MediaType.APPLICATION_JSON)
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                .headers(GeneratedToken.getMockHeaderToken())
                );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.questionId").value(1L))
                .andExpect(jsonPath("$.data.title").value("title1"))
                .andExpect(jsonPath("$.data.content").value("content1"))
                .andDo(document("vote-question",
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
                                parameterWithName("question-id").description("질문 식별자")
                        ),
                        // response body
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
                                        fieldWithPath("data.title").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("data.content").type(JsonFieldType.STRING).description("내용"),
                                        fieldWithPath("data.voteResult").type(JsonFieldType.NUMBER).description("투표 결과"),
                                        fieldWithPath("data.displayName").type(JsonFieldType.STRING).description("작성자"),
                                        fieldWithPath("data.tag").type(JsonFieldType.ARRAY).description("태그")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("질문 조회")
    @WithMockUser
    public void getQuestionTest() throws Exception {
        long questionId=1L;

        QuestionDto.response responseDto =
                new QuestionDto.response(1L,
                        "title1",
                        "content1",
                        0L,
                        "displayName1",
                        Set.of("tag1, tag2"));

        given(questionService.findQuestion(Mockito.anyLong())).willReturn(new Question());

        given(mapper.questionToQuestionResponseDto(Mockito.any(Question.class))).willReturn(responseDto);


        ResultActions actions =
                mockMvc.perform(
                        get(url+"/{question-id}", questionId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.questionId").value(responseDto.getQuestionId()))
                .andExpect(jsonPath("$.data.title").value(responseDto.getTitle()))
                .andExpect(jsonPath("$.data.content").value(responseDto.getContent()))
                .andExpect(jsonPath("$.data.voteResult").value(responseDto.getVoteResult()))
                .andDo(document("get-question",
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("question-id").description("질문 식별자")
                        ),
                        // response body
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
                                        fieldWithPath("data.title").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("data.content").type(JsonFieldType.STRING).description("내용"),
                                        fieldWithPath("data.voteResult").type(JsonFieldType.NUMBER).description("투표 결과"),
                                        fieldWithPath("data.displayName").type(JsonFieldType.STRING).description("작성자"),
                                        fieldWithPath("data.tag").type(JsonFieldType.ARRAY).description("태그")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("모든 질문 조회")
    @WithMockUser
    public void getQuestionsTest() throws Exception {
        Member member = new Member();
        member.setDisplayName("displayName1");
        QuestionDto.response responseDto =
                new QuestionDto.response(1L,
                        "title1",
                        "content1",
                        0L,
                        "displayName1",
                        Set.of("tag1","tag2"));

        QuestionDto.response responseDto2 =
                new QuestionDto.response(2L,
                        "title2",
                        "content2",
                        0L,
                        "displayName2",
                        Set.of("tag3, tag4"));
        List<QuestionDto.response> responseList=new ArrayList<>();
        responseList.add(responseDto);
        responseList.add(responseDto2);

        List<Question> list =new ArrayList<>();
        list.add(new Question());
        list.add(new Question());

        given(questionService.findQuestions()).willReturn(list);

        given(mapper.questionsToQuestionResponseDtos(Mockito.anyList())).willReturn(responseList);


        ResultActions actions =
                mockMvc.perform(
                        get(url)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andDo(document("get-questions",
                        getResponsePreProcessor(),
                        // response body
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터"),
                                        fieldWithPath("data.[].questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
                                        fieldWithPath("data.[].title").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("data.[].content").type(JsonFieldType.STRING).description("내용"),
                                        fieldWithPath("data.[].voteResult").type(JsonFieldType.NUMBER).description("투표 결과"),
                                        fieldWithPath("data.[].displayName").type(JsonFieldType.STRING).description("작성자"),
                                        fieldWithPath("data.[].tag").type(JsonFieldType.ARRAY).description("태그")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("질문 삭제")
    @WithMockUser
    public void deleteQuestionTest() throws Exception {
        long questionId=1L;


        doNothing().when(questionService).deleteQuestion(Mockito.anyLong());

        ResultActions actions =
                mockMvc.perform(
                        delete(url+"/{question-id}", questionId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(csrf())
                                .headers(GeneratedToken.getMockHeaderToken())
                );

        actions
                .andExpect(status().isNoContent())
                .andDo(document("delete-question",
                        requestHeaders(
                                headerWithName("Authorization").description("JWT 토큰")
                        ),
                        pathParameters(
                                parameterWithName("question-id").description("질문 식별자")
                        )
                ));
    }

}
