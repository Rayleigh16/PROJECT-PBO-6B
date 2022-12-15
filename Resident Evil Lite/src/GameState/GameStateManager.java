package GameState;


import java.awt.Graphics;
import java.util.Stack;

public class GameStateManager {
     public Stack<GameState> states;
     public GameStateManager(){
         states = new Stack<GameState>();
         states.push(new MenuState(this));
     }
     public void update(){
         states.peek().update();
     }
     public void draw(Graphics g){
         states.peek().draw(g);
     }
     public void keyPressed(int k){
         states.peek().keyPressed(k);
     }
     public void keyReleased(int k){
         states.peek().keyReleased(k);
     }
     public void mousePressed(int k){
         states.peek().mousePresed(k);
     }
     public void mouseReleased(int k){
         states.peek().mouseReleased(k);
     }
   
}
