package edu.umb.cs681.hw14;

public class AdmissionMonitorTest {
	public static void main(String[] args) {
		AdmissionMonitor admissionMonitor = new AdmissionMonitor();
		EntranceHandler entHand = new EntranceHandler(admissionMonitor);
		ExitHandler exitHand = new ExitHandler(admissionMonitor);
		StateHandler stHand = new StateHandler(admissionMonitor);
		
		Thread t1 = new Thread(entHand);
		Thread t2 = new Thread(entHand);
		Thread t3 = new Thread(entHand);
		Thread t4 = new Thread(entHand);
		Thread t5 = new Thread(entHand);
		Thread t6 = new Thread(exitHand);
		Thread t7 = new Thread(exitHand);
		Thread t8 = new Thread(entHand);
		Thread t9 = new Thread(exitHand);
		Thread t10 = new Thread(stHand);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		t9.start();
		t10.start();
		t9.interrupt();
		try {
			admissionMonitor.setDone();
			t9.join();
		} catch (InterruptedException e) {}
		
	}
}
