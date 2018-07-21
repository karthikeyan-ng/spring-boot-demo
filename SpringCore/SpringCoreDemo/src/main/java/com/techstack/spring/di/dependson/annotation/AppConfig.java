package com.techstack.spring.di.dependson.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.techstack.spring.di.dependson.annotation.*" })
public class AppConfig {

	@Bean("beanOne")
	@DependsOn(value = { "beanTwo", "beanThree" })
	public BeanOne getBeanOne() {
		return new BeanOne();
	}

	@Bean("beanTwo")
	public BeanTwo getBeanTwo() {
		return new BeanTwo();
	}

	@Bean("beanThree")
	public BeanThree getBeanThree() {
		return new BeanThree();
	}

}
