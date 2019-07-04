package com.melon.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.melon.persistence.MemberDAOImpl;

public class PayPage extends JFrame implements ActionListener{
	Font f1=new Font("나눔바른펜",Font.PLAIN,15);
	JLabel label01;
	JPanel top=new JPanel();//맨위 라벨배치되는 패널
	JPanel bottom=new JPanel();
	JPanel mid=new JPanel();
	JButton Pay1;
	JButton Pay2;
	JButton Pay3;
	BufferedImage img1,img2,img3=null;
	private MemberDAOImpl lp;
	public PayPage() {
		ImageIcon icon=new ImageIcon("./img/DoMain/melon.png");///
		setIconImage(icon.getImage());//상단바 아이콘///
		//top패널
		label01=new JLabel("멜론 이용권 구매",JLabel.LEFT);
		label01.setFont(new Font("나눔바른펜",Font.BOLD,20));
		top.add(label01);
		top.setBackground(Color.WHITE);
		add(top,"North");

		//프레임 윈도우 창 정중앙배치
		Dimension tsc=
				Toolkit.getDefaultToolkit().getScreenSize();//화면크기
		Dimension mySize=getSize();//띄우는 창 크기
		setLocation(tsc.width/2-mySize.width/2,
				tsc.height/5-mySize.height/2);
		/*try {
         img=ImageIO.read(new File("logo.png"));
      }catch(IOException e) {
         JOptionPane.showMessageDialog(null,"이미지 불러오기 실패");
         System.exit(0);
      }*/
		Pay1=new JButton("구매");
		Pay2=new JButton("구매");
		Pay3=new JButton("구매");
		Pay1.setForeground(Color.white);Pay2.setForeground(Color.white);Pay3.setForeground(Color.white);
		Pay1.setBackground(Color.BLACK);Pay2.setBackground(Color.BLACK);Pay3.setBackground(Color.black);
		Pay1.setFocusPainted(false);   Pay2.setFocusPainted(false);   Pay3.setFocusPainted(false);
		Pay1.setFont(f1);Pay2.setFont(f1);Pay3.setFont(f1);
		Pay1.addActionListener(this);
		Pay2.addActionListener(this);
		Pay3.addActionListener(this);

		//bottom패널
		add(bottom);
		bottom.setBackground(Color.WHITE);
		JPanel JPI1=new JPanel();
		JPanel JPI2=new JPanel();
		JPanel JPI3=new JPanel();
		bottom.add(JPI1);
		bottom.add(JPI2);
		bottom.add(JPI3);

		//mid패널
		add(mid);
		JPanel midN=new JPanel();
		JPanel midE=new JPanel();
		JPanel midW=new JPanel();
		JPanel midS=new JPanel();
		JPanel midMain=new JPanel();

		//mid패널 BorderLayout분할
		mid.setLayout(new BorderLayout());
		mid.add(midN,BorderLayout.NORTH);
		midN.setBackground(Color.WHITE);
		mid.add(midE,BorderLayout.EAST);
		midE.setBackground(Color.WHITE);
		mid.add(midW,BorderLayout.WEST);
		midW.setBackground(Color.WHITE);  
		mid.add(midS,BorderLayout.SOUTH);
		midS.setBackground(Color.WHITE); 
		mid.add(midMain,BorderLayout.CENTER);

		//midMain 패널
		JPanel mainP01=new JPanel();
		JPanel mainP02=new JPanel();
		JPanel mainP03=new JPanel();
		midMain.setLayout(new GridLayout(3,1));
		midMain.add(mainP01);
		midMain.add(mainP02);
		midMain.add(mainP03);

		//mainP01패널
		mainP01.setLayout(new GridLayout(1,3));

		JPanel MP1G=new JPanel();
		JPanel MP1T=new JPanel();
		JPanel MP1B=new JPanel();
		MP1T.setLayout(new BorderLayout());
		MP1B.setLayout(new BorderLayout(5,15));
		MP1B.add("North", new JLabel());
		MP1B.add("South", new JLabel());
		MP1B.add("East", new JLabel());
		MP1B.add("West", new JLabel());
		MP1G.setBackground(Color.WHITE);
		MP1T.setBackground(Color.WHITE);
		MP1B.setBackground(Color.WHITE);
		ImageIcon img1=new ImageIcon("./img/PayPage/3day.jpg");
		JLabel t01=new JLabel("       1000원");
		t01.setFont(f1);
		JLabel im01=new JLabel(img1);
		MP1G.add(im01);
		MP1T.add(t01);
		MP1B.add(Pay1);

		mainP01.add(MP1G);
		mainP01.add(MP1T);
		mainP01.add(MP1B);

		//mainP02패널
		mainP02.setLayout(new GridLayout(1,3));
		JPanel MP2G=new JPanel();
		JPanel MP2T=new JPanel();
		JPanel MP2B=new JPanel();
		MP2T.setLayout(new BorderLayout());
		MP2B.setLayout(new BorderLayout(5,15));
		MP2B.add("North", new JLabel());
		MP2B.add("South", new JLabel());
		MP2B.add("East", new JLabel());
		MP2B.add("West", new JLabel());
		MP2G.setBackground(Color.WHITE);
		MP2T.setBackground(Color.WHITE);
		MP2B.setBackground(Color.WHITE);
		ImageIcon img2=new ImageIcon("./img/PayPage/30day.jpg");
		JLabel im02=new JLabel(img2);
		JLabel t02=new JLabel("       6000원");
		t02.setFont(f1);
		MP2G.add(im02);
		MP2T.add(t02);
		MP2B.add(Pay2);

		mainP02.add(MP2G);
		mainP02.add(MP2T);
		mainP02.add(MP2B);

		//mainP03패널
		mainP03.setLayout(new GridLayout(1,3));
		JPanel MP3G=new JPanel();
		JPanel MP3T=new JPanel();
		JPanel MP3B=new JPanel();
		MP3T.setLayout(new BorderLayout());
		MP3B.setLayout(new BorderLayout(5,15));
		MP3B.add("North", new JLabel());
		MP3B.add("South", new JLabel());
		MP3B.add("East", new JLabel());
		MP3B.add("West", new JLabel());
		MP3G.setBackground(Color.WHITE);
		MP3T.setBackground(Color.WHITE);
		MP3B.setBackground(Color.WHITE);
		ImageIcon img3=new ImageIcon("./img/PayPage/1year.jpg");
		JLabel t03=new JLabel("     30000원");
		t03.setFont(f1);
		JLabel im03=new JLabel(img3);
		MP3G.add(im03);
		MP3T.add(t03,"Center");
		MP3B.add(Pay3,"Center");
		mainP03.add(MP3G);
		mainP03.add(MP3T);
		mainP03.add(MP3B);

		//JFrame
		/*   Container con;
      con=getContentPane();
      con.setLayout(new BoxLayout(con,BoxLayout.Y_AXIS));*/
		setTitle("이용권구매페이지");
		setSize(250,250);
		setVisible(true);
		setResizable(false);
		//setDefaultCloseOperation(PayPage.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				Main.updateday(Main.id);
			}

		});//내부 무명클래스 이벤트 처리*/}//기본생성자

	}
	/*class myPanel extends JPanel{
      public void paint(Graphics g) {
         g.drawImage(img1,0,0,null);
      }
   }*/
	public void actionPerformed(ActionEvent e) {
		lp=new MemberDAOImpl();
		if(e.getSource()==Pay1) {
			messageBox(this,"구매되었습니다.");
			lp.leftplus(Main.id, 3);
		}
		if(e.getSource()==Pay2) {
			messageBox(this,"구매되었습니다.");
			lp.leftplus(Main.id, 30);
		}
		if(e.getSource()==Pay3) {
			messageBox(this,"구매되었습니다.");
			lp.leftplus(Main.id, 365);
		}
	}
	public static void messageBox(Object obj,String m) {
		JOptionPane.showMessageDialog((Component)obj,m);      
	}//messageBox()
	public static void main(String[]args) {
		new PayPage();
	}

}