/**
 * 
 */
package com.techstack.spring.annotations.required;

import org.springframework.beans.factory.annotation.Required;

/**
 * @author KARTHIKEYAN N
 *
 */
public class ClientBean {

	private ServiceBean serviceBean;

	@Required
	public void setServiceBean(ServiceBean serviceBean) {
		this.serviceBean = serviceBean;
	}

	public void work() {
		System.out.println("service bean instance: " + serviceBean);
	}
}
