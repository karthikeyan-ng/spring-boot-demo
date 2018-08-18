/**
 * 
 */
package com.techstack.problems;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author KARTHIKEYAN N
 *
 */
public class WordFrequencyCalculator {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		whenMapWithWrapperAsCounter();
		whenMapWithLambdaAndWrapperCounter();
		whenMapWithMutableIntegerCounter();
		whenMapWithPrimitiveArray();
	}
	
	public static void whenMapWithWrapperAsCounter() {
        Map<String, Integer> counterMap = new HashMap<>();
        CounterUtil.counterWithWrapperObject(counterMap);
        System.out.println(counterMap);
        System.out.println(counterMap.get("China").intValue());
        System.out.println(counterMap.get("India").intValue());
    }
	
	public static void whenMapWithLambdaAndWrapperCounter() {
        Map<String, Long> counterMap = new HashMap<>();
        CounterUtil.counterWithLambdaAndWrapper(counterMap);
        System.out.println(counterMap);
        System.out.println(counterMap.get("China").longValue());
        System.out.println(counterMap.get("India").longValue());
    }
	
	public static void whenMapWithMutableIntegerCounter() {
        Map<String, CounterUtil.MutableInteger> counterMap = new HashMap<>();
        CounterUtil.counterWithMutableInteger(counterMap);
        System.out.println(counterMap);
        System.out.println(counterMap.get("China").getCount());
        System.out.println(counterMap.get("India").getCount());
    }

	public static void whenMapWithPrimitiveArray() {
        Map<String, int[]> counterMap = new HashMap<>();
        CounterUtil.counterWithPrimitiveArray(counterMap);
        System.out.println(counterMap);
        System.out.println(counterMap.get("China")[0]);
        System.out.println(counterMap.get("India")[0]);
    }
}

class CounterUtil {

	public static String[] COUNTRY_NAMES = { "China", "Australia", "India", "USA", "USSR", "UK", "China", "France",
			"Poland", "Austria", "India", "USA", "Egypt", "China" };

	/**
	 * We simply used Map's handy compute method which increments the counter or
	 * initializes it with 1 if the key isn't present.
	 *
	 * However, this method of creating counter isn't efficient as Integer is
	 * immutable, so every time when we increment the counter, we create a new
	 * Integer object.
	 * 
	 * @param counterMap
	 */
    public static void counterWithWrapperObject(Map<String, Integer> counterMap) {
        for (String country : COUNTRY_NAMES) {
            counterMap.compute(country, (k, v) -> v == null ? 1 : v + 1);
        }
    }

    /**
     * Now, let's leverage Java 8 Stream API, parallel Streams, and the groupingBy() collector:
     * 
     * @param counterMap
     */
    public static void counterWithLambdaAndWrapper(Map<String, Long> counterMap) {
        Stream.of(COUNTRY_NAMES)
              .collect(Collectors.groupingBy(k -> k, () -> counterMap, Collectors.counting()));
    }

    /**
     * Similarly, we could use a parallelStream:
     * @param counterMap
     */
    public static void counterWithParallelStreamAndWrapper(Map<String, Long> counterMap) {
        Stream.of(COUNTRY_NAMES)
            .parallel()
            .collect(Collectors.groupingBy(k -> k, () -> counterMap, Collectors.counting()));
    }

    public static class MutableInteger {
        int count;

        public MutableInteger(int count) {
            this.count = count;
        }

        public void increment() {
            this.count++;
        }

        public int getCount() {
            return this.count;
        }
    }

    /**
     * In the mapWithMutableInteger method, while iterating over each country in the COUNTRY_NAMES array, we:
     * 		- invoke a get on the counterMap by passing the country name as a key
     * 		- check whether the key is already present or not. If an entry is absent, we create an instance of MutableInteger
     *  which sets the counter value as 1. We increment the counter value present in the MutableInteger 
     *  if the country is present in the map
     *  
     *  This method of creating a counter is better than the previous one – <b>as we’re reusing the 
     *  same MutableInteger and thereby creating fewer objects.</b>
     *  
     *  This is how Apache Collections HashMultiSet works where it embeds a HashMap with value as MutableInteger internally.
     * 
     * @param counterMap
     */
    public static void counterWithMutableInteger(Map<String, MutableInteger> counterMap) {
        for (String country : COUNTRY_NAMES) {
            counterMap.compute(country, (k, v) -> v == null ? new MutableInteger(0) : v)
                .increment();
        }
    }

    /**
	 * let's use a Map that wraps a counter within an Integer array used as a value:
	 * 
	 * Note how we created a simple HashMap with int arrays as values.
	 * 
	 * In the counterWithPrimitiveArray method, while iterating over each value of
	 * the array, we: 
	 * 		- invoke a get on the counterMap by passing the country name
	 * as a key 
	 * 		- check whether a key was already present or not. If the entry is
	 * already present, we create a new instance of primitive integer array with a
	 * single "1". If the entry is absent, we increment the counter value present in
	 * the array
	 * 
	 * This method is better than the wrapper implementation – as it creates fewer objects.
	 * 
	 * @param counterMap
	 */
    public static void counterWithPrimitiveArray(Map<String, int[]> counterMap) {
        for (String country : COUNTRY_NAMES) {
            counterMap.compute(country, (k, v) -> v == null ? new int[] { 0 } : v)[0]++;
        }
    }

}

