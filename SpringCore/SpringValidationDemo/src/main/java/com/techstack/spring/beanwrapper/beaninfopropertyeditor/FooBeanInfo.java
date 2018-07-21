/**
 * 
 */
package com.techstack.spring.beanwrapper.beaninfopropertyeditor;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.beans.SimpleBeanInfo;

import org.springframework.beans.propertyeditors.CustomNumberEditor;

/**
 * @author KARTHIKEYAN N
 *
 */
public class FooBeanInfo extends SimpleBeanInfo {

	public PropertyDescriptor[] getPropertyDescriptors() {
		try {
			final PropertyEditor numberPE = new CustomNumberEditor(Integer.class, true);
			PropertyDescriptor ageDescriptor = new PropertyDescriptor("age", Foo.class) {
				public PropertyEditor createPropertyEditor(Object bean) {
					return numberPE;
				};
			};
			return new PropertyDescriptor[] { ageDescriptor };
		} catch (IntrospectionException ex) {
			throw new Error(ex.toString());
		}
	}
}
