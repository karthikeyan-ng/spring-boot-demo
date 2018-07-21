/**
 * 
 */
package com.techstack.spring.instantiation.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.techstack.spring.instantiation.domain.Parameter;

/**
 * @author KARTHIKEYAN N
 *
 */
@Component
@Scope(value = "prototype")
public class Service implements IService {

	public boolean convert(Parameter param) {

		System.out.println("Service ==>" + this);
		
		if (param == null)
			return false;

		System.out.println("Param is " + param.getParam());

		return true;
	}

}
