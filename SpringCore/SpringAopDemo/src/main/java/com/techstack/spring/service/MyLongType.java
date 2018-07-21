package com.techstack.spring.service;

import java.util.Collection;

import org.springframework.stereotype.Component;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
@Component
public class MyLongType<T> { //implements Sample<T> {

	public void sampleGenericMethod(T param) {
		System.out.println("Long param " + param);
	}

	public void sampleGenericCollectionMethod(Collection<T> param) {
		System.out.println("Long Collection " + param);
	}

}
