package TileSet;

import Main.Window;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;


public class GamePlayBg {

    private BufferedImage bg;
    public void draw(Graphics g){
        try {
            bg = ImageIO.read(new File("resources/tile/street.jpg"));
            g.drawImage(
                    bg,
                    0, 
                    0, 
                    Window.WIDTH, Window.HEIGHT+170,
                    null);
        } catch (Exception ex) {
           ex.printStackTrace();
        }
    }
}
