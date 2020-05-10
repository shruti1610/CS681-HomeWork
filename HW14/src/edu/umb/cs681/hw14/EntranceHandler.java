package edu.umb.cs681.hw14;

public class EntranceHandler implements Runnable {
	private AdmissionMonitor monitor;

	public EntranceHandler(AdmissionMonitor monitor) {
		super();
		this.monitor = monitor;
	}

	public void run() {
		System.out.println("Admitting a visitor: "+ Thread.currentThread().getName());
		monitor.enter();
	}
}
