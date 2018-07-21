/**
 * 
 */
package com.techstack.spring.lifecycle.destruction.annotation;

import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

/**
 * @author KARTHIKEYAN N
 *
 */
@Component
public class AccountService {

	private String accountHolder;

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}
	
	@PreDestroy
	public void cleanup() {
		// do some destruction work (like releasing pooled connections)
		System.out.println("simple destroy-method process for " + this.getClass().getSimpleName());
	}

}
