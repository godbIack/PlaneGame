package com.ui;

public class Timethread extends Thread{
	public static int time = 10;//10秒钟出现
	public static boolean isEffect=true;//设置碰撞的有效性
	public void run(){
		while (true) {
			try {
				
				sleep(1000);
				time--;
				if(isEffect == false)
					isEffect = true;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
