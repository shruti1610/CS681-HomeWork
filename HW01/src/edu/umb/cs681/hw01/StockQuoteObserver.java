package edu.umb.cs681.hw01;


public interface StockQuoteObserver extends Observer{
	public void updateStock(StockEvent stockEvent);

}
