package com.ui;

import com.entity.EnemyPlane;


public class PassFrameThread extends Thread {

    public static boolean isOne = true;//用于限制程序只执行一次
    PlanePanel gamePanel;

    public PassFrameThread(PlanePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void run() {
        while (isOne) {
            //Boos死了且为第2关
           System.out.println("");
            if (gamePanel.boss1isdie == true && PlaneThread.passCount == 2) {
                /*
                 *将敌机移开
                 */
                //取出敌人飞机集合
                EnemyPlane[] enemyPlanes = gamePanel.Enemyplanes;
                for (int i = 0; i < enemyPlanes.length; i++) {
                    EnemyPlane enemyPlane = enemyPlanes[i];
                    enemyPlane.setY(enemyPlane.getY() - 2000);
                }

                if (isOne == true) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e1) {
                    }
                }
                if (isOne == true) {
                    //gamePanel.setisDraw(false);
                    gamePanel.background = "StartFrame.jpg";
                    //停留5秒
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                    }
                    isOne = false;
                    //gamePanel.setisDraw(true);
                    gamePanel.background = "background.png";
                    //将时间设置为30
                    Timethread.time = 27;
                    //将我的灰机血加满----------------------------------
                    //gamePanel.setMyPlaneLefe(200);
                    //通知雪花到第2关了
                    //SnowsData.passCount=2;
                }
            }
        }
    }

}
