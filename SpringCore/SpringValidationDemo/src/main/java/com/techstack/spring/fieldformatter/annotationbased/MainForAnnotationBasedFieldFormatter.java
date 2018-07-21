/**
 * 
 */
package com.techstack.spring.fieldformatter.annotationbased;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.format.datetime.DateTimeFormatAnnotationFormatterFactory;
import org.springframework.format.number.NumberFormatAnnotationFormatterFactory;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.validation.DataBinder;

/**
 * @author KARTHIKEYAN N
 *
 */
public class MainForAnnotationBasedFieldFormatter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(false);

		conversionService.addFormatterForFieldAnnotation(new NumberFormatAnnotationFormatterFactory());

		conversionService.addFormatterForFieldAnnotation(new DateTimeFormatAnnotationFormatterFactory());

		Order order = new Order();
		DataBinder dataBinder = new DataBinder(order);
		dataBinder.setConversionService(conversionService);

		MutablePropertyValues mpv = new MutablePropertyValues();
		mpv.add("price", "2.7%");
		mpv.add("date", "2016");

		dataBinder.bind(mpv);
		dataBinder.getBindingResult().getModel().entrySet().forEach(System.out::println);

	}

}
