/**
 * 
 */
package com.techstack.spring.di.collaborators;

/**
 * @author KARTHIKEYAN N
 *
 */
public class DemoBean {

	public SampleBean sampleBean;
	
	public void setSampleBean(SampleBean sampleBean) {
		this.sampleBean = sampleBean;
	}

	public void m1() {
		sampleBean.m2();
	}

}
