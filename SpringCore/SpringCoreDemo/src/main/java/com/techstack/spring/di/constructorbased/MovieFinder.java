/**
 * 
 */
package com.techstack.spring.di.constructorbased;

import org.springframework.stereotype.Component;

/**
 * @author KARTHIKEYAN N
 *
 */
@Component
public class MovieFinder {

	public String findById(int id) {
		String movieName = "";
		switch(id) {
		case 100: 
			movieName = "James Bond";
			break;
		case 200:
			movieName = "Anaconda";
			break;
		case 300:
			movieName = "Jurassic Park";
			break;
		default:
			movieName = "Predator";
			break;
		}
		return movieName;
	}

}
