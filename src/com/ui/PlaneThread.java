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

//游戏的主线程
public class PlaneThread<MyPlane> extends Thread{

	PlanePanel planePanel;
	public static int passCount=1;//通关数
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
     * 判断我机和血包是否相撞
     */
	private void MyPlaneisHitRedHp(ArrayList<RedHp> redHps,com.entity.MyPlane myPlane){

	    for(int i=0;i<redHps.size();i++){
            RedHp redHp = redHps.get(i);//取当前血包
	    
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
     * 画HP移动
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
     * 判断自己灰机是否已死
     */
    public void isMyPlaneDie() {//我机死亡
        if(planePanel.life<=0)
        {
            planePanel.setMyPlaneDie(true);
            GamePassFrame.labelScore=planePanel.getScore();
            GamePassFrame.labelName="失败";
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
	 * 判断Boos是否死了
	 */
	public void isBossDie()
	{
		Boss_1 boss_1=planePanel.boss_1;
		
		//判断Boos已死
		 if(boss_1.getLeft()<=0&&passCount==1)
		 {
				//为BOOS添加爆炸图片 
			 for(int i=0;i<100;i++)//添加10张爆炸图片
				 addBossBomb(boss_1);
			 
			 //设置Boos已死
			 planePanel.boss1isdie=true;
			 passCount=2;
			 boss_1.setX(-300);
			 boss_1.setY(-300);
		 }			 
	}
	
	
	/*
	 * 添加爆炸图片到集合Boss_1的
	 */
	public void addBossBomb(Boss_1 boss_1){
		//取随机数
		Random random=new Random();
		int n=random.nextInt(100)-100;
		//先从面板中取出爆炸集合
		ArrayList<Bomb> bombs = planePanel.getBombs();
		Bomb bomb = new Bomb(boss_1.getX()+n,boss_1.getY()+n,
				boss_1.getWidth()-30,boss_1.getHeight()-30);
		//将爆炸对象添加到爆炸集合中
		bombs.add(bomb);
	}
	
	
	/*
	 * 添加爆炸效果到敌机集合
	 */
	private void addBomb(EnemyPlane enemyPlane){//添加爆炸效果到敌机集合
		//先从面板中取出爆炸集合
		ArrayList<Bomb> bombs = planePanel.getBombs();
		Bomb bomb = new Bomb(enemyPlane.getX(),enemyPlane.getY(),
				             enemyPlane.getWidth(),enemyPlane.getHeight());
		//将爆炸对象添加到爆炸集合中
		bombs.add(new Bomb(enemyPlane.getX(), enemyPlane.getY(), enemyPlane.getHeight(), enemyPlane.getWidth()));
	}
	

	/*
	 * 移除爆炸效果
	 */
	private void removeBombs(){//移除爆炸效果
		//先从面板中取出爆炸图片集合
		ArrayList<Bomb> bombs = planePanel.getBombs();
		//迭代爆炸图片集合
		for(int i=bombs.size()-1;i>=0;i--){
			Bomb bomb = bombs.get(i);
			//判断每个图片是否还有效，无效就可以删掉。
			if(bomb.isEffect()==false){
				bombs.remove(i);
			}
		}
	}
	
	private void boss1move(){//boss1移动
		if(Timethread.time<0&&planePanel.boss1isdie==false)
		{
			planePanel.boss_1.move();
		}
	}
	
	private void boss2move()//boss2移动
	    {
	        //当Boos没死且时间小于0时
	        if(passCount==2)
	        if(Timethread.time<5&&Timethread.time>0&&planePanel.isBoss2Die==false)
	        {
	            Timethread.time=3;
	            planePanel.boss_2.move();
	        }
	    }
		
	
	private void myplanebulletmove(){//我机子弹移动
		
		ArrayList<MyPlaneBullet> myPlaneBullets=planePanel.myplanebullets;//获取我机子弹集合
		for(int i=0;i<myPlaneBullets.size();i++)
			myPlaneBullets.get(i).move();
	}
	private void removeMyPlanebullet(){//移除我机子弹
		ArrayList<MyPlaneBullet> myPlaneBullets=planePanel.myplanebullets;//获取我机子弹集合
		for(int i=0;i<myPlaneBullets.size();i++)
			if(myPlaneBullets.get(i).isIseFFect()==false)
				myPlaneBullets.remove(i);//移除当前子弹
	}
	private void addmyplanebullet(){//添加我机子弹
		com.entity.MyPlane myPlane=planePanel.myplane;//获取我机
		ArrayList<MyPlaneBullet> myPlaneBullets=planePanel.myplanebullets;//获取我机子弹集合
			
			if(myPlane.isFire()==true){
				myPlaneBullets.add(new MyPlaneBullet(myPlane.getX(), myPlane.getY()));
			
		}
	}
	private void removeenemyBullet(){//移除敌人子弹
		ArrayList<EnemyBullet> enemyBullets=planePanel.enemyBullets;//获取敌机子弹集合
		for(int i=0;i<enemyBullets.size();i++)
			if(enemyBullets.get(i).isIseFFect()==false)
				enemyBullets.remove(i);//移除当前子弹
	}

    private void removebossBullet(){//移除boss子弹
        ArrayList<BossBullet> bossBullets=planePanel.bossBullets;//获取敌机子弹集合
        for(int i=0;i<bossBullets.size();i++)
            if(bossBullets.get(i).isIseFFect()==false)
                bossBullets.remove(i);//移除当前子弹
    }
	private void enemyBulletmove(){//敌人子弹移动
		ArrayList<EnemyBullet> enemyBullets=planePanel.enemyBullets;//获取敌机子弹集合
		for(int i=0;i<enemyBullets.size();i++)
			enemyBullets.get(i).move();
	}
	
    private void bossBulletmove(){//boss子弹移动
        ArrayList<BossBullet> bossBullets=planePanel.bossBullets;//获取敌机子弹集合
        for(int i=0;i<bossBullets.size();i++)
            bossBullets.get(i).move();
    }
	private void addEnemyPlaneBullets(){//添加敌机子弹
		EnemyPlane[] enemyplanes=planePanel.Enemyplanes;//获取敌机集合
		ArrayList<EnemyBullet> enemyBullets=planePanel.enemyBullets;//获取敌机子弹集合
		for(int i=0;i<enemyplanes.length;i++){
			EnemyPlane enemyplane=enemyplanes[i];//取当前敌机
			if(enemyplane.toFire()==true){
				enemyBullets.add(new EnemyBullet(enemyplane.getX(), enemyplane.getY()));
			}
		}
	}
	private void addBossBullets(){//添加boss子弹
        Boss_1 boss1 =planePanel.boss_1;//获取boss
        ArrayList<BossBullet> bossBullets = planePanel.bossBullets;//获取boss子弹集合
            if(boss1.toFire()==true){
                bossBullets.add(new BossBullet(boss1.getX(), boss1.getY()));
            }
        }
    
	private void Enemyplanesmove(){//敌机移动方法
		EnemyPlane[] Enemyplanes=planePanel.Enemyplanes;//获取敌机数组
		for(int i=0;i<Enemyplanes.length;i++)
			Enemyplanes[i].move();//移动每个敌机
	}
	
	private void myplane_hit_boss1() {//我机撞boss
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
	private void myplane_hit_boss2() {//我机撞boss2
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
	
	private void mybullet_hit_boss1(ArrayList<MyPlaneBullet> myplaneBullets,Boss_1 boss_1){//我机子弹撞BOSS

			for(int j=0;j<myplaneBullets.size();j++){
				MyPlaneBullet myplaneBullet=myplaneBullets.get(j);//取当前子弹
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
	private void mybullet_hit_boss2(ArrayList<MyPlaneBullet> myplaneBullets,Boss_2 boss_2){//我机子弹撞BOSS2

        for(int j=0;j<myplaneBullets.size();j++){
            MyPlaneBullet myplaneBullet=myplaneBullets.get(j);//取当前子弹
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
	
	private void bossbullet_hit_myplane(ArrayList<BossBullet> bossBullets,com.entity.MyPlane myPlane){//boss子弹撞我机
        for(int j=0;j<bossBullets.size();j++){
            BossBullet bossBullet = bossBullets.get(j);//取当前boss子弹
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
	
	private void enemybullet_hit_myplane(ArrayList<EnemyBullet> enemyBullets,com.entity.MyPlane myPlane){//敌机子弹撞我机
			for(int j=0;j<enemyBullets.size();j++){
				EnemyBullet enemyBullet = enemyBullets.get(j);//取当前敌机子弹
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

	
	private void mybullet_hit_enemyplane(ArrayList<MyPlaneBullet> myplaneBullets,EnemyPlane[] enemyplanes){//我机子弹撞敌机
		for(int i=0;i<enemyplanes.length;i++){
			EnemyPlane enemyplane=enemyplanes[i];//取当前敌机
			for(int j=0;j<myplaneBullets.size();j++){
				MyPlaneBullet myplaneBullet=myplaneBullets.get(j);//取当前子弹
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
	
	private void myplane_hit_enemyplane(com.entity.MyPlane myPlane,EnemyPlane[] enemyplanes){//我机撞敌机
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
	private boolean point_hit_square(int x,int y,int x1,int y1,int w,int h){//点碰撞长方形
		if(x>=x1&&x<=x1+w&&y>=y1&&y<=y1+h)
			return true;
		else {
			return false;
		}
	}
}
