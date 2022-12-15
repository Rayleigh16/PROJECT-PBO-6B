package GameState;

import javax.swing.JOptionPane;

public class InputState {
    private String namePlayer;
    public InputState(GameStateManager gsm) {
        namePlayer = JOptionPane.showInputDialog(null,"Enter your name","New Game",JOptionPane.PLAIN_MESSAGE);
        if((namePlayer != null) && (namePlayer.length() > 0)){
           gsm.states.push(new GamePlay(gsm,namePlayer));
           MenuState.stopPlaySounds();
        }
        
    }
 
}
