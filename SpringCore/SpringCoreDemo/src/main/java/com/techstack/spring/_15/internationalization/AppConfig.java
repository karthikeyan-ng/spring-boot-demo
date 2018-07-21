package com.techstack.spring._15.internationalization;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.techstack.spring._15.internationalization" })
public class AppConfig {

	@Bean
	public MyBean myBean() {
		return new MyBean();
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("format");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheMillis(500);
		return messageSource;
	}

}
