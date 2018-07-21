/**
 * 
 */
package com.techstack.spring.lifecycle.destruction;

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
	
	public void cleanup() {
		// do some destruction work (like releasing pooled connections)
		System.out.println("simple destroy-method process for " + this.getClass().getSimpleName());
	}

}
