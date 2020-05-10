package edu.umb.cs681.hw17;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class StockQuoteObservable extends Observable{
	volatile Map<String, Float> stock = new HashMap<>();
	ReentrantLock lock = new ReentrantLock();
	public StockQuoteObservable(HashMap<String, Float>quotes) {
		this.stock = quotes;
	}
	
	public void changequote(String ticker, Float quote) {
		lock.lock();
		try {
			this.stock.replace(ticker, quote);
			this.setChanged();
			this.notifyObservers(new StockEvent(ticker, quote));
			this.clearChanged();
		}
		finally {
			lock.unlock();
		}
	}
	
}
