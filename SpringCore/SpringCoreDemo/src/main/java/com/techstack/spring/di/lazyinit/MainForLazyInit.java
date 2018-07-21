package com.techstack.spring.di.lazyinit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForLazyInit {

	public static void main(String[] args) {

		ApplicationContext context;
		context = new ClassPathXmlApplicationContext("depinjection_lazyinit.xml");
		
		System.out.println("After initialization");

		/**
		 * Based on the above impacts, some applications required beans to initiate only when they are required.
		 * Spring provides an attribute called lazy-init to inform the Spring IOC container for not creating that 
		 * bean at the start up. lazy-init will be set as true to indicate the container. The beans will be created 
		 * only when requested. Consider when there is a bean which is initiated at the startup. But, it has depends-on 
		 * attribute pointing to the bean which is set as lazy-init=”true”. This case the can will be initiated without 
		 * considering lazy-init value.
		 */
		BeanTwo bean = context.getBean(BeanTwo.class);
		bean.doSomthing();
		
		BeanThree beanThree = context.getBean(BeanThree.class);
		beanThree.doSomthing();

	}

}
