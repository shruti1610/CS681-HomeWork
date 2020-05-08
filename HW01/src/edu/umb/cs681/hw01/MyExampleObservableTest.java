package edu.umb.cs681.hw01;

public class MyExampleObservableTest {
	public static void main(String args[]) {
		MyExampleObservable myExampleObservable = new MyExampleObservable();
		
		Observer obs = (Observable o,Object obj) -> {System.out.println("Lambda expression");};
		
		// not needed as such
		obs.update(myExampleObservable, new String("s"));
		
		myExampleObservable.addObserver(obs);
		System.out.print("Observer count after adding an observer ");
		myExampleObservable.countObservers();
		
		myExampleObservable.deleteObserver(obs);
		System.out.print("Observer count after deleting an observer ");
		myExampleObservable.countObservers();
		
		StockQuoteObservable observable = new StockQuoteObservable();
		observable.addObserver((Observable o,Object obj) -> {System.out.println("do nothing in Lambda expression");});
		observable.notifyObservers();
	}
}
 