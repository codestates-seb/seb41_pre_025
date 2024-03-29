package com.overflow.stack.server.auth.handler;

import com.overflow.stack.server.global.response.ErrorResponder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
// 권한이 없을 때  실행되는 핸들러
public class MemberAccessDeniedHandler implements AccessDeniedHandler {
   @Override
   public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws  ServletException, IOException {
      ErrorResponder.sendErrorResponse(response, HttpStatus.FORBIDDEN);
      log.warn("Forbidden error happened: {}", accessDeniedException.getMessage());
   }
}