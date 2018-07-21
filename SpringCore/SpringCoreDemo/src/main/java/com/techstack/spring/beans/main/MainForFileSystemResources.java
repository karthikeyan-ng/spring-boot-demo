package com.techstack.spring.beans.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.techstack.spring.account.entity.Account;
import com.techstack.spring.account.entity.Customer;
import com.techstack.spring.account.service.AccountService;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForFileSystemResources {

	public static void main(String[] args) {

		/**
		 * Instantiating a container
		 */
//		ApplicationContext context;
		
		/**
		 * FileSystem XML resources, here
		 * services.xml, daos.xml and entity.xml are various spring beans configuration files.
		 * To load all these configuration files using FileSystemXmlApplicationContext user should 
		 * give fully qualified path for each configuration.
		 */
//		context = new FileSystemXmlApplicationContext("C:/temp/beans/services.xml", 
//				"C:/temp/beans/daos.xml", "C:/temp/beans/entity.xml");
		
		/**
		 * by using internal config import
		 */
//		context = new FileSystemXmlApplicationContext("C:/temp/beans/baseConfig.xml");
		
//====================================
		/**
		 * Parameters:
		 * configLocations - array of file paths
		 * refresh - whether to automatically refresh the context, loading all bean definitions and creating all singletons. 
		 * 		   Alternatively, call refresh manually after further configuring the context.
		 */
		final FileSystemXmlApplicationContext context = 
				new FileSystemXmlApplicationContext(new String[]{"C:/temp/beans/baseConfig.xml"}, true);
		
		context.refresh();
//====================================		
		
		Account account = context.getBean(Account.class);
		account.setAccountNumber(1235);
		account.setAccountHolderName("Karthi");
		account.setActive(true);
		
		AccountService accountService = context.getBean(AccountService.class);
		System.out.println(accountService.doValudate(account));
		
		Customer customer = context.getBean(Customer.class);
		System.out.println(customer.getFirstname());
		
		//NoUniqueBeanDefinitionException
//		Customer karthi = context.getBean("karthi", Customer.class);
//		System.out.println(karthi.getFirstname());
		
		//NoUniqueBeanDefinitionException
//		Customer pascal = context.getBean("pascal", Customer.class);
//		System.out.println(pascal.getFirstname());
		
	}

}
