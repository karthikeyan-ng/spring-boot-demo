/**
 * 
 */
package com.techstack.spring.beanwrapper.builtinpropertyeditor;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Component;

/**
 * @author KARTHIKEYAN N
 *
 */
@Component
public final class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar {

	@Override
	public void registerCustomEditors(PropertyEditorRegistry registry) {

		// it is expected that new PropertyEditor instances are created
        registry.registerCustomEditor(Phone.class, new CustomPhoneEditor());
        
        // you could register as many custom property editors as are required here...
        registry.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false));
		
	}
	
	

}
