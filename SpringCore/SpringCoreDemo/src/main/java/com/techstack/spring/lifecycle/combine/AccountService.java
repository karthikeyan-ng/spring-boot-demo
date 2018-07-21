/**
 * 
 */
package com.techstack.spring.lifecycle.combine;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author KARTHIKEYAN N
 *
 */
public class AccountService implements InitializingBean, DisposableBean {

	private String accountHolder;

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		// do some initialization work
		System.out.println("simple afterPropertiesSet process for " + this.getClass().getSimpleName());
		
	}
	
	@Override
	public void destroy() throws Exception {
		// do some destruction work (like releasing pooled connections)
		System.out.println("simple destroy process for " + this.getClass().getSimpleName());
	}

	public void init() {
		// do some initialization work
		System.out.println("simple init-method process for " + this.getClass().getSimpleName());
	}
	
	public void close() {
		// do some destruction work (like releasing pooled connections)
		System.out.println("simple destroy-method process for " + this.getClass().getSimpleName());
	}
	
	@PostConstruct
	public void myInitAnnotation() {
		// do some initialization work
		System.out.println("simple init-method process using annotation PostConstruct for " + this.getClass().getSimpleName());
	}
	
	@PreDestroy
	public void myDestroyAnnotation() {
		// do some destruction work (like releasing pooled connections)
		System.out.println("simple destroy-method process using annotation PreDestroy for " + this.getClass().getSimpleName());
	}
	

}
