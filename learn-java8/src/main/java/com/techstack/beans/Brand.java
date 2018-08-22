/**
 * 
 */
package com.techstack.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Karthikeyan N
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand {

	private String name;
	
	private Model model;
}
