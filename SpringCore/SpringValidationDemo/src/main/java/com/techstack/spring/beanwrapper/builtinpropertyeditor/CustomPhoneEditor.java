/**
 * 
 */
package com.techstack.spring.beanwrapper.builtinpropertyeditor;

import java.beans.PropertyEditorSupport;

/**
 * @author KARTHIKEYAN N
 *
 */
public class CustomPhoneEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		String[] split = text.split("[|]");
		if (split.length != 2) {
			throw new IllegalArgumentException(
					"Phone is not correctly defined. The correct format is " + "PhoneType|111-111-1111");
		}
		// the method is already throwing IllegalArgumentException
		// so don't worry about split[0] invalid enum value.
		PhoneType phoneType = PhoneType.valueOf(split[0].trim().toUpperCase());
		Phone phone = new Phone();
		phone.setPhoneType(phoneType);
		phone.setPhoneNumber(split[1].trim());
		setValue(phone);

	}
}
