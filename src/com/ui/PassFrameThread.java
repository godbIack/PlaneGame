package com.ui;

import com.entity.EnemyPlane;


public class PassFrameThread extends Thread {

    public static boolean isOne = true;//�������Ƴ���ִֻ��һ��
    PlanePanel gamePanel;

    public PassFrameThread(PlanePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void run() {
        while (isOne) {
            //Boos������Ϊ��2��
           System.out.println("");
            if (gamePanel.boss1isdie == true && PlaneThread.passCount == 2) {
                /*
                 *���л��ƿ�
                 */
                //ȡ�����˷ɻ�����
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
                    //ͣ��5��
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                    }
                    isOne = false;
                    //gamePanel.setisDraw(true);
                    gamePanel.background = "background.png";
                    //��ʱ������Ϊ30
                    Timethread.time = 27;
                    //���ҵĻһ�Ѫ����----------------------------------
                    //gamePanel.setMyPlaneLefe(200);
                    //֪ͨѩ������2����
                    //SnowsData.passCount=2;
                }
            }
        }
    }

}
