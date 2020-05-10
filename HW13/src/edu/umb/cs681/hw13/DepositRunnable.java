package edu.umb.cs681.hw13;

public class DepositRunnable implements Runnable {
	
	public volatile boolean done = false;
	ThreadSafeBankAccount acc = ThreadSafeBankAccount.acc;
	
	public void setDone(){
			done = true;
	}
	
	@Override
	public void run() {
			try{
				for(int i = 0; i < 10; i++){
					if(!done) {
						acc.deposit(5790);
						Thread.sleep(300);
					}
					else {
						return;
					}
				}
			}catch(InterruptedException exception){
			}
	}

}
