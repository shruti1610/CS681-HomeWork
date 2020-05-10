package edu.umb.cs681.hw14;

public class ExitHandler implements Runnable {
	private AdmissionMonitor monitor;

	public ExitHandler(AdmissionMonitor monitor) {
		super();
		this.monitor = monitor;
	}

	public void run() {
		System.out.println("Removing a visitor: "+ Thread.currentThread().getName());
		monitor.exit();
	}
}
