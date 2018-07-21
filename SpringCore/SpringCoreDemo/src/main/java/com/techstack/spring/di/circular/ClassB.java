/**
 * 
 */
package com.techstack.spring.di.circular;

/**
 * @author KARTHIKEYAN N
 *
 */
public class ClassB {
	
	private ClassA classA;
	
	public ClassB(ClassA classA) {
		this.classA = classA;
	}

}
