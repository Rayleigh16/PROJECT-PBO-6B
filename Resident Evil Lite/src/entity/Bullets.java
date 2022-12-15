package entity;

import Main.Window;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Bullets {
    private Player player;
    private double x,y;
    public static double xPos, yPos;
    private double dx,dy;
    private double speed;
    private int r;
    private double rad;
    private BufferedImage bullet;
    public static int width, height;
    
    public Bullets(
        double angle, int x, int y, int r1,int r2){
   
        this.x = x;
        this.y = y;
        r = -2;
        
        rad =  Math.toRadians(angle);
        dx = Math.sin(rad) * (r1);
        dy =  Math.cos(rad) * (r2);
        speed = 15;
        width = height = 15;
    }
    public boolean update(){
    
            x += dx;
            y += dy;
            xPos = x;
            yPos = y;
            if(x < -r || x > Window.WIDTH ||
               y < -r || y > Window.HEIGHT){
                return true;
            }
            return false;
      
    }
    public double getXPos(){
        return xPos;
    }
    public double getYPos(){
        return yPos;
    }
    public void draw(Graphics g){
        try {
            bullet = ImageIO.read(new File("resources/bullet/0.png"));
            g.drawImage(
                    bullet,
                    (int) (x - r), 
                    (int) (y - r), 
                    width, height,
                    null);
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        }
}
