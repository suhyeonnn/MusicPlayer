package com.melon.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.melon.action.PlayMusic;
import com.melon.domain.AlbumVO;
import com.melon.persistence.AlbumDAOImpl;

public class SamplePlayer extends JFrame{
   private JPanel main,mainCenter,setLabel;
   private JLabel musicName,imgL,reMark1,reMark2;
   static boolean st;
   private JFrame JF=new JFrame();
   TimerTask ts;
   Font f1=new Font("나눔바른펜",Font.PLAIN,15);
   Font f2=new Font("나눔바른펜",Font.PLAIN,12);

   public SamplePlayer(int a) {//a가 일련번호
	   ImageIcon icon=new ImageIcon("./img/DoMain/melon.png");///
		setIconImage(icon.getImage());//상단바 아이콘///
      AlbumVO dataA=new AlbumVO();
      AlbumDAOImpl daoA=new AlbumDAOImpl();
      dataA=daoA.fromnumb(a);
      String alName = dataA.getAlName();
      String singer = dataA.getSinger();
      String mf = dataA.getMusicFile();
      System.out.println(mf);
      String musicNam = dataA.getMusicName();
      String pic = dataA.getPicFile();

      Toolkit kit=Toolkit.getDefaultToolkit();
      Dimension screenSize=kit.getScreenSize();
      setLocation(screenSize.width/5,screenSize.height/8);
      JF.setBackground(Color.WHITE);
      main=new JPanel(new BorderLayout(10,10));
      mainCenter=new JPanel(new BorderLayout());
      main.setBackground(Color.white);
      main.add("North", new JLabel());//여백설정
      main.add("South", new JLabel());//여백설정
      main.add("East", new JLabel());//여백설정
      main.add("West", new JLabel());//여백설정
      
      //이미지삽입
      Image img = new ImageIcon(pic).getImage();
      Image icb=img.getScaledInstance(230, 230,Image.SCALE_SMOOTH);
      main.add(mainCenter,"Center");

      ImageIcon ica=new ImageIcon(icb);
      imgL=new JLabel(ica);
      mainCenter.add(imgL,"North");
      
      //노래제목추가
      musicName=new JLabel(musicNam);
      musicName.setFont(f1);
   
      setLabel=new JPanel(new GridLayout());
      setLabel.setBackground(Color.white);
      mainCenter.add(setLabel,"Center");
      setLabel.add(musicName);
      
      //주석문추가
      reMark1=new JLabel("미리듣기는 30초만 재생되며 자동으로 종료됩니다.");
      reMark1.setFont(f2);
      reMark1.setForeground(Color.GRAY);
      mainCenter.add(reMark1,"South");
      mainCenter.setBackground(Color.white);
      PlayMusic mp=new PlayMusic(mf);
      mp.play();
      Timer t=new Timer();
      t.schedule(mp,30000);
      ts=new TimerTask() {

         @Override
         public void run() {
            dispose();
         }
      };
      t.schedule(ts, 30000);
      //프레임설정
      add(main);
      setTitle("30초미리듣기");
      setSize(250,340);
      setVisible(true);
      setResizable(false);
      addWindowListener(new WindowAdapter() {

         @Override
         public void windowClosing(WindowEvent e) {
            mp.stop();

         }

      });
   }
   public static void main(String[] args) {
   }

}