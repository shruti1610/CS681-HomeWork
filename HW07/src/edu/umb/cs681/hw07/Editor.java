package edu.umb.cs681.hw07;

import java.time.Instant;
import java.util.concurrent.locks.ReentrantLock;

public class Editor implements Runnable {

    public Editor(File f) {
        file = f;
    }

    private boolean done = false;
    private File file;
    private ReentrantLock lock = new ReentrantLock();
   
	@Override
	public void run() {
        while (true) {
            lock.lock();
            try {
                if (done) {
                    System.out.println("Editor Stopped at "+ Instant.now());
                    break;
                }
            } finally {
                lock.unlock();
            }
            file.change();
            file.save();
            try {
				Thread.sleep(1000);
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
