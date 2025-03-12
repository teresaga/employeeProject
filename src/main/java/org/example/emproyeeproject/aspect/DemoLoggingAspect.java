package org.example.emproyeeproject.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {

    // setup logger
    Logger myLogger = Logger.getLogger(getClass().getName());

    // setup pointcut declarations
    @Pointcut("execution(* org.example.emproyeeproject.controller.*.*(..))")
    private void forControllerPackage() {}

    @Pointcut("execution(* org.example.emproyeeproject.dao.*.*(..))")
    private void forDaoPackage() {}

    @Pointcut("execution(* org.example.emproyeeproject.service.*.*(..))")
    private void forServicePackage() {}

    @Pointcut("forControllerPackage() || forDaoPackage() || forServicePackage()")
    private void forAppFlow() {}

    // add @Before advice
    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {

        // display method we are calling
        String methodSignature = joinPoint.getSignature().toShortString();
        myLogger.info("=====>> in @Before: calling method: " + methodSignature);

        // display the arguments
        Object[] args = joinPoint.getArgs();

        Arrays.stream(args).forEach(tempArg -> myLogger.info("=====>> argument: " + tempArg));
    }

    // add @AfterReturning advice
    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "theResult"
    )
    public void afterReturning(JoinPoint joinPoint, Object theResult) {

        // display method we are calling
        String methodSignature = joinPoint.getSignature().toShortString();
        myLogger.info("=====>> in @AfterReturning: calling method: " + methodSignature);

        // display result
        myLogger.info("=====>> Result: " + theResult);
    }


}
