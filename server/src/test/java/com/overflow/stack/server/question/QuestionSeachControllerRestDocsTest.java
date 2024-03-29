package com.overflow.stack.server.question;

import com.google.gson.Gson;
import com.overflow.stack.server.domain.member.entity.Member;
import com.overflow.stack.server.domain.question.controller.QuestionController;
import com.overflow.stack.server.domain.question.dto.QuestionDto;
import com.overflow.stack.server.domain.question.entity.Question;
import com.overflow.stack.server.domain.question.mapper.QuestionMapper;
import com.overflow.stack.server.domain.question.service.QuestionService;
import com.overflow.stack.server.domain.question.service.QuestionServiceImpl;
import com.overflow.stack.server.global.response.PageResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.overflow.stack.server.util.ApiDocumentUtils.getResponsePreProcessor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(QuestionController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class QuestionSeachControllerRestDocsTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionService questionService;

    @MockBean
    private QuestionMapper mapper;

    @Autowired
    private Gson gson;
    private static final String url = "/api/v1/questions/search";


    @Test
    @WithMockUser
    @DisplayName("질문 검색")
    void searchQuestionTest() throws Exception {
        Member member = new Member();
        member.setDisplayName("displayName1");
        LocalDateTime createdAt = LocalDateTime.now();
        QuestionDto.response responseDto =
                new QuestionDto.response(1L,
                        "title1",
                        "content1",
                        0L,
                        "displayName1",
                        "test@gmail.com",
                        createdAt,
                        createdAt,
                        Set.of("tag1", "tag2"),
                        null,
                        0L);

        QuestionDto.response responseDto2 =
                new QuestionDto.response(2L,
                        "title2",
                        "content2",
                        0L,
                        "displayName2",
                        "test2@gmail.com",
                        createdAt,
                        createdAt,
                        Set.of("tag3, tag4"),
                        null,
                        0L);
        List<QuestionDto.response> responseList = new ArrayList<>();
        responseList.add(responseDto);
        responseList.add(responseDto2);

        List<Question> list = new ArrayList<>();
        list.add(new Question());
        list.add(new Question());

        given(questionService.searchQuestion(anyString(), anyString(), any()))
                .willReturn(
                        new PageImpl<>(list,
                                PageRequest.of(0, 10,
                                        Sort.by("questionId").descending()
                                ), 2)
                );

        given(mapper.questionsToQuestionResponseDtos(Mockito.anyList())).willReturn(responseList);


        ResultActions actions =
                mockMvc.perform(
                        get(url + "?kind=tag&keyword=python")
                                .param("page", "5")
                                .param("size", "10")
                                .param("sort", "questionId,desc") // <-- no space after comma!!!
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                );
        actions.andDo(print());
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andDo(document("search-questions",
                        getResponsePreProcessor(),
                        requestParameters(
                                parameterWithName("keyword").description("검색어"),
                                parameterWithName("kind").description("검색 종류 / title, content, tag,writer(displayName)/ null 이면 빈리스트"),
                                parameterWithName("page").description("페이지 번호"),
                                parameterWithName("size").description("페이지 사이즈"),
                                parameterWithName("sort").description("정렬 기준 questionId,desc")
                        ),
                        // response body
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터"),
                                        fieldWithPath("data.[].questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
                                        fieldWithPath("data.[].title").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("data.[].content").type(JsonFieldType.STRING).description("내용"),
                                        fieldWithPath("data.[].voteResult").type(JsonFieldType.NUMBER).description("투표 결과"),
                                        fieldWithPath("data.[].displayName").type(JsonFieldType.STRING).description("질문 작성자"),
                                        fieldWithPath("data.[].email").type(JsonFieldType.STRING).description("질문 작성자 이메일"),
                                        fieldWithPath("data.[].createdAt").type(JsonFieldType.STRING).description("질문 작성 일자"),
                                        fieldWithPath("data.[].modifiedAt").type(JsonFieldType.STRING).description("질문 수정 일자"),
                                        fieldWithPath("data.[].tag").type(JsonFieldType.ARRAY).description("태그"),
                                        fieldWithPath("data.[].answers").type(JsonFieldType.NULL).description("답변"),
                                        fieldWithPath("data.[].answerCount").type(JsonFieldType.NUMBER).description("답변 개수"),
                                        fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("페이지 정보"),
                                        fieldWithPath("pageInfo.currentPage").type(JsonFieldType.NUMBER).description("페이지 번호"),
                                        fieldWithPath("pageInfo.totalPage").type(JsonFieldType.NUMBER).description("총 페이지 수"),
                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("총 데이터 수"),
                                        fieldWithPath("pageInfo.pageSize").type(JsonFieldType.NUMBER).description("페이지 사이즈"),
                                        fieldWithPath("pageInfo.first").type(JsonFieldType.BOOLEAN).description("첫 페이지 여부"),
                                        fieldWithPath("pageInfo.last").type(JsonFieldType.BOOLEAN).description("마지막 페이지 여부"),
                                        fieldWithPath("pageInfo.currentElements").type(JsonFieldType.NUMBER).description("현재 페이지 데이터 수")

                                )

                        )
                ));
    }
}

