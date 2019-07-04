package com.melon.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.melon.action.PlayMusic;
import com.melon.domain.AlbumVO;
import com.melon.persistence.AlbumDAOImpl;

class PlayerTest extends JPanel {
	   //큰패널
	   private JPanel mainLeft=new JPanel(new BorderLayout());
	   private JPanel mainRight=new JPanel(new BorderLayout(0,5));
	   private JPanel main=new JPanel(new BorderLayout());
	   JPanel RightMainDate;
	   JPanel LeftBTP=new JPanel(new BorderLayout());

	   BufferedImage img1=null;

	   private JButton play;
	   private JButton stop;
	   JButton nextmu;
	   private JButton restart;
	   private ImageIcon play1,stop1,parse1,next1,restart1,before1;

	   //메인왼쪽헤더패널
	   JPanel LeftHeadJP=new JPanel(new BorderLayout());
	   JPanel LeftMainJP=new JPanel(new BorderLayout(10,5));
	   JLabel LeftHeadJL1=new JLabel("Melon ");
	   JLabel LeftHeadJL2=new JLabel(" Music Player");

	   static int a=0;
	   //폰트설정
	   Font f1=new Font("나눔바른펜",Font.BOLD,15);
	   Font f2=new Font("나눔바른펜",Font.PLAIN,15);
	   Font f3=new Font("나눔바른펜",Font.BOLD,17);

	   private JCheckBox chk; // 체크박스 
	   private int[] ids;

	   private static int chkID;
	   private static LinkedList<Integer> chkIDs = new LinkedList<>();

	   ArrayList<AlbumVO> mudataA=new ArrayList<>();//arraylist dataA에 chklist AlbumVO로 담기
	   AlbumDAOImpl mudaoA=new AlbumDAOImpl();
	   static PlayMusic ms;

	   public PlayerTest(int a) {//듣기

	      AlbumVO dataA=new AlbumVO();
	      AlbumDAOImpl daoA=new AlbumDAOImpl();
	      dataA=daoA.fromnumb(a);
	      String alName = dataA.getAlName();
	      String singer = dataA.getSinger();
	      String mf = dataA.getMusicFile();
	      String musicNam = dataA.getMusicName();
	      String pic = dataA.getPicFile();
	      int countp=dataA.getCount();
	      countp++;
	      daoA.countup(a,countp);
	      ms=new PlayMusic(mf);
	      LeftHeadJL1.setForeground(new Color(0,214,59));

	      //메인왼쪽헤더패널
	      main.add(mainLeft,BorderLayout.WEST);
	      LeftHeadJL1.setFont(f1);
	      LeftHeadJL2.setFont(f2);
	      //System.out.println(f1.getName());
	      mainLeft.add(LeftHeadJP,BorderLayout.NORTH);
	      LeftHeadJP.add(LeftHeadJL1,"West");
	      LeftHeadJP.add(LeftHeadJL2,"Center");
	      LeftHeadJP.setBackground(Color.WHITE);
	      //메인패널
	      mainLeft.add(LeftMainJP,BorderLayout.CENTER);
	      LeftMainJP.setBackground(Color.WHITE);   
	      JLabel pical= new JLabel();
	      Image img = new
	            ImageIcon(pic).getImage();
	      Image icb=img.getScaledInstance(230, 230,Image.SCALE_SMOOTH);
	      ImageIcon ica=new ImageIcon(icb);
	      pical.setIcon(ica);

	      LeftMainJP.add("North", new JLabel());//여백설정
	      LeftMainJP.add("South", new JLabel());//여백설정
	      LeftMainJP.add("East", new JLabel());//여백설정
	      LeftMainJP.add("West", new JLabel());//여백설정
	      LeftMainJP.add("Center",pical);//앨범사진 넣기
	      //타이머 패널
	      JPanel ShowTime=new JPanel(new BorderLayout(10,5));
	      restart=new JButton();
	      ShowTime.add("North", new JLabel());//여백설정
	      ShowTime.add("South", new JLabel());//여백설정
	      ShowTime.add("East", new JLabel());//여백설정
	      ShowTime.add("West", new JLabel());//여백설정
	      JPanel ShowTimeIn=new JPanel(new BorderLayout());
	      LeftMainJP.add(ShowTime,BorderLayout.SOUTH);
	      ShowTime.add(ShowTimeIn, "Center");
	      ShowTimeIn.setBackground(Color.white);
	      JLabel FTime=new JLabel(ms.time());
	      FTime.setFont(f2);
	      ShowTimeIn.add(FTime,"East");
	      JLabel MusicName=new JLabel(musicNam);
	      MusicName.setFont(f2);
	      ShowTimeIn.add(MusicName,"West");
	      stop=new JButton();
	      FTime.setBackground(Color.white);
	      ShowTime.setBackground(Color.white);
	      //왼쪽아래 플레이어버튼
	      JPanel LeftBTP=new JPanel(new BorderLayout(5,5));
	      mainLeft.add(LeftBTP,BorderLayout.SOUTH);
	      LeftBTP.setBackground(Color.white);
	      LeftBTP.add("North", new JLabel());//여백설정
	      LeftBTP.add("South", new JLabel());//여백설정
	      LeftBTP.add("East", new JLabel());//여백설정
	      LeftBTP.add("West", new JLabel());//여백설정
	      JPanel LeftBTPI=new JPanel(new GridLayout(0,3));
	      LeftBTPI.setBackground(Color.WHITE);
	      LeftBTP.add(LeftBTPI,BorderLayout.CENTER);
	      play=new JButton();


	      play.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            if(ms != null) {
	               //ms.play();//mid파일 재생
	               if(play.getIcon().equals(play1)) {
	                  play.setIcon(parse1);
	                  ms.play();
	                  FTime.setText(ms.time());
	               }
	               else if(play.getIcon().equals(parse1)) {
	                  play.setIcon(play1);
	                  ms.parse();
	               }
	            }
	         }

	      });
	      stop.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            if(ms != null) {
	               ms.stop();//mid파일 재생
	               FTime.setText("   0:00         ");
	               if(play.getIcon().equals(parse1)) {
	                  play.setIcon(play1);
	               }
	            }
	         }
	      });
	      restart.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            if(ms != null) {
	            	
	               ms.restart();//mid파일 재생
	               FTime.setText(ms.time());
	               if(play.getIcon().equals(play1)) {
	                  play.setIcon(parse1);
	               }
	            }
	         }

	      });


	      //버튼 아이콘입히기 
	      play1=new ImageIcon("./img/player/play.jpg");
	      stop1=new ImageIcon("./img/player/stop.jpg");

	      parse1=new ImageIcon("./img/player/pause.jpg");

	      restart1=new ImageIcon("./img/player/restart.jpg");

	      play.setIcon(play1);
	      stop.setIcon(stop1);
	      restart.setIcon(restart1);
	      //버튼외곽테두리삭제
	      play.setBorderPainted(false);stop.setBorderPainted(false);restart.setBorderPainted(false);
	      //버튼배경삭제
	      play.setContentAreaFilled(false);stop.setContentAreaFilled(false);restart.setContentAreaFilled(false);
	      LeftBTPI.add(restart);
	      LeftBTPI.add(play); 
	      LeftBTPI.add(stop);
	      add(main);
	   }
	}
