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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import com.melon.domain.MemberVO;
import com.melon.persistence.MemberDAOImpl;

public class Login extends JFrame implements ActionListener,MouseListener,
FocusListener{

	//패널 
	private JPanel pan01=new JPanel(new BorderLayout(10,10));
	//수평,수직간격이 10인 보더레이아웃 패널 생성
	private JPanel mainpan=new JPanel(new BorderLayout());
	private JPanel pan03=new JPanel(new BorderLayout());
	private JPanel pan02=new JPanel(new BorderLayout());
	private JPanel pan05=new JPanel(new BorderLayout());
	private JPanel pan_login=new JPanel(new GridLayout(2,1));
	//2행 *2열의 그리드 레이아웃 패널
	private JPanel pan04=new JPanel(new GridLayout(1,2));
	//1행 *2열의 그리드 레이아웃 패널

	//라벨
	//private JLabel label_id;
	//private JLabel label_pwd;

	//텍스트 필드
	private static JTextField text_id;
	private JPasswordField text_pwd;
	//JPasswordField는 입력값을 암호화
	char passwordChar;

	//버튼
	private JButton login_btn;//로그인 버튼
	private JButton newuesr_btn;//회원가입
	private JButton infouesr_btn; //아이디.비밀번호찾기

	//회원가입 
	NewUesr_UI nuui;
	//SearchDao ID; 

	MemberDAOImpl mdao=new MemberDAOImpl();
	Font f1=new Font("나눔바른펜",Font.PLAIN,15);

	public Login() {
		setTitle("Login form");//조상클래스 생성자 호출해서
		//프레임 윈도우 제목설정
		//라벨,텍스트필드,버튼 객체 생성
		//label_id=new JLabel("아이디",Label.LEFT);
		//label_pwd=new JLabel("비밀번호",Label.LEFT);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {

				new Main(null);
			}



		});//내부 무명클래스 이벤트 처리*/}//기본생성자



		text_id=new JTextField("아이디를 입력하세요");      
		text_id.setFont(f1);
		text_pwd=new JPasswordField();
		passwordChar=text_pwd.getEchoChar();
		text_pwd.setEchoChar((char)0);
		text_pwd.setText("비번을 비밀리에 입력하세요");   
		text_pwd.setFont(f1);

		text_id.addMouseListener(this); //마우스이벤트 등록
		text_id.addFocusListener(this);
		text_pwd.addMouseListener(this);//마우스이벤트 등록
		text_pwd.addFocusListener(this);//포커스 이벤트 등록

		login_btn=new JButton("로그인");
		login_btn.setForeground(Color.white);
		login_btn.setFont(f1);
		login_btn.setBorderPainted(false);
		login_btn.setBackground(new Color(0,205,60));
		newuesr_btn=new JButton("회 원 가 입");
		infouesr_btn=new JButton("아이디/비밀번호 찾기");
		newuesr_btn.setBorderPainted(false);
		infouesr_btn.setBorderPainted(false);
		newuesr_btn.setContentAreaFilled(false);
		infouesr_btn.setContentAreaFilled(false);
		infouesr_btn.setFont(f1);
		newuesr_btn.setFont(f1);
		newuesr_btn.setForeground(Color.gray);
		infouesr_btn.setForeground(Color.gray);
		infouesr_btn.setFocusPainted(false);
		newuesr_btn.setFocusPainted(false);
		infouesr_btn.addActionListener(this);
		pan01.setBackground(Color.white); //버튼 색갈바꾸기.
		pan05.setBackground(Color.white); //버튼 색갈바꾸기.
		eventSet();//3개 버튼 이벤트 등록
		panelSet();//패널초기화와 프레임에 패널추가
		setSize(340,240);
		setVisible(true);
		setResizable(false);//프레임윈도우 크기조절불가
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setDefaultCloseOperation(Login.DISPOSE_ON_CLOSE);
		ImageIcon icon1=new ImageIcon("./img/DoMain/melon.png");///
		setIconImage(icon1.getImage());//상단바 아이콘///
		//프레임 윈도우 창 정중앙 배치
		Dimension tsc=Toolkit.getDefaultToolkit().getScreenSize();//화면크기
		Dimension mySize=getSize();//띄우는 창 크기
		setLocation(tsc.width/2-mySize.width/2,
				tsc.height/2-mySize.height/2);

		//텍스트 필드 엔터키 이벤트
		text_id.registerKeyboardAction(this,"login",KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),JComponent.WHEN_FOCUSED);
		//아이디 입력박스 엔터키 VK_ENTER상수는 엔터키
		text_pwd.registerKeyboardAction(this,"login",KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),JComponent.WHEN_FOCUSED);

	}
	//텍스트 필드 초기화
	public void reset() {
		text_id.setText("");
		text_pwd.setText("");
	}//reset()

	//아이디값 반환메서드
	public static String id_get(String e) {
		String getid=text_id.getText().trim();//trim()은
		//양쪽공백제거
		return getid;   
	}//id_get()

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == newuesr_btn) {//회원가입버튼
			nuui=new NewUesr_UI();
		}else if(e.getSource()== infouesr_btn ){//아이디찾기
			new SearchService();
		}else if(e.getActionCommand()=="login"|| e.getSource() == login_btn) {
			String id=text_id.getText().trim();//로그인 아이디
			String pwd=text_pwd.getText().trim();//로그인 비번

			if(id.equals("")) {
				messageBox(this,"아이디를 입력하세요!");
				text_id.setText("");//아이디 입력박스 초기화
				text_id.requestFocus();
			}else if(mdao.loginCheck(id)== null) {//아이디 인증
				messageBox(this,"등록되지 않은 아이디입니다.!");                                      /***/
				reset();
				text_id.requestFocus();
			}else if(mdao.loginCheck(id) != null) {
				MemberVO db_id=mdao.loginCheck(id);
				//아이디에 해당하는 회원정보를 오라클로 부터 가져온다.

				if(!db_id.getM_pwd().equals(pwd)) {
					messageBox(this,"비밀번호가 일치하지 않습니다!");
					text_pwd.setText("");
					text_pwd.requestFocus();
				}else {//비번이 일치하는경우
					messageBox(this,id+"님 환영합니다.");
					dispose();//로그인 창 닫기
					new Main(id);
				}
			}
		}
	}//이벤트 발생했을때 호출

	//이벤트 등록메서드
	public void eventSet() {
		login_btn.addActionListener(this);
		newuesr_btn.addActionListener(this);
		login_btn.setActionCommand("login");
		//해당 버튼에 부여할 액션명령에 대한 명칭을 부여한다.
	}//eventSet()

	//패널정리
	public void panelSet() {
		pan01.add("North",new JLabel());
		pan01.add("South",new JLabel());
		pan01.add("East",new JLabel());
		pan01.add("West",new JLabel());//패널 바깥 상하좌우 여백설정
		pan01.add("Center",mainpan);

		mainpan.add("Center",pan03);

		pan03.add("North",pan05);
		pan05.add(new JLabel(new ImageIcon("./img/DoMain/logo.png")));

		pan03.add("Center",pan02);
		pan02.add("Center",pan_login);
		pan02.add("East",login_btn);
		//pan_login.add(label_id);
		//label_id.setFont(new Font("궁서체",Font.BOLD,14));
		//pan_login.add(new JLabel());

		pan_login.add(text_id);
		//text_id.requestFocus();//아이디 입력박스로 커서이동
		//pan_login.add(label_pwd);
		//label_pwd.setFont(new Font("굴림",Font.BOLD,14));
		//pan_login.add(new JLabel());
		pan_login.add(text_pwd);

		pan03.add("South",pan04);
		pan04.add(newuesr_btn);
		pan04.add(infouesr_btn);
		pan04.setBackground(Color.white);

		add(pan01);//프레임에 패널추가
	}//panelSet()

	//메시지 박스
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
	public void mouseClicked(MouseEvent e) {}   //마우스가 컴포넌트위에 올라갈 때
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}//마우스가벗어났을때
	@Override
	public void mousePressed(MouseEvent e) {// 마우스가 눌럿을때 
		if(e.getSource().equals(text_id)) {
			text_id.setText("");

		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		//마우스 를 띠었을대
	}
	@Override   public void focusGained(FocusEvent e) {//포커스를 가졌을때      
		if(e.getSource().equals(text_pwd)) {
			text_pwd.setText("");   
			text_pwd.setEchoChar(passwordChar);
		}
	}
	@Override
	public void focusLost(FocusEvent e) { //포커스를 잃어버렸을때
		if(e.getSource() == text_id) { // 이벤트가 발생한 객체 확인
			if(text_id.getText().trim().equals("")) {
				text_id.setText("아이디입력");

			}
		}
		else if(text_pwd.getText().trim().length() == 0) {
			text_pwd.setText("비번을 비밀리에입력하세요");
			text_pwd.setEchoChar((char)0);

		}
	}
}//UILoginFrame class
