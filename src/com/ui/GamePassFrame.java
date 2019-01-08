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
	Label label;//������ʾͨ�ػ���ʧ��
	Label scoreLb;//������ʾ�÷����
	public static String labelName;//������ʾͨ�ػ���ʧ������
	public static int labelScore;//���ڼ�¼�÷�
	PlanePanel gamePanel;
	JButton jButton_reStart;
	JButton jButton_close;
	public GamePassFrame(PlanePanel gamePanel) 
	{
		label=new Label(labelName);
		scoreLb=new Label("����"+labelScore);
		this.gamePanel=gamePanel;
		//���ô�������
		setSize(100,180);
		setLayout(null);
		setTitle("ͨ�ؽ���");
		setResizable(false);
		setLocationRelativeTo(null);
		//��ť
		jButton_reStart=new JButton("���¿�ʼ");//���¿�ʼ��ť
		jButton_close=new JButton("�ر���Ϸ");//�رհ�ť
		//���ùرհ�ťλ��
		jButton_close.setBounds(20, 105, 88, 30);
		//���ð�ťλ��
		jButton_reStart.setBounds(20, 65, 88, 30);
		//labelλ������
		label.setBounds(45, 0, 100, 30);
		//����labelλ��
		scoreLb.setBounds(45, 30, 100, 30);
		/*
		 * ���¿�ʼ��ť�����¼�
		 */
		jButton_reStart.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e)
			{
				entryGame();//���ÿ�ʼ��Ϸ����
				setVisible(false);//����Ϊ����
			}
		});
		/*
		 * �رհ�ť�����¼�
		 */
		jButton_close.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e)
			{
				System.exit(0);//�رճ���
			}
		});
		
		//��Ӱ�ť
		add(jButton_reStart);
		add(jButton_close);
		add(label);
		add(scoreLb);
		jButton_close=new JButton();//�رհ�ť	
		setVisible(true);
	}
	/*
	 * ������Ϸ����
	 */
	public void entryGame()
	{
	    
	    gamePanel.setBackPath("background.png");
	    gamePanel.setMyPlaneLefe(200);
	    
	    Timethread.time=30;
	    gamePanel.setMyPlaneDie(false);
	    PassFrameThread.isOne=true;
	    
		new PlaneFrame();
		
//		//֪ͨѩ������1����
//		//SnowsData.passCount=1;
//		
//		//������ͼƬ��ԭ-----------------------------------
//		gamePanel.setBackPath("background.png");
//		//���ҵĻһ�Ѫ����----------------------------------
//		gamePanel.setMyPlaneLefe(200);
//		//ʱ���ʼ��------------------------------------
//		Timethread.time=30;
//		//���һ�����
//		gamePanel.setMyPlaneDie(false);
//		//
//		PassFrameThread.isOne=true;
//		
//		//���������߳�
//		new PlaneThread(gamePanel).start();
//		

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PlanePanel gamePanel=new PlanePanel();
		GamePassFrame welcomeFrame=new GamePassFrame(gamePanel);
	}
}
