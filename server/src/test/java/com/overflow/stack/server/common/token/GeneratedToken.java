package com.overflow.stack.server.common.token;

import com.overflow.stack.server.auth.token.AuthTokenProvider;

import java.util.List;

public class GeneratedToken {
    private static final String AUTHERIZATION = "Authorization";
    private static final String REFRESHTOKEN = "RefreshToken";
    public static String createToken(AuthTokenProvider authTokenProvider, String userId, String role) {
        return "Bearer " + authTokenProvider.createAccessToken(userId , List.of(role)).getToken();
    }
    public static String createMockToken() {
        return "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiZXhwIjoxNjcxNjg5Nzg4fQ.eFeEyh5F5ilhUfK28DzIxNPscqrlo5d9kNcOZYgbsUs";
    }
}
