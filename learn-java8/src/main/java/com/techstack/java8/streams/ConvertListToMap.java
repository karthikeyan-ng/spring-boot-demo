/**
 * 
 */
package com.techstack.java8.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.techstack.beans.Brand;
import com.techstack.beans.Model;
import com.techstack.beans.Person;

/**
 * @author KARTHIKEYAN N
 *
 */
public class ConvertListToMap {
	
	public static void main(String[] args) {
		List<Person> persons = createPersons();
		Map<Integer, Person> personMapById;
		Map<String, Person> personMapByName;
		
		/**
		 * List to Map with no duplicate keys
		 * 
		 * If we are really sure that the List we want to convert into a Map contains no duplicate elements, we can use the following code:
		 * 
		 * Person::getId ==> p -> p.getId()
		 * Function.identity() ==> p -> p
		 * 
		 * As already stated, one of the requirements for this Collector is that there are no duplicate keys 
		 * within the initial list. In case a duplicate key is received, an IllegalStateException will be thrown as you can see in this test 
		 */
		personMapById = persons.stream().collect(Collectors.toMap(Person::getId, Function.identity()));
		System.out.println(personMapById);
		
		/**
		 * List to Map with duplicate keys
		 * 
		 * If we are not sure or we simply don’t know if the original list contains duplicities we’ll use another 
		 * Collector that can manage merging for these duplicities. For this sake, the method we used earlier 
		 * admits a third parameter as a lambda expression that will be in charge of merging the elements for a given duplicate key.
		 */
		personMapById = persons.stream().collect(Collectors.toMap(Person::getId, Function.identity(),
				(personPrevious, personNew) -> personNew));
		System.out.println(personMapById);
		
		/**
		 * List to Map keeping key ordering
		 * 
		 * Collectors.toMap admits a fourth parameter to specify the constructor of the Map that the method returns.
		 * 
		 * In this case we want to obtain an instance of TreeMap  which implements the SortedMap interface. 
		 * This Map is sorted by the natural ordering of its keys or by the comparator provided at the constructor argument.
		 * 
		 */
		personMapByName = persons.stream().collect(Collectors.toMap(Person::getName, Function.identity(),
				(personPrevious, personNew) -> personNew, TreeMap::new));
		System.out.println(personMapByName);
		
		/**
		 * Sorting using key reverse order
		 */
		personMapByName = persons.stream().collect(Collectors.toMap(Person::getName, Function.identity(),
				(personPrevious, personNew) -> personNew,
				() -> new TreeMap(Comparator.reverseOrder())));
		System.out.println(personMapByName);
		
		usingForeachAndReplaceAll();
		usingComputeIfAbsent();
		usingComputeIfPresent();
		usingGetOrDefault();
		usingChildClassProperyAsMapKey();
	}

	private static void usingForeachAndReplaceAll() {
		
		Map<Integer, List<Person>> personMap = new HashMap<>();
		
		List<Person> list2014 = Arrays.asList(new Person(1, "Karthi", "Male", 34));
		personMap.put(2014, list2014);
		List<Person> list2015 = Arrays.asList(new Person(2, "Rekha", "Female", 31), new Person(3, "Ramesh", "Male", 31));
		personMap.put(2015, list2015);
		List<Person> list2016 = Arrays.asList(new Person(3, "Sarvanan", "Male", 29), new Person(4, "Aravind", "Male", 27));
		personMap.put(2016, list2016);
		
		System.out.println("Using Map.forEach to print the Person");
		personMap.forEach((year, empList) -> System.out.println(year + "-->" + empList));
		
		System.out.println("CAPITALIZED Person Names using Map.replaceAll()");
		personMap.replaceAll((year, personList) -> {
													personList.replaceAll(person -> {
													    person.setName(person.getName().toUpperCase());
													    return person;
													});
													return personList;
												});
		personMap.forEach((year, empList)-> System.out.println(year + "-->" + empList));
	}
	
	/**
	 * computeIfAbsent
	 * 		If the specified key is not already associated with a value (or is mapped to null), attempts 
	 * to compute its value using the given mapping function and enters it into this map unless null. 
	 */
	private static void usingComputeIfAbsent() {
		Map<Integer, List<Person>> personMap = new HashMap<>();

		System.out.println("Java 7 way of adding a new key(2017) in a multi-value map");
		List<Person> list2017 = personMap.get(2017);
		if (list2017 == null) {
			list2017 = new ArrayList<>();
		}
		list2017.add(new Person(6, "Kriish", "Male", 4));
		personMap.put(2017, list2017);
		personMap.forEach((year, empList) -> System.out.println(year + "-->" + empList));
		
		System.out.println("Using Map.computeIfAbsent() to add a new key(2018) in a multi-value map");
		personMap.computeIfAbsent(2018, empList -> new ArrayList<>())
		              								.add(new Person(7, "Githika", "Female", 18));
		personMap.forEach((year, empList) -> System.out.println(year + "-->" + empList));
	}
	
	/**
	 * computeIfPresent
	 * 		- If the value for the specified key is present and non-null, attempts to compute a new mapping 
	 * given the key and its current mapped value. 
	 * 		- If the function returns null, the mapping is removed. If the function itself throws an 
	 * (unchecked) exception, the exception is rethrown, and the current mapping is left unchanged.
	 */
	private static void usingComputeIfPresent() {
		Map<Integer, List<Person>> personMap = new HashMap<>();
		
		List<Person> list2017 = new ArrayList<>();
		list2017.add(new Person(6, "Kriish", "Male", 4));
		
		List<Person> list2018 = new ArrayList<>();
		list2018.add(new Person(7, "Githika", "Female", 18));
		
		personMap.put(2017, list2017);
		personMap.put(2018, list2018);
		
		System.out.println("Java 7 way of removing a key(2017) in a multi-value map for which no entry exists");
		List<Person> people = personMap.get(2017);
		people.removeIf(person -> person.getName().equals("Kriish"));
		if (people.size() == 0) {
			personMap.remove(2017);
		}
		personMap.forEach((year, empList) -> System.out.println(year + "-->" + empList));
		
		System.out.println("Using Map.computeIfPresent() to remove a key(2018) for which no entry exists");
		personMap.computeIfPresent(2018, (year, empList) -> 
											empList.removeIf(
													employee -> employee.getName().equals("Githika")) && 
													empList.size() == 0 ? null : empList);
		personMap.forEach((year, empList) -> System.out.println(year + "-->" + empList));
	}
	
	/**
	 * getOrDefault
	 * 		Map.getOrDefault() method has been designed for scenarios where the value returned for a 
	 * given key might be null. 
	 * 		i.e. the given key is not present in the map. In case of multi-value maps, it gives the programmer 
	 * a utility to avoid NullPointerException at runtime by instantiating a new collection instance 
	 * and returning it in case the key is not present and a null-value would otherwise have been returned.
	 */
	private static void usingGetOrDefault() {
		Map<Integer, List<Person>> personMap = new HashMap<>();
		
		System.out.println("Avoiding a null return when fetching a non-existent key's entry using Map.getOrDefault() method");
		List<Person> personList2019 = personMap.getOrDefault(2019, new ArrayList<>());
		System.out.println("Size of person List 2019 = " + personList2019.size());
	}
	
	/**
	 * Collect Key as object from {@link Brand#getModel()} and
	 * Value as self {@link Brand} object.
	 */
	private static void usingChildClassProperyAsMapKey() {
		Map<Model, Brand> modelBrandMap = new HashMap<>();
		modelBrandMap = createBrands().stream().collect(Collectors.toMap(Brand::getModel, Function.identity()));
		System.out.println(modelBrandMap);
	}

	private static List<Person> createPersons() {
		List<Person> persons = new ArrayList<>();
		persons.add(new Person(1, "Karthi", "Male", 34));
		persons.add(new Person(2, "Rekha", "Female", 31));
		persons.add(new Person(3, "Sarvanan", "Male", 29));
		persons.add(new Person(4, "Aravind", "Male", 27));
		return persons;
	}
	
	private static List<Brand> createBrands() {
		List<Brand> brands = new ArrayList<>();
		brands.add(new Brand("Ford", new Model("EcoSport")));
		brands.add(new Brand("Ford", new Model("Aspire")));
		brands.add(new Brand("Audi", new Model("A7")));
		brands.add(new Brand("Ford", new Model("Mustang")));
		return brands;
	}

}
