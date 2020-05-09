package edu.umb.cs681.hw11;

import java.util.concurrent.locks.ReentrantLock;

public class CustomerTest implements Runnable {
	public ReentrantLock lock = new ReentrantLock();
	private boolean done = false;

	public void setDone() {
		lock.lock();
		try {
			this.done = true;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void run() {
		lock.lock();
		try {
			Customer customer = new Customer(new Address("1 Wellington Terrace", "Brookline", "MA", 02445));
			System.out.println("Customer Address : "+customer.getAddress());
			customer.setAddress(customer.getAddress().change("2 Wellington Terrace", "Boston", "MA", 02110));
			System.out.println("Changed Customer Address : "+customer.getAddress());
		} finally {
			lock.unlock();
		}
	}

	public static void main(String args[]) {
		for (int i = 0; i < 8; i++) {
			CustomerTest customer = new CustomerTest();
			Thread thread = new Thread(customer);
			thread.start();
			customer.setDone();
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
