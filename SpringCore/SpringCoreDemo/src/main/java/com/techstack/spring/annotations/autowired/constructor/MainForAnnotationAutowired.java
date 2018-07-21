package com.techstack.spring.annotations.autowired.constructor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForAnnotationAutowired {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		ClientBean bean = context.getBean(ClientBean.class);
        bean.work();
		
		context.registerShutdownHook();
	}

}
