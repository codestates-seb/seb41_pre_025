package com.overflow.stack.server.auth.token;


import com.overflow.stack.server.global.exception.CustomLogicException;
import com.overflow.stack.server.global.exception.ExceptionCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
public class AuthTokenProvider {

    private final Key key;
    private static final String AUTHORITIES_KEY = "role";
    private final long tokenValidTime;
    private final long refreshTokenValidTime;
    public AuthTokenProvider(String secret, long tokenValidTime, long refreshTokenValidTime) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.tokenValidTime = tokenValidTime;
        this.refreshTokenValidTime = refreshTokenValidTime;
    }

    public AuthToken createAuthToken(String id, Date expiry) {
        return new AuthToken(id, expiry, key);
    }
    public AuthToken createAccessToken(String id) {
        return new AuthToken(id, new Date(System.currentTimeMillis() + tokenValidTime), key);
    }
    public AuthToken createRefreshToken(String id) {
        return new AuthToken(id, new Date(System.currentTimeMillis() + refreshTokenValidTime), key);
    }
    public AuthToken createAuthToken(String id, String role, Date expiry) {
        return new AuthToken(id, role, expiry, key);
    }

    public AuthToken convertAuthToken(String token) {
        return new AuthToken(token, key);
    }

    public Authentication getAuthentication(AuthToken authToken) {

        if(authToken.validate()) {

            Claims claims = authToken.getTokenClaims();
            Collection<? extends GrantedAuthority> authorities =
                    Arrays.stream(new String[]{claims.get(AUTHORITIES_KEY).toString()})
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());

            log.debug("claims subject := [{}]", claims.getSubject());
            User principal = new User(claims.getSubject(), "", authorities);

            return new UsernamePasswordAuthenticationToken(principal, authToken, authorities);
        } else {
            throw new CustomLogicException(ExceptionCode.MEMBER_NONE);
        }
    }
}
