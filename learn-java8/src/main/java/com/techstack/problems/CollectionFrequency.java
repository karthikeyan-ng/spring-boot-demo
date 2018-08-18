package com.techstack.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Problem Statement: 
 * 	- Given Integer array contains "1, 2, 1, 3, 2, 4, 5, 6, 5, 7, 7, 8" elements.
 *  - Find more than one occurrence of the elements from the given elements.
 *  - expected result: 1,2, 1, 2, 5, 5, 7, 7
 *
 */
public class CollectionFrequency {

	public static void main(String[] arg) {
		usingImperativeApproach();
		usingIntStreams();
		usingWords();
	}

	private static void usingImperativeApproach() {
		Integer[] array = { 1, 2, 1, 3, 2, 4, 5, 6, 5, 7, 7, 8 };
		ArrayList<Integer> input = new ArrayList<>(Arrays.asList(array));
		List<Integer> output = new ArrayList<>();
		int count = 0;
		for (Integer x : input) {
			count = search(input, x);
			if (count >= 2) {
				output.add(x);
			}
		}
		System.out.println("Input : " + input);
		System.out.println("Output : " + output);
	}

	private static int search(List<Integer> list, Integer key) {
		return Collections.frequency(list, key);
	}
	
	private static void usingIntStreams() {
		IntStream values = IntStream.of(1, 2, 1, 3, 2, 4, 5, 6, 5, 7, 7, 8);
		List<Integer> input = values.boxed().collect(Collectors.toList());
		List<Integer> result = input.stream()
									.filter(value -> Collections.frequency(input, value) >= 2)
									.collect(Collectors.toList());
		System.out.println("Output : " + result);
	}
	
	private static void usingWords() {
		
		String randomParagraph = "Difficulty on insensible reasonable in. From as went "
		        + "he they. Preference themselves me as thoroughly partiality considered "
		        + "on in estimating. Middletons acceptance discovered projecting so is so "
		        + "or. In or attachment inquietude remarkably comparison at an. Is "
		        + "surrounded prosperous stimulated am me discretion expression. But "
		        + "truth being state can she china widow. Occasional preference fat "
		        + "remarkably now projecting uncommonly dissimilar. Sentiments projection "
		        + "particular companions interested do at my delightful. Listening newspaper "
		        + "in advantage frankness to concluded unwilling.";
		
		List<String> statements;
		List<String> words = new ArrayList<>();
		String wordToFind = "Preference"; //me
		statements = Arrays.asList(randomParagraph.split("\\. "));
		statements.forEach(statement -> {
										words.addAll(Arrays.asList(statement.replace(".", "").split(" ")));
									});
			 
		long numberOfOccurences = words.stream()
							           .filter(p -> p.equalsIgnoreCase(wordToFind))
							           .count();
		System.out.println("Given word '" + wordToFind + "' was occured " + numberOfOccurences + " times in the above given paragraph");

	}
	

}
