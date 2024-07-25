package com.shu.backend.aspect;

import com.shu.backend.utils.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author Jaanai（XZY）
 * @date 2024/7/23 17:12
 */
@Slf4j
@Component

public class LogAspect {

    @Before("execution(* com.shu.backend.controller..*(..)) " +
            "&& !execution(* com.shu.backend.controller.UserController.*(..)) ")
    public void addLogInfo(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();

        // 通过方法的签名对象获取目标方法的详细信息
        String methodName = signature.getName();
//        System.out.println(UserContextHolder.getUserInfo());
        log.info("user {} accessed {}", UserContextHolder.getUserInfo().getUserId(),methodName);;
    }
}
