package com.overflow.stack.server.global.exception;

import lombok.Getter;
@Getter
public enum ExceptionCode {

   TITLE_NONE(400, "TITLE_NONE"),
   TODO_NONE(404, "MEMBER_NONE"),
   QUESTION_NOT_FOUND(409, "Question not found");
   @Getter
   private final int code;

   @Getter
   private final String message;

   ExceptionCode(int code, String message) {
      this.code = code;
      this.message = message;
   }
}
