package com.techstack.spring._15.internationalization;

import java.util.Locale;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 
 * @author KARTHIKEYAN N
 *
 *	using ReloadableResourceBundleMessageSource
 */
public class MainForMessageSource4 {

	public static void main(String[] args) {

		//Locale.setDefault(Locale.US);
        //uncomment next line to change the locale
        Locale.setDefault(Locale.GERMANY);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        MyBean bean = context.getBean(MyBean.class);
        bean.doSomething();
	}

}
