package com.entity;

import com.ui.PlaneFrame;
//我机子弹
public class MyPlaneBullet {
	private int x;
	private int y;
	private int width;
	private int height;
	private String path;//图片路径
	private boolean iseFFect;//子弹是否有效
	public MyPlaneBullet(int x,int y){//构造函数
		setX(x);
		setY(y);
		setWidth(10);
		setHeight(18);
		setPath("missile.png");
		setIseFFect(true);
	}
	public void move(){//我机子弹移动
		setY(getY()-15);
		if(getY()<0)
			setIseFFect(false);
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
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
	public boolean isIseFFect() {
		return iseFFect;
	}
	public void setIseFFect(boolean iseFFect) {
		this.iseFFect = iseFFect;
	}
	
}

