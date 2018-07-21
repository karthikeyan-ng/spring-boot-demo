package com.ford.fce;

public class Main {
	
	/**
	 * server side: domain startup script
	 * 
	 * set JAVA_OPTIONS=-javaagent:c:/domains/profiler.jar
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		
		Sub1 sub1 = new Sub1();
		sub1.a();
		sub1.b();
		sub1.c();

		Sub2 sub2 = new Sub2();
		sub2.d();
		sub2.e();
		sub2.f();
		
	}
	
}
