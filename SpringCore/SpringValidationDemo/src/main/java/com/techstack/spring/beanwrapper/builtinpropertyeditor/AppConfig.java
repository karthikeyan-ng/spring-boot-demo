package com.techstack.spring.beanwrapper.builtinpropertyeditor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.techstack.spring.beanwrapper.builtinpropertyeditor" })
public class AppConfig {

	@Bean
	public CustomEditorConfigurer createCustomEditorConfigurer() {

		CustomEditorConfigurer customEditorConfigurer = new CustomEditorConfigurer();
		
		//Approach1: Add to CustomEditorConfigurer
		customEditorConfigurer.setPropertyEditorRegistrars(
				new PropertyEditorRegistrar[]{createCustomPropertyEditorRegistrar()});
		
		//Approach2: Using List collection
//		List<PropertyEditorRegistrar> propertyEditorRegistrars = new ArrayList<>();
//		propertyEditorRegistrars.add(createCustomPropertyEditorRegistrar());
//		customEditorConfigurer.setPropertyEditorRegistrars(
//				propertyEditorRegistrars.toArray(new PropertyEditorRegistrar[propertyEditorRegistrars.size()]));
		
		
		return customEditorConfigurer;
	}
	
	@Bean
	public CustomPropertyEditorRegistrar createCustomPropertyEditorRegistrar() {
		return new CustomPropertyEditorRegistrar();
	}
	

}
