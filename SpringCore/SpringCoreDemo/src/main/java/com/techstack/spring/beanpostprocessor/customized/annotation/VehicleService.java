/**
 * 
 */
package com.techstack.spring.beanpostprocessor.customized.annotation;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

/**
 * @author KARTHIKEYAN N
 *
 */
@Component
public class VehicleService {

	@PostConstruct
	public void init() {
		System.out.println("Inside init method");
	}

	public void print() {
		System.out.println("Hello Vehicle Service!");
	}

	@PreDestroy
	public void destroy() {
		System.out.println("Inside destroy method");
	}
}
