package com.techstack.spring.beanfactorypostprocessor.customized;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForBeanFactoryPostProcessorCustomized {

	public static void main(String[] args) {

		AbstractApplicationContext context;
		context = new ClassPathXmlApplicationContext("beanFactoryPostProcessorCustomized.xml");
		
		CredentialsInfo credentials = context.getBean(CredentialsInfo.class);
		System.out.println(credentials.getUsername());
		context.registerShutdownHook();
	}

}
