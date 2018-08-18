/**
 * 
 */
package com.techstack.problems;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.techstack.beans.Employee;

/**
 * 5 Different Ways to Create Objects in Java
 * 
 * 	1. using new keyword 								-> constructor gets called
 * 	2. Using newInstance() method of Class class		-> constructor gets called
 * 	3. Using newInstance() method of Constructor class	-> constructor gets called
 *  4. Using clone() method								-> no constructor call
 *  5. Using deserialization							-> no constructor call
 *  
 *  https://dzone.com/articles/5-different-ways-to-create-objects-in-java-with-ex
 * 
 * @author KARTHIKEYAN N
 *
 */
public class DifferentWaysToCreateObjectsInJava {
	
	private static String fileName = "src/main/resources/data.obj";
	
	public static void main(String[] args) {
		using_NewKeyword();
		using_NewInstance();
		using_Clone();
		using_Deserialization();
	}

	/**
	 * 1. using new keyword
	 */
	private static void using_NewKeyword() {
		Employee employee = new Employee(1, "Sam", 1000d);
		System.out.println("Employee object created using new keyword. " + employee);
	}
	
	/**
	 * 2. Using newInstance() method of Class class
	 * 3. Using newInstance() method of Constructor class
	 */
	private static void using_NewInstance() {
//		try {
//			// Default constructor should be defined explicitly, else Caused by: java.lang.NoSuchMethodException: com.techstack.beans.Employee.<init>()
//			Employee employee = (Employee) Class.forName("com.techstack.beans.Employee").newInstance();
//			System.out.println("Type 2 : " + employee);
//		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
//			e.printStackTrace();
//		}
		
//		try {
//			// Default constructor should be defined explicitly, else Caused by:
//			// java.lang.NoSuchMethodException: com.techstack.beans.Employee.<init>()
//			Employee employee = Employee.class.newInstance();
//			System.out.println("Type 3 : " + employee);
//		} catch (InstantiationException | IllegalAccessException e) {
//			e.printStackTrace();
//		}
		
		try {
			// Default constructor should be defined explicitly, else Caused by:
			// java.lang.NoSuchMethodException: com.techstack.beans.Employee.<init>()
			Constructor<Employee> constructor = Employee.class.getConstructor();
			Employee employee = constructor.newInstance();
			System.out.println("Type 3.1 : " + employee);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 4. Using clone() method
	 */
	private static void using_Clone() {
		Employee employee = new Employee(1, "Sam", 1000d);
		try {
			Employee clonedEmployee = (Employee) employee.clone();
			System.out.println("Cloned Employee : " + clonedEmployee);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 5. Using deserialization
	 * 
	 * Whenever we serialize and deserialize an object, the JVM creates a separate
	 * object for us. In deserialization, the JVM doesn’t use any constructor to
	 * create the object.
	 * 
	 * To deserialize an object we need to implement a Serializable interface in our class. 
	 */
	private static void using_Deserialization() {
		
		Employee employee = new Employee(1, "Sam", 1000d);
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
			out.writeObject(employee);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
			Employee deSerializedEmployee = (Employee) in.readObject();
			System.out.println("Deserialized Employee object : " + deSerializedEmployee);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
}
