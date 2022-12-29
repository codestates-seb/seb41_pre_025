package com.overflow.stack.server.domain.question.controller;

import com.overflow.stack.server.domain.question.entity.Question;
import com.overflow.stack.server.domain.question.mapper.QuestionMapper;
import com.overflow.stack.server.domain.question.service.QuestionService;
import com.overflow.stack.server.global.response.ListResponse;
import com.overflow.stack.server.global.response.PageInfo;
import com.overflow.stack.server.global.response.PageResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
public class QuestionSearchController {
    private final QuestionService questionService;
    private final QuestionMapper mapper;

    public QuestionSearchController(QuestionService questionService, QuestionMapper mapper) {
        this.questionService = questionService;
        this.mapper = mapper;
    }

}
