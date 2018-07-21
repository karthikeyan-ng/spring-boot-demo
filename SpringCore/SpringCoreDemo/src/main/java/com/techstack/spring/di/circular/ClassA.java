/**
 * 
 */
package com.techstack.spring.di.circular;

/**
 * @author KARTHIKEYAN N
 *
 */
public class ClassA {
	
	private ClassB classB;
	
	public ClassA(ClassB classB) {
		this.classB = classB;
	}

}
