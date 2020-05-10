package edu.umb.cs681.hw17;

import java.util.concurrent.locks.ReentrantLock;

public class TeslaQuoteObservable extends Observable {
	volatile Float quote;
	ReentrantLock lock = new ReentrantLock();
	public TeslaQuoteObservable(Float quote) {
		this.quote = quote;
	}
	
	public void changequote(Float quote) {
		lock.lock();
		try {
			this.quote = quote;
			this.setChanged();
			this.notifyObservers(new TeslaStockEvent(quote));
			this.clearChanged();
		}finally {
			lock.unlock();
		}
	}
}
