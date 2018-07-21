package com.ford.fce;

public class Sub2 {

	Sub1 mySub1;
	
	public Sub2() {
		mySub1 = new Sub1();
	}
	
	public void d() {
		System.out.println("d");
		mySub1.a();
	}

	public void e() {
		System.out.println("e");
		mySub1.b();		
	}

	public void f() {
		System.out.println("f");
		mySub1.c();
		e();
	}

}
