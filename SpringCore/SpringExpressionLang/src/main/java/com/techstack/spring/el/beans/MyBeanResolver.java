/**
 * 
 */
package com.techstack.spring.el.beans;

import org.springframework.expression.AccessException;
import org.springframework.expression.BeanResolver;
import org.springframework.expression.EvaluationContext;

/**
 * @author KARTHIKEYAN N
 *
 */
public class MyBeanResolver implements BeanResolver {

	/* (non-Javadoc)
	 * @see org.springframework.expression.BeanResolver#resolve(org.springframework.expression.EvaluationContext, java.lang.String)
	 */
	@Override
	public Object resolve(EvaluationContext arg0, String arg1) throws AccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
