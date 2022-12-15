
package GameState;
import Connection.Koneksi;
import java.sql.Connection;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class EndGameState {
    private Connection konek;
    private Statement stm;
    private String query;
    private String namePlayer;
    private int zombiesKilled;
    private int Scores;
    int yes = JOptionPane.YES_OPTION;
    public EndGameState(GameStateManager gsm, String namePlayer) {
        this.namePlayer = namePlayer;
        Scores = GamePlay.getZombiesKilled * 50;
        zombiesKilled = GamePlay.getZombiesKilled;
       int dialResult = 
           JOptionPane.showConfirmDialog(
                   null, 
                   "Would You Like to Save Your Score?\n "
                    + "Zombie Killed : "
                    +zombiesKilled+
                   " \n Your Score : "+Scores,
                   "Game Over", 
                   yes);
       if(dialResult == JOptionPane.YES_OPTION){
               insertToDB();
               System.out.println("Save!");
               gsm.states.pop();
               GamePlay.stopMusic();
               MenuState.rePlaySounds();
               System.out.println(gsm.states.size());
       }
       else{
           System.out.println("No");
               gsm.states.pop();
               GamePlay.stopMusic();
               System.out.println(gsm.states.size());
               gsm.states.push(new GamePlay(gsm,GamePlay.namePlayer));     
       }   
    }
    public void insertToDB(){
        konek = new Koneksi().connect();
        query = "INSERT INTO player VALUES(NULL,'"+namePlayer+"',"+Scores+","+zombiesKilled+")";
        try {
            stm = konek.createStatement();
            stm.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
 
}
