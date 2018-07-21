/**
 * 
 */
package com.techstack.spring.lifecycle.destruction;

import org.springframework.beans.factory.DisposableBean;

/**
 * @author KARTHIKEYAN N
 *
 */
public class AccountService2 implements DisposableBean {

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
	public void destroy() throws Exception {
		// do some destruction work (like releasing pooled connections)
		System.out.println("using DisposableBean#destroy process for " + this.getClass().getSimpleName());
	}

}
