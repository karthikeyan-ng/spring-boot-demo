/**
 * 
 */
package com.techstack.spring.di.constructorbased;

import java.beans.ConstructorProperties;

/**
 * @author KARTHIKEYAN N
 *
 */
public class ExampleBean {
	
	// Number of years to calculate the Ultimate Answer
    private int years;

    // The Answer to Life, the Universe, and Everything
    private String ultimateAnswer;

    public ExampleBean(int years) {
        this.years = years;
    }
    
//    public ExampleBean(int years, String ultimateAnswer) {
//        this.years = years;
//        this.ultimateAnswer = ultimateAnswer;
//    }
    
    /**
     * Keep in mind that to make this work out of the box your code must be compiled with the debug flag enabled 
     * so that Spring can look up the parameter name from the constructor. If you can’t compile your code with 
     * debug flag (or don’t want to) you can use @ConstructorProperties JDK annotation to explicitly name your 
     * constructor arguments.
	 *
     * @ConstructorProperties is used in constructor based dependency injection.
     * It is mainly used when we need the constructor arguments passed in the application context(
     * metadata to the spring container) should be resolved by the constructor parameter name in the bean object 
     * to be created.By annotating @ConstructorProperties in the constructor of the bean class,it will make the 
     * constructor parameter names available to the spring container at runtime.Below sample will show how this 
     * annotation can be used:
     * 
     * @param years
     * @param ultimateAnswer
     */
    @ConstructorProperties({"years", "ultimateAnswer"})
    public ExampleBean(int years, String ultimateAnswer) {
        this.years = years;
        this.ultimateAnswer = ultimateAnswer;
    }
    
    public void print() {
    	System.out.println("years="+years+"\tultimateAnswer"+ultimateAnswer);
    }

}
