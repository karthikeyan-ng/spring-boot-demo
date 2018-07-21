/**
 * 
 */
package com.techstack.spring.fieldformatter.customformatter;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.validation.DataBinder;

/**
 * How to implement your own custom annotations for formatting
 * 1. Create your custom annotation class LocaleFormat.java, if your annotation dealing with any 
 * 	  additional ENUM style, create it like LocaleStyle.java
 * 
 * 2. Create your custom formatter class by implements Formatter interface. (LocaleFormatter.java)
 * 
 * 3. Binding annotation with formatter (LocaleFormatAnnotationFormatterFactory.java)
 * 		Now we are going to bind the annotation and the formatter (created in the last two steps) together by 
 * 		implementing AnnotationFormatterFactory
 * 
 * 		Spring finds the annotation on a field, will look for annotation-formatter mapping, as provided by 
 * 		this factory, and then invoke the corresponding formatter to populate the value to the field.
 * 
 *  4. Creating the target bean (any bean which will have a custom annotation as created in the previous step) (MyBean.java)
 *  
 *  5. Registering the AnnotationFormatterFactory to DataService and Using DataBinder (MainForCustomFormatAnnotationExample.java)
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainForCustomFormatAnnotationExample {

	public static void main(String[] args) {
		DefaultFormattingConversionService service = new DefaultFormattingConversionService();
		service.addFormatterForFieldAnnotation(new LocaleFormatAnnotationFormatterFactory());

		MyBean bean = new MyBean();
		DataBinder dataBinder = new DataBinder(bean);
		dataBinder.setConversionService(service);

		MutablePropertyValues mpv = new MutablePropertyValues();
		mpv.add("myLocale", "msa");

		dataBinder.bind(mpv);
		dataBinder.getBindingResult().getModel().entrySet().forEach(System.out::println);
		System.out.println(bean.getMyLocale().getDisplayCountry());
	}

}
