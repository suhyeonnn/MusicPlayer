package com.melon.action;



import java.io.File;
import java.util.TimerTask;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class PlayMusic extends TimerTask{     

   public Clip clip;
   File file;
   public PlayMusic(String fileName) {

      try {
    	 if(fileName.substring(fileName.length()-4, fileName.length()).equals(".wav")) {
    		file = new File(fileName);
    	 }else {
    		file = new File(fileName+".wav");
    	 }
         
         if(file.exists()) {
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(stream);
         }

      } catch(Exception e) {

         e.printStackTrace();
      }
   }
   public void play() {
      clip.start();
   }
   public void stop() {
      clip.setFramePosition(0);
      clip.stop();
   }
   public void parse() {
      clip.stop();
   }
   public void restart() {
      clip.setLoopPoints(0,-1);
      clip.loop(clip.LOOP_CONTINUOUSLY);
   }
   public void play10() {
      clip.setLoopPoints(0,3);
      clip.loop(1);
   }
   public int when() {
	   int muNow=clip.getFramePosition();
	return muNow;
	   
   }
   public String time() {
      int re=(int)clip.getMicrosecondLength();
      int min=re/60000000;
      String sec;
      if(re/1000000%60<10) {
    	  sec="0"+re/1000000%60;
      }else{
    	  sec=""+re/1000000%60;;
      }
      String i="   "+min+":"+sec+"         ";
      return i;
   }
   @Override
   public void run() {
      clip.setFramePosition(0);
      clip.stop();
      
   }

}