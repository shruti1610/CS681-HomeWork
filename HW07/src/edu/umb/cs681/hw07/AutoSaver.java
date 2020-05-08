package edu.umb.cs681.hw07;

import java.time.Instant;
import java.util.concurrent.locks.ReentrantLock;

public class AutoSaver implements Runnable {

	private boolean done = false;
    private File file;
    private ReentrantLock lock = new ReentrantLock();
    
    public AutoSaver(File f) {
        file = f;
    }
  
	@Override
	public void run() {
        while (true) {
            lock.lock();
            try {
                if (done) { 
                    System.out.println("Autosaver Stopped at "+ Instant.now());
                    return;
                }
            } finally {
                lock.unlock();
            }
            file.save();
            try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
                return;
			}
        }
	}
	public void setDone() {
        lock.lock();
        try {
            done = true;
        } finally {
            lock.unlock();
        }
    }
  
    
}
