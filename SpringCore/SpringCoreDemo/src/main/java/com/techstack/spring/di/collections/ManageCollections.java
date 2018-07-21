/**
 * 
 */
package com.techstack.spring.di.collections;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author KARTHIKEYAN N
 *
 */
public class ManageCollections {
	
	private Properties adminEmails;
	private List<String> listCollection;
	private Set<String> setCollection;
	private Map<String, String> mapCollection;
	
	public Properties getAdminEmails() {
		return adminEmails;
	}
	
	public void setAdminEmails(Properties adminEmails) {
		this.adminEmails = adminEmails;
	}
	
	public String getEmailFor(String key) {
		return this.adminEmails.getProperty(key);
	}
	
	public List<String> getListCollection() {
		return listCollection;
	}
	
	public void setListCollection(List<String> listCollection) {
		this.listCollection = listCollection;
	}
	
	public Set<String> getSetCollection() {
		return setCollection;
	}
	
	public void setSetCollection(Set<String> setCollection) {
		this.setCollection = setCollection;
	}
	
	public Map<String, String> getMapCollection() {
		return mapCollection;
	}
	
	public void setMapCollection(Map<String, String> mapCollection) {
		this.mapCollection = mapCollection;
	}
	
}
