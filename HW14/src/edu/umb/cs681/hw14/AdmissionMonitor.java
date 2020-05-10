package edu.umb.cs681.hw14;


import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class AdmissionMonitor {
	private int currentVisitors = 0;
	private ReentrantLock lock = new ReentrantLock();
	private final Condition condition = lock.newCondition();
	private static int time = 0;
	private boolean done = false;

	public void enter() {
		lock.lock();
		
		try {
            if (done){
            	System.out.println("Stopped admitting visitors.");
            	return;
            }
        } finally {
            lock.unlock();
        }
		lock.lock();
		while(currentVisitors >= 5){
			
		System.out.print("Too many visitors. Please wait for a while!");
		
		try {
			condition.await();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
		try {
			condition.await();
			currentVisitors++;
		} catch (InterruptedException e) {
		} finally {
			condition.signalAll();
			lock.unlock();
		}
	}

	public void exit() {
		lock.lock();
		try {
			condition.await();
			currentVisitors--;
		} catch (InterruptedException e) {
		} finally {
			condition.signalAll();
			lock.unlock();
		}
	}

	public int countCurrentVisitors() {
		return currentVisitors;
	}
	
	public void setDone() {
        lock.lock();
        try {
        	System.out.println("Lock obtained");
            this.done = true;
        } finally {
            lock.unlock();
            System.out.println("Lock released");
        }
    }
}