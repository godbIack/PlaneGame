package com.entity;

import java.util.Random;

import com.ui.PlaneFrame;

/*
 * ��2��BOOS
 *���ߣ�̷�ջ�
 * bug:��ʱû����
 */
public class Boss_2 
{
    int x;//�ɻ�������ϵ�x����
    int y;//�ɻ�������ϵ�y����
    int width;//�ɻ�������ϵĿ��
    int height;//�ɻ�������ϵĸ߶�
    String path;//�ɻ���ͼƬ·��
    int life=1200;//�л�����Ĭ��300����
    /*
     * ���캯��
     */
    public Boss_2() 
    {
        setPath("enemy3_n2.png");
        setWidth(150);
        setHeight(200);
        setX(-150);
        setY(-200);
    }
    /*
     * ���˷ɻ��ƶ�����
     */
    boolean isLeft=true;//�ж�BOOS������
    boolean istop=true;//�ж�BOOS������
    public void move()
    {
        if(getY()>PlaneFrame.height-65)
            istop=true;
        else if(getY()<0)
            istop=false;
        if(istop==true)
            setY(getY()-2);
        if(istop==false)
            setY(getY()+5);
        
        if(getX()<20)
            isLeft=false;
        else if(getX()>PlaneFrame.width-55)
            isLeft=true;
        if(isLeft==true)
            setX(getX()-2);
        if(isLeft==false)
        setX(getX()+2);
        //System.out.println(getX());
    }
    /*
     * BOOS����
     */
    public void toFire()
    {
        
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
            y=-y/2;
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
    public int getLeft() {
        return life;
    }
    public void setLeft(int left) {
        this.life = left;
    }
    
}


