package com.ford.fce;

public class Sub1 {

	public void a() {
		System.out.println("a");
		b();
	}

	public void b() {
		String importantInfo[] = {
	            "Mares eat oats",
	            "Does eat oats",
	            "Little lambs eat ivy",
	            "A kid will eat ivy too"
	    };

        for (int i = 0; i < importantInfo.length; i++) {
            //Pause for 15 seconds
            try {
				Thread.sleep(5000);  //15000
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            //Print a message
            System.out.println(importantInfo[i]);
        }
		System.out.println("b");
	}

	public void c() {
		System.out.println("c");	
	}
	
}
