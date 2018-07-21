/**
 * 
 */
package com.techstack.spring.annotations.inject;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

/**
 * @author KARTHIKEYAN N
 *
 */
@Component
public class ClientBean {

	private ServiceBean serviceBean;
	
	/**
	 * JSR 330’s @Inject annotation can be used in place of Spring’s @Autowired annotation in the examples below. 
	 * @param serviceBean
	 */
	@Inject
	public ClientBean(ServiceBean serviceBean) {
		this.serviceBean = serviceBean;
	}

	//@Autowired
	public void setServiceBean(ServiceBean serviceBean) {
		this.serviceBean = serviceBean;
	}

	public void work() {
		System.out.println("service bean instance: " + serviceBean);
	}
}
