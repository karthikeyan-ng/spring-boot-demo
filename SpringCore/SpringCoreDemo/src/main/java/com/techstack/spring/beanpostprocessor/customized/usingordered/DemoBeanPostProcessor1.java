package com.techstack.spring.beanpostprocessor.customized.usingordered;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

public class DemoBeanPostProcessor1 implements BeanPostProcessor, Ordered {

	int order;
	
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {

		System.out.println("DemoBeanPostProcessor1 processing bean instance after initialization ( i.e just after init life cycle event ) of: " + beanName);
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		
		System.out.println("DemoBeanPostProcessor1 processing bean instance before initialization ( i.e after spring instantiates bean and before init life cycle event ) of: " + beanName);
		return bean;
	}

	public void setOrder(int order) {

		this.order = order;
	}

	@Override
	public int getOrder() {
		
		return order;
	}
}