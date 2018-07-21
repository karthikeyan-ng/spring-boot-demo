package com.techstack.spring.fieldformatter.customformatter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface LocaleFormat {
	LocaleStyle style() default LocaleStyle.CountryDisplayName;
}
