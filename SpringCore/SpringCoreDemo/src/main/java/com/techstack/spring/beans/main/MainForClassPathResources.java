package com.techstack.spring.beans.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.techstack.spring.account.entity.Account;
import com.techstack.spring.account.service.AccountService;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForClassPathResources {

	public static void main(String[] args) {

		/**
		 * Instantiating a container
		 */
		ApplicationContext context;
		
		/**
		 * ClassPath resources, here
		 * services.xml, daos.xml and entity.xml are various spring beans configuration files.
		 * To load all these configuration files using ClassPathXmlApplicationContext all thses
		 * xmls should be placed in scr/main/resources
		 */
		//context = new ClassPathXmlApplicationContext("services.xml", "daos.xml", "entity.xml");
		
		//by using internal config import
		context = new ClassPathXmlApplicationContext("baseConfig.xml");
		
		//3.AnnotationConfig (Refer AppConfig.java)
		//context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		
		Account account = context.getBean(Account.class);
		account.setAccountNumber(1235);
		account.setAccountHolderName("Karthi");
		account.setActive(true);
		
		AccountService accountService = context.getBean(AccountService.class);
		System.out.println(accountService.doValudate(account));
		
	}

}
