package com.overflow.stack.server.domain.question.controller;

import com.overflow.stack.server.domain.question.mapper.QuestionMapper;
import com.overflow.stack.server.domain.question.service.QuestionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/v1/questions")
public class QuestionController {
   private final QuestionService questionService;
   private final QuestionMapper questionMapper;

   public QuestionController(QuestionService questionService, QuestionMapper questionMapper) {
      this.questionService = questionService;
      this.questionMapper = questionMapper;
   }
}
