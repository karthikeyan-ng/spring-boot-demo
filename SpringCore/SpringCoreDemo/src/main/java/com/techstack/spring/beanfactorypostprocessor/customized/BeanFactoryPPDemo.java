/**
 * 
 */
package com.techstack.spring.beanfactorypostprocessor.customized;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

/**
 * @author KARTHIKEYAN N
 *
 */
public class BeanFactoryPPDemo implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		PropertyPlaceholderConfigurer cfg = new PropertyPlaceholderConfigurer();
		cfg.setLocation(new FileSystemResource("C:\\Workspaces\\SPRING_Workspace\\SpringCoreDemo\\src\\main\\resources\\properties\\credentialsConfig.properties"));
		cfg.postProcessBeanFactory(beanFactory);
		System.out.println("Bean factory post processor is initialized");
	}

}
