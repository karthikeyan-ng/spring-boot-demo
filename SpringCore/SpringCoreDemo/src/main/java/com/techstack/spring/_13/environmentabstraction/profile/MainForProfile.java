package com.techstack.spring._13.environmentabstraction.profile;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForProfile {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.getEnvironment().setActiveProfiles("development");
		ctx.register(SomeConfig.class, StandaloneDataConfig.class, JndiDataConfig.class);
		ctx.refresh();

	}

}
