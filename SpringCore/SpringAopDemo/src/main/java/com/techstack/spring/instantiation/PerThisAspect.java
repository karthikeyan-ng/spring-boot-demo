/**
 * 
 */
package com.techstack.spring.instantiation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author KARTHIKEYAN N
 *
 */
@Aspect("perthis(within(com.techstack.spring.instantiation.service.Service))")
//@Aspect("pertarget(within(com.techstack.spring.instantiation.service.Service))")
@Component
@Scope(value = "prototype")
public class PerThisAspect {

	private int i = 0;
	
	//@Pointcut("execution(* com.techstack.spring.instantiation.service.Service.convert(..))")
	@Pointcut("within(com.techstack.spring.instantiation.service.Service)")
	public void serviceMethodExecuted() {};
	
	@Before("serviceMethodExecuted()")
	public void returned(JoinPoint jp) {
		
		System.out.println("PerThisAspect ==>" + this);
		//System.out.println(this.getClass().getName()+": After returning method executed in method '"+jp.toString()+"'.");
		System.out.println(this.getClass().getName()+": i is "+i);
		i++;
	}
}
