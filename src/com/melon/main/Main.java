package com.melon.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import com.melon.admin.AdminLogin;
import com.melon.admin.Manage;
import com.melon.domain.AlbumVO;
import com.melon.domain.MemberVO;
import com.melon.others.MainAlbum;
import com.melon.others.MainChart;
import com.melon.others.MainCont;
import com.melon.others.MainMyMusic;
import com.melon.others.SearchResult;
import com.melon.persistence.AlbumDAOImpl;
import com.melon.persistence.MemberDAOImpl;

public class Main extends JFrame implements ActionListener,MouseListener, FocusListener {
	Container con;
	JPanel headMain,upMain,midMain,downMain,big;
	JPanel midleMain=new JPanel();
	JPanel adP=new JPanel();
	JLabel buyingL,myPageL,logoL,chart,album,myMusic,adL;
	static JLabel guidL=new JLabel();
	static public JTextField searchT;
	JButton searchB;
	MainCont cont; 
	ImageIcon logo,serB;
	MainChart mc;
	MainMyMusic mm;
	MainAlbum ma;
	SearchResult sr=new SearchResult();
	static public JComboBox cb;
	public static String id=null;
	static JLabel logL=new JLabel();


	private AlbumDAOImpl daoA=new AlbumDAOImpl(); 
	static MemberDAOImpl daoM=new MemberDAOImpl();
	static MemberVO dataM=new MemberVO();
	private ArrayList<AlbumVO> dataA;



	static double screenWid=Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	static double Mainwid=screenWid/2;
	static double screenHei=Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	static double MianHei=screenHei*2/3;
	Font f1=new Font("나눔바른펜",Font.PLAIN,15);
	Font f2=new Font("나눔바른펜",Font.PLAIN,30);
	static Font f3=new Font("나눔바른펜",Font.PLAIN,12);

	public Main(String id) {//Title,Close,Size(Resizable),상단바아이콘,창위치,Layout / 마지막에 Visible
		this.id=id;      


		//프레임 기본 설정
		setTitle("메인창");
		setDefaultCloseOperation(Main.DISPOSE_ON_CLOSE);
		setSize((int)Mainwid+265,(int)MianHei+150);
		//setSize(1000,1000);
		//setResizable(false);
		ImageIcon icon=new ImageIcon("./img/DoMain/melon.png");///
		setIconImage(icon.getImage());//상단바 아이콘///
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screenSize=kit.getScreenSize();
		setLocation(screenSize.width/5,screenSize.height/8);
		con=getContentPane();
		con.setLayout(new BoxLayout(con,BoxLayout.Y_AXIS));//전체화면 레이아웃

		big=new JPanel();
		//big.setLayout(new BoxLayout(big,BoxLayout.Y_AXIS));
		big.setLayout(new BorderLayout());
		//big 전체화면 패널-(headMain)과 (cont|mm|mc|ma) 2개패널

		//HeadMain안에 upMain,midMain,downMain

		//upMain만들기//객체생성,사이즈,색깔,레이아웃,추가항목
		upMain=new JPanel();
		upMain.setSize((int)Main.Mainwid,(int)Main.MianHei/10);
		upMain.setBackground(Color.WHITE);
		upMain.setLayout(new FlowLayout(FlowLayout.RIGHT,20,0));

		guidL.setText(" ");
		logL.setText("로그인");

		dataM = daoM.findinfo(Main.id);
		if(id==null) {
			guidL.setText(" ");
		}else if(id=="admin"){
			guidL.setText("관리자님 반갑습니다.");
			guidL.setFont(f3);
		} else {
			guidL.setText(dataM.getM_id()+"님 "+dataM.getM_left()+"일 남았습니다.");
			guidL.setFont(f3);
		}


		if(id==null) {
			logL.setText("로그인");
			logL.setFont(f1);
		}else {
			logL.setText("로그아웃");
			logL.setFont(f1);
		}

		buyingL=new JLabel("이용권구매");
		myPageL=new JLabel("마이페이지");
		buyingL.setFont(f1);
		myPageL.setFont(f1);
		logL.addMouseListener(this);
		buyingL.addMouseListener(this);
		myPageL.addMouseListener(this);
		upMain.add(guidL);
		upMain.add(logL);
		upMain.add(buyingL);
		upMain.add(myPageL);

		//midMain만들기
		midMain=new JPanel();
		midMain.setSize((int)Main.Mainwid,(int)Main.MianHei/10);
		midMain.setBackground(Color.WHITE);
		midMain.setLayout(new FlowLayout());
		logo=new ImageIcon("./img/DoMain/logo.png");
		logoL=new JLabel();
		String[] comboName = { "가수", "앨범명", "노래제목" };
		cb = new JComboBox(comboName);
		cb.setBackground(Color.WHITE);
		cb.setFocusable(false);
		cb.setFont(f1);
		searchT=new JTextField(25);
		searchT.setText("검색어를 입력하세요");
		searchT.setFont(f1);
		searchT.addFocusListener(this);
		serB=new ImageIcon("./img/DoMain/ser.png");
		searchB=new JButton();
		searchB.setBackground(Color.WHITE);
		searchB.setOpaque(true);
		searchB.setIcon(serB);
		logoL.setIcon(logo);
		logoL.addMouseListener(this);
		midMain.add(logoL);
		midMain.add(cb);
		midMain.add(searchT);
		midMain.add(searchB);
		searchB.addActionListener(this);

		//downMain만들기

		downMain=new JPanel();
		downMain.setSize((int)Main.Mainwid,(int)Main.MianHei/10);
		downMain.setBackground(Color.WHITE);
		downMain.setLayout(new FlowLayout(FlowLayout.CENTER,50,0));
		chart=new JLabel("멜론차트");
		album=new JLabel("멜론앨범");
		myMusic=new JLabel("마이뮤직");
		chart.setFont(f2);
		album.setFont(f2);
		myMusic.setFont(f2);
		chart.addMouseListener(this);
		album.addMouseListener(this);
		myMusic.addMouseListener(this);
		downMain.add(chart);
		downMain.add(album);
		downMain.add(myMusic);

		//headMain에 up,mid,down추가
		headMain=new JPanel();
		headMain.setLayout(new BoxLayout(headMain, BoxLayout.Y_AXIS));
		headMain.add(upMain);
		headMain.add(midMain);
		headMain.add(downMain);
		//headMain.setVisible(true);
		//전체패널 big에 headMain추가
		big.add(headMain,"North");

		//중간 내용패널
		//big.removeAll();
		big.add(new MainCont(),"Center");
		big.revalidate();
		big.repaint();


		//sr.setVisible(false);

		adL=new JLabel("관리자로그인");
		adL.setFont(f1);
		adP.setBackground(Color.white);
		adP.add(adL);
		adL.addMouseListener(this);
		big.add(adP,"South");
		if(id==null) {

		}else {
			if(id.equals("admin")) {
				adL.setText("관리자창");
				adL.setFont(f1);
			}else {

			}
		}

		//전체화면에 전체패널big추가
		con.add(big);
		setVisible(true);

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == searchB) {// 검색
			if (searchT.getText().trim().equals("")) {
				messageBox(this, "검색어 입력");            
				//searchT.requestFocus();
			}else{//검색어를 입력한 경우                  
				big.removeAll();
				big.add(headMain,"North");
				big.add(new SearchResult(searchT.getText()),"Center");
				big.revalidate();
				big.repaint();
			}
		}

	}//actionPerformed()

	@Override
	public void mouseClicked(MouseEvent e) {


		if(e.getSource()==logL) {
			if(id==null) {
				new Login();
				dispose();
			}else{
				id=null;
				messageBox(this, "로그아웃되었습니다");
				logL.setText("로그인");
			}

		}
		if(e.getSource()==buyingL) {
			if(id==null) {
				messageBox(this, "로그인 해주세요");
			}else {
				new PayPage();

			}

		}
		if(e.getSource()==myPageL) {//로그인 여부에 따라 창 분할
			if(id==null) {
				messageBox(this, "로그인 해주세요");
			}else if(id=="admin"){
				messageBox(this, "관리자 전용창입니다");
			} else{
				new MyPage();
			}
		}
		if(e.getSource()==adL) {
			if(id==null) {
				dispose();
				new AdminLogin();
			}else {
				if(id.equals("admin")) {
					dispose();
					new Manage();
				}else {
					dispose();
					new AdminLogin();
				}
			}
		}
		if(e.getSource()==chart) {
			big.removeAll();
			big.add(headMain,"North");
			big.add(new MainChart(),"Center");
			big.revalidate();
			big.repaint();
		}
		if(e.getSource()==album) {
			big.removeAll();
			big.add(headMain,"North");
			big.add(new MainAlbum(),"Center");
			big.revalidate();
			big.repaint();
		}
		if(e.getSource()==myMusic) {
			big.removeAll();
			big.add(headMain,"North");
			big.add(new MainMyMusic(Main.id),"Center");
			big.revalidate();
			big.repaint();
		}
		if(e.getSource()==logoL) {
			big.removeAll();
			big.add(headMain,"North");
			big.add(new MainCont(),"Center");
			adP.add(adL);
			adL.addMouseListener(this);
			big.add(adP,"South");
			big.revalidate();
			big.repaint();
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {      
	}
	@Override
	public void mousePressed(MouseEvent e) {      
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	// 메세지 박스
	public static void messageBox(Object obj, String m) {
		UIManager.put("OptionPane.background", Color.white);
		UIManager.put("Panel.background", Color.white);
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
				"나눔바른펜", Font.BOLD, 18)));  
		UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font(  
				"나눔바른펜", Font.BOLD, 13))); 
		JOptionPane.showMessageDialog((Component) obj, m);
	}
	@Override
	public void focusGained(FocusEvent e) {
		if(e.getSource()==searchT) {
			searchT.setText("");
		}
	}
	@Override
	public void focusLost(FocusEvent e) {
		if(e.getSource()==searchT) {
		}
	}
	public static void updateday(String id) {
		dataM = daoM.findinfo(id);
		if(id==null) {
			guidL.setText(" ");
		}else if(id=="admin"){
			guidL.setText("관리자님 반갑습니다.");
			guidL.setFont(f3);
		}else {
			guidL.setText(dataM.getM_id()+"님 "+dataM.getM_left()+"일 남았습니다.");
		}
	}
	public static void main(String[] args) {
		new Main(null);
	}
}