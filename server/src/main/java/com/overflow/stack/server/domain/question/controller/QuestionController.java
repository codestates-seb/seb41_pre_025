package com.overflow.stack.server.domain.question.controller;

import com.overflow.stack.server.domain.member.entity.Member;
import com.overflow.stack.server.domain.member.service.MemberService;
import com.overflow.stack.server.domain.question.dto.QuestionDto;
import com.overflow.stack.server.domain.question.entity.Question;
import com.overflow.stack.server.domain.question.mapper.QuestionMapper;
import com.overflow.stack.server.domain.question.service.QuestionService;
import com.overflow.stack.server.global.response.ListResponse;
import com.overflow.stack.server.global.response.PageInfo;
import com.overflow.stack.server.global.response.PageResponseDto;
import com.overflow.stack.server.global.response.SingleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
@RequestMapping("/api/v1/questions")
public class QuestionController {
   private final QuestionService questionService;
   private final QuestionMapper mapper;


   public QuestionController(QuestionService questionService, QuestionMapper mapper) {
      this.questionService = questionService;
      this.mapper = mapper;
   }
   @PostMapping
   public ResponseEntity postQuestion(@Valid @RequestBody QuestionDto.Post questionPostDto,
                                      @AuthenticationPrincipal User members){
      Question question=questionService.createQuestion(mapper.questionPostDtoToQuestion(questionPostDto),members.getUsername());
      return new ResponseEntity<>(
              new SingleResponse<>(mapper.questionToQuestionResponseDto(question)),
              HttpStatus.CREATED);
   }
   @PatchMapping("/{question-id}")
   public ResponseEntity patchQuestion(@PathVariable("question-id") long questionId,
                                      @Valid @RequestBody QuestionDto.Patch questionPatchDto,
                                       @AuthenticationPrincipal User members){
      questionPatchDto.setQuestionId(questionId);
      Question question=questionService.updateQuestion(mapper.questionPatchDtoToQuestion(questionPatchDto),members.getUsername());
      return new ResponseEntity<>(
              new SingleResponse<>(mapper.questionToQuestionResponseDto(question)),
              HttpStatus.OK);
   }
   @PatchMapping("/votes/{question-id}")
   public ResponseEntity voteQeustion(@PathVariable("question-id") long questionId,
                                       @RequestParam boolean voteUp,
                                       @AuthenticationPrincipal User members){
      Question question=questionService.voteQuestion(questionId, members.getUsername(), voteUp);
      return new ResponseEntity<>(
              new SingleResponse<>(mapper.questionToQuestionResponseDto(question)),
              HttpStatus.OK);
   }

   @GetMapping("/{question-id}")
   public ResponseEntity getQuestion(@PathVariable("question-id") long questionId){
      Question question = questionService.findQuestion(questionId);

      return new ResponseEntity<>(
              new SingleResponse<>(mapper.questionToQuestionResponseDto(question)),
              HttpStatus.OK);
   }

   @GetMapping
   public ResponseEntity getQuestions(){
      List<Question>questions = questionService.findQuestions();
      return new ResponseEntity<>(
              new ListResponse<>(mapper.questionsToQuestionResponseDtos(questions)),
              HttpStatus.OK);
   }

   @DeleteMapping("/{question-id}")
   public ResponseEntity deleteQuestion(@PathVariable("question-id") long questionId,
                                        @AuthenticationPrincipal User members){
      questionService.deleteQuestion(questionId,members.getUsername());
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }
   @GetMapping("/search")
   public ResponseEntity searchQuestion(@RequestParam String keyword , @RequestParam String kind, @PageableDefault Pageable pageable){
      Page<Question> questions = questionService.searchQuestion(keyword, kind , pageable);
      return new ResponseEntity<>(
              new PageResponseDto<>(mapper.questionsToQuestionResponseDtos(questions.getContent()), new PageInfo(questions.getPageable(), questions.getTotalElements())),
              HttpStatus.OK);
   }
}
