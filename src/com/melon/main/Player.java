package com.melon.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.melon.action.PlayMusic;
import com.melon.domain.AlbumVO;
import com.melon.persistence.AlbumDAOImpl;

public class Player extends JFrame {
	//큰패널
	private JPanel mainLeft=new JPanel(new BorderLayout());
	private JPanel mainRight=new JPanel(new BorderLayout(0,5));
	private JPanel main=new JPanel(new BorderLayout());
	JPanel RightMainDate;
	JPanel LeftBTP=new JPanel(new BorderLayout());

	BufferedImage img1=null;
	private JButton before;

	private JButton next;
	private JButton restart;
	private ImageIcon play1,stop1,parse1,next1,restart1,before1;


	//메인왼쪽헤더패널
	JPanel LeftHeadJP=new JPanel(new BorderLayout());
	JPanel LeftMainJP=new JPanel(new BorderLayout(10,5));
	JLabel LeftHeadJL1=new JLabel("Melon ");
	JLabel LeftHeadJL2=new JLabel(" Music Player");

	static int b=0;
	//폰트설정
	Font f1=new Font("나눔바른펜",Font.PLAIN,15);
	Font f2=new Font("나눔바른펜",Font.PLAIN,30);
	Font f3=new Font("나눔바른펜",Font.PLAIN,12);

	private JCheckBox chk; // 체크박스 
	private int[] ids;

	private static int chkID;
	private static LinkedList<Integer> chkIDs = new LinkedList<>();

	ArrayList<AlbumVO> mudataA=new ArrayList<>();//arraylist dataA에 chklist AlbumVO로 담기
	AlbumDAOImpl mudaoA=new AlbumDAOImpl();

	//PlayerTest mainLef;

	JButton nextBtn;

	public Player(int a) {//메인창에서 듣기
		ImageIcon icon=new ImageIcon("./img/DoMain/melon.png");///
		setIconImage(icon.getImage());//상단바 아이콘///

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
		Timer tms=new Timer();
		PlayMusic ms=new PlayMusic(mf);

		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screenSize=kit.getScreenSize();
		setLocation(screenSize.width/5,screenSize.height/8);

		JPanel cc=new JPanel();
		PlayerTest pp=new PlayerTest(a);
		pp.setBackground(Color.white);
		/*
		cc.setLayout(new BorderLayout());
		JPanel nn=new JPanel();
		nn.setBackground(Color.white);
		JPanel ss=new JPanel();
		ss.setBackground(Color.white);
		JPanel ee=new JPanel();
		ee.setBackground(Color.white);
		JPanel ww=new JPanel();
		ww.setBackground(Color.white);
		cc.add(pp,"Center");
		cc.add(ww,"West");
		cc.add(nn,"North");
		cc.add(ss,"South");
		cc.add(ee,"East");
		 */

		//JFrame 설정
		add(pp);
		setTitle("멜론플레이어");
		setSize(280,400);
		setVisible(true);
		//setResizable(false);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				PlayerTest.ms.stop();

			}

		});
	}





	public Player(LinkedList<Integer> chklist) {//플레이어에서 듣기
		b=0;
		//mudataA.clear();
		//System.out.println(mudataA);
		ImageIcon icon=new ImageIcon("./img/DoMain/melon.png");///
		setIconImage(icon.getImage());//상단바 아이콘///
		//[7,8,9]
		//for(int i=0;i<chklist.size();i++) {
		String chkbefore=chklist.toString();
		String chkafter=chkbefore.substring(1, chkbefore.length()-1).trim();//7,8,9
		StringTokenizer tokens=new StringTokenizer(chkafter.trim(),",");
		LinkedList<Integer> list=new LinkedList<>();
		while(tokens.hasMoreTokens()) {
			int a;
			a=Integer.parseInt(tokens.nextToken().trim());
			//System.out.print(a+" ");
			mudataA.add(mudaoA.fromnumb(a));
		}
		//
		//}//mudataA에 리스트 정보 담음
		//System.out.println("\n=====================>");
		for(AlbumVO r:mudataA) {
			//System.out.print(r.getNo()+" ");
		}
		//System.out.println("\n=====================>");
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screenSize=kit.getScreenSize();
		setLocation(screenSize.width/5,screenSize.height/8);

		JPanel butbox=new JPanel();
		mainLeft.setLayout(new BorderLayout());
		mainLeft.setBackground(Color.WHITE);
		new PlayerTest(mudataA.get(b).getNo()).setBackground(Color.white);
		mainLeft.add(new PlayerTest(mudataA.get(b).getNo()),"Center");
		mainLeft.add(butbox,"South");
		main.setBackground(Color.WHITE);
		main.add(mainLeft,BorderLayout.WEST);	


		before1=new ImageIcon("./img/player/before.jpg");
		next1=new ImageIcon("./img/player/next.jpg");
		before=new JButton();
		next=new JButton();
		before.setIcon(before1);
		next.setIcon(next1);

		butbox.setBackground(Color.WHITE);
		next.setBorderPainted(false);before.setBorderPainted(false);
		before.setContentAreaFilled(false);next.setContentAreaFilled(false);
		butbox.add(before);
		butbox.add(next);
		before.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(b-1<0) {
					alert1();
				}else {
					PlayerTest.ms.stop();
					b=b-1;
					mainLeft.removeAll();
					mainLeft.add(new PlayerTest(mudataA.get(b).getNo()),"Center");
					mainLeft.add(butbox,"South");
					mainLeft.revalidate();
					mainLeft.repaint();
				}
			}
		});
		next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println(mudataA.size());
				if(b+1>=mudataA.size()) {
					alert();
				}else {
					PlayerTest.ms.stop();
					b=b+1;
					mainLeft.removeAll();
					mainLeft.add(new PlayerTest(mudataA.get(b).getNo()),"Center");
					mainLeft.add(butbox,"South");
					mainLeft.revalidate();
					mainLeft.repaint();
				}
			}
		});


		//메인 오른쪽패널
		main.add(mainRight,BorderLayout.CENTER);
		JPanel RightHeadJP=new JPanel(new BorderLayout());
		JPanel RightHeadJPin=new JPanel();
		RightHeadJP.add(RightHeadJPin,BorderLayout.WEST);
		RightHeadJP.setBackground(Color.WHITE);
		RightHeadJPin.setBackground(Color.WHITE);
		JLabel RightHeadJB1=new JLabel("플레이리스트");
		RightHeadJB1.setFont(f3);
		//플레이리스트 라벨 추가
		mainRight.add(RightHeadJP,BorderLayout.NORTH);
		RightHeadJPin.add(RightHeadJB1);
		//우측 메인페널
		RightMainDate=new JPanel();
		JPanel ononP=new JPanel();
		JScrollPane Scroll=new JScrollPane(ononP,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		mainRight.add(Scroll,"Center");
		ononP.setLayout(new BorderLayout());
		
		RightMainDate.setBackground(Color.WHITE);
		RightMainDate.setLayout(new GridLayout(0,1,0,8));

	

		//chklist.clear();
		JPanel[] rowPanel = new JPanel[chklist.size()];
		for (int i = 0; i < chklist.size(); i++) {
			rowPanel[i] = new JPanel();
			rowPanel[i].setLayout(new GridLayout(1,2));



			String alName = mudataA.get(i).getAlName();
			String singer = mudataA.get(i).getSinger();
			String mf = mudataA.get(i).getMusicFile();
			String musicNam = mudataA.get(i).getMusicName();
			String pic = mudataA.get(i).getPicFile();
			int countp=mudataA.get(i).getCount();

			JLabel[] contentsLbl = new JLabel[5];
			for (int j = 0; j < contentsLbl.length; j++) {
				contentsLbl[j] = new JLabel();
			}
			//countp++;
			//contentsLbl[0].setText(i+1+" "+countp+"회 재생됨");
			//contentsLbl[1].setIcon(ica);//이미지 넣기
			contentsLbl[2].setText(musicNam);
			contentsLbl[2].setFont(f1);
			contentsLbl[3].setText(singer);
			contentsLbl[3].setFont(f1);
			//contentsLbl[4].setText(alName);
			rowPanel[i].setBackground(Color.WHITE);
			//rowPanel[i].add(contentsLbl[0]);
			//rowPanel[i].add(contentsLbl[1]);
			rowPanel[i].add(contentsLbl[2]);
			rowPanel[i].add(contentsLbl[3]);
			//rowPanel[i].add(contentsLbl[4]);

			RightMainDate.add(rowPanel[i],BorderLayout.NORTH);
		}
		ononP.add(RightMainDate,"North");
		//여백추가
		JPanel SideCut=new JPanel();
		mainRight.add(SideCut,"East");
		SideCut.setBackground(Color.white);
		mainRight.setBackground(Color.WHITE);
		//우측 아래 패널
		JPanel RightBottonJP=new JPanel(new BorderLayout());
		mainRight.add(RightBottonJP,BorderLayout.SOUTH);
		//JLabel RBB1=new JLabel("전체선택   ");
		//JLabel RBB2=new JLabel("삭제");
		//RBB1.setFont(f2);
		//RBB2.setFont(f2);

		JPanel RBL=new JPanel(new FlowLayout());
		//RightBottonJP.add(RBL,BorderLayout.WEST);
		RightBottonJP.setBackground(Color.WHITE);
		RBL.setBackground(Color.WHITE);
		//RBL.add(RBB1);
		//RBL.add(RBB2);


		//JFrame 설정
		add(main);
		setTitle("멜론플레이어");
		setSize(600,435);
		setVisible(true);
		//setResizable(false);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				PlayerTest.ms.stop();

			}
		});

	}


	public void alert() {
		messageBox(this,"마지막곡입니다!");
	}

	public void alert1() {
		messageBox(this,"첫곡입니다!");
	}
	public static void main(String[] args) {
		new Player(8);

	}
	// 메세지 박스
	public static void messageBox(Object obj, String m) {
		JOptionPane.showMessageDialog((Component) obj, m);
	}




}
