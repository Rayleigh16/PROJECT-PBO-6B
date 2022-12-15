package entity;

import Sounds.Sounds;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;

public class Controller implements Component{
    
    private int zombiesKilled;
    public static ArrayList<Bullets> bullets;
    private ArrayList<Zombies> zombie;
    private Player player;
    public static double hpWidth,hpHeight;
    
     private ArrayList<ZombiesBos> zombos;
    private long zombTimer;
    private long zombDelay;
    private HashMap<String, Sounds> sfx;
    public Controller(){

        zombiesKilled = 0;
        hpWidth = 200;
        hpHeight = 20;
        bullets =  new ArrayList<Bullets>();
        zombie = new ArrayList<Zombies>();
        
        zombTimer = System.nanoTime();
        zombDelay = 1000;
        sfx = new HashMap<String, Sounds>();
        sfx.put("Zombiehit", new Sounds("/zombies.wav"));
        }
        
    @Override
    public void update() {
       for(int i=0; i < bullets.size(); i++){
                boolean remove = bullets.get(i).update();
                if(remove){
                    bullets.remove(i);
                    i--;
                }
                //startfor
                for(int j=0; j < zombie.size(); j++){
                    int zx = (int) zombie.get(j).getX();
                    int zy = (int) zombie.get(j).getY();
                  
                //to Check Collision Zombies and Bullets
                if(new Rectangle(
                    (int) zx,
                    (int) zy,
                    Player.PLAYERSIZE/2 + 50,
                    Player.PLAYERSIZE/2 + 50).intersects(
                        new Rectangle(
                        (int) Bullets.xPos + Bullets.width-10,
                        (int) Bullets.yPos + Bullets.height,
                        Bullets.width,Bullets.height)
                    )){
                       try{
                        removeBullet(bullets.get(i));
                       }catch(Exception e){
                           
                       }
                        removeZombies(zombie.get(j));
                        zombiesKilled++;
                        sfx.get("Zombiehit").play();
                    }
                }
                //endfor
        }
       
       for(int i=0; i < zombie.size(); i++){
                zombie.get(i).update();
          
        }
     
       long elapsed = (System.nanoTime() - zombTimer) / 1000000;
           if(elapsed > zombDelay){
               addZombies(new Zombies(Math.random() * 670, Math.random() * 623,true) );
               zombTimer = System.nanoTime();
           }
   
    }
    public double getHpWidth(){
        return hpWidth;
    }
    @Override
    public void draw(Graphics g) {
       
        for(int i=0; i < bullets.size(); i++){
            bullets.get(i).draw(g);
            
        }
        
        for(int i=0; i < zombie.size(); i++){
                zombie.get(i).draw(g);
   
        }
        
         if(hpWidth >= 110){
            g.setColor(Color.GREEN);
            g.fillRect(80, 38, (int) hpWidth, (int) hpHeight);
        }
        else if(hpWidth < 110 && hpWidth >= 50){
            g.setColor(Color.yellow);
            g.fillRect(80, 38, (int) hpWidth, (int) hpHeight);
        }
        else if(hpWidth < 50){
            g.setColor(Color.red);
            g.fillRect(80, 38, (int) hpWidth, (int) hpHeight);
        }
         
    }
    
    public int getBullet(){
        return bullets.size();
    }
    public int getZombiesKilled(){
        return zombiesKilled;
    }
   
    public void addBullet(Bullets block){
        bullets.add(block);
    }
    public void removeBullet(Bullets block){
        bullets.remove(block);
    }
     public void addZombies(Zombies block){
        zombie.add(block);
    }
    public void removeZombies(Zombies block){
        zombie.remove(block);
    }

}
