package edu.umb.cs681.hw01;

import java.util.ArrayList;
import java.util.EventListener;

public class StockQuoteObservable extends Observable {
	
ArrayList<StockQuoteObserver> Stock_Q_Obj = new ArrayList<>();
	
	public void addEventListner(EventListener el){
		Stock_Q_Obj.add((StockQuoteObserver) el);
	}
	public void notifyObservers(StockEvent sv){
		for(int i=0;i<Stock_Q_Obj.size();i++){
			Stock_Q_Obj.get(i).updateStock(sv);	
		}
	}
	@Override
	void setChanged() {
		// TODO Auto-generated method stub
		
	}
	@Override
	void clearChanged() {
		// TODO Auto-generated method stub
		
	}
	@Override
	void notifyObservers() {
		notifyObservers(new StockEvent(this,"Ticker 1",10));
		System.out.println("Notified Observers");
	}
	
	@Override
	void setQuote() {
		// TODO Auto-generated method stub
		
	}


}
