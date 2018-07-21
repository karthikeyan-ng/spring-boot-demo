/**
 * 
 */
package com.techstack.spring.fieldformatter.customformatter;

import java.util.Locale;

/**
 * @author KARTHIKEYAN N
 *
 */
public class MyBean {
	
	@LocaleFormat(style = LocaleStyle.ISO3Language)
    private Locale myLocale;

	public Locale getMyLocale() {
		return myLocale;
	}

	public void setMyLocale(Locale myLocale) {
		this.myLocale = myLocale;
	}

}
