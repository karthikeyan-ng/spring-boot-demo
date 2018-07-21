/**
 * 
 */
package com.techstack.spring._15.events.custom;

import org.springframework.context.ApplicationEvent;
import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;

/**
 * 
 * 
 * 
 * @author KARTHIKEYAN N
 *
 */
public class EntityCreatedEvent<T> extends ApplicationEvent implements ResolvableTypeProvider {

	public EntityCreatedEvent(T entity) {
		super(entity);
	}

	/* (non-Javadoc)
	 * @see org.springframework.core.ResolvableTypeProvider#getResolvableType()
	 */
	@Override
	public ResolvableType getResolvableType() {
        return ResolvableType.forClassWithGenerics(getClass(),
                ResolvableType.forInstance(getSource()));
    }

}
