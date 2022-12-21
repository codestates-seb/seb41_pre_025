package com.overflow.stack.server.question;

import com.overflow.stack.server.domain.question.controller.QuestionController;
import com.overflow.stack.server.domain.question.dto.QuestionDto;
import com.overflow.stack.server.domain.question.entity.Question;
import com.overflow.stack.server.domain.question.mapper.QuestionMapper;
import com.overflow.stack.server.domain.question.service.QuestionServiceImpl;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static com.overflow.stack.server.util.ApiDocumentUtils.getRequestPreProcessor;
import static com.overflow.stack.server.util.ApiDocumentUtils.getResponsePreProcessor;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
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

    @Test
    public void postQuestionTest() throws Exception {

        QuestionDto.Post post = new QuestionDto.Post("title1", "content1", 0L);
        String content = gson.toJson(post);

        QuestionDto.response responseDto =
                new QuestionDto.response(1L,
                        "title1",
                        "content1",
                        0L);


        given(mapper.questionPostDtoToQuestion(Mockito.any(QuestionDto.Post.class))).willReturn(new Question());

        given(questionService.createQuestion(Mockito.any(Question.class))).willReturn(new Question());

        given(mapper.questionToQuestionResponseDto(Mockito.any(Question.class))).willReturn(responseDto);


        ResultActions actions =
                mockMvc.perform(
                        post("/api/v1/questions")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.title").value(post.getTitle()))
                .andExpect(jsonPath("$.data.content").value(post.getContent()))
                .andExpect(jsonPath("$.data.voteResult").value(post.getVoteResult()))
                .andDo(document("post-question",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        // request body
                        requestFields(
                                List.of(
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("내용"),
                                        fieldWithPath("voteResult").type(JsonFieldType.NUMBER).description("투표 결과")
                                )
                        ),
                        // response body
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("data.title").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("data.content").type(JsonFieldType.STRING).description("내용"),
                                        fieldWithPath("data.voteResult").type(JsonFieldType.NUMBER).description("투표 결과")
                                )
                        )
                ));
    }

    @Test
    public void patchQuestionTest() throws Exception {
        long questionId=1L;
        QuestionDto.Patch patch = new QuestionDto.Patch(questionId, "title1", "content1", 0L);
        String content = gson.toJson(patch);

        QuestionDto.response responseDto =
                new QuestionDto.response(1L,
                        "title1",
                        "content1",
                        0L);


        given(mapper.questionPatchDtoToQuestion(Mockito.any(QuestionDto.Patch.class))).willReturn(new Question());

        given(questionService.updateQuestion(Mockito.any(Question.class))).willReturn(new Question());

        given(mapper.questionToQuestionResponseDto(Mockito.any(Question.class))).willReturn(responseDto);


        ResultActions actions =
                mockMvc.perform(
                        patch("/api/v1/questions/{question-id}", questionId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.questionId").value(patch.getQuestionId()))
                .andExpect(jsonPath("$.data.title").value(patch.getTitle()))
                .andExpect(jsonPath("$.data.content").value(patch.getContent()))
                .andExpect(jsonPath("$.data.voteResult").value(patch.getVoteResult()))
                .andDo(document("patch-question",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("question-id").description("질문 식별자")
                        ),
                        // request body
                        requestFields(
                                List.of(
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("질문 식별자").ignored(),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("제목").optional(),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("내용").optional(),
                                        fieldWithPath("voteResult").type(JsonFieldType.NUMBER).description("투표 결과").optional()
                                )
                        ),
                        // response body
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
                                        fieldWithPath("data.title").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("data.content").type(JsonFieldType.STRING).description("내용"),
                                        fieldWithPath("data.voteResult").type(JsonFieldType.NUMBER).description("투표 결과")
                                )
                        )
                ));
    }

    @Test
    public void getQuestionTest() throws Exception {
        long questionId=1L;

        QuestionDto.response responseDto =
                new QuestionDto.response(1L,
                        "title1",
                        "content1",
                        0L);

        given(questionService.findQuestion(Mockito.anyLong())).willReturn(new Question());

        given(mapper.questionToQuestionResponseDto(Mockito.any(Question.class))).willReturn(responseDto);


        ResultActions actions =
                mockMvc.perform(
                        get("/api/v1/questions/{question-id}", questionId)
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
                                        fieldWithPath("data.voteResult").type(JsonFieldType.NUMBER).description("투표 결과")
                                )
                        )
                ));
    }

    @Test
    public void getQuestionsTest() throws Exception {

        QuestionDto.response responseDto =
                new QuestionDto.response(1L,
                        "title1",
                        "content1",
                        0L);

        QuestionDto.response responseDto2 =
                new QuestionDto.response(2L,
                        "title2",
                        "content2",
                        0L);
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
                        get("/api/v1/questions")
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
                                        fieldWithPath("data.[].voteResult").type(JsonFieldType.NUMBER).description("투표 결과")
                                )
                        )
                ));
    }

    @Test
    public void deleteQuestionTest() throws Exception {
        long questionId=1L;


        doNothing().when(questionService).deleteQuestion(Mockito.anyLong());

        ResultActions actions =
                mockMvc.perform(
                        delete("/api/v1/questions/{question-id}", questionId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                );

        actions
                .andExpect(status().isNoContent())
                .andDo(document("delete-question",
                        pathParameters(
                                parameterWithName("question-id").description("질문 식별자")
                        )
                ));
    }

}
