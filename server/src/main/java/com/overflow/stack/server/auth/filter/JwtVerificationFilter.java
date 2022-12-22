package com.overflow.stack.server.auth.filter;

import com.overflow.stack.server.auth.token.AuthToken;
import com.overflow.stack.server.auth.token.AuthTokenProvider;
import com.overflow.stack.server.auth.utils.HeaderUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtVerificationFilter extends OncePerRequestFilter {
   private final AuthTokenProvider tokenProvider;

   public JwtVerificationFilter(AuthTokenProvider tokenProvider) {
      this.tokenProvider = tokenProvider;
   }

   @Override
   protected void doFilterInternal(
           HttpServletRequest request,
           HttpServletResponse response,
           FilterChain filterChain)  throws ServletException, IOException {

      String tokenStr = HeaderUtil.getAccessToken(request);
      AuthToken token = tokenProvider.convertAuthToken(tokenStr);

      if (token.validate()) {
         Authentication authentication = tokenProvider.getAuthentication(token);
         SecurityContextHolder.getContext().setAuthentication(authentication);
      }

      filterChain.doFilter(request, response);
   }
   @Override
   protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
      String tokenStr = HeaderUtil.getAccessToken(request);
      return tokenStr == null; // (6-2)
   }
}
