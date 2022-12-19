package com.example.bestpractices.configuration.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Aspect
@Component
@Slf4j
public class LoggingInterceptor {

    @Around("execution(* com.example.bestpractices..*.*(..))")
    public Object executionTime(ProceedingJoinPoint point) throws Throwable {
        if(hasParams(point)) {
            log.info("opening method", kv("method", point.getSignature().getName()),
                    kv("class",point.getSignature().getDeclaringTypeName()), "params",
                    kv("method-params", getParamsAndValuesFromMethod(point)));
        }
        else{
            log.info("opening method", kv("method", point.getSignature().getName()),
                    kv("class",point.getSignature().getDeclaringTypeName()));
        }

        long startTime = System.currentTimeMillis();

        Object object = point.proceed();

        long endTime = System.currentTimeMillis();
        log.info("closing method", kv("method", point.getSignature().getName()),
                kv("class",point.getSignature().getDeclaringTypeName()),
                kv("execution-time in milliseconds", endTime-startTime));
        return object;
    }

    @AfterReturning(pointcut = "execution(* com.example.bestpractices..*.*(..))", returning = "result")
    public void logAfter(JoinPoint point, Object result) {
        if(result!=null){
            log.info("method-response", kv("method", point.getSignature().getName()),
                    kv("class", point.getSignature().getDeclaringTypeName()),
                    kv("response", result));
        }
    }

    private Boolean hasParams(ProceedingJoinPoint point){
        if(point.getArgs()!= null && point.getArgs().length != 0){
            MethodSignature methodSignature = (MethodSignature) point.getSignature();
            Map<String, Object> paramsAndValues = new HashMap<>();
            String[] parameterNames = methodSignature.getParameterNames();
            return parameterNames != null;
        }
        else return false;

    }

    private Map<String, Object> getParamsAndValuesFromMethod(ProceedingJoinPoint point){
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Map<String, Object> paramsAndValues = new HashMap<>();
        String[] parameterNames = methodSignature.getParameterNames();
        Object[] args = point.getArgs();

        for(int i=0; i<args.length; i++){
            paramsAndValues.put(parameterNames[i], args[i]);
        }

        return paramsAndValues;
    }
}
