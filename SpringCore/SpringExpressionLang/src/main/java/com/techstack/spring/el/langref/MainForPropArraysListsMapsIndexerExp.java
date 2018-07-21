/**
 * 
 */
package com.techstack.spring.el.langref;

import java.util.GregorianCalendar;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import com.techstack.spring.el.beans.Inventor;
import com.techstack.spring.el.beans.PlaceOfBirth;
import com.techstack.spring.el.beans.Society;

/**
 * @author KARTHIKEYAN N
 *
 */
public class MainForPropArraysListsMapsIndexerExp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Create and set a calendar
		GregorianCalendar c = new GregorianCalendar();
		c.set(1856, 7, 9);

		PlaceOfBirth teslaPlaceOfBirth = new PlaceOfBirth("NewYork", "USA");
		
		Inventor tesla = new Inventor();
		tesla.setName("Tesla");
		tesla.setNationality("USA");
		tesla.setBirthdate(c.getTime());
		tesla.setPlaceOfBirth(teslaPlaceOfBirth);
		tesla.setInventions(new String[] {"Invention1", "Invention2"});
		
		Society society = new Society();
		society.setName("ieee");
		society.getMembers().add(tesla);

		ExpressionParser parser = new SpelExpressionParser();

		// evals to 1856
		int year = (Integer) parser.parseExpression("birthdate.Year + 1900").getValue(tesla);
		System.out.println(year);

		String city = (String) parser.parseExpression("placeOfBirth.City").getValue(tesla);
		System.out.println(city);
		
		String inventione1 = (String) parser.parseExpression("inventions[0]").getValue(tesla);
		System.out.println(inventione1);
		
		
		/**
		 * Case insensitivity is allowed for the first letter of property names. The contents of arrays and 
		 * lists are obtained using square bracket notation.
		 */
		
		parser = new SpelExpressionParser();
		EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build(); //>v4.3.15
		
		// Inventions Array

		// evaluates to "Induction motor"
		String invention = parser.parseExpression("inventions[1]").getValue(
		        context, tesla, String.class);
		
		// Members List

		// evaluates to "Nikola Tesla"
		String name = parser.parseExpression("Members[0].Name").getValue(
		        context, society, String.class);

		// List and Array navigation
		// evaluates to "Wireless communication"
		invention = parser.parseExpression("Members[0].Inventions[0]").getValue(
		        context, society, String.class);
		
		/**
		 * The contents of maps are obtained by specifying the literal key value within the brackets. 
		 * In this case, because keys for the Officers map are strings, we can specify string literals.
		 */
		// Officer's Dictionary

		Inventor pupin = parser.parseExpression("Officers['president']").getValue(
		        societyContext, Inventor.class);

		// evaluates to "Idvor"
		String city = parser.parseExpression("Officers['president'].PlaceOfBirth.City").getValue(
		        societyContext, String.class);

		// setting values
		parser.parseExpression("Officers['advisors'][0].PlaceOfBirth.Country").setValue(
		        societyContext, "Croatia");
	}

}
