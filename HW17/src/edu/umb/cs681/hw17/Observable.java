package edu.umb.cs681.hw17;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class Observable {
	Boolean change;
	ConcurrentLinkedQueue<Observer> observer;
	public Observable() {
		this.observer = new ConcurrentLinkedQueue<>();
		this.change = false;
	}
	
	public void addObserver(Observer o) {
		try {
			if(!this.observer.contains(o)) {
				this.observer.add(o);
			}
		}catch(NullPointerException e) {
			System.out.println("Observer object is NULL");
		}
	}
	
	protected void clearChanged() {
		this.change = false;
	}
	
	public int countObservers() {
		return this.observer.size();
	}
	
	public void deleteObserver(Observer o) {
		this.observer.remove(o);
	}
	
	public void deleteObservers() {
		Iterator<Observer> it = this.observer.iterator();
		while(it.hasNext()) {
			it.remove();
		}
	}
	
	public boolean hasChanged() {
		return this.change;
	}
	
	public void notifyObservers() {
		if(this.hasChanged() == true) {
			Iterator<Observer> it = this.observer.iterator();
			while(it.hasNext()) {
				it.next().update(this, null);
			}
			this.clearChanged();
		}
	}
	
	public void notifyObservers(Object arg){
		Object[] arrLocal = null;
			if(this.hasChanged() == false)return;
			if(this.hasChanged() == true) {
				arrLocal = this.observer.toArray();
				this.clearChanged();
			}
		for(int i=arrLocal.length-1; i==0; i++) {
			((Observer)arrLocal[i]).update(this, arg);
		}
	}
	
	protected void setChanged() {
		this.change = true;
	}
}
