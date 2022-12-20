package com.overflow.stack.server.domain.answer.controller;

import com.overflow.stack.server.domain.answer.mapper.AnswerMapper;
import com.overflow.stack.server.domain.answer.service.AnswerService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/v1/answers")
public class AnswerController {
   private final AnswerService answerService;
   private final AnswerMapper answerMapper;

   public AnswerController(AnswerService answerService, AnswerMapper answerMapper) {
      this.answerService = answerService;
      this.answerMapper = answerMapper;
   }
}
