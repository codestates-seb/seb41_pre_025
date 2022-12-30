package com.overflow.stack.server.domain.answer.controller;

import com.overflow.stack.server.domain.answer.dto.AnswerDto;
import com.overflow.stack.server.domain.answer.entity.Answer;
import com.overflow.stack.server.domain.answer.mapper.AnswerMapper;
import com.overflow.stack.server.domain.answer.service.AnswerService;
import com.overflow.stack.server.global.response.ListResponse;
import com.overflow.stack.server.global.response.SingleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
   public ResponseEntity postAnswer(@Valid @RequestBody AnswerDto.Post answerPostDto,
                                    @RequestParam long questionId,
                                    @AuthenticationPrincipal User members) {
      Answer answer = answerService.createAnswer(answerMapper.answerPostDtoToAnswer(answerPostDto), members.getUsername(), questionId);
      return new ResponseEntity<>(
              new SingleResponse<>(answerMapper.answerToAnswerResponseDto(answer)),
              HttpStatus.CREATED);
   }

   @PatchMapping("/{answer-id}")
   public ResponseEntity patchAnswer(@PathVariable("answer-id") long answerId,
                                     @Valid @RequestBody AnswerDto.Patch answerPatchDto,
                                     @AuthenticationPrincipal User members) {
      answerPatchDto.setAnswerId(answerId);
      Answer answer = answerService.updateAnswer(answerMapper.answerPatchDtoToAnswer(answerPatchDto), members.getUsername());
      return new ResponseEntity(new SingleResponse<>(answerMapper.answerToAnswerResponseDto(answer)), HttpStatus.OK);
   }

   @GetMapping("/{answer-id}")
   public ResponseEntity getAnswer(@PathVariable("answer-id") long answerId) {
      Answer answer = answerService.findAnswer(answerId);
      return new ResponseEntity(new SingleResponse<>(answerMapper.answerToAnswerResponseDto(answer)), HttpStatus.OK);
   }

   @GetMapping("/myanswers")
   public ResponseEntity getMyAnswers(@AuthenticationPrincipal User members) {
      List<Answer> myAnswers = answerService.findMyAnswers(members.getUsername());
      return new ResponseEntity(new ListResponse<>(answerMapper.answersToAnswerResponseDtos(myAnswers)), HttpStatus.OK);
   }

   @GetMapping
   public ResponseEntity getAnswers() {
      List<Answer> answers = answerService.findAnswers();
      return new ResponseEntity(new ListResponse<>(answerMapper.answersToAnswerResponseDtos(answers)), HttpStatus.OK);
   }

   @DeleteMapping("/{answer-id}")
   public ResponseEntity deleteAnswer(@PathVariable("answer-id") long answerId,
                                      @AuthenticationPrincipal User members) {
      answerService.deleteAnswer(answerId, members.getUsername());
      return new ResponseEntity(HttpStatus.NO_CONTENT);
   }

   @PatchMapping("/votes/{answer-id}")
   public ResponseEntity voteAnswer(@PathVariable("answer-id") long answerId,
                                    @RequestParam boolean voteUp,
                                    @AuthenticationPrincipal User members) {
      Answer answer = answerService.voteAnswer(answerId, members.getUsername(), voteUp);
      return new ResponseEntity(new SingleResponse<>(answerMapper.answerToAnswerResponseDto(answer)), HttpStatus.OK);
   }
}