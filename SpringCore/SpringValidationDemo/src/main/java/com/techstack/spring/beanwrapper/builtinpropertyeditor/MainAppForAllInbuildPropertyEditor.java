package com.techstack.spring.beanwrapper.builtinpropertyeditor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainAppForAllInbuildPropertyEditor {

	public static void main(String[] args) throws Exception {

		ApplicationContext context = new ClassPathXmlApplicationContext("InBuildPropertyEditor.xml");

		SampleBean testBean = context.getBean(SampleBean.class);
		System.out.println("file: " + testBean.getFile());
		printContent(testBean.getFile());
		System.out.println("url: " + testBean.getUrl());
		printContent(new File(testBean.getUrl().getFile()));
		System.out.println("Charset: " + testBean.getCharset());
		System.out.println("Class: " + testBean.getClazz());
		System.out.println("Class Array: " + Arrays.asList(testBean.getClazzArray()));
		System.out.println("Currency: " + testBean.getCurrency());
		System.out.println("Input Source: " + testBean.getInputSource());
		System.out.println("Input Stream: " + testBean.getInputStream());
		System.out.println("Locale: " + testBean.getLocale());
		System.out.println("Pattern: " + testBean.getPattern());
		System.out.println("Properties: " + testBean.getProperties());
		System.out.println("Resources:");
		for (Resource resource : testBean.getResources()) {
			System.out.println("location: " + resource.getFile().getCanonicalPath());
		}
		System.out.println("TimeZone: " + testBean.getTimeZone());
		System.out.println("UUID: " + testBean.getUuid().getMostSignificantBits());
		System.out.println("bytes in string form: " + new String(testBean.getBytes()));
		System.out.println("char array in string form: " + new String(testBean.getCharArray()));
		System.out.println("character: " + testBean.getCharacter());
		System.out.println("boolean value: " + testBean.isBool());
		System.out.println("double value: " + testBean.getDoubleValue());
		System.out.println("String array: " + Arrays.asList(testBean.getStringArray()));

	}

	private static void printContent(File file) throws IOException {
		String line;
		InputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
		BufferedReader br = new BufferedReader(isr);
		try {
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} finally {
			br.close();
		}
	}
}
