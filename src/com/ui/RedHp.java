package com.ui;

import java.util.Random;
public class RedHp 
{
	int x;//Ѫλ��
	int y;//�ɻ�������ϵ�y����
	int width;//�ɻ�������ϵĿ��
	int height;//�ɻ�������ϵĸ߶�
	String path;//�ɻ���ͼƬ·��
	/*
	 * ���캯��
	 */
	public RedHp() 
	{
		Random random=new Random();//ȡ�����
		setPath("ufo1.png");;//����ѡ��ս���ķ���
		setWidth(50);
		setHeight(50);
		setX(random.nextInt(PlaneFrame.width-20));
		setY(random.nextInt(300)-1000);
	}
	/*
	 * Ѫ�ƶ�����
	 */
	public void move()
	{
		setY(getY()+1);
	}

	//******************************************************
	public int getX() {
		return x;
	}
	public void setX(int x) {
		if(x<0)
			x=0;
		if(x>PlaneFrame.width-50)
			x=PlaneFrame.width-50;
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		if(y>PlaneFrame.height)
			y=-2000;
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

}


