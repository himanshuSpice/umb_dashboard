package com.spice.service.creation.config;

import java.sql.CallableStatement;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Aspect
@Component
public class LoggingAspect {

	private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

	@Before("execution(* com.spice.service.creation.controller.*.*(..)) || execution(* com.spice.service.creation.dao.*.*(..)) || execution(* com.spice.service.creation.service.*.*(..))")
	public void logBefore(JoinPoint joinpoint) {
		Object className = joinpoint.getTarget().getClass().getName();
		String methodName = joinpoint.getSignature().getName();
		log.info(className + "->" + methodName + "(" + Arrays.asList(joinpoint.getArgs()) + ")");
	}

	@AfterThrowing(pointcut = "execution(* com.spice.service.creation.dao.*.*(..))", throwing = "ex")
	public void logAfterThrowing(JoinPoint joinpoint, Exception ex) {
		Object className = joinpoint.getTarget().getClass().getName();
		String methodName = joinpoint.getSignature().getName();
		log.error(className + "->" + methodName, ex);
	}
	@AfterReturning(pointcut = "execution(* com.spice.service.creation.dao.*.logResponse(..))", returning = "stmt")
	public void runAfter(JoinPoint joinPoint, CallableStatement stmt) throws Throwable {
		log.info("resultSet ::", stmt.getResultSet());
	}
}
