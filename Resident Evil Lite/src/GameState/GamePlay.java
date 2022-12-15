package GameState;

import Main.Window;
import Sounds.Sounds;
import TileSet.GamePlayBg;

import entity.Controller;
import entity.Player;
import entity.ZombiesBos;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GamePlay extends GameState{
    private Player player;
    private Controller controller;
    private Color color;
    private ZombiesBos zombos;
    public static int timelaps;
    private long zombosTimer;
    private long zombosDelay;
    private GamePlayBg bg;
    private static Sounds musicGame;
    public static String namePlayer;
    public static int getZombiesKilled;
    public GamePlay(GameStateManager gsm,String tmp) {
        super(gsm);
        namePlayer = tmp;
    }

    @Override
    public void init() {
     musicGame = new Sounds("/bgsounds.wav");
     musicGame.play();
     musicGame.loop();   
     timelaps = 0;
     zombosTimer = System.nanoTime();
     player = new Player();  
     
     bg = new GamePlayBg();
     controller = new Controller();
     zombos = new ZombiesBos(Math.random() * 670, Math.random() * 623,true);
    }
    public static void stopMusic(){
        musicGame.stop();
    }
    @Override
    public void update() {
     
     
     player.update();
     controller.update();
     
    
     if(timelaps > 4){
          zombos.update();
     }
    }

    @Override
    public void draw(Graphics g) {
    bg.draw(g);
   
    //player
    player.draw(g);
    //bos
    long elapsed = (System.nanoTime() - zombosTimer) / 1000000;
           if(elapsed > 1000){
               timelaps++;
               zombosTimer = System.nanoTime();
           }
           
           if(timelaps > 4  ){
                 zombos.draw(g);
               }
    controller.draw(g);
         //Draw bar component
         //NamePlayer
          g.setFont(
                   new Font("Arial", Font.PLAIN,19)
              );  
          g.setColor(Color.white);
          g.drawString("Name : "+namePlayer, 20, 30);
          g.drawString("Healt", 20, 54);
          
          g.setFont(
                   new Font("Arial", Font.PLAIN,20)
              );    

          //FPS
          g.setColor(Color.white);
          g.drawString("FPS: "+Window.averageFPS, 760, 30);
          
          
          //Zombie Killed
          g.setColor(Color.white);
          g.drawString("Zombie Killed " + controller.getZombiesKilled(), 570,30);
          getZombiesKilled = controller.getZombiesKilled();
          //Bullet Draw
          g.drawString("Bullet : " + controller.getBullet(),440,30);
          
          g.setColor(Color.white);
          g.drawString("Time " + timelaps,330,30); 

          if(timelaps > 4){
              boolean life = zombos.getLife();
              if(life){
                  g.setColor(Color.red);
                   g.fillRect((int) zombos.getX()  - 10,(int)zombos.getY() - 20, zombos.getHaltBos(), 10); 
              }
          }
     //End Draw
            if(controller.getHpWidth() < 1){
                 new EndGameState(gsm,namePlayer);
            }
    }
    
    @Override
    public void keyPressed(int k) {
       if(k == KeyEvent.VK_A){
          player.setLeft(true);
        }
        if(k == KeyEvent.VK_D){
         player.setRight(true);
        }
        if(k == KeyEvent.VK_S){
         player.setUp(true);
        }
        if(k == KeyEvent.VK_W){
         player.setDown(true);
        }
        if(k == KeyEvent.VK_C){
          Window.suspendRequest();
        // System.exit(0);
        }
         if(k == KeyEvent.VK_X){
          Window.resumeSuspend();
         
        // System.exit(0);
        }
         if(k == KeyEvent.VK_BACK_SPACE){  
            gsm.states.pop();
            musicGame.stop();
            MenuState.rePlaySounds();
            System.out.println(gsm.states.size());
          }
     
    }

    @Override
    public void keyReleased(int k) {
        if(k == KeyEvent.VK_A){
          player.setLeft(false);
        }
        if(k == KeyEvent.VK_D){
         player.setRight(false);
        }
        if(k == KeyEvent.VK_S){
         player.setUp(false);
        }
        if(k == KeyEvent.VK_W){
         player.setDown(false);
        }
}

    @Override
    public void mousePresed(int k) {
        if(k == MouseEvent.BUTTON1){

        player.setFiring(true);
       
        }
    }

    @Override
    public void mouseReleased(int k) {
         if(k == MouseEvent.BUTTON1){
        player.setFiring(false);
    
         }
    }
    }
