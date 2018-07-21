/**
 * 
 */
package com.techstack.spring.declarativeTransactionManagement.annotation;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author KARTHIKEYAN N
 *
 */
@EnableTransactionManagement	//Spring's declarative transaction management using annotation
@Configuration
@ComponentScan(basePackages = "com.techstack.spring.declarativeTransactionManagement.annotation")
public class AppConfig2 {
	
	@Bean("transactionManager1")
	@Qualifier(value="order")
	public DataSourceTransactionManager dataSourceTransactionManager1() {
		return new DataSourceTransactionManager();
	}
	
	@Bean("transactionManager2")
	@Qualifier(value="account")
	public DataSourceTransactionManager dataSourceTransactionManager2() {
		return new DataSourceTransactionManager();
	}
}