package edu.umb.cs681.hw10;

import java.util.concurrent.locks.ReentrantLock;

import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentSingleton {

	private ConcurrentSingleton() { };

	
	//private static ReentrantLock lock = new ReentrantLock();
	private static ConcurrentSingleton _instance = null;
	
	private static AtomicReference<ConcurrentSingleton> instance = new AtomicReference<ConcurrentSingleton>();
	
	public static ConcurrentSingleton getInstance() {
		
			if (instance.compareAndSet(null,_instance)) {
			_instance = new ConcurrentSingleton();
				instance.set(_instance);
			}
			return instance.get();
		
		}
	
}
