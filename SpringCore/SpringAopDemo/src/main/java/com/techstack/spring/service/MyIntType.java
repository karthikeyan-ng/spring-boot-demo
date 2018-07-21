package com.techstack.spring.service;

import java.util.Collection;

import org.springframework.stereotype.Component;

/**
 * 
 * @author KARTHIKEYAN N
 *
 * @param <T>
 */
@Component
public class MyIntType<T> { //implements Sample<T> {

	public void sampleGenericMethod(T param) {
		System.out.println("Integer param " + param);
	}

	public void sampleGenericCollectionMethod(Collection<T> param) {
		System.out.println("Integer Collection " + param);
	}

}
