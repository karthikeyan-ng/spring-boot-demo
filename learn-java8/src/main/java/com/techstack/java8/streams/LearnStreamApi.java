package com.techstack.java8.streams;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import com.techstack.beans.Employee;
import com.techstack.beans.EmployeeRepository;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class LearnStreamApi {
	
	private static String fileName = "src/main/resources/test.txt";

	private static Employee[] arrayOfEmps = { new Employee(1, "Jeff Bezos", 100000.0),
			new Employee(2, "Bill Gates", 200000.0), new Employee(3, "Mark Zuckerberg", 300000.0) };

	private static List<Employee> empList = Arrays.asList(arrayOfEmps);

	private static EmployeeRepository employeeRepository = new EmployeeRepository(empList);

	public static void main(String[] args) throws Exception {

		Stream<Employee> employeeStream;

		// Obtain a stream from an existing array
		employeeStream = Stream.of(arrayOfEmps);

		// Obtain a stream from an existing list
		employeeStream = empList.stream();

		// We can create a stream from individual objects using Stream.of()
		Stream.of(arrayOfEmps[0], arrayOfEmps[1], arrayOfEmps[2]);

		// -OR- simply using Stream.builder():
		Stream.Builder<Employee> empStreamBuilder = Stream.builder();

		empStreamBuilder.accept(arrayOfEmps[0]);
		empStreamBuilder.accept(arrayOfEmps[1]);
		empStreamBuilder.accept(arrayOfEmps[2]);

		Stream<Employee> empStream = empStreamBuilder.build();

		whenIncrementSalaryForEachEmployee_thenApplyNewSalary();
		whenMapIdToEmployees_thenGetEmployeeStream();
		whenCollectStreamToList_thenGetList();
		whenFilterEmployees_thenGetFilteredStream();
		whenFindFirst_thenGetFirstEmployeeInStream();
		whenStreamToArray_thenGetArray();
		whenFlatMapEmployeeNames_thenGetNameStream();
		whenIncrementSalaryUsingPeek_thenApplyNewSalary();
		whenStreamCount_thenGetElementCount();
		whenLimitInfiniteStream_thenGetFiniteElements();
		whenSortStream_thenGetSortedStream();
		whenFindMin_thenGetMinElementFromStream();
		whenApplyDistinct_thenRemoveDuplicatesFromStream();
		whenApplyMatch_thenReturnBoolean();
		whenFindMaxOnIntStream_thenGetMaxInteger();
		whenApplySumOnIntStream_thenGetSum();
		whenApplyReduceOnStream_thenGetValue();
		whenCollectByJoining_thenGetJoinedString();
		whenCollectBySet_thenGetSet();
		whenApplySummarizing_thenGetBasicStats();
		whenApplySummaryStatistics_thenGetBasicStats();
		whenStreamPartition_thenGetMap();
		whenStreamGroupingBy_thenGetMap();
		whenStreamMapping_thenGetMap();
		whenStreamReducing_thenGetValue();
		whenStreamGroupingAndReducing_thenGetMap();
		whenParallelStream_thenPerformOperationsInParallel();
		givenList_whenCallingParallelStream_shouldBeParallelStream();
		giveRangeOfLongs_whenSummedInParallel_shouldBeEqualToExpectedTotal();
		whenGenerateStream_thenGetInfiniteStream();
		whenIterateStream_thenGetInfiniteStream();
		whenStreamToFile_thenGetFile();
		whenFileToStream_thenGetStream();
	}

	/**
	 * forEach() is a terminal operation, which means that, after the operation is
	 * performed, the stream pipeline is considered consumed, and can no longer be
	 * used.
	 */
	private static void whenIncrementSalaryForEachEmployee_thenApplyNewSalary() {
		empList.stream().forEach(e -> e.salaryIncrement(10.0));
		System.out.println(empList.toString());
	}

	/**
	 * map() produces a new stream after applying a function to each element of the
	 * original stream. The new stream could be of different type.
	 * 
	 * Here, we obtain an Integer stream of employee ids from an array. Each Integer
	 * is passed to the function employeeRepository::findById() – which returns the
	 * corresponding Employee object; this effectively forms an Employee stream
	 */
	public static void whenMapIdToEmployees_thenGetEmployeeStream() {
		Integer[] empIds = { 1, 2 };

		List<Employee> employees = Stream.of(empIds).map(employeeRepository::findById).collect(Collectors.toList());

		System.out.println(employees);
		System.out.println(employees.size() == empIds.length);
	}

	/**
	 * collect() its one of the common ways to get stuff out of the stream once we
	 * are done with all the processing:
	 * 
	 * collect() performs mutable fold operations (repackaging elements to some data
	 * structures and applying some additional logic, concatenating them, etc.) on
	 * data elements held in the Stream instance.
	 * 
	 * The strategy for this operation is provided via the Collector interface
	 * implementation. In the example above, we used the toList collector to collect
	 * all Stream elements into a List instance.
	 */
	public static void whenCollectStreamToList_thenGetList() {
		List<Employee> employees = empList.stream().collect(Collectors.toList());

		System.out.println("Source : " + empList);
		System.out.println("Collected : " + employees);
	}
	
	/**
	 * filter
	 * 	- this produces a new stream that contains elements of the original stream that pass a given test (specified by a Predicate).
	 * 
	 * we first filter out null references for invalid employee ids and then again apply a filter to only keep employees 
	 * with salaries over a certain threshold.
	 */
	public static void whenFilterEmployees_thenGetFilteredStream() {
	    Integer[] empIds = { 1, 2, 3, 4 };
	    
	    List<Employee> employees = Stream.of(empIds)
	      .map(employeeRepository::findById)
	      .filter(e -> e != null)
	      .filter(e -> e.getSalary() > 200000)
	      .collect(Collectors.toList());
	    
	    System.out.println("Filterd Collection : " + employees);
	}
	
	/**
	 * findFirst() 
	 * 	- returns an Optional for the first entry in the stream; the Optional can, of course, be empty:
	 * 
	 * Here, the first employee with the salary greater than 100000 is returned. If no such employee exists, then null is returned.
	 * 
	 * <h1>Lazy Evaluation</h1><ul>
	 * <li>One of the most important characteristics of streams is that they allow for significant optimizations through lazy evaluations.</li>
	 * <li><b>Computation on the source data is only performed when the terminal operation is initiated, and source elements are consumed only as needed.</b></li>
	 * <li><b>All intermediate operations are lazy, so they’re not executed until a result of a processing is actually needed.</b></li></ul>
	 * 
	 * <h1>Explanation</h1><ul>
	 * <li>How many times is the map() operation performed here? 4 times, since the input array contains 4 elements?</li>
	 * <ul><li>Stream performs the map and two filter operations, one element at a time.</li>
	 * 	   <li>It first performs all the operations on id 1. Since the salary of id 1 is not greater than 100000, the processing moves on to the next element.</li>
	 *     <li>Id 2 satisfies both of the filter predicates and hence the stream evaluates the terminal operation findFirst() and returns the result</li>
	 *     <li>No operations are performed on id 3 and 4.</li>
	 *     <li>Processing streams lazily allows avoiding examining all the data when that’s not necessary. This behavior becomes even more important when the input stream is infinite and not just very large.</li><ul><ul>
	 *     
	 */
	public static void whenFindFirst_thenGetFirstEmployeeInStream() {
	    Integer[] empIds = { 1, 2, 3, 4 };
	    
	    Employee employee = Stream.of(empIds)
	      .map(employeeRepository::findById)
	      .filter(e -> e != null)
	      .filter(e -> e.getSalary() > 100000)	//.filter(e -> e.getSalary() < 80000)
	      .findFirst()
	      .orElse(null);
	    
	    System.out.println("Find first employee : " + employee);
	}
	
	/**
	 * toArray
	 * 	- If we need to get an array out of the stream, we can simply use toArray():
	 * 
	 * The syntax Employee[]::new creates an empty array of Employee – which is then filled with elements from the stream.
	 */
	public static void whenStreamToArray_thenGetArray() {
	    Employee[] employees = empList.stream().toArray(Employee[]::new);

	    System.out.println(Arrays.toString(employees));
	}
	
	/**
	 * flatMap:
	 * 	- A stream can hold complex data structures like Stream<List<String>>. In cases like this, 
	 * flatMap() helps us to flatten the data structure to simplify further operations:
	 * 
	 * Notice how we are able to convert the Stream<List<String>> to a simpler Stream<String> – using the flatMap() API.
	 */
	public static void whenFlatMapEmployeeNames_thenGetNameStream() {
	    List<List<String>> namesNested = Arrays.asList( 
	      Arrays.asList("Jeff", "Bezos"), 
	      Arrays.asList("Bill", "Gates"), 
	      Arrays.asList("Mark", "Zuckerberg"));

	    List<String> namesFlatStream = namesNested.stream()
	      .flatMap(Collection::stream)
	      .collect(Collectors.toList());

	    System.out.println("Source List : " + namesNested);
	    System.out.println("Flat Map List : " + namesFlatStream);
	}
	
	/**
	 * peek:
	 * 	sometimes we need to perform multiple operations on each element of the stream before any 
	 * terminal operation (ex. foreach) is applied.
	 * 
	 * peek() can be useful in situations like this. Simply put, it performs the specified operation on each element 
	 * of the stream and returns a new stream which can be used further. peek() is an intermediate operation:
	 * 
	 * Here, the first peek() is used to increment the salary of each employee. The second peek() is used to 
	 * print the employees. Finally, collect() is used as the terminal operation.
	 */
	public static void whenIncrementSalaryUsingPeek_thenApplyNewSalary() {
	    Employee[] arrayOfEmps = {
	        new Employee(1, "Jeff Bezos", 100000.0), 
	        new Employee(2, "Bill Gates", 200000.0), 
	        new Employee(3, "Mark Zuckerberg", 300000.0)
	    };

	    List<Employee> empList = Arrays.asList(arrayOfEmps);
	    
	    empList.stream()
	      .peek(e -> e.salaryIncrement(10.0))
	      .peek(System.out::println)
	      .collect(Collectors.toList());

	    System.out.println(empList);
	}
	
	/**
	 * Method Types and Pipelines 
	 * 	Here’s a sample stream pipeline, where empList is the source, filter() is 
	 * the intermediate operation and count is the terminal operation:
	 */
	public static void whenStreamCount_thenGetElementCount() {
	    Long empCount = empList.stream()
							   .filter(e -> e.getSalary() > 200000)
							   .count();

	    System.out.println("Employee's salary count > 200000 is : " + empCount);
	}
	
	/**
	 * short-circuiting operations
	 * 	- Some operations are deemed short-circuiting operations. Short-circuiting operations allow 
	 * computations on infinite streams to complete in finite time:
	 * 
	 * Here, we use short-circuiting operations skip() to skip first 3 elements, and limit() to 
	 * limit to 5 elements from the infinite stream generated using iterate().
	 */
	public static void whenLimitInfiniteStream_thenGetFiniteElements() {
		//seed (starting) : 2, 
		// iteration 1 = 2 * 2 = 4
		// iteration 2 = 3 * 2 = 6
		// iteration 3 = 4 * 2 = 8		
	    Stream<Integer> infiniteStream = Stream.iterate(2, i -> i * 2);

	    List<Integer> collect = infiniteStream
								      .skip(3)	// skip first 3 elements (iteration 1,2,3)
								      .limit(5) // limit to next 5 elements in the stream
								      .collect(Collectors.toList());

	    System.out.println("Using skip and limit : " + collect);
	}

	/**
	 * <h1>Comparison Based Stream Operations</h1>
	 * sorted
	 * 	- the sorted() operation – this sorts the stream elements based on the comparator passed we pass into it.
	 * 
	 * we can sort Employees based on their names:
	 * 
	 * Note that short-circuiting will not be applied for sorted().
	 * 
	 * This means, in the example above, even if we had used findFirst() after the sorted(), 
	 * the sorting of all the elements is done before applying the findFirst(). This happens because 
	 * the operation cannot know what the first element is until the entire stream is sorted.
	 */
	public static void whenSortStream_thenGetSortedStream() {
	    List<Employee> employees = empList.stream()
									      .sorted((e1, e2) -> e1.getName().compareTo(e2.getName()))
									      .collect(Collectors.toList());

	    System.out.println("Before sort : " + empList);
	    System.out.println("After sort : " + employees);
	}
	
	/**
	 * min and max
	 * 	- min() and max() return the minimum and maximum element in the stream respectively, based on a comparator. 
	 * They return an Optional since a result may or may not exist (due to, say, filtering):
	 */
	public static void whenFindMin_thenGetMinElementFromStream() {
		List<Employee> myEmp = new ArrayList<>(empList);
		//myEmp.clear();
		
	    Employee firstEmp = myEmp.stream()
						      	 .min((e1, e2) -> e1.getId() - e2.getId())
						      	 .orElseThrow(NoSuchElementException::new);
	    
	    /**
	     * We can also avoid defining the comparison logic by using Comparator.comparing():
	     */
	    Employee maxSalEmp = empList.stream()
					    	      	.max(Comparator.comparing(Employee::getSalary))
					    	      	.orElseThrow(NoSuchElementException::new);

	    System.out.println(firstEmp);
	    System.out.println(maxSalEmp);
	}
	
	/**
	 * distinct
	 * 	- distinct() does not take any argument and returns the distinct elements in the stream, 
	 * eliminating duplicates. It uses the equals() method of the elements to decide whether two elements are equal or not:
	 */
	public static void whenApplyDistinct_thenRemoveDuplicatesFromStream() {
	    List<Integer> intList = Arrays.asList(2, 5, 3, 2, 4, 3);
	    List<Integer> distinctIntList = intList.stream().distinct().collect(Collectors.toList());
	    
	    System.out.println("Distinct evaluation: " + distinctIntList);
	    
	    List<Employee> employees = new ArrayList<>();
	    employees.add(new Employee(1, "Jeff Bezos", 100000.0));
	    employees.add(new Employee(2, "Bill Gates", 200000.0));
	    employees.add(new Employee(3, "Mark Zuckerberg", 300000.0));
	    employees.add(new Employee(3, "Mark Zuckerberg", 400000.0));
	    employees.add(new Employee(2, "Bill Gates", 300000.0));
	    
	    /**
	     * TIP: distinct() on complex type must override equals() and hashCode()
	     * either equals() or hashCode() not implemented then no effect on distinct operation.
	     */
	    List<Employee> distinctEmployees = employees.stream().distinct().collect(Collectors.toList());
	    System.out.println("Distinct Employees by using equals and hashCode: " + distinctEmployees);
	    
	    List<Employee> distinctElementsByName = employees.stream()
	    										   		 .filter(distinctByKey(emp -> emp.getName()))
	    										   		 .collect(Collectors.toList());
	    System.out.println("Distinct by Employee Name : " + distinctElementsByName);
	    
		List<Employee> distinctElementsByNameAndId = employees.stream()
														 	  .filter(distinctByKey(emp -> emp.getName() + emp.getId()))
														 	  .collect(Collectors.toList());
		System.out.println("Distinct by Employee Name and Id : " + distinctElementsByNameAndId);
	    
	}
	
	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> key) {
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(key.apply(t), Boolean.TRUE) == null;
	}
	
	/**
	 * allMatch, anyMatch, and noneMatch
	 * 	- These operations all take a predicate and return a boolean. Short-circuiting is applied and 
	 * processing is stopped as soon as the answer is determined:
	 * 
	 * allMatch() - checks if the predicate is true for all the elements in the stream. Here, it returns false 
	 * as soon as it encounters 5, which is not divisible by 2.
	 *
	 * anyMatch() - checks if the predicate is true for any one element in the stream. Here, 
	 * again short-circuiting is applied and true is returned immediately after the first element.
	 *
	 * noneMatch() - checks if there are no elements matching the predicate. Here, it simply returns false 
	 * as soon as it encounters 6, which is divisible by 3.
	 */
	public static void whenApplyMatch_thenReturnBoolean() {
	    List<Integer> intList = Arrays.asList(2, 4, 5, 6, 8);
	    
	    boolean allEven = intList.stream().allMatch(i -> i % 2 == 0);
	    boolean oneEven = intList.stream().anyMatch(i -> i % 2 == 0);
	    boolean noneMultipleOfThree = intList.stream().noneMatch(i -> i % 3 == 0);
	    
	    System.out.println(allEven);
	    System.out.println(oneEven);
	    System.out.println(noneMultipleOfThree);
	    
	    List<Employee> employees = new ArrayList<>();
	    employees.add(new Employee(1, "Jeff Bezos", 100000.0));
	    employees.add(new Employee(2, "Bill Gates", 200000.0));
	    employees.add(new Employee(3, "Mark Zuckerberg", 300000.0));
	    employees.add(new Employee(3, "Mark Zuckerberg", 400000.0));
	    employees.add(new Employee(2, "Bill Gates", 300000.0));
	    
	    boolean isAllBillGatesEmployee = employees.stream().allMatch(employee -> employee.getName().equals("Bill Gates"));
	    boolean atleastOneEmployeeSalaryEqualsTo100000 = employees.stream().anyMatch(employee -> employee.getSalary() == 100000.0);
	    boolean noneOfTheEmployeeSalaryGreaterThan500000 = employees.stream().noneMatch(employee -> employee.getSalary() > 500000.0);
	    
	    System.out.println(isAllBillGatesEmployee);
	    System.out.println(atleastOneEmployeeSalaryEqualsTo100000);
	    System.out.println(noneOfTheEmployeeSalaryGreaterThan500000);
	}
	
	/**
	 * IntStream / DoubleStream / LongStream
	 * 	- The most common way of creating an IntStream is to call mapToInt() on an existing stream:
	 * 
	 * Here, we start with a Stream<Employee> and get an IntStream by supplying the 
	 * Employee::getId to mapToInt. Finally, we call max() which returns the highest integer.
	 */
	public static void whenFindMaxOnIntStream_thenGetMaxInteger() {
	    Integer latestEmpId = empList.stream()
							      	 .mapToInt(Employee::getId)
							      	 .max()
							      	 .orElseThrow(NoSuchElementException::new);
	    System.out.println(latestEmpId);
	    
	    // We can also use IntStream.of() for creating the IntStream:
	    IntStream intStream = IntStream.of(1, 2, 3);
	    
	    // or IntStream.range():
	    IntStream.range(10, 20);	// which creates IntStream of numbers 10 to 19.
	    
	    // One important distinction to note:
	    Stream<Integer> integerStream = Stream.of(1, 2, 3);	//returns Stream<Integer> and not IntStream
	    
	    // Similarly, using map() instead of mapToInt() returns a Stream<Integer> and not an IntStream.:
	    Stream<Integer> empIdStream = empList.stream().map(Employee::getId);
	}
	
	/**
	 * Specialized Operations
	 * 		Specialized streams provide additional operations as compared to the standard Stream – 
	 * which are quite convenient when dealing with numbers.
	 * 
	 * For example sum(), average(), range() etc:
	 */
	public static void whenApplySumOnIntStream_thenGetSum() {
	    Double avgSal = empList.stream()
						       .mapToDouble(Employee::getSalary)
						       .average()
						       .orElseThrow(NoSuchElementException::new);
	    
	    System.out.println("Average Salary of all employees : " + avgSal);
	}
	
	/**
	 * <h1>Reduction Operations</h1>
	 * 
	 * A reduction operation (also called as fold) takes a sequence of input elements and combines them into a 
	 * single summary result by repeated application of a combining operation. We already saw few reduction 
	 * operations like findFirst(), min() and max().
	 * 
	 * The most common form of reduce() is:
	 * 
	 * <code>T reduce(T identity, BinaryOperator<T> accumulator)</code>
	 * 
	 * where identity is the starting value and accumulator is the binary operation we repeated apply.
	 * 
	 * <h1>Explanation</h1>
	 * Here, we start with the initial value of 0 and repeated apply Double::sum() on elements of the stream. 
	 * Effectively we’ve implemented the DoubleStream.sum() by applying reduce() on Stream.
	 */
	public static void whenApplyReduceOnStream_thenGetValue() {
	    Double sumSal = empList.stream()
						       .map(Employee::getSalary)
						       .reduce(0.0, Double::sum);
	    System.out.println(sumSal);
	    
	}
	
	/**
	 * Advanced collect: 
	 * 		- joining, 
	 * 		- toSet, 
	 * 		- toCollection, 
	 * 		- summarizingDouble, 
	 * 		- partitioningBy, 
	 * 		- groupingBy, 
	 * 		- mapping, 
	 * 		- reducing
	 */
	
	/**
	 * Joining:
	 * 	Collectors.joining() will insert the delimiter between the two String elements of the stream. 
	 * It internally uses a java.util.StringJoiner to perform the joining operation.
	 */
	public static void whenCollectByJoining_thenGetJoinedString() {
		
		//Before Java5
		String names = "";
		for(int i=0; i < empList.size(); i++) {
			names = names + empList.get(i).getName() + ", ";
		}
		System.out.println(names.substring(0, names.length()-2));
		
		//After Java5
		names = "";
		for(Employee emp : empList) {
			names = names + emp.getName() + ", ";
		}
		System.out.println(names.substring(0, names.length()-2));
		
		//Java8 Streams - Collector joining - great improvement in java :-)
	    String empNames = empList.stream()
							     .map(Employee::getName)
							     .collect(Collectors.joining(", "))
							     .toString();
	    System.out.println(empNames);
	}
	
	/**
	 * toSet
	 * 	- We can also use toSet() to get a set out of stream elements:
	 */
	public static void whenCollectBySet_thenGetSet() {
		List<Employee> employees = new ArrayList<>();
	    employees.add(new Employee(1, "Jeff Bezos", 100000.0));
	    employees.add(new Employee(2, "Bill Gates", 200000.0));
	    employees.add(new Employee(3, "Mark Zuckerberg", 300000.0));
	    employees.add(new Employee(3, "Mark Zuckerberg", 400000.0));
	    employees.add(new Employee(2, "Bill Gates", 300000.0));
		
	    Set<String> empNames = employees.stream()
	            					  .map(Employee::getName)
	            					  .collect(Collectors.toSet()); //==>HashSet implementation
	    System.out.println(empNames);
	}
	
	/**
	 * toCollection
	 * 	- We can use Collectors.toCollection() to extract the elements into any other collection 
	 * by passing in a Supplier<Collection>. We can also use a constructor reference for the Supplier:
	 * 
	 * Here, an empty collection is created internally, and its add() method is called on each element of the stream.
	 */
	public void whenToVectorCollection_thenGetVector() {
	    Vector<String> empNames = empList.stream()
	            						 .map(Employee::getName)
	            						 .collect(Collectors.toCollection(Vector::new));	//ArrayList::new or any collection type
	    
	    System.out.println(empNames);
	}
	
	/**
	 * DoubleSummaryStatistics / IntSummaryStatistics / LongSummaryStatistics
	 * 
	 * summarizingDouble
	 * 	- summarizingDouble() is another interesting collector – which applies a double-producing mapping 
	 * function to each input element and returns a special class containing statistical information for the resulting values:
	 * 
	 * Notice how we can analyze the salary of each employee and get statistical information on that data – such as min, max, average etc.
	 */
	public static void whenApplySummarizing_thenGetBasicStats() {
	    DoubleSummaryStatistics statistics = empList.stream()
	    									        .collect(Collectors.summarizingDouble(Employee::getSalary));

	    System.out.println("Element Count = " + statistics.getCount());
	    System.out.println("Total Salary = " + statistics.getSum());
	    System.out.println("Minimum Salary = " + statistics.getMin());
	    System.out.println("Maximum Salary = " + statistics.getMax());
	    System.out.println("Average Salary = " + statistics.getAverage());
	}
	
	/**
	 * -- ALTERNATE APPROACH -- for the above example Collectors.summarizingDouble
	 * 
	 * summaryStatistics() can be used to generate similar result when we’re using one of the specialized streams:
	 */
	public static void whenApplySummaryStatistics_thenGetBasicStats() {
	    DoubleSummaryStatistics statistics = empList.stream()
	    									   .mapToDouble(Employee::getSalary)
	    									   .summaryStatistics();

	    System.out.println("Element Count = " + statistics.getCount());
	    System.out.println("Total Salary = " + statistics.getSum());
	    System.out.println("Minimum Salary = " + statistics.getMin());
	    System.out.println("Maximum Salary = " + statistics.getMax());
	    System.out.println("Average Salary = " + statistics.getAverage());
	}
	
	/**
	 * partitioningBy
	 * 	- We can partition a stream into two – based on whether the elements satisfy certain criteria or not.
	 * Let’s split our List of numerical data, into even and odds:
	 * 
	 * Here, the stream is partitioned into a Map, with even and odds stored as true and false keys.
	 */
	public static void whenStreamPartition_thenGetMap() {
	    List<Integer> intList = Arrays.asList(2, 4, 5, 6, 8);
	    Map<Boolean, List<Integer>> isEven = intList.stream().collect(
	    											Collectors.partitioningBy(i -> i % 2 == 0));
	    System.out.println(isEven);
	    System.out.println(isEven.get(true).size());
	    System.out.println(isEven.get(false).size());
	}
	
	/**
	 * groupingBy
	 * 	- groupingBy() offers advanced partitioning – where we can partition the stream into more than just two groups.
	 *  - It takes a classification function as its parameter. This classification function is applied to each element of the stream.
	 *  - The value returned by the function is used as a key to the map that we get from the groupingBy collector:
	 *  
	 *  In this quick example, we grouped the employees based on the initial character of their first name.
	 */
	public static void whenStreamGroupingBy_thenGetMap() {
		List<Employee> employees = new ArrayList<>();
	    employees.add(new Employee(1, "Jeff Bezos", 100000.0));
	    employees.add(new Employee(2, "Bill Gates", 200000.0));
	    employees.add(new Employee(3, "Mark Zuckerberg", 300000.0));
	    employees.add(new Employee(3, "Mark Zuckerberg", 400000.0));
	    employees.add(new Employee(2, "Bill Gates", 300000.0));
		
	    Map<Character, List<Employee>> groupByAlphabet = 
	    		employees.stream()
	    				 .collect(Collectors.groupingBy(e -> new Character(e.getName().charAt(0))));

	    System.out.println(groupByAlphabet);
	    System.out.println(groupByAlphabet.get('B').get(0).getName());
	    System.out.println(groupByAlphabet.get('J').get(0).getName());
	    System.out.println(groupByAlphabet.get('M').get(0).getName());
	}
	
	/**
	 * mapping
	 * 	- groupingBy() discussed in the section above, groups elements of the stream with the use of a Map.
	 *  - However, sometimes we might need to group data into a type other than the element type.
	 *  - Here’s how we can do that; we can use mapping() which can actually adapt the collector to a different type – using a mapping function:
	 *  
	 * Here mapping() maps the stream element Employee into just the employee id – which is an Integer – 
	 * using the getId() mapping function. These ids are still grouped based on the initial character of employee first name.
	 */
	public static void whenStreamMapping_thenGetMap() {
		List<Employee> employees = new ArrayList<>();
	    employees.add(new Employee(1, "Jeff Bezos", 100000.0));
	    employees.add(new Employee(2, "Bill Gates", 200000.0));
	    employees.add(new Employee(3, "Mark Zuckerberg", 300000.0));
	    employees.add(new Employee(3, "Mark Zuckerberg", 400000.0));
	    employees.add(new Employee(2, "Bill Gates", 300000.0));
		
	    Map<Character, List<Integer>> idGroupedByAlphabet = 
	    		employees.stream()
	    			     .collect(
	    					   Collectors.groupingBy(e -> new Character(e.getName().charAt(0)),
							   Collectors.mapping(Employee::getId, Collectors.toList())));

	    System.out.println(idGroupedByAlphabet);
	    System.out.println(idGroupedByAlphabet.get('B').get(0));
	    System.out.println(idGroupedByAlphabet.get('J').get(0));
	    System.out.println(idGroupedByAlphabet.get('M').get(0));
	}
	
	/**
	 * reducing
	 * 	- reducing() is similar to reduce() – which we explored before. It simply returns a collector which performs a 
	 * reduction of its input elements:
	 *  - Here reducing() gets the salary increment of each employee and returns the sum.
	 *  - reducing() is most useful when used in a multi-level reduction, downstream of groupingBy() or partitioningBy(). 
	 * To perform a simple reduction on a stream, use reduce() instead. 
	 */
	public static void whenStreamReducing_thenGetValue() {
	    Double percentage = 10.0;
	    Double salIncrOverhead = empList.stream()
	    								.collect(Collectors.reducing(
	    											0.0, e -> e.getSalary() * percentage / 100, (s1, s2) -> s1 + s2));

	    System.out.println(salIncrOverhead);
	}
	
	/**
	 * let’s see how we can use reducing() with groupingBy():
	 * 
	 * Here we group the employees based on the initial character of their first name. 
	 * Within each group, we find the employee with the longest name.
	 */
	public static void whenStreamGroupingAndReducing_thenGetMap() {
		List<Employee> employees = new ArrayList<>();
	    employees.add(new Employee(1, "Jeff Bezos", 100000.0));
	    employees.add(new Employee(2, "Bill Gates First", 200000.0));
	    employees.add(new Employee(3, "Mark Zuckerberg I", 300000.0));
	    employees.add(new Employee(3, "Mark Zuckerberg II", 400000.0));
	    employees.add(new Employee(2, "Bill Gates Second", 300000.0));
		
	    Comparator<Employee> byNameLength = Comparator.comparing(Employee::getName);
	    
	    Map<Character, Optional<Employee>> longestNameByAlphabet = 
	    		employees.stream().collect(
	    					Collectors.groupingBy(e -> new Character(e.getName().charAt(0)),
	    					Collectors.reducing(BinaryOperator.maxBy(byNameLength))));

	    System.out.println(longestNameByAlphabet);
	    System.out.println(longestNameByAlphabet.get('B').get().getName());
	    System.out.println(longestNameByAlphabet.get('J').get().getName());
	    System.out.println(longestNameByAlphabet.get('M').get().getName());
	}
	
	/**
	 * <h1>Parallel Streams</h1>
	 * Using the support for parallel streams, we can perform stream operations in parallel without having 
	 * to write any boilerplate code; we just have to designate the stream as parallel:
	 * 
	 * Here salaryIncrement() would get executed in parallel on multiple elements of the stream, by simply adding the parallel() syntax.
	 */
	public static void whenParallelStream_thenPerformOperationsInParallel() {
	    Employee[] arrayOfEmps = {
	      new Employee(1, "Jeff Bezos", 100000.0), 
	      new Employee(2, "Bill Gates", 200000.0), 
	      new Employee(3, "Mark Zuckerberg", 300000.0)
	    };

	    List<Employee> empList = Arrays.asList(arrayOfEmps);
	    
	    empList.stream().parallel().forEach(e -> e.salaryIncrement(10.0));
	    
	    //-- OR -- parallelStream
	    empList.parallelStream().forEach(e -> e.salaryIncrement(10.0));
	    
	    System.out.println(empList);
	}
	
	/**
	 * calling the parallelStream method on any of the Collection types – which will return a possibly parallel Stream:
	 * 
	 * The default processing that occurs in such a Stream uses the ForkJoinPool.commonPool(), a Thread Pool shared by the entire application.
	 */
	public static void givenList_whenCallingParallelStream_shouldBeParallelStream() {
	    List<Long> aList = new ArrayList<>();
	    Stream<Long> parallelStream = aList.parallelStream();
	         
	    System.out.println(parallelStream.isParallel());
	}
	
	/**
	 * The above said stream().parallel() OR parallelStream() functionality can, of course, be tuned and configured further,
	 * if you need more control over the performance characteristics of the operation.
	 * 
	 * --- USING CUSTOM THREAD POOLS in Java8 ---
	 * 
	 * We can actually pass a custom ThreadPool when processing the stream.
	 * 
	 * The following example lets have a parallel Stream use a custom Thread Pool to calculate the 
	 * sum of long values from 1 to 1,000,000, inclusive:
	 * 
	 * We used the ForkJoinPool constructor with a parallelism level of 4. Some experimentation is 
	 * required to determine the optimal value for different environments, but a good rule of thumb is 
	 * simply choosing the number based on how many cores your CPU has.
	 * 
	 * Next, we processed the content of the parallel Stream, summing them up in the reduce call.
	 * 
	 * This simple example may not demonstrate the full usefulness of using a custom Thread Pool, 
	 * but the benefits become obvious in situations where we do not want to tie-up the common Thread Pool 
	 * with long-running tasks (e.g. processing data from a network source), or the common Thread Pool is 
	 * being used by other components within the application.
	 * 
	 * NOTES:
	 * As is the case with writing multi-threaded code, we need to be aware of few things while using parallel streams:
	 * 	(1) We need to ensure that the code is thread-safe. Special care needs to be taken if the operations performed 
	 * in parallel modifies shared data.
	 *  (2) We should not use parallel streams if the order in which operations are performed or the order returned 
	 * in the output stream matters. For example operations like findFirst() may generate the different result in case of parallel streams.
	 *  (3) Also, we should ensure that it is worth making the code execute in parallel. Understanding the performance 
	 * characteristics of the operation in particular, but also of the system as a whole – is naturally very important here.
	 */
	public static void giveRangeOfLongs_whenSummedInParallel_shouldBeEqualToExpectedTotal()
			throws InterruptedException, ExecutionException {

		long firstNum = 1;
		long lastNum = 1_000_000;

		List<Long> aList = LongStream.rangeClosed(firstNum, lastNum).boxed().collect(Collectors.toList());

		ForkJoinPool customThreadPool = new ForkJoinPool(4);
		long actualTotal = customThreadPool.submit(() -> aList.parallelStream().reduce(0L, Long::sum)).get();

		System.out.println("Expected : " + (lastNum + firstNum) * lastNum / 2);
		System.out.println("Actual : " + actualTotal);
	}
	
	/**
	 * <h1>Infinite Streams</h1>
	 * 
	 * Sometimes, we might want to perform operations while the elements are still getting generated. 
	 * We might not know beforehand how many elements we’ll need. Unlike using list or map, where all the 
	 * elements are already populated, we can use infinite streams, also called as unbounded streams.
	 * 
	 * There are two ways to generate infinite streams:
	 * 		- generate
	 * 		- iterate
	 * 
	 * generate
	 * 	- We provide a Supplier to generate() which gets called whenever new stream elements need to be generated:
	 *  - Here, we pass Math::random() as a Supplier, which returns the next random number.
	 *  - With infinite streams, we need to provide a condition to eventually terminate the processing. One 
	 *  common way of doing this is using limit(). In above example, we limit the stream to 5 random numbers 
	 *  and print them as they get generated.
	 *  - Please note that the Supplier passed to generate() could be stateful and such stream 
	 *  may not produce the same result when used in parallel.
	 */
	public static void whenGenerateStream_thenGetInfiniteStream() {
	    Stream.generate(Math::random)
		      .limit(5)
		      .forEach(System.out::println);
	}
	
	/**
	 * iterate
	 * 	- iterate() takes two parameters: an initial value, called seed element and a function which 
	 * generates next element using the previous value. iterate(), by design, is stateful and hence 
	 * may not be useful in parallel streams:
	 * 
	 * Explanation:
	 * 		- Here, we pass 2 as the seed value, which becomes the first element of our stream. This value 
	 * is passed as input to the lambda, which returns 4. This value, in turn, is passed as input in the next iteration.
	 * 		- This continues until we generate the number of elements specified by limit() which acts as the terminating condition.
	 */
	public static void whenIterateStream_thenGetInfiniteStream() {
	    Stream<Integer> evenNumStream = Stream.iterate(2, i -> i * 2);

	    List<Integer> collect = evenNumStream
							      .limit(5)
							      .collect(Collectors.toList());

	    System.out.println(collect);
	}
	
	/**
	 * 	--- FILE OPERATIONS ---
	 * Let’s see how we could use the stream in file operations.
	 * 
	 * File Write Operation
	 * 	- Here we use forEach() to write each element of the stream into the file by calling PrintWriter.println().
	 * 
	 * @throws IOException
	 */
	public static void whenStreamToFile_thenGetFile() throws IOException {
	    String[] words = {
	      "hello", 
	      "refer",
	      "world",
	      "level"
	    };
	    
	    try (PrintWriter pw = new PrintWriter(
	    			Files.newBufferedWriter(Paths.get(fileName)))) {
	        Stream.of(words).forEach(pw::println);
	    }
	}
	
	/**
	 * File Read Operation
	 * 	- Here Files.lines() returns the lines from the file as a Stream which is consumed by the 
	 * getPalindrome() for further processing.
	 *  - getPalindrome() works on the stream, completely unaware of how the stream was generated. This also increases 
	 *  code reusability and simplifies unit testing.
	 *  
	 * @throws IOException
	 */
	public static void whenFileToStream_thenGetStream() throws IOException {
	    List<String> str = getPalindrome(Files.lines(Paths.get(fileName)), 5);
	    System.out.println(str);
	}
	
	private static List<String> getPalindrome(Stream<String> stream, int length) {
	    return stream.filter(s -> s.length() == length)
	    			 .filter(s -> s.compareToIgnoreCase(
	    					 		new StringBuilder(s).reverse().toString()) == 0)
	    			 .collect(Collectors.toList());
	}
}
