/**
 * 
 */
package com.techstack.spring.scopes.custom;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/**
 * @author KARTHIKEYAN N
 *
 */
public class MyThreadScope implements Scope {
	
	private final ThreadLocal<Object> myThreadScope = new ThreadLocal<Object>() {
		protected Map<String, Object> initialValue() {
			System.out.println("initialize ThreadLocal");
			return new HashMap<String, Object>();
		}
	};

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.config.Scope#get(java.lang.String, org.springframework.beans.factory.ObjectFactory)
	 */
	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		Map<String, Object> scope = (Map<String, Object>) myThreadScope.get();
		System.out.println("getting object from scope.");
		Object object = scope.get(name);
		if (object == null) {
			object = objectFactory.getObject();
			scope.put(name, object);
		}
		return object;
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.config.Scope#remove(java.lang.String)
	 */
	@Override
	public Object remove(String name) {
		System.out.println("removing object from scope.");
		@SuppressWarnings("unchecked")
		Map<String, Object> scope = (Map<String, Object>) myThreadScope.get();
		return scope.remove(name);
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.config.Scope#registerDestructionCallback(java.lang.String, java.lang.Runnable)
	 */
	@Override
	public void registerDestructionCallback(String name, Runnable callback) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.config.Scope#resolveContextualObject(java.lang.String)
	 */
	@Override
	public Object resolveContextualObject(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.config.Scope#getConversationId()
	 */
	@Override
	public String getConversationId() {
		// TODO Auto-generated method stub
		return null;
	}

}
