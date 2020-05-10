package edu.umb.cs681.hw15;

public class StockEvent {
	String ticker;
	Float quote;
	public StockEvent(String ticker, float quote) {
		this.ticker = ticker;
		this.quote = quote;
	}
	public String getTicker() {
		return this.ticker;
	}
	public Float getQuote() {
		return this.quote;
	}
}
