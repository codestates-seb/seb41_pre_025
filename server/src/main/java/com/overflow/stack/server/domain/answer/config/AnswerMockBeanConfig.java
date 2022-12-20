package com.overflow.stack.server.domain.answer.config;

import com.overflow.stack.server.domain.answer.repository.AnswerRepository;
import com.overflow.stack.server.domain.answer.repository.MockAnswerRepository;
import com.overflow.stack.server.domain.answer.service.AnswerService;
import com.overflow.stack.server.domain.answer.service.MockAnswerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("mock")
// Mock Data 를 위한 Bean 설정
public class AnswerMockBeanConfig {
    @Bean
    public AnswerService answerMockService() {
        return new MockAnswerService(this.answerMockRepository());
    }
    @Bean
    public AnswerRepository answerMockRepository() {
        return new MockAnswerRepository();
    }
}
