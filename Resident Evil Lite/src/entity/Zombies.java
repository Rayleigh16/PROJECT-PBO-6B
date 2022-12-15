package entity;

import Main.Window;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Zombies{
    Image image,attack;
    private double x , y;
    private boolean isRunning = false;
    private boolean setLeft,setRight,setAttackLeft,setAttackRight;
    
    //Zombie biasa
    public Zombies(double x, double y, boolean run){
       this.x = x;
       this.y = y;
     
       isRunning = run;
       init();
   } 
    
    public void init(){
        
        try {
           image = new ImageIcon("resources/zombie/walk.gif").getImage();
           attack = new ImageIcon("resources/zombie/attack.gif").getImage();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void update() {
     if(isRunning){
         
         //to Following Player Movements
          if(Player.px +(Window.WIDTH / 2 - 50) > x){
                x += 1.5;
                setAttackLeft = false;
                setAttackRight = false;
                setLeft = false;
                setRight = true;
          }
          if(Player.px + (Window.WIDTH / 2) - 50 < x){
                x -= 1.5;
               setAttackLeft = false;
               setAttackRight = false;
                setRight = false;
                setLeft = true;
          }     
            
          if(Player.py + (Window.HEIGHT / 2) - 50 > y){
                y += 1.5;
             
          }
                
          if(Player.py + (Window.HEIGHT / 2) - 50 < y){
                y -= 1.5;
          }
           //Rec for zombies
           Rectangle rec1 = new Rectangle(
             (int) x + 58,
             (int) y + 60,
             Player.PLAYERSIZE/2 - 17,
             Player.PLAYERSIZE/2 - 20);
           //Rec for player
           Rectangle rec2 = new Rectangle(
                     (int) Player.px + (Window.WIDTH / 2),
                     (int) Player.py + (Window.HEIGHT / 2),
                     Player.PLAYERSIZE/2,Player.PLAYERSIZE/2);
           
           //to Checking Collision between Player and Zombies
          if(setLeft){
              if(rec1.intersects(rec2)){
                setLeft = false;
                setRight = false;
                setAttackRight = false;
                setAttackLeft = true;
                Controller.hpWidth -= 0.8;
              }
          }
          if(setRight){
              if(rec1.intersects(rec2)){
                 setLeft = false;
                setRight = false;
                 setAttackLeft = false;
                setAttackRight = true;
               
                Controller.hpWidth -= 0.8;
              }
          }
        }
     
      }
   public double getX(){
       return x;
   }
   public double getY(){
       return y;
   }
   
    public void draw(Graphics g) {  
        if(isRunning){
         
            if(setLeft){
                  g.drawImage(
                       image,
                        (int) x + 88, 
                        (int) y - 13, 
                        -(Player.PLAYERSIZE/2 + 50), (Player.PLAYERSIZE/2 + 50),
                        null);
            }
            if(setRight){
                 g.drawImage(
                       image,
                        (int) x - 13, 
                        (int) y - 13, 
                        (Player.PLAYERSIZE/2 + 50), (Player.PLAYERSIZE/2 + 50),
                        null);
            }
             if(setAttackRight){
                 g.drawImage(
                       attack,
                        (int) x - 13, 
                        (int) y - 13, 
                        (Player.PLAYERSIZE/2 + 60), (Player.PLAYERSIZE/2 + 60),
                        null);
            }
             if(setAttackLeft){
                 g.drawImage(
                       attack,
                        (int) x + 88, 
                        (int) y - 13, 
                        -(Player.PLAYERSIZE/2 + 60), (Player.PLAYERSIZE/2 + 60),
                        null);
            }
           
            }
        
    }
}
