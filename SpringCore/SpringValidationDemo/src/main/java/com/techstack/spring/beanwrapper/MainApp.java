package com.techstack.spring.beanwrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyValue;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class MainApp {

	public static void main(String[] args) {
		
		//ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		BeanWrapper company = new BeanWrapperImpl(new Company());
		// setting the company name..
		company.setPropertyValue("name", "Some Company Inc.");
		
		// ... can also be done like this using PropertyValue:
		PropertyValue value = new PropertyValue("name", "Some Company Inc.");
		company.setPropertyValue(value);

		// ok, let's create the director and tie it to the company:
		BeanWrapper jim = new BeanWrapperImpl(new Employee());
		jim.setPropertyValue("name", "Jim Stravinsky");
		jim.setPropertyValue("salary", 1000.00f);
		company.setPropertyValue("managingDirector", jim.getWrappedInstance());
		
		// ok, let's create a list of address for a company
		List<Address> addresses = new ArrayList<Address>();
		Address add1 = new Address();
		add1.setCity("Chennai");
		add1.setStreetName("Gandhi Street");
		add1.setZipcode(12345);
		addresses.add(add1);
		
//		BeanWrapper add1 = new BeanWrapperImpl(new Address());
//		add1.setPropertyValue("streetName", "Gandhi Street");
//		add1.setPropertyValue("city", "Chennai");
//		add1.setPropertyValue("zipcode", 12345);
		
		BeanWrapper add2 = new BeanWrapperImpl(new Address());
		add2.setPropertyValue("streetName", "Nethaji Street");
		add2.setPropertyValue("city", "Chennai");
		add2.setPropertyValue("zipcode", 98765);
		addresses.add((Address) add2.getWrappedInstance());
		
		company.setPropertyValue("companyAddresses", addresses);
		System.out.println(company.getPropertyValue("companyAddresses[0].streetName"));	//Access from List
		
		Map<String, Address> addressMap = new HashMap<String, Address>();
		addressMap.put("HOME_ADDRESS", add1);
		addressMap.put("OFFICE_ADDRESS", (Address) add2.getWrappedInstance());
		
		company.setPropertyValue("companyAddressesMap", addressMap);
		System.out.println(company.getPropertyValue("companyAddressesMap[OFFICE_ADDRESS].streetName"));	//Access from Map

		// retrieving the salary of the managingDirector through the company
		Float salary = (Float) company.getPropertyValue("managingDirector.salary");
		System.out.println(salary);
	}
}
