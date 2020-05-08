package edu.umb.cs681.hw01;

@FunctionalInterface
interface Observer{
	public abstract void update(Observable obs, Object obj);
}
