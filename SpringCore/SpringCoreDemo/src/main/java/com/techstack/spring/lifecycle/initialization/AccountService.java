/**
 * 
 */
package com.techstack.spring.lifecycle.initialization;

/**
 * @author KARTHIKEYAN N
 *
 */
public class AccountService {

	private String accountHolder;

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}
	
	public void init() {
		// do some initialization work
		System.out.println("simple init-method process for " + this.getClass().getSimpleName());
	}

}
