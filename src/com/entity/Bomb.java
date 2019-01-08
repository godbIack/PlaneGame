package com.entity;


public class Bomb {//±¬Õ¨Ð§¹û

	int x;
	int y;
	int width;
	int height;
	String path;
	boolean isEffect;
	
	public Bomb(int x,int y,int width,int height){
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		setPath("enemy1_down3.png");
		setEffect(true);
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
	public boolean isEffect() {
		return isEffect;
	}
	public void setEffect(boolean isEffect) {
		this.isEffect = isEffect;
	}
	
}
