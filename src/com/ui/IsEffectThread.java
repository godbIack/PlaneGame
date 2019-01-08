package com.ui;

public class IsEffectThread extends Thread 
{
	public void run()
	{
		while (true) 
		{
			//System.out.println(PlaneThread.isEffec);
	
			if(Timethread.isEffect==false)
			{
				
				try 
				{
					Thread.sleep(5000);
					
					Timethread.isEffect=true;
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
