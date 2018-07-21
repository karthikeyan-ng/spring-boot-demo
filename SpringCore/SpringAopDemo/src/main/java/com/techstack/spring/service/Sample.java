/**
 * 
 */
package com.techstack.spring.service;

import java.util.Collection;

/**
 * @author KARTHIKEYAN N
 *
 */
public interface Sample<T> {

	void sampleGenericMethod(T param);

	void sampleGenericCollectionMethod(Collection<T> param);
}
