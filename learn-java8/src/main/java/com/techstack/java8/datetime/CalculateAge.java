/**
 * 
 */
package com.techstack.java8.datetime;

import java.time.LocalDate;
import java.time.Period;

/**
 * @author KARTHIKEYAN N
 *
 */
public class CalculateAge {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int age = calculateAge(LocalDate.of(1984, 11, 22), LocalDate.now());
		System.out.println("My age is : " + age);
	}

	/**
	 * LocalDate is helpful here because represents just a date, compared to Java’s
	 * Date class, which represents both a date and a time. LocalDate.now() can give
	 * us the current date.
	 * 
	 * And Period is helpful when we need to think about time periods in years,
	 * months and days.
	 * 
	 * If we wanted to get a more exact age, say in seconds, then we’d want to take
	 * a look at LocalDateTime and Duration, respectively (and maybe return a long
	 * instead).
	 * 
	 * @param birthDate
	 * @param currentDate
	 * @return
	 */
	public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
		// validate inputs ...
		return Period.between(birthDate, currentDate).getYears();
	}
	
}
