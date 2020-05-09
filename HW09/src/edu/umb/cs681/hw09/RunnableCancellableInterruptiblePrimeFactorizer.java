package edu.umb.cs681.hw09;

import java.util.concurrent.locks.ReentrantLock;
import java.util.LinkedList;

public class RunnableCancellableInterruptiblePrimeFactorizer extends RunnablePrimeFactorizer {
	private boolean done = false;
    private final ReentrantLock lock = new ReentrantLock();
    
    public RunnableCancellableInterruptiblePrimeFactorizer(long dividend, long from, long to) {
		super(dividend, from, to);
	}

public ReentrantLock getLock() {
		return lock;
	}


    public void setDone() {
        lock.lock();
        try {
        	System.out.println("Lock obtained");
            done = true;
        } finally {
            lock.unlock();
            System.out.println("Lock released");
        }
    }

	public void generatePrimeFactors() {
		long divisor = from;
	    while( dividend != 1 && divisor <= to ){
            lock.lock();
            if (RunnableCancellableInterruptiblePrimeFactorizer.interrupted()) {
				System.out.println("Stopped due to interruption");
				this.factors.clear();
				break; // Balking
			}
			lock.unlock();
	    	if( divisor > 2 && isEven(divisor)) {
	    		divisor++;
	    		continue;
	    	}
		    if(dividend % divisor == 0) {
		        factors.add(divisor);
		        dividend /= divisor;
		    }else {
		    	if(divisor==2){ divisor++; }
		    	else{ divisor += 2; }
		    	
		    }
		}
	}
	
	public static boolean interrupted(){
		return Thread.currentThread().interrupted();
		}
	
	public void run() {
		generatePrimeFactors();
		//System.out.println("Thread #" + Thread.currentThread().getId() + " generated " + factors);
	}

	public static void main(String[] args) {
		
		System.out.println("Factorization of 36");
		RunnableCancellableInterruptiblePrimeFactorizer runnable = new RunnableCancellableInterruptiblePrimeFactorizer(36, 2, (long) Math.sqrt(36));
		Thread thread = new Thread(runnable);
		System.out.println("Thread #" + thread.getId() + " performs factorization in the range of " + runnable.getFrom()
				+ "->" + runnable.getTo());
		thread.start();
		synchronized(runnable){
					runnable.getLock().lock();
					thread.interrupt();
					runnable.getLock().unlock();
				}
				
				try {
					thread.join();
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
		System.out.println("Final result: " + runnable.getPrimeFactors() + "\n");

		
		
	}
}
