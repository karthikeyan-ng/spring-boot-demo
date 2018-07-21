package com.techstack.spring.lifecycle.defaultinitializationanddestroy;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class Account {
	
	private Integer accountNumber;
	
	private String accountHolderName;
	
	private Boolean active;

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public void init() {
		// do some initialization work
		System.out.println("simple init-method process for " + this.getClass().getSimpleName());
	}

}
