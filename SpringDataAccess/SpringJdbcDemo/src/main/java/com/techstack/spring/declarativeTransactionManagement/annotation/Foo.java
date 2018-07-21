/**
 * 
 */
package com.techstack.spring.declarativeTransactionManagement.annotation;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author KARTHIKEYAN N
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Foo {
	
	private String fooName;
	
	private String barName;

	
}
