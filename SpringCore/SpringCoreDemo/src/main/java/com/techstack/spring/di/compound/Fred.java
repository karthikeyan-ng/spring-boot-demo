package com.techstack.spring.di.compound;

/**
 * 
 * @author KARTHIKEYAN N
 *
 */
public class Fred {
	
	private Bob bob;

    public Fred() {
        bob = new Bob();
    }

    public Bob getBob() {
        return bob;
    }

    public void setBob(Bob bob) {
        this.bob = bob;
    }
}
