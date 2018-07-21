package com.techstack.spring.di.methodinjection.arbitarymethodreplacement;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForArbitraryMethodReplacement {

	public static void main(String[] args) {

		ApplicationContext context;
		context = new ClassPathXmlApplicationContext("depinjection_methodInjection_arbitraryMethodReplacement.xml");
		
		System.out.println("After initialization");

		MyValueCalculator calc = context.getBean(MyValueCalculator.class);
		System.out.println(calc.computeValue("sample input text"));
	}

}
