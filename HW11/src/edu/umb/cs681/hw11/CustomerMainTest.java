package edu.umb.cs681.hw11;

public class CustomerMainTest {
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
