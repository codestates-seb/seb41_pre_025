package com.overflow.stack.server.domain.member.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class AuthoritiesUtils {
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    public static Set<String> ADMINS_EMAIL;

    @Value("${admin.email}")
    public void setKey(String value) {
        ADMINS_EMAIL = Set.of(value.split(","));
    }
    // value 주입 필요한데 bean으로 관리 하기 싫은데 value 주입을 할려면 bean으로 관리해야 되네 큐ㅠ

    public static List<String> createRoles(String email) {
        if (ADMINS_EMAIL != null && ADMINS_EMAIL.contains(email)) {
            return List.of(ROLE_ADMIN, ROLE_USER);
        }

        return List.of(ROLE_USER);
    }

}
