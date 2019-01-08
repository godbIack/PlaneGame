package com.ui;

import javax.swing.JFrame;

import com.ui.PlaySound;


//空战游戏框架
public class PlaneFrame extends JFrame {

    public static int width = 500;//长和宽
    public static int height = 600;
    static PlaySound p;//声音对象

    public PlaneFrame() {
        PlanePanel planePanel = new PlanePanel();
        this.add(planePanel);
        this.addKeyListener(planePanel);
        this.setSize(width, height);
        this.setTitle("空战");
        this.setVisible(true);
        this.setResizable(false);//设置窗体大小固定
        this.setLocationRelativeTo(null);//居中
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PlaneThread planeThread = new PlaneThread(planePanel);
        planeThread.start();

        Timethread timethread = new Timethread();
        timethread.start();

        PassFrameThread passFrameThread = new PassFrameThread(planePanel);
        passFrameThread.start();
        planePanel.requestFocus();
        
//        if(p == null) {
//            //声音设置  
//
//                    p = new PlaySound();
//                    p.open("sounds/纯音乐 - 森林狂想曲.mid");
//                    p.play();
//                    p.loop();
//                    p.start();
            
//        }
    }
}












































