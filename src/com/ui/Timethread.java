package com.ui;

public class Timethread extends Thread{
	public static int time = 10;//10���ӳ���
	public static boolean isEffect=true;//������ײ����Ч��
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
