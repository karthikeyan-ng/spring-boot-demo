/**
 * 
 */
package com.techstack.spring.fieldformatter.customformatter;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Optional;

import org.springframework.format.Formatter;

/**
 * Creating the formatter for accessing Locale
 * 
 * @author KARTHIKEYAN N
 *
 */
public class LocaleFormatter implements Formatter<Locale> {

	private LocaleStyle localeStyle;

	public LocaleStyle getLocaleStyle() {
		return localeStyle;
	}

	public void setLocaleStyle(LocaleStyle localeStyle) {
		this.localeStyle = localeStyle;
	}

	@Override
	public Locale parse(String text, Locale locale) throws ParseException {
		Optional<Locale> o = Arrays.stream(Locale.getAvailableLocales()).parallel()
				.filter(l -> this.localeByStylePredicate(l, text)).findAny();
		if (o.isPresent()) {
			return o.get();
		}
		return null;
	}

	@Override
	public String print(Locale object, Locale locale) {
		switch (localeStyle) {
		case CountryDisplayName:
			return object.getDisplayCountry();
		case ISO3Country:
			return object.getISO3Country();
		case ISO3Language:
			return object.getISO3Language();
		}
		return object.toString();
	}

	private boolean localeByStylePredicate(Locale locale, String text) {
		try {
			switch (localeStyle) {
			case CountryDisplayName:
				return locale.getDisplayCountry().equalsIgnoreCase(text);
			case ISO3Country:
				return locale.getISO3Country().equalsIgnoreCase(text);
			case ISO3Language:
				return locale.getISO3Language().equalsIgnoreCase(text);
			}
		} catch (MissingResourceException e) {
			// ignore;
		}
		return false;
	}

}
