package com.techstack.spring.annotations.inject;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForAnnotationInject {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		ClientBean bean = context.getBean(ClientBean.class);
        bean.work();
		
		context.registerShutdownHook();
	}

}
