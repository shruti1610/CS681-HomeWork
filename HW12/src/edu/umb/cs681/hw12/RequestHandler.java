package edu.umb.cs681.hw12;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class RequestHandler implements Runnable {
	public List<Path> results;
	private boolean done = false;
	public ReentrantLock lock = new ReentrantLock();

	public RequestHandler(List<Path> files) {
		this.results = files;
	}

	public void setDone() {
		done = true;
	}

	@Override
	public void run() {
		while (true) {
			lock.lock();
			try {
				if (done) {
					System.out.println("Thread Stopped");
					break;
				}
				Random rand = new Random();
				int RandomElement = rand.nextInt(results.size());

				AccessCounter counter = AccessCounter.getInstance();
				System.out.println("Processing file: "+results.get(RandomElement));
				counter.increment(results.get(RandomElement));
				System.out.println("Access counter value: "+counter.getCount(results.get(RandomElement)));
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					continue;
				}
			} finally {
				lock.unlock();
			}
		}
	}

	public static void main(String args[]) {
		List<Path> files = new ArrayList<Path>();
		try {
			files = Files.walk(Paths.get("./test/dummy files/"))
					.filter(Files::isRegularFile).collect(Collectors.toList());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Total number of files "+files.size());
		RequestHandler r = new RequestHandler(files);
		Thread t1 = new Thread(r);
		Thread t2 = new Thread(r);
		Thread t3 = new Thread(r);
		Thread t4 = new Thread(r);
		Thread t5 = new Thread(r);
		Thread t6 = new Thread(r);
		Thread t7 = new Thread(r);
		Thread t8 = new Thread(r);
		Thread t9 = new Thread(r);
		Thread t10 = new Thread(r);
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
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		r.setDone();
		t1.interrupt();
		t2.interrupt();
		t3.interrupt();
		t4.interrupt();
		t5.interrupt();
		t6.interrupt();
		t7.interrupt();
		t8.interrupt();
		t9.interrupt();
		t10.interrupt();

		AccessCounter accessCounter = AccessCounter.getInstance();
		System.out.println("Access counter : "+accessCounter.counter);
	}

}
