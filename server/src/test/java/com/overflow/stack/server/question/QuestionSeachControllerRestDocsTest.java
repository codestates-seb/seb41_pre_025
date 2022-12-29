package com.overflow.stack.server.question;

import com.google.gson.Gson;
import com.overflow.stack.server.domain.question.controller.QuestionController;
import com.overflow.stack.server.domain.question.mapper.QuestionMapper;
import com.overflow.stack.server.domain.question.service.QuestionService;
import com.overflow.stack.server.domain.question.service.QuestionServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
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
    void searchQuestionTest(){
        //given

        //when
        //then
    }
}
