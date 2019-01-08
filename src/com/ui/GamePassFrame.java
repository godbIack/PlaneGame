package com.ui;

import java.awt.Color;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextMeasurer;

import javax.swing.JButton;
import javax.swing.JFrame;


public class GamePassFrame extends JFrame 
{
	Label label;//用于显示通关还是失败
	Label scoreLb;//用于显示得分情况
	public static String labelName;//用于显示通关还是失败文字
	public static int labelScore;//用于记录得分
	PlanePanel gamePanel;
	JButton jButton_reStart;
	JButton jButton_close;
	public GamePassFrame(PlanePanel gamePanel) 
	{
		label=new Label(labelName);
		scoreLb=new Label("分数"+labelScore);
		this.gamePanel=gamePanel;
		//设置窗体样子
		setSize(100,180);
		setLayout(null);
		setTitle("通关界面");
		setResizable(false);
		setLocationRelativeTo(null);
		//按钮
		jButton_reStart=new JButton("重新开始");//重新开始按钮
		jButton_close=new JButton("关闭游戏");//关闭按钮
		//设置关闭按钮位置
		jButton_close.setBounds(20, 105, 88, 30);
		//设置按钮位置
		jButton_reStart.setBounds(20, 65, 88, 30);
		//label位置设置
		label.setBounds(45, 0, 100, 30);
		//分数label位置
		scoreLb.setBounds(45, 30, 100, 30);
		/*
		 * 重新开始按钮监听事件
		 */
		jButton_reStart.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e)
			{
				entryGame();//调用开始游戏方法
				setVisible(false);//设置为隐藏
			}
		});
		/*
		 * 关闭按钮监听事件
		 */
		jButton_close.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e)
			{
				System.exit(0);//关闭程序
			}
		});
		
		//添加按钮
		add(jButton_reStart);
		add(jButton_close);
		add(label);
		add(scoreLb);
		jButton_close=new JButton();//关闭按钮	
		setVisible(true);
	}
	/*
	 * 进入游戏方法
	 */
	public void entryGame()
	{
	    
	    gamePanel.setBackPath("background.png");
	    gamePanel.setMyPlaneLefe(200);
	    
	    Timethread.time=30;
	    gamePanel.setMyPlaneDie(false);
	    PassFrameThread.isOne=true;
	    
		new PlaneFrame();
		
//		//通知雪花到第1关了
//		//SnowsData.passCount=1;
//		
//		//将背景图片还原-----------------------------------
//		gamePanel.setBackPath("background.png");
//		//将我的灰机血加满----------------------------------
//		gamePanel.setMyPlaneLefe(200);
//		//时间初始化------------------------------------
//		Timethread.time=30;
//		//将我机复活
//		gamePanel.setMyPlaneDie(false);
//		//
//		PassFrameThread.isOne=true;
//		
//		//从新启动线程
//		new PlaneThread(gamePanel).start();
//		

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PlanePanel gamePanel=new PlanePanel();
		GamePassFrame welcomeFrame=new GamePassFrame(gamePanel);
	}
}
