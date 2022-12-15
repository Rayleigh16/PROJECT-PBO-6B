
package graphics;

import Main.Window;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;


public class CoverGame {

    private BufferedImage bg;
    
    public void draw(Graphics g){
        try {
            bg = ImageIO.read(new File("resources/graphics/bg2.jpg"));
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
}
