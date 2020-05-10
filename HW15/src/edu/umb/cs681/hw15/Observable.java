package edu.umb.cs681.hw15;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Observable {
	ReentrantLock lockObs = new ReentrantLock();
	Boolean change;
	LinkedList<Observer> observer;
	public Observable() {
		this.observer = new LinkedList<Observer>();
		this.change = false;
	}
	
	public void addObserver(Observer o) {
		try {
			lockObs.lock();
			if(!this.observer.contains(o)) {
				this.observer.add(o);
			}
			lockObs.unlock();
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
		lockObs.lock();
		this.observer.remove(o);
		lockObs.unlock();
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
//				Iterator<Observer> it = this.observer.iterator();
//				while(it.hasNext()) {
//					it.next().update(this, arg);
//				}
				this.clearChanged();
			}
//		for(int i=arrLocal.length-1; i<=0; i--) {
		for(int i=arrLocal.length-1; i==0; i++) {
			((Observer)arrLocal[i]).update(this, arg);
		}
	}
	
	protected void setChanged() {
		this.change = true;
	}
}
