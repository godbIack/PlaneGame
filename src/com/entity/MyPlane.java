package com.entity;

import com.ui.PlaneFrame;

//我机类
public class MyPlane {
	private int x;
	private int y;
	private int width;
	private int height;
	
	private String path;//图片路径
	private boolean isFire;//是否开火
	
	public MyPlane() {
		// TODO 自动生成的构造函数存根
		x = PlaneFrame.width/2-40;
		y = PlaneFrame.height-120; 
		width = height = 45;
		path = "hero1.png";
		isFire = true;//默认没开火
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

	public boolean isFire() {
		return isFire;
	}

	public void setFire(boolean isFire) {
		this.isFire = isFire;
	}
	
}


























































