package com.techstack.spring.scopes.custom;

import org.springframework.beans.factory.config.Scope;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForCustomeScopeExample {

	public static void main(String[] args) {

		/**
		 * Register Created Custom Bean Scope using code. Refer beans.xml for
		 * xml configs
		 */
		ConfigurableApplicationContext context;
		context = new ClassPathXmlApplicationContext("depinjection_customescopes.xml");

		Scope scope = new MyThreadScope();
		context.getBeanFactory().registerScope("myThreadScope", scope);

		MyBean myBean = context.getBean(MyBean.class);
		System.out.println(myBean.getName());
		System.out.println("All registered Scopes are : ");
		for (String scopeValue : context.getBeanFactory().getRegisteredScopeNames()) {
			System.out.println(scopeValue);
		}
		context.close();

	}

}
