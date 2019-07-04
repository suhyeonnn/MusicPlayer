package com.melon.admin;

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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import com.melon.persistence.AlbumDAOImpl;
import com.melon.persistence.MemberDAOImpl;

public class IsMain extends JFrame implements ActionListener {

	//패널 

	private JPanel mainpan=new JPanel(new BorderLayout());


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


	private int chkCount = 0; // 체크박스를 선택한 개수를 담을 변수

	private JCheckBox[] chk; // 체크박스 
	private int[] ids;
	private static LinkedList<String> chkIDs = new LinkedList<>();

	MemberDAOImpl mdao=new MemberDAOImpl();
	AlbumDAOImpl adao=new AlbumDAOImpl();
	
	   Font f1=new Font("나눔바른펜",Font.PLAIN,15);
	   Font f2=new Font("나눔바른펜",Font.PLAIN,30);
	   Font f3=new Font("나눔바른펜",Font.PLAIN,12);

	   Color c1=new Color(0,205,60);

	public IsMain() {

		setTitle("메인앨범 편집");//조상클래스 생성자 호출해서
		//프레임 윈도우 제목설정
		//라벨,텍스트필드,버튼 객체 생성
		//label_id=new JLabel("아이디",Label.LEFT);
		//label_pwd=new JLabel("비밀번호",Label.LEFT);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {

			}



		});//내부 무명클래스 이벤트 처리*/}//기본생성자
		JPanel nor=new JPanel();
		JPanel cen=new JPanel();
		JPanel sou=new JPanel();
		nor.setBackground(Color.WHITE);
		cen.setBackground(Color.WHITE);
		sou.setBackground(Color.WHITE);
		JButton chan=new JButton("변경");
		chan.setBackground(c1);chan.setFont(f1);chan.setForeground(Color.white);chan.setBorderPainted(false);chan.setFocusPainted(false);

		JScrollPane bigbig=new JScrollPane(cen,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		ImageIcon icon=new ImageIcon("./img/DoMain/melon.png");///
		setIconImage(icon.getImage());//상단바 아이콘///

		text_id=new JTextField(15);      
		text_id.setFont(f1);
		JButton alSearch=new JButton("검색");
		alSearch.setBackground(c1);alSearch.setFont(f1);alSearch.setForeground(Color.white);alSearch.setBorderPainted(false);alSearch.setFocusPainted(false);

		List<String> listal=adao.findAlbum();
		//adao.callEditMain();

		chk = new JCheckBox[adao.findAlbum().size()];
		cen.setLayout(new GridLayout(0, 2));
		for(int i=0;i<adao.findAlbum().size();i++) {
			chk[i] = new JCheckBox(adao.findAlbum().get(i));
			cen.add(chk[i]);
			chk[i].setBackground(Color.WHITE);
			String id = adao.findAlbum().get(i);
			
			/*
			//chk[i].getText()=adao.callEditMain();
			String alnames=adao.callEditMain();
			List<String> listMain = new ArrayList<String>();
			String after=alnames.substring(1, alnames.length()-1).trim();
			StringTokenizer tokens=new StringTokenizer(after.trim(),",");
			while(tokens.hasMoreTokens()) {
				String a;
				a=tokens.nextToken().trim();
				listMain.add(a);
			}
			if(listMain.contains(chk[i].getText())) {
				chk[i].setSelected(true);
			}*/
			
			
			chk[i].setFont(f1);
			chk[i].addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					if(e.getStateChange() == e.SELECTED){
						chkCount++;
						chkIDs.add(id);
						//System.out.println(chkIDs);

					}else if(e.getStateChange() == e.DESELECTED){
						chkCount--;
						chkIDs.remove(id);
						//System.out.println(chkIDs);
					}                    

				}
			});
		}
		

		nor.setLayout(new FlowLayout());
		nor.add(text_id);
		nor.add(alSearch);
		sou.add(chan);
		mainpan.add(nor,"North");
		mainpan.add(bigbig,"Center");
		mainpan.add(sou,"South");

		add(mainpan);
		setSize(500,400);
		setVisible(true);
		setResizable(false);//프레임윈도우 크기조절불가
		chan.addActionListener(this);

		//프레임 윈도우 창 정중앙 배치
		Dimension tsc=Toolkit.getDefaultToolkit().getScreenSize();//화면크기
		Dimension mySize=getSize();//띄우는 창 크기
		setLocation(tsc.width/2-mySize.width/2,
				tsc.height/2-mySize.height/2);
	}

	public static void main(String[] args) {
		new IsMain();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(chkCount>6||chkCount<6) {
			messageBox(this,"메인에 띄울 앨범 6개를 선택해주세요");
		}else {
			adao.editMain(chkIDs);
			if(adao.editMain(chkIDs)==0) {
				messageBox(this,"다시 시도해주세요");
			}else {
			messageBox(this,"편집되었습니다.");
			
			dispose();
			}
		}
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
}//UILoginFrame class
