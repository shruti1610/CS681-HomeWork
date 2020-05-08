package edu.umb.cs681.hw01;

import java.util.EventObject;

@SuppressWarnings("serial")
public class StockEvent extends EventObject{

	String ticker;
	float quote;
	
	public StockEvent(Object source, String ticker, float quote) {
		super(source);
		this.ticker = ticker;
		this.quote = quote;
	}

	public StockEvent(Object source) {
		super(source);	
	}
	
	@Override
	public Object getSource() {
		return quote;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public float getQuote() {
		return quote;
	}

	public void setQuote(float quote) {
		this.quote = quote;
	}

}
