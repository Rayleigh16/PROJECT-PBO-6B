package GameState;

import Main.Window;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class HelpState extends GameState{
    private BufferedImage bg;
    int i = 0;
    public HelpState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {}

    @Override
    public void update() {}

    @Override
    public void draw(Graphics g) {
       
        try {
            bg = ImageIO.read(new File("resources/graphics/help.png"));
            g.drawImage(
                    bg,
                    0, 
                    0, 
                    Window.WIDTH, Window.HEIGHT,
                    null);
        } catch (Exception ex) {
           ex.printStackTrace();
        }
    }

    @Override
    public void keyPressed(int k) {
        if(k == KeyEvent.VK_BACK_SPACE){
          gsm.states.pop();
          System.out.println(gsm.states.size());
          }
    }
    @Override
    public void keyReleased(int k) {}
    @Override
    public void mousePresed(int k) {}
    @Override
    public void mouseReleased(int k) {}
    
}
