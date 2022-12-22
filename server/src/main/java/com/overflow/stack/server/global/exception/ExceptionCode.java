package com.overflow.stack.server.global.exception;

import lombok.Getter;
@Getter
public enum ExceptionCode {

   TITLE_NONE(400, "TITLE_NONE"),
   MEMBER_NONE(404, "MEMBER_NONE"),

   MEMBER_DUPLICATE(409, "MEMBER_DUPLICATE"),
   QUESTION_NOT_FOUND(409, "Question not found"),

   TOKEN_INVALID(401 , "TOKEN_INVALID");

   @Getter
   private final int code;

   @Getter
   private final String message;

   ExceptionCode(int code, String message) {
      this.code = code;
      this.message = message;
   }
}
