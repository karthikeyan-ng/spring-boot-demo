/**
 * 
 */
package com.techstack.spring.beanwrapper;

import java.util.List;
import java.util.Map;

/**
 * @author KARTHIKEYAN N
 *
 */
public class Company {

	private String name;
	private Employee managingDirector;
	private List<Address> companyAddresses;
	private Map<String, Address> companyAddressesMap;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Employee getManagingDirector() {
		return this.managingDirector;
	}

	public void setManagingDirector(Employee managingDirector) {
		this.managingDirector = managingDirector;
	}
	
	public List<Address> getCompanyAddresses() {
		return companyAddresses;
	}
	
	public void setCompanyAddresses(List<Address> companyAddresses) {
		this.companyAddresses = companyAddresses;
	}
	
	public Map<String, Address> getCompanyAddressesMap() {
		return companyAddressesMap;
	}
	
	public void setCompanyAddressesMap(Map<String, Address> companyAddressesMap) {
		this.companyAddressesMap = companyAddressesMap;
	}

}
