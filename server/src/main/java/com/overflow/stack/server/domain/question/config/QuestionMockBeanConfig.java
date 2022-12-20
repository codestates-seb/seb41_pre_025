package com.overflow.stack.server.domain.question.config;

import com.overflow.stack.server.domain.answer.repository.AnswerRepository;
import com.overflow.stack.server.domain.answer.repository.MockAnswerRepository;
import com.overflow.stack.server.domain.answer.service.AnswerService;
import com.overflow.stack.server.domain.answer.service.MockAnswerService;
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
public class QuestionMockBeanConfig {
    @Bean
    public QuestionService questionMockService() {
        return new MockQuestionService(this.questionMockRepository());
    }
    @Bean
    public QuestionRepository questionMockRepository() {
        return new MockQuestionRepository();
    }


}
