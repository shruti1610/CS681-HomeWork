package edu.umb.cs681.hw21;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.atomic.AtomicInteger;
import java.lang.Object;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Long;

import java.util.concurrent.Future;

import java.util.*;

public class RunnableInterruptiblePrimeGenExecutorTest extends RunnablePrimeGenerator {

	private boolean done = false;
    private ReentrantLock lock = new ReentrantLock();
	 
	  
	  
	public RunnableInterruptiblePrimeGenExecutorTest(long from, long to) {
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
           
            if(Thread.interrupted()) {
            	System.out.println("Stopped generating prime numbers due to a Thread Interruption.");
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
		RunnableInterruptiblePrimeGenExecutorTest g1 = new RunnableInterruptiblePrimeGenExecutorTest(1, 1000000L);
		RunnableInterruptiblePrimeGenExecutorTest g2 = new RunnableInterruptiblePrimeGenExecutorTest(1000001L, 2000000L);
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(g1);
		executor.execute(g2);
		
		System.out.println("Implementing Executor using 'shutdownNow()' method");
			System.out.println(" ");
		
		try {
   
     if (!executor.awaitTermination(2, TimeUnit.MILLISECONDS)) {
      executor.shutdownNow(); 
       if (!executor.awaitTermination(2, TimeUnit.MILLISECONDS))
           System.out.println("Executor did not terminate");
     }
   } 
   	catch (InterruptedException ie) {
 	executor.shutdownNow();
   	Thread.currentThread().interrupt();
   }
	
	

		g1.getPrimes().forEach( (Long prime)->System.out.print(prime + ", ") );
		g2.getPrimes().forEach( (Long prime)->System.out.print(prime + ", ") );
		
		long primeNum1 = g1.getPrimes().size();
        System.out.println("g1 generated " + primeNum1 + " prime numbers.");
        long primeNum2 = g2.getPrimes().size();
        System.out.println("g2 generated " + primeNum2 + " prime numbers.");

	}
}
