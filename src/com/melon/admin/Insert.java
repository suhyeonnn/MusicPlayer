package com.melon.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.melon.domain.AlbumVO;
import com.melon.persistence.AlbumDAOImpl;


public class Insert extends JDialog implements ActionListener {

	//패널
	private JPanel backpan01=new JPanel(new BorderLayout(10,10));
	private JPanel intopanlow=new JPanel(new BorderLayout());
	private JPanel mpan=new JPanel(new BorderLayout());
	private JPanel lapan=new JPanel(new GridLayout(7, 1));
	private JPanel txtpan=new JPanel(new GridLayout(7, 1));
	private JPanel btncom=new JPanel(new FlowLayout());

	private JPanel number=new JPanel(new BorderLayout());
	private JPanel number_01=new JPanel(new FlowLayout());

	private JPanel idfield=new JPanel(new BorderLayout());
	private JPanel idfield_01=new JPanel(new FlowLayout());
	private JPanel txtflow01=new JPanel(new BorderLayout());
	private JPanel txtflow01_01=new JPanel(new FlowLayout());
	private JPanel txtflow02=new JPanel(new BorderLayout());
	private JPanel txtflow02_01=new JPanel(new FlowLayout());
	private JPanel txtflow03=new JPanel(new BorderLayout());
	private JPanel txtflow03_01=new JPanel(new FlowLayout());
	private JPanel emailtxtpan=new JPanel(new BorderLayout());
	private JPanel emailtxtpan_01=new JPanel(new FlowLayout());
	private JPanel emailtxtpan1=new JPanel(new BorderLayout());
	private JPanel emailtxtpan1_01=new JPanel(new FlowLayout());
	//라벨
	private JLabel nlnumber;
	private JLabel nlid;
	private JLabel nlpwd;
	private JLabel nlpwde;
	private JLabel nlname;
	private JLabel nlemail;
	private JLabel nlemail1;

	//텍스트 입력박스
	private JTextField ntnumber;
	private JTextField ntid;
	private JTextField ntpwd;
	private JTextField ntpwde;
	private JTextField ntname;
	private JTextField ntemail;
	private JTextField ntemail02;

	//버튼
	private JButton registerB;
	private JButton overbtn;
	private JButton overbtn1;
	private JButton calbtn;


	private JFrame jf;
	private FileDialog fd1;
	private InputStream fis, musicFis;
	private String picName, musicName;
	//콤보박스
	//String[] emailname= {"선택","@naver.com","@daum.net","@hotmail.com","@nate.com","@gmail.com","직접입력"};
	//private JComboBox emailcom=new JComboBox(emailname);

	AlbumVO a=new AlbumVO();
	AlbumDAOImpl dao = new AlbumDAOImpl();
	
	   Font f1=new Font("나눔바른펜",Font.PLAIN,15);
	   Font f2=new Font("나눔바른펜",Font.PLAIN,30);
	   Font f3=new Font("나눔바른펜",Font.PLAIN,12);

	   Color c1=new Color(0,205,60);
	   
	public Insert() {
		setTitle("노래등록");
	      ImageIcon icon=new ImageIcon("./img/DoMain/melon.png");///
	      setIconImage(icon.getImage());//상단바 아이콘///
		
		//라벨 객체생성
		nlnumber=new JLabel("일련번호");
		nlid=new JLabel("앨범명");
		nlpwd=new JLabel("발매일");
		nlpwde=new JLabel("앨범사진");
		nlname=new JLabel("가수");
		nlemail=new JLabel("노래명");
		nlemail1=new JLabel("노래파일");
		nlnumber.setFont(f3);
		nlid.setFont(f3);
		nlpwd.setFont(f3);
		nlpwde.setFont(f3);
		nlname.setFont(f3);
		nlemail.setFont(f3);
		nlemail1.setFont(f3);
		
		//입력박스
		ntnumber=new JTextField(5);
		ntid=new JTextField(17);
		ntpwd=new JTextField(17);
		ntpwde=new JTextField(17);
		ntname=new JTextField(17);
		ntemail=new JTextField(17);
		ntemail02=new JTextField(17);

		//버튼생성
		registerB=new JButton("등록");
		registerB.setFont(f3);
		calbtn=new JButton("취소");
		calbtn.setFont(f3);
		overbtn=new JButton("사진등록");
		overbtn.setFont(f3);
		overbtn1=new JButton("노래등록");
		overbtn1.setFont(f3);

		//각 메서드 호출
		panelnu_Set(); //eventnu_Set();

		setSize(370,360);setVisible(true);
		setResizable(true);
		ntemail02.setEditable(false);//콤보박스 락 비활성화
		ntpwde.setEditable(false);//콤보박스 락 비활성화
		ntnumber.setEditable(false);//콤보박스 락 비활성화

		Dimension tsc=Toolkit.getDefaultToolkit().getScreenSize();//화면사이즈
		Dimension mySize=getSize();//띄우는 창 크기
		setLocation(tsc.width/2-mySize.width/2,tsc.height/2-mySize.height/2);//정중앙 위치
	}//생성자

	//패널 배치
	public void panelnu_Set() {
		backpan01.add("North",new JLabel());
		backpan01.add("South",new JLabel());
		backpan01.add("East",new JLabel());
		backpan01.add("West",new JLabel());//상하좌우 여백
		backpan01.add("Center",intopanlow);
		intopanlow.add("Center",mpan);

		mpan.add("West",lapan);
		lapan.add(nlnumber);
		lapan.add(nlid);lapan.add(nlpwd);
		lapan.add(nlpwde);lapan.add(nlname);
		lapan.add(nlemail);
		lapan.add(nlemail1);
	

		mpan.add("Center",txtpan);
		
		
		txtpan.add(number);
		number.add("West",number_01);
		number_01.add(ntnumber);
		dao.numb(a);
		ntnumber.setText(Integer.toString(a.getNo()));//일련번호 증가 HOW?
		txtpan.add(idfield);
		idfield.add("West",idfield_01);
		idfield_01.add(ntid);	
		txtpan.add(txtflow01);
		txtflow01.add("West",txtflow01_01);
		txtflow01_01.add(ntpwd);
		txtpan.add(txtflow02);
		txtflow02.add("West",txtflow02_01);
		txtflow02_01.add(ntpwde);txtflow02_01.add(overbtn);
		txtpan.add(txtflow03);
		txtflow03.add("West",txtflow03_01);
		txtflow03_01.add(ntname);
		txtpan.add(emailtxtpan);
		emailtxtpan.add("West",emailtxtpan_01);
		txtpan.add(emailtxtpan1);
		emailtxtpan1.add("West",emailtxtpan1_01);
		emailtxtpan_01.add(ntemail);
		emailtxtpan1_01.add(ntemail02);emailtxtpan1_01.add(overbtn1);
		//emailtxtpan_01.add(emailcom);


		intopanlow.add("South",btncom);
		btncom.add(registerB);btncom.add(calbtn);
		add(backpan01);

		overbtn.addActionListener(this);
		overbtn1.addActionListener(this);
		registerB.addActionListener(this);
		calbtn.addActionListener(this);
		//패널색칠
		backpan01.setBackground(Color.WHITE);
		intopanlow.setBackground(Color.WHITE);
		mpan.setBackground(Color.WHITE);
		lapan.setBackground(Color.WHITE);
		txtpan.setBackground(Color.WHITE);
		btncom.setBackground(Color.WHITE);

		number.setBackground(Color.WHITE);
		number_01.setBackground(Color.WHITE);

		idfield.setBackground(Color.WHITE);
		idfield_01.setBackground(Color.WHITE);
		txtflow01.setBackground(Color.WHITE);
		txtflow01_01.setBackground(Color.WHITE);
		txtflow02.setBackground(Color.WHITE);
		txtflow02_01.setBackground(Color.WHITE);
		txtflow03.setBackground(Color.WHITE);
		txtflow03_01.setBackground(Color.WHITE);
		emailtxtpan.setBackground(Color.WHITE);
		emailtxtpan_01.setBackground(Color.WHITE);
		emailtxtpan1.setBackground(Color.WHITE);
		emailtxtpan1_01.setBackground(Color.WHITE);
		
		nlnumber.setBackground(Color.WHITE);
		nlid.setBackground(Color.WHITE);
		nlpwd.setBackground(Color.WHITE);
		nlpwde.setBackground(Color.WHITE);
		nlname.setBackground(Color.WHITE);
		nlemail.setBackground(Color.WHITE);
		nlemail1.setBackground(Color.WHITE);
		
		ntnumber.setBackground(Color.WHITE);
		ntid.setBackground(Color.WHITE);
		ntpwd.setBackground(Color.WHITE);
		ntpwde.setBackground(Color.WHITE);
		ntname.setBackground(Color.WHITE);
		ntemail.setBackground(Color.WHITE);
		ntemail02.setBackground(Color.WHITE);
		
		registerB.setBackground(Color.WHITE);
		calbtn.setBackground(Color.WHITE);
		overbtn.setBackground(Color.WHITE);
		overbtn1.setBackground(Color.WHITE);
		registerB.setBackground(c1);registerB.setFont(f1);registerB.setForeground(Color.white);registerB.setBorderPainted(false);registerB.setFocusPainted(false);
		overbtn.setBackground(c1);overbtn.setFont(f1);overbtn.setForeground(Color.white);overbtn.setBorderPainted(false);overbtn.setFocusPainted(false);
		overbtn1.setBackground(c1);overbtn1.setFont(f1);overbtn1.setForeground(Color.white);overbtn1.setBorderPainted(false);overbtn1.setFocusPainted(false);
		calbtn.setBackground(c1);calbtn.setFont(f1);calbtn.setForeground(Color.white);calbtn.setBorderPainted(false);calbtn.setFocusPainted(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//사진 넣기
		if(e.getSource() == overbtn){
			try {
				jf = new JFrame();
				jf.setSize(300, 300);
				jf.setDefaultCloseOperation(jf.DISPOSE_ON_CLOSE);

				fd1 = new FileDialog(jf, "사진을 선택하시오.", FileDialog.LOAD);
				fd1.setVisible(true);
				String fileDir = fd1.getDirectory(); // 선택한 파일 경로가져옴
				String fileName = fd1.getFile(); // 파일이름,확장자

				//File file = new File(fileDir + fileName);
				//fis = new FileInputStream(file);
				
				//picName = fileDir + fileName;
				//picLength = (int) file.length();

				ntpwde.setText(fileDir + fileName);
				//a.setPicName(fileDir + fileName);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		//음악 넣기
		if(e.getSource() == overbtn1){
			try {
				jf = new JFrame();
				jf.setSize(300, 300);
				jf.setDefaultCloseOperation(jf.DISPOSE_ON_CLOSE);

				fd1 = new FileDialog(jf, "음악을 선택하시오.", FileDialog.LOAD);
				fd1.setVisible(true);
				String fileDir = fd1.getDirectory(); // 선택한 파일 경로가져옴
				String fileName = fd1.getFile(); // 파일이름,확장자

				//File file = new File(fileDir + fileName);
				//musicFis = new FileInputStream(file);
				//musicName = fileDir + fileName;
				
				//musicLength = (int) file.length();

				ntemail02.setText(fileDir + fileName);
				//a.setMusicName(fileDir + fileName);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}


		//저장
		if(e.getSource() == registerB){

			//String num = ntnumber.getText().toString();
			a.setAlName(ntid.getText().trim());
			a.setDate(ntpwd.getText().trim());
			a.setSinger(ntname.getText().trim());
			a.setMusicName(ntemail.getText().trim());//
			a.setPicFile(ntpwde.getText().trim());
			a.setMusicFile(ntemail02.getText().trim());//
			if(dao.insertMusic(a)>0) {
				messageBox(this,"성공하였습니다.");
				//reset();
				dispose();//다이얼로그 박스를 닫는다.
			}else {
				messageBox(this,"다시입력해주세요.");
			}
			
			//dbc.insertMusic(name, date, singer, picName, musicName);
			//SuccessDialog sd = new SuccessDialog();
			//Dialog dl = new Dialog(sd);
			//if(dbc.insertMusic(a)>0) {
			//messageBox(this,ntid.getText()+"아이디 님 가입을 환영합니다!");
			//reset();dispose();//다이얼로그 박스를 닫는다.
		}
		//ntid.setText("");
		//ntpwd.setText("");
		//ntpwde.setText("");
		//ntname.setText("");
		//ntemail.setText("");
		//ntemail02.setText("");
		if(e.getSource()==calbtn) {
			dispose();
		}
	}
	

	public static void messageBox(Object obj,String m) {
		JOptionPane.showMessageDialog((Component)obj,m);
	}
	public static void main(String[] args) {
		new Insert();
	}
}


