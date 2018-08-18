/**
 * 
 */
package com.techstack.java8.streams;

import java.util.Arrays;
import java.util.Comparator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Java 8 introduced several enhancements to the Comparator interface, including
 * a handful of static functions that are of great utility when coming up with a
 * sort order for collections.
 * 
 * Java 8 lambdas can be leveraged effectively with the Comparator interface as well.
 * 
 * 
 * @author KARTHIKEYAN N
 *
 */
public class TestJava8Comparator {
	
	private static Employee[] employees;
    private static Employee[] employeesArrayWithNulls;
    private static Employee[] sortedEmployeesByName;
    private static Employee[] sortedEmployeesByNameDesc;
    private static Employee[] sortedEmployeesByAge;
    private static Employee[] sortedEmployeesByMobile;
    private static Employee[] sortedEmployeesBySalary;
    private static Employee[] sortedEmployeesArray_WithNullsFirst;
    private static Employee[] sortedEmployeesArray_WithNullsLast;
    private static Employee[] sortedEmployeesByNameAge;
    private static Employee[] someMoreEmployees;
    private static Employee[] sortedEmployeesByAgeName;;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		prepareData();
//		whenComparing_thenSortedByName();
//		whenComparingWithComparator_thenSortedByNameDesc();
//		whenReversed_thenSortedByNameDesc();
//		whenComparingInt_thenSortedByAge();
//		whenComparingLong_thenSortedByMobile();
//		whenComparingDouble_thenSortedBySalary();
//		whenNaturalOrder_thenSortedByName();
//		whenReverseOrder_thenSortedByNameDesc();
//		whenNullsFirst_thenSortedByNameWithNullsFirst();
//		whenNullsLast_thenSortedByNameWithNullsLast();
//		whenThenComparing_thenSortedByAgeName();
		whenThenComparing_thenSortedByNameAge();
	}

	public static void prepareData() {
        employees = new Employee[] { new Employee("John", 25, 3000, 9922001), new Employee("Ace", 22, 2000, 5924001), new Employee("Keith", 35, 4000, 3924401) };
        employeesArrayWithNulls = new Employee[] { new Employee("John", 25, 3000, 9922001), null, new Employee("Ace", 22, 2000, 5924001), null, new Employee("Keith", 35, 4000, 3924401) };

        sortedEmployeesByName = new Employee[] { new Employee("Ace", 22, 2000, 5924001), new Employee("John", 25, 3000, 9922001), new Employee("Keith", 35, 4000, 3924401) };
        sortedEmployeesByNameDesc = new Employee[] { new Employee("Keith", 35, 4000, 3924401), new Employee("John", 25, 3000, 9922001), new Employee("Ace", 22, 2000, 5924001) };

        sortedEmployeesByAge = new Employee[] { new Employee("Ace", 22, 2000, 5924001), new Employee("John", 25, 3000, 9922001), new Employee("Keith", 35, 4000, 3924401) };

        sortedEmployeesByMobile = new Employee[] { new Employee("Keith", 35, 4000, 3924401), new Employee("Ace", 22, 2000, 5924001), new Employee("John", 25, 3000, 9922001), };

        sortedEmployeesBySalary = new Employee[] { new Employee("Ace", 22, 2000, 5924001), new Employee("John", 25, 3000, 9922001), new Employee("Keith", 35, 4000, 3924401), };

        sortedEmployeesArray_WithNullsFirst = new Employee[] { null, null, new Employee("Ace", 22, 2000, 5924001), new Employee("John", 25, 3000, 9922001), new Employee("Keith", 35, 4000, 3924401) };
        sortedEmployeesArray_WithNullsLast = new Employee[] { new Employee("Ace", 22, 2000, 5924001), new Employee("John", 25, 3000, 9922001), new Employee("Keith", 35, 4000, 3924401), null, null };

        someMoreEmployees = new Employee[] { new Employee("Jake", 25, 3000, 9922001), new Employee("Jake", 22, 2000, 5924001), new Employee("Ace", 22, 3000, 6423001), new Employee("Keith", 35, 4000, 3924401) };

        sortedEmployeesByAgeName = new Employee[] { new Employee("Ace", 22, 3000, 6423001), new Employee("Jake", 22, 2000, 5924001), new Employee("Jake", 25, 3000, 9922001), new Employee("Keith", 35, 4000, 3924401) };
        sortedEmployeesByNameAge = new Employee[] { new Employee("Ace", 22, 3000, 6423001), new Employee("Jake", 22, 2000, 5924001), new Employee("Jake", 25, 3000, 9922001), new Employee("Keith", 35, 4000, 3924401) };
    }
	
	/**
	 * Let's use the name field in Employee as the sort key and pass its method
	 * reference as an argument of type Function. The Comparator returned from
	 * the same is used for sorting:
	 * 
	 * Key Selector Variant
	 * <code>
	 * 		static <T,U extends Comparable<? super U>> Comparator<T> comparing(
	 *				   Function<? super T,? extends U> keyExtractor)
	 * </code>
	 * 
	 * the employees array values are sorted by name as a result of the sort
	 */
	public static void whenComparing_thenSortedByName() {
		System.out.println("Before sort : " + Arrays.toString(employees));
        Comparator<Employee> employeeNameComparator = Comparator.comparing(Employee::getName);	//Natural order of sorting
        Arrays.sort(employees, employeeNameComparator);
        System.out.println("After sorted Employee by Name : " + Arrays.toString(employees));
        System.out.println(Arrays.equals(employees, sortedEmployeesByName));
    }
	
	/**
	 * Key Selector and Comparator Variant
	 * 
	 * There is another option that facilitates overriding the natural ordering
	 * of the sort key by providing the Comparator that creates a custom
	 * ordering for the sort key:
	 * 
	 * <code> static <T,U> Comparator<T> comparing(
	 * 										Function<? super T,? extends U> keyExtractor, 
	 * 										Comparator<? super U> keyComparator) 
	 * </code>
	 * 
	 * Let's modify the previous method above, overriding the natural order of
	 * sorting by the name field by providing a Comparator for sorting the names
	 * in <b>descending order</b> as the second argument to Comparator.comparing:
	 */
	public static void whenComparingWithComparator_thenSortedByNameDesc() {
		System.out.println("Before sort : " + Arrays.toString(employees));
        Comparator<Employee> employeeNameComparator = 
        		Comparator.comparing(Employee::getName, (s1, s2) -> {
            return s2.compareTo(s1);
        });
        Arrays.sort(employees, employeeNameComparator);
        System.out.println("After sorted Employee by Name Reversed : " + Arrays.toString(employees));
        System.out.println(Arrays.equals(employees, sortedEmployeesByNameDesc));
    }
	
	/**
	 * When invoked on an existing Comparator, the instance method
	 * Comparator.reversed returns a new Comparator that reverses the sort order
	 * of the original.
	 *
	 * Let's use the Comparator that sorts the employees by name and reverse it
	 * so that employees are sorted in descending order of the name:
	 */
	public static void whenReversed_thenSortedByNameDesc() {
		System.out.println("Before sort : " + Arrays.toString(employees));
        Comparator<Employee> employeeNameComparator = Comparator.comparing(Employee::getName);
        Comparator<Employee> employeeNameComparatorReversed = employeeNameComparator.reversed();
        Arrays.sort(employees, employeeNameComparatorReversed);
        System.out.println("After sorted Employee by Name Reversed : " + Arrays.toString(employees));
        System.out.println(Arrays.equals(employees, sortedEmployeesByNameDesc));
    }
	
	/**
	 * Using Comparator.comparingInt
	 *  
	 * There is also a function Comparator.comparingInt which does the same thing as
	 * Comparator.comparing, but it takes only int selectors. Let’s try this
	 * with an example where we order employees by age:
	 */
	public static void whenComparingInt_thenSortedByAge() {
		System.out.println("Before sort : " + Arrays.toString(employees));
        Comparator<Employee> employeeAgeComparator = Comparator.comparingInt(Employee::getAge);
        Arrays.sort(employees, employeeAgeComparator);
        System.out.println("After sorted Employee by Age : " + Arrays.toString(employees));
        System.out.println(Arrays.equals(employees, sortedEmployeesByAge));
    }
	
	/**
	 * Using Comparator.comparingLong
	 * 
	 * Similar to what we did for int keys, let's see an example using
	 * Comparator.comparingLong to consider a sort key of type long by ordering
	 * the employees array by the mobile field:
	 */
	public static void whenComparingLong_thenSortedByMobile() {
		System.out.println("Before sort : " + Arrays.toString(employees));
        Comparator<Employee> employeeMobileComparator = Comparator.comparingLong(Employee::getMobile);
        Arrays.sort(employees, employeeMobileComparator);
        System.out.println("After sorted Employee by Mobile : " + Arrays.toString(employees));
        System.out.println(Arrays.equals(employees, sortedEmployeesByMobile));
    }
	
	/**
	 * Using Comparator.comparingDouble
	 * 
	 * Again, similar to what we did for int and long keys, let's see an example
	 * using Comparator.comparingDouble to consider a sort key of type double by
	 * ordering the employees array by the salary field:
	 */
	public static void whenComparingDouble_thenSortedBySalary() {
		System.out.println("Before sort : " + Arrays.toString(employees));
        Comparator<Employee> employeeSalaryComparator = Comparator.comparingDouble(Employee::getSalary);
        Arrays.sort(employees, employeeSalaryComparator);
        System.out.println("After sorted Employee by Salary : " + Arrays.toString(employees));
        System.out.println(Arrays.equals(employees, sortedEmployeesBySalary));
    }
	
	/**
	 * Considering Natural Order in Comparator
	 * 
	 * The natural order is defined by the behavior of the Comparable interface
	 * implementation. More information about the difference between Comparator
	 * and uses of the Comparable interface.
	 * 
	 * Let’s implement Comparable in our Employee class so that we can try the
	 * natrualOrder and reverseOrder functions of the Comparator interface:
	 * 
	 * <code>
	 * 		public class Employee implements Comparable<Employee>{
	 *		    // ...
	 *		 
	 *		    @Override
	 *		    public int compareTo(Employee argEmployee) {
	 *		        return name.compareTo(argEmployee.getName());
	 *		    }
 	 *		}
	 * </code>
	 * 
	 * Using Natural Order The naturalOrder function returns the Comparator for
	 * the return type mentioned in the signature:
	 * 
	 * <code>static <T extends Comparable<? super T>> Comparator<T> naturalOrder()</code>
	 * 
	 * Given the above logic to compare employees based on name field, let’s use
	 * this function to obtain to a Comparator which sorts the employees array
	 * in natural order:
	 */
	public static void whenNaturalOrder_thenSortedByName() {
		System.out.println("Before sort : " + Arrays.toString(employees));
        Comparator<Employee> employeeNameComparator = Comparator.<Employee> naturalOrder();
        Arrays.sort(employees, employeeNameComparator);
        System.out.println("After sorted Employee by Name : " + Arrays.toString(employees));
        System.out.println(Arrays.equals(employees, sortedEmployeesByName));
    }
	
	/**
	 * Using Reverse Natural Order 
	 * 		Similar to naturalOrder, let’s use the reverseOrder method to generate 
	 * a Comparator which will produce a reverse ordering of employees to the 
	 * one in the naturalOrder example:
	 */
	public static void whenReverseOrder_thenSortedByNameDesc() {
		System.out.println("Before sort : " + Arrays.toString(employees));
        Comparator<Employee> employeeNameComparator = Comparator.<Employee> reverseOrder();
        Arrays.sort(employees, employeeNameComparator);
        System.out.println("After sorted Employee by Name in Reverse : " + Arrays.toString(employees));
        System.out.println(Arrays.equals(employees, sortedEmployeesByNameDesc));
    }
	
	/**
	 * Considering Null Values in Comparator
	 * 
	 * This section covers functions nullsFirst and nullsLast, which consider
	 * null values in ordering and keep the null values at the beginning or end
	 * of the ordering sequence.
	 * 
	 * Considering Null First
	 */
	public static void whenNullsFirst_thenSortedByNameWithNullsFirst() {
		System.out.println("Before sort : " + Arrays.toString(employeesArrayWithNulls));
        Comparator<Employee> employeeNameComparator = Comparator.comparing(Employee::getName);
        Comparator<Employee> employeeNameComparator_nullFirst = Comparator.nullsFirst(employeeNameComparator);
        Arrays.sort(employeesArrayWithNulls, employeeNameComparator_nullFirst);
        System.out.println("After sorted Employee by Name and null values in first place : " + Arrays.toString(employeesArrayWithNulls));
        System.out.println(Arrays.equals(employeesArrayWithNulls, sortedEmployeesArray_WithNullsFirst));
    }
	
	/**
	 * Considering Null Last
	 * 
	 * The nullsLast function will return a Comparator that keeps all nulls at the end of the ordering sequence:
	 */
	public static void whenNullsLast_thenSortedByNameWithNullsLast() {
		System.out.println("Before sort : " + Arrays.toString(employeesArrayWithNulls));
        Comparator<Employee> employeeNameComparator = Comparator.comparing(Employee::getName);
        Comparator<Employee> employeeNameComparator_nullLast = Comparator.nullsLast(employeeNameComparator);
        Arrays.sort(employeesArrayWithNulls, employeeNameComparator_nullLast);
        System.out.println(Arrays.toString(employeesArrayWithNulls));
        System.out.println(Arrays.equals(employeesArrayWithNulls, sortedEmployeesArray_WithNullsLast));
    }
	
	/**
	 * Using Comparator.thenComparing
	 * 		The thenComparing function lets you set up lexicographical ordering of values 
	 * by provisioning multiple sort keys in a particular sequence.
	 * 
	 * Let's write a sequence of comparisons as age followed by the name and see the ordering of this array:
	 * 
	 * Here the ordering will be done by age, and for the values with the same age, ordering will be done by name. 
	 * Let’s observe this in the sequence we receive after sorting:
	 */
	public static void whenThenComparing_thenSortedByAgeName() {
		System.out.println("Before sort : " + Arrays.toString(someMoreEmployees));
        Comparator<Employee> employee_Age_Name_Comparator = 
        		Comparator.comparing(Employee::getAge).thenComparing(Employee::getName);

        Arrays.sort(someMoreEmployees, employee_Age_Name_Comparator);
        System.out.println(Arrays.toString(someMoreEmployees));
        System.out.println(Arrays.equals(someMoreEmployees, sortedEmployeesByAgeName));
    }
	
	/**
	 * Let’s use the other version of thenComparing that is thenComparingInt, by
	 * changing the lexicographical sequence to name followed by age:
	 * 
	 * Similarly, there are functions thenComparingLong and thenComparingDouble for using long and double sorting keys.
	 */
	public static void whenThenComparing_thenSortedByNameAge() {
		System.out.println("Before sort : " + Arrays.toString(someMoreEmployees));
        Comparator<Employee> employee_Name_Age_Comparator = 
        		Comparator.comparing(Employee::getName).thenComparingInt(Employee::getAge);

        Arrays.sort(someMoreEmployees, employee_Name_Age_Comparator);
        System.out.println(Arrays.toString(someMoreEmployees));
        System.out.println(Arrays.equals(someMoreEmployees, sortedEmployeesByNameAge));
    }
	
}

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
class Employee implements Comparable<Employee> {
	String name;
	int age;
	double salary;
	long mobile;

	@Override
	public int compareTo(Employee argEmployee) {
		return name.compareTo(argEmployee.getName());
	}

}
