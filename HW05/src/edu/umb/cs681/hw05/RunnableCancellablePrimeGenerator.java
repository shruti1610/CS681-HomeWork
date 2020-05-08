package edu.umb.cs681.hw05;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeGenerator extends RunnablePrimeGenerator {

	private boolean done = false;
    private ReentrantLock lock = new ReentrantLock();
	
	public RunnableCancellablePrimeGenerator(long from, long to) {
        super(from, to);
    }
   
    public void setDone() {
        lock.lock();
        try {
            done = true;
        } finally {
            lock.unlock();
        }
    }

    public void generatePrimes() {
        for( long n = from; n <= to; n++ ) {
            lock.lock();
            try {
            	System.out.println("Lock obtained");
            } finally {
                lock.unlock();
                System.out.println("Lock Released");
            }
            if(done == true) {
            	System.out.println("Stopped generating prime nums.");
            	this.primes.clear();
                break;
            }
            if( isPrime(n) ) {
                this.primes.add(n);
            }
        }
    }

    public void run() {
        generatePrimes();
    }

	public static void main(String[] args) {
		RunnableCancellablePrimeGenerator g1 = new RunnableCancellablePrimeGenerator(1, 1000000L);
		RunnableCancellablePrimeGenerator g2 = new RunnableCancellablePrimeGenerator(1000001L, 2000000L);
		Thread t1 = new Thread(g1);
		Thread t2 = new Thread(g2);
		t1.start();
		t2.start();
		try {
            g1.setDone();
            g1.setDone();
            g1.setDone();
			t2.join();
		} catch (InterruptedException e) {}

		g1.getPrimes().forEach( (Long prime)->System.out.print(prime + ", ") );
        System.out.println();
		g2.getPrimes().forEach( (Long prime)->System.out.print(prime + ", ") );
		
		long primeNum = g1.getPrimes().size() + g2.getPrimes().size();
		System.out.println("\n" + primeNum + " prime numbers are found in total.");
        

	}
}
