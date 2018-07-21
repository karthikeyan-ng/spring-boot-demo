package com.techstack.spring.instantiation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
@Aspect
@Component
public class SingletonAspect {

	private int i = 0;
	
	@Pointcut("execution(* com.techstack.spring.instantiation.service..*(..))")
	public void serviceMethodExecuted() {};
	
	@AfterReturning(pointcut="serviceMethodExecuted()")
	public void returned(JoinPoint jp) {
		
		System.out.println(this.getClass().getName()+": After returning method executed in method '"+jp.toString()+"'.");
		System.out.println(this.getClass().getName()+": i is "+i);
		i++;
	}
}
