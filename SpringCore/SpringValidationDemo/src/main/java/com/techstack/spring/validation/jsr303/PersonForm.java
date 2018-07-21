/**
 * 
 */
package com.techstack.spring.validation.jsr303;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * When an instance of this class is validated by a JSR-303 Validator, these constraints will be enforced.
 * 
 * @author KARTHIKEYAN N
 *
 */
public class PersonForm {
	
	@NotNull
    @Size(max=64)
    private String name;

    @Min(0)
    private int age;

}
