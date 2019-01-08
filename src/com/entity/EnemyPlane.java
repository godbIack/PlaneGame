package com.entity;

import java.util.Random;

import com.ui.PlaneFrame;

//�л���
public class EnemyPlane {
	
	private int x;
	private int y;
	private int width;
	private int height;
	private String path;//ͼƬ·��
	//�л������Ǹ�������¼�	
	public boolean toFire() {
		// TODO Auto-generated method stub
		Random random=new Random();
		return random.nextInt(100)==1?true:false;//���֮һ�Ŀ������
	}
	public EnemyPlane(){//�л��ķ���
		Random random = new Random();
		setX(random.nextInt(PlaneFrame.width));
		setY(random.nextInt(PlaneFrame.height)-600);
		width = height = 45;
		path = "enemy1.png";
	}
	
//	private void colsePlane(){//ѡ��صл��ķ���
//		Random random = new Random();
//		int closeplanenum = random.nextInt(9);
//		switch (closeplanenum) {
//			case 0:setPath("enemy_1.gif");	break;
//			case 1:setPath("enemy_2.png");	break;
//			case 2:setPath("enemy_3.png");	break;
//			case 3:setPath("enemy_4.png");	break;
//			case 4:setPath("enemy_5.png");	break;
//			case 5:setPath("enemy_6.png");	break;
//			case 6:setPath("enemy_7.png");	break;
//			case 7:setPath("enemy_8.png");	break;
//			case 8:setPath("enemy_9.png");	break;
//
//		}
//	}
	
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
			y=-20;
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
	
	
	public void move(){//�л��ƶ�
		Random random=new Random();
		setY(getY()+5);
		setX(random.nextInt(2)==1?getX()+5:getX()-5);
	}

}
