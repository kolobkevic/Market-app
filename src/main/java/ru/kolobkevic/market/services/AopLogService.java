package ru.kolobkevic.market.services;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AopLogService {
    private Long startTime;
    private String name;

    @Before("execution(* ru.kolobkevic.market.services..*.*(..))")
    private void startTimeCounting() {
        startTime = System.currentTimeMillis();
//        name =
    }

    @After("execution(* ru.kolobkevic.market.services..*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        name = joinPoint.getTarget().getClass().getName().replaceAll("ru.kolobkevic.market.services.", "");
        startTime = System.currentTimeMillis() - startTime;
        System.out.println(name + ": " + startTime + " ms");
    }
}
