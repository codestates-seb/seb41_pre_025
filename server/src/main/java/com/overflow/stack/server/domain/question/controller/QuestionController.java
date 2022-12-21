package com.overflow.stack.server.domain.question.controller;

import com.overflow.stack.server.domain.question.dto.QuestionDto;
import com.overflow.stack.server.domain.question.entity.Question;
import com.overflow.stack.server.domain.question.mapper.QuestionMapper;
import com.overflow.stack.server.domain.question.repository.JpaQuestionRepository;
import com.overflow.stack.server.domain.question.repository.QuestionRepository;
import com.overflow.stack.server.domain.question.service.QuestionService;
import com.overflow.stack.server.domain.question.service.QuestionServiceImpl;
import com.overflow.stack.server.global.response.ListResponse;
import com.overflow.stack.server.global.response.SingleResponse;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/v1/questions")
public class QuestionController {
   private final QuestionServiceImpl questionService;
   private final QuestionMapper mapper;

   public QuestionController(QuestionServiceImpl questionService, QuestionMapper mapper) {
      this.questionService = questionService;
      this.mapper = mapper;
   }
   @PostMapping
   public ResponseEntity postQuestion(@Valid @RequestBody QuestionDto.Post questionPostDto){
      Question question=questionService.createQuestion(mapper.questionPostDtoToQuestion(questionPostDto));
      return new ResponseEntity<>(
              new SingleResponse<>(mapper.questionToQuestionResponseDto(question)),
              HttpStatus.CREATED);
   }
   @PatchMapping("/{question-id}")
   public ResponseEntity patchQuestion(@PathVariable("question-id") long questionId,
                                      @Valid @RequestBody QuestionDto.Patch questionPatchDto){
      questionPatchDto.setQuestionId(questionId);
      Question question=questionService.updateQuestion(mapper.questionPatchDtoToQuestion(questionPatchDto));
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
   public ResponseEntity deleteQuestion(@PathVariable("question-id") long questionId){
      questionService.deleteQuestion(questionId);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }
}
