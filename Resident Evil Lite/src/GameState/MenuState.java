package GameState;

import Main.Window;
import Sounds.Sounds;
import graphics.CoverGame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class MenuState extends GameState{
    private CoverGame coverGame;
    private BufferedImage gun;
   
    private String[] opsi = {
        "New Game",
        "High Scores",
        "Help",
        "Quit"
    };
    private int currentSelect = 0;
    private Color titleColor;
    private Font titleFont;
    private Font font;
    
    private static HashMap<String, Sounds> sfx = new HashMap<String, Sounds>();
    public MenuState(GameStateManager gsm) {
        super(gsm);
        try {
            init();
            gun = ImageIO.read(new File("resources/gun/aim.png"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    
//        bgMusic = new Sounds("/bg.wav");
//        bgMusic.play();
//        bgMusic.loop();
        sfx.put("BgSound", new Sounds("/bg.wav"));
        sfx.get("BgSound").play();
        sfx.get("BgSound").loop();
        sfx.put("Choise", new Sounds("/choise2.wav"));
    }
    public static void rePlaySounds(){
            sfx.put("BgSound", new Sounds("/bg.wav"));
            sfx.get("BgSound").play();
            sfx.get("BgSound").loop();
    }
    public static void stopPlaySounds(){
         sfx.get("BgSound").stop();
    }
    @Override
    public void init() {
       coverGame = new CoverGame();
    }

    @Override
    public void update() {
        
    }

    @Override
    public void draw(Graphics g) {
    coverGame.draw(g);
    
    for(int i = 0 ; i < opsi.length; i++){
        if(i == currentSelect){
            g.setColor(Color.black);
            
            g.drawImage(gun,Window.WIDTH/2 - 195, 300 + i * 45, 35, 35,null);
        }
        else{
            g.setColor(Color.white);
        }
        g.setFont(
             new Font("Ubuntu", Font.BOLD,23)
        );
        g.drawString(
             opsi[i], (Window.WIDTH/2 - 150),
             324 + i * 45
        );
    }
    }
    @Override
    public void keyPressed(int k) {
         
      if(k == KeyEvent.VK_DOWN){
            sfx.get("Choise").play();
            currentSelect++;
            
            if(currentSelect >= opsi.length){
                currentSelect = 0;
            }
        }
        if(k == KeyEvent.VK_UP){
            sfx.get("Choise").play();
            currentSelect--;
                
                if(currentSelect < 0){
                    currentSelect = opsi.length -1;
                }
            }
        if(k == KeyEvent.VK_SPACE){
                if(currentSelect == 0){
                       new InputState(gsm);
           
                }
                else if(currentSelect == 1){
                    gsm.states.push(new HighScores(gsm));
                }
                else if(currentSelect == 2){
                    gsm.states.push(new HelpState(gsm));
                  //  bgMusic.stop();
                }
                else if(currentSelect == 3){
                    System.exit(0);
                }
            }
         if(k == KeyEvent.VK_ESCAPE){
             
                    System.exit(0);
         
            }
    }

    @Override
    public void keyReleased(int k) {}

    @Override
    public void mousePresed(int k) {}

    @Override
    public void mouseReleased(int k) {}
}
