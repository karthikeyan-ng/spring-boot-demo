/**
 * 
 */
package com.techstack.spring.annotations.autowired.constructor;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author KARTHIKEYAN N
 *
 */
@Configuration
@ComponentScan(basePackages = "com.techstack.spring.annotations.autowired.constructor")
public class AppConfig {

//	@Bean(autowire = Autowire.BY_TYPE)
//	public ClientBean clientBean() {
//		return new ClientBean();
//	}

	// remove this comment to fix BeanInitializationException
//	@Bean 
//	public ServiceBean serviceBean () { 
//		return new ServiceBean(); 
//	}
	 

//	@Bean
//	public RequiredAnnotationBeanPostProcessor processor() {
//		return new RequiredAnnotationBeanPostProcessor() {
//			@Override
//			protected boolean shouldSkip(ConfigurableListableBeanFactory beanFactory, String beanName) {
//				if (beanName.equals("clientBean")) {
//					return false;
//				}
//				return super.shouldSkip(beanFactory, beanName);
//			}
//		};
//	}
}