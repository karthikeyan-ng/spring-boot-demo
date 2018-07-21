package com.techstack.spring.annotations.required;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForAnnotationRequired {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		ClientBean bean = context.getBean(ClientBean.class);
        bean.work();
		
		context.registerShutdownHook();
	}

}
