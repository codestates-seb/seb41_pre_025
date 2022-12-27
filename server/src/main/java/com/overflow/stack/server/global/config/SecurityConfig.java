package com.overflow.stack.server.global.config;

import com.overflow.stack.server.auth.filter.CustomFilterConfigurer;
import com.overflow.stack.server.auth.handler.MemberAccessDeniedHandler;
import com.overflow.stack.server.auth.handler.MemberAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
public class SecurityConfig {
    private final CustomFilterConfigurer customFilterConfigurer;

    public SecurityConfig(CustomFilterConfigurer customFilterConfigurer) {
        this.customFilterConfigurer = customFilterConfigurer;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .cors().and()
                .formLogin().disable()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().apply(customFilterConfigurer)
                .and().exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                .authenticationEntryPoint(authenticationEntryPoint())
                .and().authorizeRequests(
                        authorize -> authorize
                                .antMatchers("/docs/index.html").permitAll()
                                .antMatchers(HttpMethod.POST, "/api/v1/members").permitAll()
                                .antMatchers("/api/v1/members/**").authenticated()
                                .antMatchers(HttpMethod.POST , "/api/v1/questions").authenticated()
                                .antMatchers("/h2/**").permitAll()
                                .anyRequest().permitAll()
                                // 작은 것 부터 큰 순서

                );
        return http.build();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new MemberAuthenticationEntryPoint();
    }
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new MemberAccessDeniedHandler();
    }
}
