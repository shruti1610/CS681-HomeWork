package edu.umb.cs681.hw15;

import java.util.HashMap;

public class Client {
	public static void main(String[] args) {
		HashMap<String, Float>quotes = new HashMap<>();
		quotes.put("Microsoft", 184.68f);
		quotes.put("Aplhabet", 1384.34f);
		quotes.put("Facebook", 212.35f);
		StockQuoteObservable stockobservable = new StockQuoteObservable(quotes);
		TeslaQuoteObservable teslaobservable = new TeslaQuoteObservable(819.42f);
		stockobservable.addObserver((Observable o,Object arg)->{
			if(arg instanceof StockEvent) {
				System.out.println("There was a change in stock quote: "+stockobservable.hasChanged());
				System.out.println("Changed stock quote: "+((StockEvent) arg).getTicker()+" "+((StockEvent) arg).getQuote());
			}
			else {
				System.out.println("Current stock quote: "+stockobservable.stock);
			}
		});
		
		teslaobservable.addObserver((Observable o,Object arg)->{
			if(arg instanceof TeslaStockEvent) {
				System.out.println("There was a change in Tesla quote: "+teslaobservable.hasChanged());
				System.out.println("Changed Tesla quote: "+((TeslaStockEvent) arg).getQuote());
			}
			else {
				System.out.println("Current Tesla quote is "+teslaobservable.quote);
			}
		});
		System.out.println(("Initial stock value: "+stockobservable.stock));
		System.out.println("Initial Tesla quote value: "+teslaobservable.quote);
		System.out.println();
		stockobservable.changequote("Microsoft", 250.50f);
		stockobservable.changequote("Aplhabet", 1800.50f);
		teslaobservable.changequote(900f);	
	}
}
