package edu.umb.cs681.hw13;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeBankAccount{ 
	private double balance = 0;
	private ReentrantLock lock;
	public static ThreadSafeBankAccount acc; 
	Condition sufficientFundsCondition;
	Condition belowUpperLimitFundsCondition;
	

	
	public ThreadSafeBankAccount(){
		lock = new ReentrantLock();
		acc = this;
		sufficientFundsCondition = lock.newCondition();
		belowUpperLimitFundsCondition = lock.newCondition();
	}
	
	public void deposit(double amount){
		lock.lock();
		System.out.println("Deposit Lock obtained");
		try {
			while(balance >= 300) {
				try {
					System.out.println("Balance over 300. Thread waiting");
					belowUpperLimitFundsCondition.await();
					
				} catch (InterruptedException e) {
					continue;
				}
			}
			System.out.print("Balance before deposit: " + balance);
			double newBalance = balance + amount;
			System.out.println(", Balance after deposit: " + newBalance);
			balance = newBalance;
			sufficientFundsCondition.signalAll();
		}
		finally{
			lock.unlock();
			System.out.println("Released lock for Deposit");
		}
	}
	
	public void withdraw(double amount){
		lock.lock();
		System.out.println("Withdraw Lock obtained");
		try {
			while(balance <=0) {
				try {
					System.out.println("Insufficient balance. Thread waiting");
					sufficientFundsCondition.await();
				}catch(InterruptedException e) {
					continue;
				}
			}
			System.out.print("Balance before withdrawal : " + balance);
			double newBalance = balance - amount;
			System.out.println(", Balance after withdrwawl : " + newBalance);
			balance = newBalance;
			belowUpperLimitFundsCondition.signalAll();
		}
		finally{
			lock.unlock();
			System.out.println("Released lock for Withdraw");
		}
	}
	
	public static void main(String[] args){
		acc = new ThreadSafeBankAccount();
		DepositRunnable deposit = new DepositRunnable();
		WithdrawRunnable withdraw = new WithdrawRunnable();
		for(int i = 0; i < 1; i++){
			Thread t1 = new Thread(deposit);
			Thread t2 = new Thread(withdraw);
			t1.start();
			t2.start();
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
			t1.interrupt();
			t2.interrupt();
			deposit.setDone();
			withdraw.setDone();
		}
	}
	
}
