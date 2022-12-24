package com.overflow.stack.server.domain.answer.controller;

import com.overflow.stack.server.domain.answer.dto.AnswerDto;
import com.overflow.stack.server.domain.answer.entity.Answer;
import com.overflow.stack.server.domain.answer.mapper.AnswerMapper;
import com.overflow.stack.server.domain.answer.service.AnswerService;
import com.overflow.stack.server.global.response.SingleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

   @PostMapping
   public ResponseEntity postAnswer(@Valid @RequestBody AnswerDto.Post answerPostDto) {
      Answer answer = answerService.createAnswer(answerMapper.answerPostDtoToAnswer(answerPostDto));
      return new ResponseEntity<>(
              new SingleResponse<>(answerMapper.answerToAnswerResponseDto(answer)),
              HttpStatus.CREATED);
   }

   @PatchMapping("/{answer-id}")
   public ResponseEntity patchAnswer(@PathVariable("answer-id") long answerId,
                                     @Valid @RequestBody AnswerDto.Patch answerPatchDto) {
      answerPatchDto.setAnswerId(answerId);
      Answer answer = answerService.updateAnswer(answerMapper.answerPatchDtoToAnswer(answerPatchDto));
      return new ResponseEntity(new SingleResponse<>(answerMapper.answerToAnswerResponseDto(answer)), HttpStatus.OK);
   }


}