package com.techstack.spring.service;

import java.util.Collection;

public class MyType implements Sample {

	public void sampleGenericMethod(Object param) {
		System.out.println("in MyType sampleGenericMethod " + param);
		
	}

	public void sampleGenericCollectionMethod(Collection param) {
		System.out.println("in MyType sampleGenericCollectionMethod " + param);
	}

}
