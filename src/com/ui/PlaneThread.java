package com.ui;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;


import com.entity.Boss_1;
import com.entity.Boss_2;
import com.entity.EnemyBullet;
import com.entity.EnemyPlane;
import com.entity.MyPlane;
import com.entity.MyPlaneBullet;


import com.entity.Bomb;
import com.entity.BossBullet;

//��Ϸ�����߳�
public class PlaneThread<MyPlane> extends Thread{

	PlanePanel planePanel;
	public static int passCount=1;//ͨ����
	public PlaneThread(PlanePanel planePanel){
		this.planePanel=planePanel;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		int num = 1;
		while(planePanel.life>0){
			try {
				sleep(60);
			} catch (Exception e) {
				// TODO: handle exception
				//e.printStackTrace();
			}
			num++;
			Enemyplanesmove();
			addEnemyPlaneBullets();
			addBossBullets();
			enemyBulletmove();
			bossBulletmove();
			removeenemyBullet();
			removebossBullet();
			removeBombs(); 
			if(num%5==0){
				addmyplanebullet();
				num = 1;
			}
			myplanebulletmove();
			removeMyPlanebullet();
			boss1move();
			boss2move();
			myplane_hit_enemyplane(planePanel.myplane,planePanel.Enemyplanes);
			mybullet_hit_enemyplane(planePanel.myplanebullets,planePanel.Enemyplanes);
			enemybullet_hit_myplane(planePanel.enemyBullets,planePanel.myplane);
			mybullet_hit_boss1(planePanel.myplanebullets,planePanel.boss_1);
			mybullet_hit_boss2(planePanel.myplanebullets,planePanel.boss_2);
			
			bossbullet_hit_myplane(planePanel.bossBullets,planePanel.myplane);
			myplane_hit_boss1();
			myplane_hit_boss2();
			isBossDie();
			planePanel.repaint();
			hpMove();
			MyPlaneisHitRedHp(planePanel.redHps,planePanel.myplane);
			
			isMyPlaneDie();
		}
	}
	   /*
     * �ж��һ���Ѫ���Ƿ���ײ
     */
	private void MyPlaneisHitRedHp(ArrayList<RedHp> redHps,com.entity.MyPlane myPlane){

	    for(int i=0;i<redHps.size();i++){
            RedHp redHp = redHps.get(i);//ȡ��ǰѪ��
	    
            int x1=redHp.getX();
            int y1=redHp.getY();
            int w1=redHp.getWidth();
            int h1=redHp.getHeight();
            int x2=myPlane.getX();
            int y2=myPlane.getY();
            int w2=myPlane.getWidth();
            int h2=myPlane.getHeight();
            if(square_hit_square(x1, y1, w1, h1, x2, y2, w2, h2)){
                redHp.setY(-2000);
                planePanel.life=planePanel.life+50;
            }
        }
    }
   
	
	/*
     * ��HP�ƶ�
     */
    public void hpMove()
    {
        ArrayList<RedHp> redHps =planePanel.getRedHps();
        for(int i=0;i<redHps.size();i++)
        {
            RedHp redHp=redHps.get(i);
            redHp.move();
        }
    }
    
    /*
     * �ж��Լ��һ��Ƿ�����
     */
    public void isMyPlaneDie() {//�һ�����
        if(planePanel.life<=0)
        {
            planePanel.setMyPlaneDie(true);
            GamePassFrame.labelScore=planePanel.getScore();
            GamePassFrame.labelName="ʧ��";
            new GamePassFrame(planePanel);
            passCount=1;
            planePanel.setboss1isdie(false);
            planePanel.setBoss2Die(false);
            Boss_1 boss_1=planePanel.getBoss_1();
            boss_1.setLeft(300);
            
            Boss_2 boss_2=planePanel.getBoss_2();
            
            boss_1.setX(-1000);
            boss_1.setY(-1000);
            boss_2.setX(-1000);
            boss_2.setY(-1000);
        }
    }

	/*
	 * �ж�Boos�Ƿ�����
	 */
	public void isBossDie()
	{
		Boss_1 boss_1=planePanel.boss_1;
		
		//�ж�Boos����
		 if(boss_1.getLeft()<=0&&passCount==1)
		 {
				//ΪBOOS��ӱ�ըͼƬ 
			 for(int i=0;i<100;i++)//���10�ű�ըͼƬ
				 addBossBomb(boss_1);
			 
			 //����Boos����
			 planePanel.boss1isdie=true;
			 passCount=2;
			 boss_1.setX(-300);
			 boss_1.setY(-300);
		 }			 
	}
	
	
	/*
	 * ��ӱ�ըͼƬ������Boss_1��
	 */
	public void addBossBomb(Boss_1 boss_1){
		//ȡ�����
		Random random=new Random();
		int n=random.nextInt(100)-100;
		//�ȴ������ȡ����ը����
		ArrayList<Bomb> bombs = planePanel.getBombs();
		Bomb bomb = new Bomb(boss_1.getX()+n,boss_1.getY()+n,
				boss_1.getWidth()-30,boss_1.getHeight()-30);
		//����ը������ӵ���ը������
		bombs.add(bomb);
	}
	
	
	/*
	 * ��ӱ�ըЧ�����л�����
	 */
	private void addBomb(EnemyPlane enemyPlane){//��ӱ�ըЧ�����л�����
		//�ȴ������ȡ����ը����
		ArrayList<Bomb> bombs = planePanel.getBombs();
		Bomb bomb = new Bomb(enemyPlane.getX(),enemyPlane.getY(),
				             enemyPlane.getWidth(),enemyPlane.getHeight());
		//����ը������ӵ���ը������
		bombs.add(new Bomb(enemyPlane.getX(), enemyPlane.getY(), enemyPlane.getHeight(), enemyPlane.getWidth()));
	}
	

	/*
	 * �Ƴ���ըЧ��
	 */
	private void removeBombs(){//�Ƴ���ըЧ��
		//�ȴ������ȡ����ըͼƬ����
		ArrayList<Bomb> bombs = planePanel.getBombs();
		//������ըͼƬ����
		for(int i=bombs.size()-1;i>=0;i--){
			Bomb bomb = bombs.get(i);
			//�ж�ÿ��ͼƬ�Ƿ���Ч����Ч�Ϳ���ɾ����
			if(bomb.isEffect()==false){
				bombs.remove(i);
			}
		}
	}
	
	private void boss1move(){//boss1�ƶ�
		if(Timethread.time<0&&planePanel.boss1isdie==false)
		{
			planePanel.boss_1.move();
		}
	}
	
	private void boss2move()//boss2�ƶ�
	    {
	        //��Boosû����ʱ��С��0ʱ
	        if(passCount==2)
	        if(Timethread.time<5&&Timethread.time>0&&planePanel.isBoss2Die==false)
	        {
	            Timethread.time=3;
	            planePanel.boss_2.move();
	        }
	    }
		
	
	private void myplanebulletmove(){//�һ��ӵ��ƶ�
		
		ArrayList<MyPlaneBullet> myPlaneBullets=planePanel.myplanebullets;//��ȡ�һ��ӵ�����
		for(int i=0;i<myPlaneBullets.size();i++)
			myPlaneBullets.get(i).move();
	}
	private void removeMyPlanebullet(){//�Ƴ��һ��ӵ�
		ArrayList<MyPlaneBullet> myPlaneBullets=planePanel.myplanebullets;//��ȡ�һ��ӵ�����
		for(int i=0;i<myPlaneBullets.size();i++)
			if(myPlaneBullets.get(i).isIseFFect()==false)
				myPlaneBullets.remove(i);//�Ƴ���ǰ�ӵ�
	}
	private void addmyplanebullet(){//����һ��ӵ�
		com.entity.MyPlane myPlane=planePanel.myplane;//��ȡ�һ�
		ArrayList<MyPlaneBullet> myPlaneBullets=planePanel.myplanebullets;//��ȡ�һ��ӵ�����
			
			if(myPlane.isFire()==true){
				myPlaneBullets.add(new MyPlaneBullet(myPlane.getX(), myPlane.getY()));
			
		}
	}
	private void removeenemyBullet(){//�Ƴ������ӵ�
		ArrayList<EnemyBullet> enemyBullets=planePanel.enemyBullets;//��ȡ�л��ӵ�����
		for(int i=0;i<enemyBullets.size();i++)
			if(enemyBullets.get(i).isIseFFect()==false)
				enemyBullets.remove(i);//�Ƴ���ǰ�ӵ�
	}

    private void removebossBullet(){//�Ƴ�boss�ӵ�
        ArrayList<BossBullet> bossBullets=planePanel.bossBullets;//��ȡ�л��ӵ�����
        for(int i=0;i<bossBullets.size();i++)
            if(bossBullets.get(i).isIseFFect()==false)
                bossBullets.remove(i);//�Ƴ���ǰ�ӵ�
    }
	private void enemyBulletmove(){//�����ӵ��ƶ�
		ArrayList<EnemyBullet> enemyBullets=planePanel.enemyBullets;//��ȡ�л��ӵ�����
		for(int i=0;i<enemyBullets.size();i++)
			enemyBullets.get(i).move();
	}
	
    private void bossBulletmove(){//boss�ӵ��ƶ�
        ArrayList<BossBullet> bossBullets=planePanel.bossBullets;//��ȡ�л��ӵ�����
        for(int i=0;i<bossBullets.size();i++)
            bossBullets.get(i).move();
    }
	private void addEnemyPlaneBullets(){//��ӵл��ӵ�
		EnemyPlane[] enemyplanes=planePanel.Enemyplanes;//��ȡ�л�����
		ArrayList<EnemyBullet> enemyBullets=planePanel.enemyBullets;//��ȡ�л��ӵ�����
		for(int i=0;i<enemyplanes.length;i++){
			EnemyPlane enemyplane=enemyplanes[i];//ȡ��ǰ�л�
			if(enemyplane.toFire()==true){
				enemyBullets.add(new EnemyBullet(enemyplane.getX(), enemyplane.getY()));
			}
		}
	}
	private void addBossBullets(){//���boss�ӵ�
        Boss_1 boss1 =planePanel.boss_1;//��ȡboss
        ArrayList<BossBullet> bossBullets = planePanel.bossBullets;//��ȡboss�ӵ�����
            if(boss1.toFire()==true){
                bossBullets.add(new BossBullet(boss1.getX(), boss1.getY()));
            }
        }
    
	private void Enemyplanesmove(){//�л��ƶ�����
		EnemyPlane[] Enemyplanes=planePanel.Enemyplanes;//��ȡ�л�����
		for(int i=0;i<Enemyplanes.length;i++)
			Enemyplanes[i].move();//�ƶ�ÿ���л�
	}
	
	private void myplane_hit_boss1() {//�һ�ײboss
		com.entity.MyPlane myPlane=planePanel.myplane;
		Boss_1 boss_1=planePanel.boss_1;
		int x1=myPlane.getX();
		int y1=myPlane.getY();
		int w1=myPlane.getWidth();
		int h1=myPlane.getHeight();
		int x2=boss_1.getX();
		int y2=boss_1.getY();
		int w2=boss_1.getWidth();
		int h2=boss_1.getHeight();
		
		if(square_hit_square(x1, y1, w1, h1, x2, y2, w2, h2)&&Timethread.isEffect) {
			planePanel.life=planePanel.life-20;
			boss_1.setLeft(boss_1.getLeft()-50);
			planePanel.setScore(planePanel.getScore()-30);
			Timethread.isEffect=false;
		}
	}
	private void myplane_hit_boss2() {//�һ�ײboss2
        com.entity.MyPlane myPlane=planePanel.myplane;
        Boss_2 boss_2=planePanel.boss_2;
        int x1=myPlane.getX();
        int y1=myPlane.getY();
        int w1=myPlane.getWidth();
        int h1=myPlane.getHeight();
        int x2=boss_2.getX();
        int y2=boss_2.getY();
        int w2=boss_2.getWidth();
        int h2=boss_2.getHeight();
        
        if(square_hit_square(x1, y1, w1, h1, x2, y2, w2, h2)&&Timethread.isEffect) {
            planePanel.life=planePanel.life-20;
            boss_2.setLeft(boss_2.getLeft()-50);
            planePanel.setScore(planePanel.getScore()-50);
            Timethread.isEffect=false;
        }
    }
	
	private void mybullet_hit_boss1(ArrayList<MyPlaneBullet> myplaneBullets,Boss_1 boss_1){//�һ��ӵ�ײBOSS

			for(int j=0;j<myplaneBullets.size();j++){
				MyPlaneBullet myplaneBullet=myplaneBullets.get(j);//ȡ��ǰ�ӵ�
				int x1=myplaneBullet.getX();
				int y1=myplaneBullet.getY();
				int w1=myplaneBullet.getWidth();
				int h1=myplaneBullet.getHeight();
				int x2=boss_1.getX();
				int y2=boss_1.getY();
				int w2=boss_1.getWidth();
				int h2=boss_1.getHeight();
				if(square_hit_square(x1, y1, w1, h1, x2, y2, w2, h2)){
					myplaneBullet.setIseFFect(false);
					boss_1.setLeft(boss_1.getLeft()-15);
				}
			}
		}
	private void mybullet_hit_boss2(ArrayList<MyPlaneBullet> myplaneBullets,Boss_2 boss_2){//�һ��ӵ�ײBOSS2

        for(int j=0;j<myplaneBullets.size();j++){
            MyPlaneBullet myplaneBullet=myplaneBullets.get(j);//ȡ��ǰ�ӵ�
            int x1=myplaneBullet.getX();
            int y1=myplaneBullet.getY();
            int w1=myplaneBullet.getWidth();
            int h1=myplaneBullet.getHeight();
            int x2=boss_2.getX();
            int y2=boss_2.getY();
            int w2=boss_2.getWidth();
            int h2=boss_2.getHeight();
            if(square_hit_square(x1, y1, w1, h1, x2, y2, w2, h2)){
                myplaneBullet.setIseFFect(false);
                boss_2.setLeft(boss_2.getLeft()-10);
            }
        }
    }
	
	private void bossbullet_hit_myplane(ArrayList<BossBullet> bossBullets,com.entity.MyPlane myPlane){//boss�ӵ�ײ�һ�
        for(int j=0;j<bossBullets.size();j++){
            BossBullet bossBullet = bossBullets.get(j);//ȡ��ǰboss�ӵ�
            int x1=bossBullet.getX();
            int y1=bossBullet.getY();
            int w1=bossBullet.getWidth();
            int h1=bossBullet.getHeight();
            int x2=myPlane.getX();
            int y2=myPlane.getY();
            int w2=myPlane.getWidth();
            int h2=myPlane.getHeight();
            if(square_hit_square(x1, y1, w1, h1, x2, y2, w2, h2)){
                bossBullet.setIseFFect(false);
                planePanel.life=planePanel.life-50;
                planePanel.setScore(planePanel.getScore()-10);
            }
        }
    }
	
	private void enemybullet_hit_myplane(ArrayList<EnemyBullet> enemyBullets,com.entity.MyPlane myPlane){//�л��ӵ�ײ�һ�
			for(int j=0;j<enemyBullets.size();j++){
				EnemyBullet enemyBullet = enemyBullets.get(j);//ȡ��ǰ�л��ӵ�
				int x1=enemyBullet.getX();
				int y1=enemyBullet.getY();
				int w1=enemyBullet.getWidth();
				int h1=enemyBullet.getHeight();
				int x2=myPlane.getX();
				int y2=myPlane.getY();
				int w2=myPlane.getWidth();
				int h2=myPlane.getHeight();
				if(square_hit_square(x1, y1, w1, h1, x2, y2, w2, h2)){
					enemyBullet.setIseFFect(false);
					planePanel.life=planePanel.life-10;
					planePanel.setScore(planePanel.getScore()-10);
				}
			}
		}

	
	private void mybullet_hit_enemyplane(ArrayList<MyPlaneBullet> myplaneBullets,EnemyPlane[] enemyplanes){//�һ��ӵ�ײ�л�
		for(int i=0;i<enemyplanes.length;i++){
			EnemyPlane enemyplane=enemyplanes[i];//ȡ��ǰ�л�
			for(int j=0;j<myplaneBullets.size();j++){
				MyPlaneBullet myplaneBullet=myplaneBullets.get(j);//ȡ��ǰ�ӵ�
				int x1=myplaneBullet.getX();
				int y1=myplaneBullet.getY();
				int w1=myplaneBullet.getWidth();
				int h1=myplaneBullet.getHeight();
				int x2=enemyplane.getX();
				int y2=enemyplane.getY();
				int w2=enemyplane.getWidth();
				int h2=enemyplane.getHeight();
				if(square_hit_square(x1, y1, w1, h1, x2, y2, w2, h2)){
					myplaneBullet.setIseFFect(false);
					addBomb(enemyplane);
					enemyplane.setY(-enemyplane.getY());
					planePanel.setScore(planePanel.getScore()+20);
				}
			}
		}
	}
	
	private void myplane_hit_enemyplane(com.entity.MyPlane myPlane,EnemyPlane[] enemyplanes){//�һ�ײ�л�
		for(int i=0;i<enemyplanes.length;i++){
			EnemyPlane enemyplane=enemyplanes[i];
			int x1=myPlane.getX();
			int y1=myPlane.getY();
			int w1=myPlane.getWidth();
			int h1=myPlane.getHeight();
			int x2=enemyplane.getX();
			int y2=enemyplane.getY();
			int w2=enemyplane.getWidth();
			int h2=enemyplane.getHeight();
			if(square_hit_square(x1, y1, w1, h1, x2, y2, w2, h2)){
				planePanel.life=planePanel.life-20;
				enemyplane.setY(-enemyplane.getY());
			}
		}
	}
	private boolean square_hit_square(int x1,int y1,int w1,int h1,int x2,int y2,int w2,int h2){
		if(point_hit_square(x1,y1,x2,y2,w2,h2)||
				point_hit_square(x1+w1,y1,x2,y2,w2,h2)||
				point_hit_square(x1,y1+h1,x2,y2,w2,h2)||
				point_hit_square(x1+w1,y1+h1,x2,y2,w2,h2))
			return true;
		else
			return false;
	}
	private boolean point_hit_square(int x,int y,int x1,int y1,int w,int h){//����ײ������
		if(x>=x1&&x<=x1+w&&y>=y1&&y<=y1+h)
			return true;
		else {
			return false;
		}
	}
}
