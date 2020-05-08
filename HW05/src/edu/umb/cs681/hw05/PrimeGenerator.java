package edu.umb.cs681.hw05;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class PrimeGenerator {
	protected long from, to;
	protected List<Long> primes=  new ArrayList<>();;

	public PrimeGenerator(long from, long to) {
		this.from = from;
		this.to = to;
	}

	public void generatePrimes() {
		primes = LongStream.rangeClosed(from, to)
				.filter((long n) -> isPrime(n))
				.boxed()
				.collect(Collectors.toList());
	}

	public List<Long> getPrimes() {
		return primes;
	};
	protected boolean isEven(long n){
		if(n%2 == 0){ return true; }
		else{ return false; }
	}
	
	protected boolean isPrime(long n){

		if(n == 1){ return false; }
		if( n > 2 && isEven(n) ){ return false; }
		long i;
		for (i = (long) Math.sqrt(n); n%i != 0 && i >= 1; i--){}
      	if (i == 1){ return true; }
        else{ return false; }
	}
}
