package com.overflow.stack.server.global.response;


import com.overflow.stack.server.global.exception.CustomLogicException;
import lombok.Getter;
import lombok.ToString;
import org.springframework.validation.BindingResult;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@ToString
@Getter
public class ErrorResponse {
   private int status;
   private String message;
   private final List<FieldError> fieldErrors;
   private final List<ConstraintViolationError> violationErrors;



   private ErrorResponse(final List<FieldError> fieldErrors,
                         final List<ConstraintViolationError> violationErrors) {
      this.fieldErrors = fieldErrors;
      this.violationErrors = violationErrors;
   }

   public ErrorResponse(int status, String message, List<FieldError> fieldErrors, List<ConstraintViolationError> violationErrors) {
      this.status = status;
      this.message = message;
      this.fieldErrors = fieldErrors;
      this.violationErrors = violationErrors;
   }

   public static ErrorResponse of(BindingResult bindingResult) {
        return new ErrorResponse(FieldError.of(bindingResult), null);
   }
   public static ErrorResponse of(Set<ConstraintViolation<?>> violations) {
      return new ErrorResponse(null, ConstraintViolationError.of(violations));
   }
   public static ErrorResponse of(CustomLogicException e) {
      return new ErrorResponse(e.getExceptionCode().getCode(), e.getExceptionCode().getMessage(), null, null);
   }


   @Getter
   public static class FieldError {
      private String field;
      private Object rejectedValue;
      private String reason;

      private FieldError(String field, Object rejectedValue, String reason) {
         this.field = field;
         this.rejectedValue = rejectedValue;
         this.reason = reason;
      }

      public static List<FieldError> of(BindingResult bindingResult) {
         final List<org.springframework.validation.FieldError> fieldErrors =
                 bindingResult.getFieldErrors();
         return fieldErrors.stream()
                 .map(error -> new FieldError(
                         error.getField(),
                         error.getRejectedValue() == null ?
                                 "" : error.getRejectedValue().toString(),
                         error.getDefaultMessage()))
                 .collect(Collectors.toList());
      }
   }

   @Getter
   public static class ConstraintViolationError {
      private String propertyPath;
      private Object rejectedValue;
      private String reason;

      private ConstraintViolationError(String propertyPath, Object rejectedValue,
                                       String reason) {
         this.propertyPath = propertyPath;
         this.rejectedValue = rejectedValue;
         this.reason = reason;
      }

      public static List<ConstraintViolationError> of(
              Set<ConstraintViolation<?>> constraintViolations) {
         return constraintViolations.stream()
                 .map(constraintViolation -> new ConstraintViolationError(
                         constraintViolation.getPropertyPath().toString(),
                         constraintViolation.getInvalidValue().toString(),
                         constraintViolation.getMessage()
                 )).collect(Collectors.toList());
      }
   }


}
