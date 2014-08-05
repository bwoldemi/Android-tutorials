package com.example.tutservice;

public class TestPublicClass extends Thread{

	@Override
	public void run() {
		super.run();
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(1000);
				System.out.println("The "+i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
}