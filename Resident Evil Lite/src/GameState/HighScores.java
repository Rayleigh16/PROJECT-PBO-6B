
package GameState;

import Connection.Koneksi;
import Main.Window;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class HighScores extends GameState{
    public  ResultSet result;
    private Statement stm;
    private Connection konek;
    private String query;
    private BufferedImage bg;
    private String name,scores,zombiesKilled;
    public HighScores(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init(){
    konek = new Koneksi().connect();
     
        try {
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(){}

    @Override
    public void draw(Graphics g) {
       
        try {
            bg = ImageIO.read(new File("resources/graphics/score.png"));
            g.drawImage(
                    bg,
                    0, 
                    0, 
                    Window.WIDTH, Window.HEIGHT,
                    null);
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        
        g.setColor(Color.white);
        g.setFont(
                   new Font("Arial", Font.BOLD,24)
              ); 
        g.drawString("Rank",130, 190);
        g.drawString("Name",217, 190);
        g.drawString("Zombie",394, 190);
        g.drawString("Killed",490, 190);
        g.drawString("Scores",631, 190);
        
        g.setFont(
                   new Font("Arial", Font.PLAIN,24)
              ); 
        try {
            int i = 0;
            int j = 30;
            query = ("SELECT * FROM player ORDER BY scores DESC");
            stm = konek.createStatement();
            result = stm.executeQuery(query);
            while(result.next()){
                
                i++;
                name = result.getString("name");
                scores = result.getString("scores");
                zombiesKilled = result.getString("zombie_killed");
                
                g.drawString(""+i+"",130, 190+j);
                g.drawString(name,217, 190 + j);
                g.drawString(zombiesKilled,454, 190 + j);
                g.drawString(scores,631, 190 + j);
                j += 25;
            }
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
        if(k == KeyEvent.VK_DELETE){
           
            String dialResult = 
                    JOptionPane.showInputDialog(null,"Delete By Name Player","Delete Data",JOptionPane.WARNING_MESSAGE);
            if((dialResult!= null) && (dialResult.length() > 0)){
                query = ("DELETE FROM player where name ='"+dialResult+"'");
                    try{stm = konek.createStatement();
                    stm.executeUpdate(query);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
             }
            
        }
    }
    

    @Override
    public void keyReleased(int k) {}

    @Override
    public void mousePresed(int k) {}

    @Override
    public void mouseReleased(int k) {}
}
