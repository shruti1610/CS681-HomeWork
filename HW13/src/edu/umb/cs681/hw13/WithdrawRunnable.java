package edu.umb.cs681.hw13;

public class WithdrawRunnable implements Runnable {
	public volatile boolean done = false;
	ThreadSafeBankAccount acc = ThreadSafeBankAccount.acc;
	
	public void setDone(){
		done = true;
	}
	
	@Override
	public void run() {
			try{
				for(int i = 0; i < 5; i++){
					if(!done) {
						acc.withdraw(4000);
						Thread.sleep(300);
					}
					else {
						return;
					}
				}
			}catch(InterruptedException exception){}
	}

}
