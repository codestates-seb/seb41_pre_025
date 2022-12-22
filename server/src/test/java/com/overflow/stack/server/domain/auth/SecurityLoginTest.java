package com.overflow.stack.server.domain.auth;

import com.google.gson.Gson;
import com.overflow.stack.server.auth.dto.LoginDto;
import com.overflow.stack.server.domain.member.entity.Member;
import com.overflow.stack.server.domain.member.factory.MemberFactory;
import com.overflow.stack.server.domain.member.repository.JpaMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.overflow.stack.server.util.ApiDocumentUtils.getRequestPreProcessor;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureRestDocs
@AutoConfigureMockMvc
public class SecurityLoginTest {
    @Autowired
    private JpaMemberRepository jpaMemberRepository;
    @Autowired
    private Gson gson;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MockMvc mockMvc;
    @AfterEach
    public void tearDown() {
        jpaMemberRepository.deleteAll();
    }
    @Test
    @DisplayName("로그인 성공")
    public void loginSuccess() throws Exception {
        // given
        Member member = MemberFactory.createMember(passwordEncoder);
        jpaMemberRepository.save(member);
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail(member.getEmail());
        loginDto.setPassword("password");
        // when
        ResultActions resultActions = mockMvc.perform(post("/api/v1/auth/login")
                .contentType("application/json")
                .content(gson.toJson(loginDto)))
                .andExpect(status().isOk());
        // then
        resultActions.andDo(document("auth-login" ,
                getRequestPreProcessor(),
                requestFields(
                        fieldWithPath("email").description("이메일"),
                        fieldWithPath("password").description("비밀번호")
                ),
                responseHeaders(
                        headerWithName("Authorization").description("Authorization"),
                        headerWithName("RefreshToken").description("RefreshToken accessToken 재발급 용")
                )
        ));
    }
}
