/**
 * 
 */
package com.techstack.java8.reflection;

import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Method Parameter Reflection support was added in Java 8. Simply put, it
 * provides support for getting the names of parameters at runtime.
 * 
 * we'll take a look at how to access parameter names for constructors and
 * methods at runtime – using reflection.
 * 
 * <p>
 * Compiler Argument
 * </p>
 * In order to be able to get access to method name information, we must opt-in
 * explicitly. To do this, we specify the parameters option during compilation.
 * For a Maven project, we can declare this option in the pom.xml:
 * 
 * <pre>
 * <plugin>
 *	  <groupId>org.apache.maven.plugins</groupId>
 *	  <artifactId>maven-compiler-plugin</artifactId>
 *	  <version>3.1</version>
 *	  <configuration>
 *	    <source>1.8</source>
 *	    <target>1.8</target>
 *	    <compilerArgument>-parameters</compilerArgument>
 *	  </configuration>
 * </plugin>
 * </pre>
 * 
 * Usage 
 * The Parameter class is new in Java 8 and has a variety of interesting
 * methods. If the -parameters compiler option was provided, the isNamePresent()
 * method will return true.
 * 
 * To access the name of a parameter, we can simply call getName():
 * 
 * @author Karthikeyan N
 *
 */
public class TestJava8MethodParameterReflection {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		whenGetConstructorParams_thenOk();
		whenGetMethodParams_thenOk();
	}

	public static void whenGetConstructorParams_thenOk() throws NoSuchMethodException, SecurityException {

		List<Parameter> parameters = Arrays.asList(Person.class.getConstructor(String.class).getParameters());
		Optional<Parameter> parameter = parameters.stream().filter(Parameter::isNamePresent).findFirst();
		System.out.println(parameter.get().getName());
	}
	
	public static void whenGetMethodParams_thenOk() throws NoSuchMethodException, SecurityException {

		List<Parameter> parameters = Arrays.asList(Person.class.getMethod("setFullName", String.class).getParameters());
		Optional<Parameter> parameter = parameters.stream().filter(Parameter::isNamePresent).findFirst();

		System.out.println(parameter.get().getName());
	}
}

class Person {
	 
    private String fullName;
 
    public Person(String fullName) {
        this.fullName = fullName;
    }
 
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
 
    // other methods
}
