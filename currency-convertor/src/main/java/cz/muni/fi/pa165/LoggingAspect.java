package cz.muni.fi.pa165;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import javax.inject.Named;

@Named
@Aspect
public class LoggingAspect {

    @Around("execution(public * *(..))")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.nanoTime();
        System.err.println("Calling method: "
                + joinPoint.getSignature());


        Object result = joinPoint.proceed();

        System.err.println("Method finished: "
                + joinPoint.getSignature());
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.err.println("Duration: " + duration + " ms");
        return result;
    }

}