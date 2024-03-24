package com.shu.backend.aspect;

import com.shu.backend.vo.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionHandlingAspect {

    @Around("execution(* com.shu.backend.controller..*(..))")
    public Object handleControllerExceptions(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (Exception e) {
            log.error("服务器异常, method = {}, Error message = {}", joinPoint.getSignature().getName(), e.getMessage());
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器异常");
        }
    }
}
