package com.kovaliv.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Slf4j
@Aspect
@Component
public class CountTimeAspect {
    @Pointcut("@annotation(CountTime)")
    public void pointCut() {
    }

    @Around(value = "pointCut()")
    public Object after(ProceedingJoinPoint joinPoint) throws Throwable {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        log.info("Start time " + time.toString());

        Object result = joinPoint.proceed();

        Timestamp endTime = new Timestamp(System.currentTimeMillis());
        log.info("Method " + joinPoint.getSignature() + ".");
        log.info("Work time " + (endTime.getTime() - time.getTime()) + " milliseconds");

        return result;
    }
}
