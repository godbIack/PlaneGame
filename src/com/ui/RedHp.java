package com.ui;

import java.util.Random;
public class RedHp 
{
	int x;//血位置
	int y;//飞机在面板上的y坐标
	int width;//飞机在面板上的宽度
	int height;//飞机在面板上的高度
	String path;//飞机的图片路径
	/*
	 * 构造函数
	 */
	public RedHp() 
	{
		Random random=new Random();//取随机数
		setPath("ufo1.png");;//调用选择战机的方法
		setWidth(50);
		setHeight(50);
		setX(random.nextInt(PlaneFrame.width-20));
		setY(random.nextInt(300)-1000);
	}
	/*
	 * 血移动方法
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


