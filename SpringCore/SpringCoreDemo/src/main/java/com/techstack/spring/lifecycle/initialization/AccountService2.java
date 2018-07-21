/**
 * 
 */
package com.techstack.spring.lifecycle.initialization;

import org.springframework.beans.factory.InitializingBean;

/**
 * @author KARTHIKEYAN N
 *
 */
public class AccountService2 implements InitializingBean {

	private String accountHolder;

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}
	
	/**
	 * using InitializingBean is exactly same as init-method="init"
	 * 
	 * TIP: It is recommended that you do not use the InitializingBean interface because it unnecessarily couples the code to Spring. 
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		// do some initialization work
		System.out.println("using InitializingBean#afterPropertiesSet process for " + this.getClass().getSimpleName());
	}

}
