package com.overflow.stack.server.auth.filter;

import com.overflow.stack.server.auth.handler.CustomAuthenticationFailureHandler;
import com.overflow.stack.server.auth.handler.CustomAuthenticationSuccessHandler;
import com.overflow.stack.server.auth.token.AuthTokenProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
    private final AuthTokenProvider authTokenProvider;

    public CustomFilterConfigurer(AuthTokenProvider authTokenProvider) {
        this.authTokenProvider = authTokenProvider;
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authTokenProvider, authenticationManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/v1/auth/login");
        jwtAuthenticationFilter.setAuthenticationSuccessHandler(new CustomAuthenticationSuccessHandler());
        jwtAuthenticationFilter.setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler());
        JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(authTokenProvider);
        builder.addFilter(jwtAuthenticationFilter)
                 .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);
    }
}
