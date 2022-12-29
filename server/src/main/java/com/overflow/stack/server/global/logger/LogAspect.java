package com.overflow.stack.server.global.logger;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Slf4j
@Profile("local")
@Component
public class LogAspect {

    @Pointcut("execution(* com.overflow.stack..*(..))")
    public void all() {
    }
    @Pointcut("execution(* com.overflow.stack..*Controller.*(..))")
    public void controller() {
    }
    @Pointcut("execution(* com.overflow.stack..*Service.*(..))")
    public void service(){}
    @Around("all()")
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            return result;
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            log.info("log = {}" , joinPoint.getSignature());
            log.info("timeMs = {}", timeMs);
        }
    }
    @Before("controller() || service()")
    public void beforeLogic(JoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        log.info("method = {}", method.getName());

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if(arg != null) {
                log.info("type = {}", arg.getClass().getSimpleName());
                log.info("value = {}", arg);
            }

        }
    }


}
