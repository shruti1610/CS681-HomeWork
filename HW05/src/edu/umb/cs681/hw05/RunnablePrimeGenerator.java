package edu.umb.cs681.hw05;

import java.time.Duration;
import java.time.Instant;

public class RunnablePrimeGenerator extends PrimeGenerator implements Runnable {

	public RunnablePrimeGenerator(long from, long to) {
		super(from, to);
	}

	public void run() {
		generatePrimes();
	}

	public static void main(String[] args) {
		Instant start = Instant.now();
		countPrimeWith1Thread();
		Instant finish = Instant.now();
		 
	    long timeElapsed1 = Duration.between(start, finish).toMillis();  
	 
	    start = Instant.now();
		countPrimeWith2Threads();
		finish = Instant.now();
		 
	    long timeElapsed2 = Duration.between(start, finish).toMillis(); 
	  
	    start = Instant.now();
		countPrimeWith4Threads();
		finish = Instant.now();
		 
	    long timeElapsed4 = Duration.between(start, finish).toMillis(); 
	 
	    start = Instant.now();
		countPrimeWith8Threads();
		finish = Instant.now();
		 
	    long timeElapsed8 = Duration.between(start, finish).toMillis(); 
	    
	    System.out.println("Time taken with 1 thread in milliseconds "+timeElapsed1);
 		System.out.println("Time taken with 2 thread in milliseconds "+timeElapsed2);
 		System.out.println("Time taken with 4 thread in milliseconds "+timeElapsed4);
 		System.out.println("Time taken with 8 thread in milliseconds "+timeElapsed8);
	}
	
	public static void countPrimeWith1Thread(){
		RunnablePrimeGenerator g1 = new RunnablePrimeGenerator(1L, 2000000L);
		Thread t1 = new Thread(g1);
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {}

		g1.getPrimes().forEach(n->System.out.print(n+", "));
		
		System.out.println("\n" + g1.getPrimes().size() + " prime numbers are found in total.");
	}
	
	public static void countPrimeWith2Threads(){
		RunnablePrimeGenerator g1 = new RunnablePrimeGenerator(1L, 1000000L);
		RunnablePrimeGenerator g2 = new RunnablePrimeGenerator(1000001L, 2000000L);
		Thread t1 = new Thread(g1);
		Thread t2 = new Thread(g2);
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {}

		g1.getPrimes().forEach(n->System.out.print(n+", "));
		System.out.println();
		g2.getPrimes().forEach(n->System.out.print(n+", "));
		System.out.println();
		
		long primeNum = g1.getPrimes().size() + g2.getPrimes().size();
		System.out.println("\n" + primeNum + " prime numbers are found in total.");
	}

	public static void countPrimeWith4Threads(){
		RunnablePrimeGenerator g1 = new RunnablePrimeGenerator(1L, 500000L);
		RunnablePrimeGenerator g2 = new RunnablePrimeGenerator(500001L, 1000000L);
		RunnablePrimeGenerator g3 = new RunnablePrimeGenerator(1000001L, 1500000L);
		RunnablePrimeGenerator g4 = new RunnablePrimeGenerator(1500001L, 2000000L);
		Thread t1 = new Thread(g1);
		Thread t2 = new Thread(g2);
		Thread t3 = new Thread(g3);
		Thread t4 = new Thread(g4);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		} catch (InterruptedException e) {}

		g1.getPrimes().forEach(n->System.out.print(n+", "));
		System.out.println();
		g2.getPrimes().forEach(n->System.out.print(n+", "));
		System.out.println();
		g3.getPrimes().forEach(n->System.out.print(n+", "));
		System.out.println();
		g4.getPrimes().forEach(n->System.out.print(n+", "));
		System.out.println();
		
		long primeNum = g1.getPrimes().size() + g2.getPrimes().size() + g3.getPrimes().size() + g4.getPrimes().size();
		System.out.println("\n" + primeNum + " prime numbers are found in total.");
	}
	public static void countPrimeWith8Threads(){
		RunnablePrimeGenerator g1 = new RunnablePrimeGenerator(1L, 250000L);
		RunnablePrimeGenerator g2 = new RunnablePrimeGenerator(250001L, 500000L);
		RunnablePrimeGenerator g3 = new RunnablePrimeGenerator(500001L, 750000L);
		RunnablePrimeGenerator g4 = new RunnablePrimeGenerator(750001L, 1000000L);
		RunnablePrimeGenerator g5 = new RunnablePrimeGenerator(1000001L, 1250000L);
		RunnablePrimeGenerator g6 = new RunnablePrimeGenerator(1250001L, 1500000L);
		RunnablePrimeGenerator g7 = new RunnablePrimeGenerator(1500001L, 1750000L);
		RunnablePrimeGenerator g8 = new RunnablePrimeGenerator(1750001L, 2000000L);
		Thread t1 = new Thread(g1);
		Thread t2 = new Thread(g2);
		Thread t3 = new Thread(g3);
		Thread t4 = new Thread(g4);
		Thread t5 = new Thread(g5);
		Thread t6 = new Thread(g6);
		Thread t7 = new Thread(g7);
		Thread t8 = new Thread(g8);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
			t5.join();
			t6.join();
			t7.join();
			t8.join();
		} catch (InterruptedException e) {}

		g1.getPrimes().forEach(n->System.out.print(n+", "));
		System.out.println();
		
		g2.getPrimes().forEach(n->System.out.print(n+", "));
		System.out.println();
		
		g3.getPrimes().forEach(n->System.out.print(n+", "));
		System.out.println();
		
		g4.getPrimes().forEach(n->System.out.print(n+", "));
		System.out.println();
		
		g5.getPrimes().forEach(n->System.out.print(n+", "));
		System.out.println();
		
		g6.getPrimes().forEach(n->System.out.print(n+", "));
		System.out.println();
		
		g7.getPrimes().forEach(n->System.out.print(n+", "));
		System.out.println();
		
		g8.getPrimes().forEach(n->System.out.print(n+", "));
		System.out.println();
		
		
		long primeNum = g1.getPrimes().size() + g2.getPrimes().size() + g3.getPrimes().size() + g4.getPrimes().size() + g5.getPrimes().size() + g6.getPrimes().size() + g7.getPrimes().size() + g8.getPrimes().size();
		System.out.println("\n" + primeNum + " prime numbers are found in total for each thread.");
	}
	

	
}