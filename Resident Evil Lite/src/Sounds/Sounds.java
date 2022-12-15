package Sounds;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sounds{
    private Clip clip;
   public Sounds(String s){
       try{
           AudioInputStream ais =
                 AudioSystem.getAudioInputStream(
                   getClass().getResourceAsStream(s)
                 );
           AudioFormat baseFormat = ais.getFormat();
           AudioFormat decodeFormat = new AudioFormat(
               AudioFormat.Encoding.PCM_SIGNED, //Encoding to use
               baseFormat.getSampleRate(), //sample rate (same as base format)
               16,       // sample size in bits (thx to javazoom)
               baseFormat.getChannels(), // # of channels
               baseFormat.getChannels() * 2, //frame size
               baseFormat.getSampleRate(), //frame rate 
                   false //Big Endian
           );
           AudioInputStream dais = 
                   AudioSystem.getAudioInputStream(
                     decodeFormat, ais
                   );
           clip = AudioSystem.getClip(null);
           clip.open(dais);
         
       }catch(Exception e){
          
       }
   }
   
   public void play(){
       if (clip == null) return;
            stop();
            
            clip.setFramePosition(0);
            clip.start();
           
   }   
   public void stop(){
       if(clip.isRunning()) clip.stop();
   }
   public void loop(){
       clip.loop(3);
   }
   public void close(){
       stop();
       clip.close();
   }
    
}
