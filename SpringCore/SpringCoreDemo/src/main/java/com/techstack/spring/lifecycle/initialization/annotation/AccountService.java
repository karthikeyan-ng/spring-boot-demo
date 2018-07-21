/**
 * 
 */
package com.techstack.spring.lifecycle.initialization.annotation;

import javax.annotation.PostConstruct;

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
	
	@PostConstruct
	public void init() {
		// do some initialization work
		System.out.println("simple init-method process for " + this.getClass().getSimpleName());
	}

}
