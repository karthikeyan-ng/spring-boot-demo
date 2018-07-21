package com.techstack.spring.fieldformatter.customformatter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

public class LocaleFormatAnnotationFormatterFactory implements AnnotationFormatterFactory<LocaleFormat> {

	@Override
	public Set<Class<?>> getFieldTypes() {
		return new HashSet<>(Arrays.asList(Locale.class));
	}

	@Override
	public Printer<?> getPrinter(LocaleFormat annotation, Class<?> fieldType) {
		return getLocaleFormatter(annotation, fieldType);
	}

	@Override
	public Parser<?> getParser(LocaleFormat annotation, Class<?> fieldType) {
		return getLocaleFormatter(annotation, fieldType);
	}

	private Formatter<?> getLocaleFormatter(LocaleFormat annotation, Class<?> fieldType) {
		LocaleFormatter lf = new LocaleFormatter();
		lf.setLocaleStyle(annotation.style());
		return lf;
	}

}
