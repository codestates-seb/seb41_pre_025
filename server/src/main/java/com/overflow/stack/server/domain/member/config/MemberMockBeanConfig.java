package com.overflow.stack.server.domain.member.config;

import com.overflow.stack.server.domain.member.repository.MemberRepository;
import com.overflow.stack.server.domain.member.repository.MockMemberRepository;
import com.overflow.stack.server.domain.member.service.MemberService;
import com.overflow.stack.server.domain.member.service.MockMemberService;
import com.overflow.stack.server.domain.question.repository.MockQuestionRepository;
import com.overflow.stack.server.domain.question.repository.QuestionRepository;
import com.overflow.stack.server.domain.question.service.MockQuestionService;
import com.overflow.stack.server.domain.question.service.QuestionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("mock")
// Mock Data 를 위한 Bean 설정
public class MemberMockBeanConfig {
    @Bean
    public MemberService memberMockService() {
        return new MockMemberService(this.memberMockRepository());
    }
    @Bean
    public MemberRepository memberMockRepository() {
        return new MockMemberRepository();
    }


}
