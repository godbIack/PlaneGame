package com.ui;

import javax.swing.JFrame;

import com.ui.PlaySound;


//��ս��Ϸ���
public class PlaneFrame extends JFrame {

    public static int width = 500;//���Ϳ�
    public static int height = 600;
    static PlaySound p;//��������

    public PlaneFrame() {
        PlanePanel planePanel = new PlanePanel();
        this.add(planePanel);
        this.addKeyListener(planePanel);
        this.setSize(width, height);
        this.setTitle("��ս");
        this.setVisible(true);
        this.setResizable(false);//���ô����С�̶�
        this.setLocationRelativeTo(null);//����
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PlaneThread planeThread = new PlaneThread(planePanel);
        planeThread.start();

        Timethread timethread = new Timethread();
        timethread.start();

        PassFrameThread passFrameThread = new PassFrameThread(planePanel);
        passFrameThread.start();
        planePanel.requestFocus();
        
//        if(p == null) {
//            //��������  
//
//                    p = new PlaySound();
//                    p.open("sounds/������ - ɭ�ֿ�����.mid");
//                    p.play();
//                    p.loop();
//                    p.start();
            
//        }
    }
}












































