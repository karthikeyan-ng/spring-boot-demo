/**
 * 
 */
package com.techstack.spring.lifecycle.startupandshutdown;

import org.springframework.context.SmartLifecycle;

/**
 * @author KARTHIKEYAN N
 *
 */
public class SimpleLifeCycle implements SmartLifecycle {

	public SimpleLifeCycle() {
		System.out.println("LifecycleImpl class instantiated..");
	}

	@Override
	public boolean isRunning() {
		System.out.println("isRunning method of our LifecycleImpl class called..");
		return true;
	}

	@Override
	public void start() {
		System.out.println("start method of our LifecycleImpl class called..");
	}

	@Override
	public void stop() {
		System.out.println("stop method of our LifecycleImpl class called..");

	}

	@Override
	public int getPhase() {
		System.out.println("getPhase method of our LifecycleImpl class called..");
        return 1;
	}

	@Override
	public boolean isAutoStartup() {
		System.out.println("isAutoStartup method our LifecyleImpl class called..");
        return true;
	}

	@Override
	public void stop(Runnable arg0) {
		System.out.println("stop method of our LifecycleImpl class called..");
	}

}
