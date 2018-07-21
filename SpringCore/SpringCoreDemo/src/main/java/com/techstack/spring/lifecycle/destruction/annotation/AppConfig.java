package com.techstack.spring.lifecycle.destruction.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.techstack.spring.lifecycle.destruction.annotation" })
public class AppConfig {

	//no custom defined configuration. Let Spring initialize all it's configuration by default.
	
	

}
