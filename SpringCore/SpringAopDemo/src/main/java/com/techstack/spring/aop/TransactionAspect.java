/**
 * 
 */
package com.techstack.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author KARTHIKEYAN N
 *
 */
@Aspect
@Component
@Order(1)
public class TransactionAspect {
	
	 /**
	  * Declaring before advice for all transfer methods whose taking three arguments of any type 
	  * of all classes in the package com.techstack.spring.service
	  * @param jp
	  * @throws Throwable
	  */
	 @Before("execution(* com.techstack.spring.service.*.transfer(*,*,*))")
	 public void beforeAdviceForTransferMethods(JoinPoint jp) throws Throwable {
		 System.out.println("****TransactionAspect.beforeAdviceForTransferMethods() " + jp.getSignature().getName());
	 }

}
