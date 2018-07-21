/**
 * 
 */
package com.techstack.spring.beanwrapper.builtinpropertyeditor;

import org.springframework.beans.PropertyEditorRegistrar;

/**
 * This demo class is used to do register PropertyEditorRegistrar in 
 * Spring MVC applications.
 * 
 * @author KARTHIKEYAN N
 *
 */
public final class RegisterUserController extends SimpleFormController {

	private final PropertyEditorRegistrar customPropertyEditorRegistrar;

	public RegisterUserController(PropertyEditorRegistrar propertyEditorRegistrar) {
		this.customPropertyEditorRegistrar = propertyEditorRegistrar;
	}

	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		this.customPropertyEditorRegistrar.registerCustomEditors(binder);
	}

	// other methods to do with registering a User
}
