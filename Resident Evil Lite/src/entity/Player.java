package entity;

import Main.Window;
import Sounds.Sounds;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class Player {

    public static final int PLAYERSIZE = 112;
    private BufferedImage player,player2;
    private double xMove, yMove;
    private boolean left,right,down,up;
    private double moveSpeed;
    private boolean firing;
    private long firingTimer;
    private long firingDelay;
    public static double px, py;
    private boolean setLeft,setRight;
    private HashMap<String, Sounds> sfx = new HashMap<String, Sounds>();
    
    public Player(){
        moveSpeed = 4.6;
        firing = false;
        firingTimer = System.nanoTime();
        firingDelay = 200;
        sfx.put("Shoot", new Sounds("/fire.wav"));
        init();
    }
    public void init(){
        setRight = true;
       
        try {
            player = ImageIO.read(new File("resources/player/0.png"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void setFiring(boolean b){
        firing = b;
    }
    
    public void setLeft(boolean b){left = b;}
    public void setRight(boolean b){right = b;}
    public void setUp(boolean b){up = b;}
    public void setDown(boolean b){down = b;}
    
    
    public void update(){
        
       if(left){
            xMove -= moveSpeed;
            setRight = false;
            setLeft = true;
        }
       if(right){
            xMove += moveSpeed;
            setLeft = false;
            setRight = true;
        }
       if(down){
            yMove -=moveSpeed;
        }
       if(up){
            yMove += moveSpeed;
            
        }
       
       px = xMove;
       py = yMove;
       
       if(firing){
             
           long elapsed = (System.nanoTime() - firingTimer) / 1000000;
           if(elapsed > firingDelay){
               if(setRight){
                 
                    Controller.bullets.add(new Bullets(270, 
                                      (int) getXmove() + 75,
                                      (int) getYmove() + 42,
                                      -15, -15));
               }
               if(setLeft){
                  
                   Controller.bullets.add(new Bullets(270, 
                                      (int) getXmove() - 15,
                                      (int) getYmove() + 42,
                                      15, 15));
               }
               firingTimer = System.nanoTime();
                
                try{
                    
                    sfx.get("Shoot").play();}catch(Exception e){}
           }
          
       }
       
    }
    public double getXmove(){
        return px+(Window.WIDTH / 2) - 50;
    }
    public double getYmove(){
        return py+(Window.HEIGHT / 2) - 50;
    }
    
    public void draw(Graphics g){
        
        if(xMove + (Window.WIDTH / 2) <= 0){
           xMove = Window.WIDTH / 2;
        }
        else if(xMove + (Window.WIDTH / 2) >= Window.WIDTH){
            xMove = - (Window.WIDTH / 2); 
        }
        else if(yMove + (Window.HEIGHT / 2) <= 0){
           yMove = Window.HEIGHT / 2;
        }
        else if(yMove + (Window.HEIGHT / 2) >= Window.HEIGHT){
           yMove = -(Window.HEIGHT / 2);
        }
        
        if(setRight){
            g.drawImage(
                    player,
                    (int) xMove + (Window.WIDTH / 2) - 65, 
                    (int) yMove + (Window.HEIGHT /2) -70, 
                    PLAYERSIZE/2 + 40, PLAYERSIZE/2 + 40,
                    null);
        }
        if(setLeft){
             g.drawImage(
                    player,
                    (int) xMove + (Window.WIDTH / 2) + 45, 
                    (int) yMove + (Window.HEIGHT /2) -70, 
                   - (PLAYERSIZE/2 + 40), PLAYERSIZE/2 + 40,
                    null);
        }
    }
}
