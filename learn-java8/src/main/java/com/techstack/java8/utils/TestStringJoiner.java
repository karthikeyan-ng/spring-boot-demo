/**
 * 
 */
package com.techstack.java8.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * StringJoiner is a new class added in Java 8 under java.util package.
 * 
 * Simply put, it can be used for joining Strings making use of a delimiter, prefix, and suffix.
 * 
 * @author KARTHIKEYAN N
 *
 */
public class TestStringJoiner {
	
	public static void main(String[] args) {
		whenAddingElements_thenJoinedElements();
		whenAddingListElements_thenJoinedListElements();
		whenEmptyJoinerWithoutPrefixSuffix_thenEmptyString();
		whenEmptyJoinerJoinerWithPrefixSuffix_thenPrefixSuffix();
		whenEmptyJoinerWithEmptyValue_thenDefaultValue();
		whenEmptyJoinerWithPrefixSuffixAndEmptyValue_thenDefaultValue();
		whenMergingJoiners_thenReturnMerged();
	}

	/**
	 * We can add Strings using the add() method:
	 */
	public static void whenAddingElements_thenJoinedElements() {
	    StringJoiner joiner = new StringJoiner(",");
	    joiner.add("Red")
	      	  .add("Green")
	      	  .add("Blue");
	 
	    System.out.println("String Joiner using Delimeter : " + joiner.toString());
	    
	    joiner = new StringJoiner(",", "[", "]");
	    joiner.add("Red")
    	  	  .add("Green")
    	  	  .add("Blue");
	    System.out.println("String Joiner using Delimeter with PREFIX and SUFFIX : " + joiner.toString());
	}
	
	/**
	 * If we want to join all elements of a list, we’ll have to loop through the
	 * list. Unfortunately, there’s no easy way to do it using StringJoiner:
	 */
	public static void whenAddingListElements_thenJoinedListElements() {
	    List<String> rgbList = new ArrayList<>();
	    rgbList.add("Red");
	    rgbList.add("Green");
	    rgbList.add("Blue");
	 
	    StringJoiner rgbJoiner = new StringJoiner(",", "[", "]");
	    for (String color : rgbList) {
	        rgbJoiner.add(color);
	    }
	    
	    System.out.println(rgbJoiner.toString());
	    
	    // Using stream
	    System.out.println("Using Stream : " + rgbList.stream().collect(Collectors.joining(",", "[", "]")));
	}
	
	/**
	 * A Joiner without prefix and suffix returns an empty String.
	 */
	public static void whenEmptyJoinerWithoutPrefixSuffix_thenEmptyString() {
		StringJoiner joiner = new StringJoiner(",");
		System.out.println("StringJoiner with delimeter : " + joiner.toString().length());
	}
	
	/**
	 * A Joiner with prefix and suffix returns a String containing both prefix and suffix.
	 */
	public static void whenEmptyJoinerJoinerWithPrefixSuffix_thenPrefixSuffix() {
	    StringJoiner joiner = new StringJoiner(",", "[", "]");
	    System.out.println("StringJoiner info : " + joiner.toString() + " with Size : " + joiner.length());
	}
	
	/**
	 * We can change the default String returned by using setEmptyValue():
	 */
	public static void whenEmptyJoinerWithEmptyValue_thenDefaultValue() {
	    StringJoiner joiner = new StringJoiner(",");
	    joiner.setEmptyValue("default");
	    System.out.println("Using setEmptyValue : " + joiner.toString());
	}
	
	/**
	 * setEmptyValue with PREFIX and SUFFIX
	 * 
	 * Here, joiners return the EMPTY_JOINER constant.
	 * The default value is returned only when the StringJoiner is empty.
	 */
	public static void whenEmptyJoinerWithPrefixSuffixAndEmptyValue_thenDefaultValue() {
	    StringJoiner joiner = new StringJoiner(",", "[", "]");
	    joiner.setEmptyValue("default");
	    System.out.println(joiner.toString());
	}
	
	/**
	 * We can merge two joiners using merge(). It adds the contents of the given
	 * StringJoiner without prefix and suffix as the next element:
	 * 
	 * Note how "-" is used to concatenate content of cmybJoiner while rgbJoiner still use ",".
	 */
	public static void whenMergingJoiners_thenReturnMerged() {
	    StringJoiner rgbJoiner = new StringJoiner(",", "[", "]");
	    StringJoiner cmybJoiner = new StringJoiner("-", "[", "]");
	 
	    rgbJoiner.add("Red")
	      		 .add("Green")
	      		 .add("Blue");
	    cmybJoiner.add("Cyan")
	      		  .add("Magenta")
	      		  .add("Yellow")
	      		  .add("Black");
	 
	    rgbJoiner.merge(cmybJoiner);
	 
	    System.out.println(rgbJoiner.toString()); 
	}
	
}
