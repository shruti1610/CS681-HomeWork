package edu.umb.cs681.hw08;

public class ConcurrentSingletonTest extends Thread {
	public void run() {
		System.out.println("ConcurrentSingleton Instance " + ConcurrentSingleton.getInstance());
	}

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new ConcurrentSingletonTest();
		Thread t2 = new ConcurrentSingletonTest();
		Thread t3 = new ConcurrentSingletonTest();
		Thread t4 = new ConcurrentSingletonTest();
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t1.join();
		t2.join();
		t3.join();
		t4.join();
	}

}
