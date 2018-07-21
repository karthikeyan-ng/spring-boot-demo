/**
 * 
 */
package com.techstack.spring.declarativeTransactionManagement.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author KARTHIKEYAN N
 *
 */
@EnableTransactionManagement	//Spring's declarative transaction management using annotation
@Configuration
@ComponentScan(basePackages = "com.techstack.spring.declarativeTransactionManagement.annotation")
public class AppConfig {
}