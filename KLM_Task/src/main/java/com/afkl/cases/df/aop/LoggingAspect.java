package com.afkl.cases.df.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @author NitinSingh
 *
 */
@Aspect
@Configuration
public class LoggingAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * It logs the information before calling any method of controller
	 * 
	 * @param joinPoint
	 */
	@Before("execution(* com.afkl.cases.df.controller..*(..))")
	public void before(JoinPoint joinPoint) {
		System.out.println("Before running loggingAdvice on method=" + joinPoint.toString());
		logger.debug("Before running loggingAdvice on method=" + joinPoint.toString());
		logger.debug("Agruments Passed=" + Arrays.toString(joinPoint.getArgs()));
	}

	/**
	 * It logs the information after execution of any method of controller
	 * 
	 * @param joinPoint
	 */
	@After("execution(* com.afkl.cases.df.controller..*(..))")
	public void after(JoinPoint joinPoint) {
		System.out.println("After running loggingAdvice on method=" + joinPoint.toString());
		logger.debug("After running loggingAdvice on method=" + joinPoint.toString());
	}

}
