package com.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.ui.Timethread;


import com.entity.Bomb;
import com.entity.BossBullet;
import com.ui.PlanePanel;
import com.entity.Boss_1;
import com.entity.Boss_2;
import com.entity.EnemyPlane;
import com.entity.MyPlane;
import com.entity.EnemyBullet;
import com.entity.MyPlaneBullet;

//��Ϸ�����
public class PlanePanel extends JPanel implements KeyListener {

    private boolean isPress01, isPress02, isPress03, isPress04;//�����ĸ��������͵�ֵ����¼����
    MyPlane myplane;//�һ�
    EnemyPlane[] Enemyplanes;//�л�����
    int life;//�һ�����
    ArrayList<EnemyBullet> enemyBullets;//�л��ӵ�����
    ArrayList<MyPlaneBullet> myplanebullets;//�һ��ӵ�����
    ArrayList<BossBullet> bossBullets;//boss�ӵ�����
    Boss_1 boss_1;
    Boss_2 boss_2;//��2��Boos
    boolean boss1isdie=false;//�ж�boss1
    boolean isBoss2Die=false;//�ж�Boss2�Ƿ�����
    String background;
    int score=0;//�÷�
    ArrayList<Bomb> bombs;//����ըͼƬ����
    ArrayList<RedHp> redHps;//Ѫ������
    
    boolean isMyPlaneDie;//�ж��һ��Ƿ�����

    @Override
    public void paint(Graphics g) {
        // TODO �Զ����ɵķ������
        super.paint(g);
        drawBack(g);
        drawmyplane(g);
        drawenemyplane(g);
        drawLifeAndscore(g);
        drawEnemyBullets(g);
        drawBossBullets(g);
        drawMyPlaneBullets(g);
        drawboss1(g);
        drawboss2(g);
        drawboss1hit(g);
        drawBossLife(g);
        drawBoosLife02(g);
        drawBombs(g);
        drawRedHp(g);
    }

    public PlanePanel() {
        // TODO �Զ����ɵĹ��캯�����
        myplane = new MyPlane();
        life = 200;//��ʼ����
        background = "background.png";
        Enemyplanes = new EnemyPlane[10]; //��ʼ��10���л�
        for (int i = 0; i < Enemyplanes.length; i++) {
            Enemyplanes[i] = new EnemyPlane();
        }
//        addMouseMotionListener(this);//���������
//        addMouseListener(this);
        addKeyListener(this);
        enemyBullets = new ArrayList<EnemyBullet>();
        myplanebullets = new ArrayList<MyPlaneBullet>();
        bossBullets = new ArrayList<BossBullet>();
        boss_1 = new Boss_1();
        boss_2 = new Boss_2();//��2��Boos
        redHps=new ArrayList<RedHp>();
        for(int i=0;i<3;i++)
        {
            redHps.add(new RedHp());
        }
        bombs = new ArrayList<Bomb>();
    }

    public void drawBossLife(Graphics g) {//��bossѪ��

        //ʱ��Ϊ0ʱ BOOS����
        if (Timethread.time < 0 && boss1isdie == false) {
            g.setColor(Color.RED);
            g.setFont(new Font("����", Font.PLAIN, 18));
            g.drawString("B", 22, 50);
            g.drawString("O", 22, 70);
            g.drawString("S", 22, 95);
            g.drawString("S", 22, 115);
            g.drawRect(23, 120, 6, boss_1.getLeft());
            g.fillRect(23, 120, 6, boss_1.getLeft());
        }
    }
    
    public void drawBoosLife02(Graphics g)//��boss2Ѫ��
    {
        //ʱ��Ϊ0ʱ BOOS����
        if(Timethread.time<5&&Timethread.time>0&&isBoss2Die==false&&PlaneThread.passCount==2)
        {
            g.setColor(Color.BLUE);
            g.drawString("B", 17, 45);
            g.drawString("O",17, 70);
            g.drawString("S",17, 100);
            g.drawString("S",17, 115);
            g.drawRect(23, 120, 6,boss_2.getLeft());
            g.fillRect(23, 120, 6,boss_2.getLeft());
        }
    }

    public void drawboss1hit(Graphics g) {//��boss1��Ϯ
        if (Timethread.time > 0 && Timethread.time < 5) {
            g.setFont(new Font("����", Font.PLAIN, 32));
            g.setColor(Color.WHITE);
            g.drawString("BOSS������Ϯ!!!", 150, 200);
        }
    }

    public void drawboss1(Graphics g) {//��boss1
        g.drawImage(new ImageIcon(PlanePanel.class.getResource(boss_1.getPath())).getImage(),
                boss_1.getX(), boss_1.getY(), boss_1.getWidth(), boss_1.getHeight(), this);
    }
    
    public void drawboss2(Graphics g){//��boss2
        if(Timethread.time<5&&Timethread.time>0&&isBoss2Die==false)
        g.drawImage(new ImageIcon(PlanePanel.class.getResource(boss_2.getPath())).getImage(), 
                boss_2.getX(), boss_2.getY(), boss_2.getWidth(), boss_2.getHeight(), this);
    }


    public void drawMyPlaneBullets(Graphics g) {//���һ��ӵ�
        for (int i = 0; i < myplanebullets.size(); i++)
            g.drawImage(new ImageIcon(PlanePanel.class.getResource(myplanebullets.get(i).getPath())).getImage(),
                    myplanebullets.get(i).getX()+18, myplanebullets.get(i).getY(), myplanebullets.get(i).getWidth(), myplanebullets.get(i).getHeight(), this);

    }

    public void drawEnemyBullets(Graphics g) {//�����˷ɻ��ӵ�
        for (int i = 0; i < enemyBullets.size(); i++)
            g.drawImage(new ImageIcon(PlanePanel.class.getResource(enemyBullets.get(i).getPath())).getImage(),
                    enemyBullets.get(i).getX(), enemyBullets.get(i).getY(), enemyBullets.get(i).getWidth(), enemyBullets.get(i).getHeight(), this);

    }
    public void drawBossBullets(Graphics g) {//��boss�ӵ�
        for (int i = 0; i < bossBullets.size(); i++)
            g.drawImage(new ImageIcon(PlanePanel.class.getResource(bossBullets.get(i).getPath())).getImage(),
                    bossBullets.get(i).getX()+60, bossBullets.get(i).getY()+50, bossBullets.get(i).getWidth(), bossBullets.get(i).getHeight(), this);

    }
    
    public void drawRedHp(Graphics g)
    {
            ArrayList<RedHp> en=redHps;
            for(int i=0;i<en.size();i++)
            {
                RedHp enemyPlane=en.get(i);
                g.drawImage(new ImageIcon(RedHp.class.getResource(enemyPlane.getPath())).getImage(),enemyPlane.getX(),enemyPlane.getY(),enemyPlane.getWidth(),enemyPlane.getHeight(),this);
            }
    }

    /*
     * ����ըЧ��
     */
    int bombsSize = 0;//��ը����ʱ��

    public void drawBombs(Graphics g) {//����ըЧ��
        for (int i = 0; i < bombs.size(); i++) {
            //�ڹ����У�ȡ��ÿһ����ըͼƬ
            Bomb bomb = bombs.get(i);
            Image img = new ImageIcon(
                    PlanePanel.class.getResource(bomb.getPath())
            ).getImage();
            bombsSize += 1;
            g.drawImage(img, bomb.getX(), bomb.getY(),
                    bomb.getWidth() - bombsSize, bomb.getHeight() - bombsSize, this);
            new PlanePanel().repaint();
            //ֻҪ����󣬾Ͱѱ�ըͼƬ��Ϊ��Ч
            if (bombsSize == 15) {
                bombsSize = 0;
                bomb.setEffect(false);
            }
        }
    }

    public ArrayList<Bomb> getBombs() {
        return bombs;
    }

    public void setBombs(ArrayList<Bomb> bombs) {
        this.bombs = bombs;
    }


    public void drawBack(Graphics g) {//�������ķ���
        g.drawImage(
                new ImageIcon(PlanePanel.class.getResource(background)).getImage(),
                0, 0, PlaneFrame.width, PlaneFrame.height, this);
    }

    public void drawmyplane(Graphics g) {//���һ��ķ���

        if (isPress03) {
            int x = myplane.getX();
            if (x >= 0) {
                x = Math.max(x - 10, 0);
            }
            myplane.setX(x);
        }

        if (isPress01) {
            int y = myplane.getY();
            if (y >= 0) {
                y = Math.max(y - 10, 0);
            }
            myplane.setY(y);
        }

        if (isPress04) {
            int x = myplane.getX();
            if (x >= 0) {
                x = Math.min(x + 10, 450);
            }
            myplane.setX(x);
        }

        if (isPress02) {
            int y = myplane.getY();
            if (y >= 0) {
                y = Math.min(y + 10, 530);
            }
            myplane.setY(y);
        }
        g.drawImage(
                new ImageIcon(PlanePanel.class.getResource(myplane.getPath())).getImage(),
                myplane.getX(), myplane.getY(), myplane.getWidth(), myplane.getHeight(), this);
    }

    public void drawenemyplane(Graphics g) {//���л��ķ���
        for (int i = 0; i < Enemyplanes.length; i++) {
            g.drawImage(
                    new ImageIcon(PlanePanel.class.getResource(Enemyplanes[i].getPath())).getImage(),
                    Enemyplanes[i].getX(), Enemyplanes[i].getY(), Enemyplanes[i].getWidth(), Enemyplanes[i].getHeight(), this);
        }
    }

    public void drawLifeAndscore(Graphics g) {//���һ������ͻ���
        if (life > 100)
            g.setColor(Color.green);
        else
            g.setColor(Color.red);
        g.setFont(new Font("����",Font.PLAIN,18));
        g.drawString("����:"+score, PlaneFrame.width-100, 30);
        g.drawString("��", PlaneFrame.width - 34, 60);
        g.drawString("��", PlaneFrame.width - 34, 75);
        g.fillRect(PlaneFrame.width - 30, 80, 10, life);//ʵ��������
        g.drawRect(PlaneFrame.width - 30, 80, 10, 200);//����������
    }
    
    public ArrayList<RedHp> getRedHps() {
        return redHps;
    }
    public void setRedHps(ArrayList<RedHp> redHps) {
        this.redHps = redHps;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public boolean getisMyPlaneDie() {
        return isMyPlaneDie;
    }
    public void setMyPlaneDie(boolean isMyPlaneDie) {
        this.isMyPlaneDie = isMyPlaneDie;
    }
    public void setBackPath(String backPath) {
        this.background = backPath;
    }
    public int getMyPlaneLefe() {
        return life;
    }
    public void setMyPlaneLefe(int myPlaneLefe) {
        this.life = myPlaneLefe;
    }
    public void setboss1isdie(boolean isBoosDie) {
        this.boss1isdie = isBoosDie;
    }
    public void setBoss2Die(boolean isBoos2Die) {
        this.isBoss2Die = isBoos2Die;
    }
    public Boss_1 getBoss_1() {
        return boss_1;
    }
    public Boss_2 getBoss_2() {
        return boss_2;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

//    @Override
//    public void keyPressed(KeyEvent e) {
//        if (e.getKeyCode() == 37) {
//            int x = myplane.getX();
//            if (x >= 0) {
//                x = Math.max(x - 10, 0);
//            }
//            myplane.setX(x);
//        } else if (e.getKeyCode() == 38) {
//            int y = myplane.getY();
//            if (y >= 0) {
//                y = Math.max(y - 10, 0);
//            }
//            myplane.setY(y);
//        } else if (e.getKeyCode() == 39) {
//            int x = myplane.getX();
//            if (x >= 0) {
//                x = Math.min(x + 10, 450);
//            }
//            myplane.setX(x);
//        } else if (e.getKeyCode() == 40) {
//            int y = myplane.getY();
//            if (y >= 0) {
//                y = Math.min(y + 10, 530);
//            }
//            myplane.setY(y);
//        }
//
//    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            isPress01 = true;
        }

        if (key == KeyEvent.VK_DOWN) {
            isPress02 = true;
        }

        if (key == KeyEvent.VK_LEFT) {
            isPress03 = true;
        }

        if (key == KeyEvent.VK_RIGHT) {
            isPress04 = true;
        }

        if (key == KeyEvent.VK_ENTER) {
            myplane.setFire(true);//����
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            isPress01 = false;
        }

        if (key == KeyEvent.VK_DOWN) {
            isPress02 = false;
        }


        if (key == KeyEvent.VK_LEFT) {
            isPress03 = false;
        }


        if (key == KeyEvent.VK_RIGHT) {
            isPress04 = false;
        }

        if (key == KeyEvent.VK_ENTER) {
            myplane.setFire(false);//����
        }

    }
}




































