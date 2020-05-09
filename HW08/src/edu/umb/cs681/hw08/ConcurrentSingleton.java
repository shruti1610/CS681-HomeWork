package edu.umb.cs681.hw08;

import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentSingleton {

	private ConcurrentSingleton() {
	};

	
	private static ReentrantLock lock = new ReentrantLock();
	private static ConcurrentSingleton instance = null;

	
	public static ConcurrentSingleton getInstance() {
		lock.lock();
		try {
			if (instance == null) {
				instance = new ConcurrentSingleton();
			}
			return instance;
		} finally {
			lock.unlock();
		}
	}
}
