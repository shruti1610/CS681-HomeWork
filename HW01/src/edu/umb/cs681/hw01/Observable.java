package edu.umb.cs681.hw01;

import java.util.*;

public abstract class Observable {
	
	public Observable() {
	}

	private LinkedList<Observer> observers = new LinkedList<Observer>();

	public void addObserver(Observer o) {
		observers.add(o);
	}

	abstract void setChanged();

	abstract void setQuote();
	
	abstract void clearChanged();

	abstract void notifyObservers();

	public void countObservers() {
		System.out.println(observers.size());
	}

	public void deleteObserver(Observer o) {
		observers.remove(o);
	}

}
