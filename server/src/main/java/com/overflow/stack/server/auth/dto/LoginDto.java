package com.overflow.stack.server.auth.dto;

import lombok.Getter;

@Getter
// 로그인 정보를 받아올 데이터 클래스
public class LoginDto {
    private String email;
    private String password;
}
