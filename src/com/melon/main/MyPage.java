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
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.melon.admin.ControlMember_UI;
import com.melon.domain.MemberVO;
import com.melon.persistence.MemberDAOImpl;

public class MyPage extends JFrame implements ActionListener{
	JPanel backpan01 = new JPanel(new BorderLayout(10, 10));
	JPanel intopanlow = new JPanel(new BorderLayout());
	JPanel mpan = new JPanel(new BorderLayout());
	JPanel lapan = new JPanel(new GridLayout(6, 1));
	JPanel txtpan = new JPanel(new GridLayout(6, 1));
	JPanel btncom = new JPanel(new FlowLayout());
	JPanel txtflow01 = new JPanel(new BorderLayout());
	JPanel txtflow01_01 = new JPanel(new FlowLayout());
	JPanel txtflow02 = new JPanel(new BorderLayout());
	JPanel txtflow02_01 = new JPanel(new FlowLayout());
	JPanel txtflow03 = new JPanel(new BorderLayout());
	JPanel txtflow03_01 = new JPanel(new FlowLayout());
	JPanel emailtxtpan = new JPanel(new BorderLayout());
	JPanel emailtxtpan_01 = new JPanel(new FlowLayout());
	JPanel phonetxtpan = new JPanel(new BorderLayout());
	JPanel phonetxtpan_01 = new JPanel(new FlowLayout());
	JPanel datetxtpan_01 = new JPanel(new FlowLayout());
	JPanel datetxtpan = new JPanel(new BorderLayout());

	JLabel nlpwd = new JLabel("새 비밀번호");
	JLabel nlpwde = new JLabel("비밀번호 확인");
	JLabel nlname = new JLabel("이름");
	JLabel nlemail = new JLabel("이메일");
	JLabel nlphone = new JLabel("핸드폰번호");
	JLabel nldate = new JLabel("아이디생성일");

	JPasswordField ntpwd = new JPasswordField(17);
	JPasswordField ntpwde = new JPasswordField(17);
	JTextField ntname;
	JTextField ntemail;
	JTextField ntphone2,ntphone3;
	JTextField ntdate;

	JButton newintobtn2 = new JButton("수정");
	JButton calbtn = new JButton("취소");

	MemberVO m1 = new MemberVO();
	MemberVO m2 = new MemberVO();
	MemberDAOImpl dao = new MemberDAOImpl();
	ControlMember_UI conMem;
	String[] phoneName= {"010","011","016","019"};
	private JComboBox phonecom= new JComboBox(phoneName);
	Font f1=new Font("나눔바른펜",Font.PLAIN,15);
	public MyPage() {

		m1=dao.findinfo(Main.id);
		ntname = new JTextField(m1.getM_name(),17);
		ntemail = new JTextField(m1.getM_email(),17);
		ntphone2 = new JTextField(m1.getM_phone().substring(4,8),4);
		ntphone3 = new JTextField(m1.getM_phone().substring(9),4);
		ntdate = new JTextField(m1.getM_date(),17);
		ntdate.disable();
		ntemail.disable();
		phonecom.setBackground(Color.WHITE);
		phonecom.setFocusable(false);
		ntname.setFont(f1);
		ntemail.setFont(f1);
		ntphone2.setFont(f1);
		ntphone3.setFont(f1);
		ntdate.setFont(f1);
		phonecom.setFont(f1);
		nlpwd.setFont(f1);
		nlpwde.setFont(f1);
		nlname.setFont(f1);
		nlemail.setFont(f1);
		nlphone.setFont(f1);
		nldate.setFont(f1);

		setTitle(m1.getM_id()+"님의 정보수정");
		Panelnu_Set();
		Eventnu_Set();

		setSize(350, 300);
		setVisible(true);
		setResizable(false);
		Dimension tsc = Toolkit.getDefaultToolkit().getScreenSize();// 화면 사이즈
		Dimension mySize = getSize();// 띄우는 창 사이즈
		setLocation(tsc.width / 2 - mySize.width / 2, tsc.height / 2 - mySize.height / 2);
		ImageIcon icon=new ImageIcon("./img/DoMain/melon.png");///
		setIconImage(icon.getImage());//상단바 아이콘///


	}
	/* private void Reset() {
         ntpwd.setText("");
         ntpwde.setText("");
         ntname.setText("");
         ntemail.setText("");
      }*/
	private void Panelnu_Set() {
		backpan01.add("North", new JLabel());
		backpan01.add("South", new JLabel());
		backpan01.add("East", new JLabel());
		backpan01.add("West", new JLabel());
		backpan01.add("Center", intopanlow);
		intopanlow.add("Center", mpan);
		mpan.add("West", lapan);
		lapan.add(nlpwd);
		lapan.add(nlpwde);
		lapan.add(nlname);
		lapan.add(nlemail);
		lapan.add(nlphone);
		lapan.add(nldate);
		mpan.add("Center", txtpan);
		txtpan.add(txtflow01);
		txtflow01.add("West", txtflow01_01);
		txtflow01_01.add(ntpwd);
		txtpan.add(txtflow02);
		txtflow02.add("West", txtflow02_01);
		txtflow02_01.add(ntpwde);
		txtpan.add(txtflow03);
		txtflow03.add("West", txtflow03_01);
		txtflow03_01.add(ntname);
		txtpan.add(emailtxtpan);
		emailtxtpan.add("West", emailtxtpan_01);
		emailtxtpan_01.add(ntemail);
		txtpan.add(phonetxtpan,"West");
		phonetxtpan.add(phonetxtpan_01,"West");
		phonetxtpan_01.add(phonecom);
		phonetxtpan_01.add(new JLabel("-"));
		phonetxtpan_01.add(ntphone2);
		phonetxtpan_01.add(new JLabel("-"));
		phonetxtpan_01.add(ntphone3);
		txtpan.add(datetxtpan,"West");
		datetxtpan.add(datetxtpan_01,"West");
		datetxtpan_01.add(ntdate);

		intopanlow.add("South", btncom);
		btncom.add(newintobtn2);newintobtn2.setFont(f1);newintobtn2.setBackground(Color.white);
		btncom.add(calbtn);calbtn.setFont(f1);calbtn.setBackground(Color.WHITE);
		add(backpan01);

	}
	private void Eventnu_Set() {
		newintobtn2.addActionListener(this);
		calbtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(newintobtn2)) {
			if ((ntpwd.getText().length() < 6 || ntpwd.getText().length() > 16)
					|| !(Pattern.matches("^[a-z0-9]*$", ntpwd.getText()))) {
				JOptionPane.showMessageDialog(this, "비밀번호는  " + "6~16자리 영문소문자와 숫자로만 입력하세요");
				ntpwd.requestFocus();
				return;
			}
			if (ntpwde.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "비밀번호확인을 입력하세요");
				ntpwde.requestFocus();
				return;
			}
			if (!ntpwd.getText().equals(ntpwde.getText())) {
				JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다");
				ntpwde.setText("");
				ntpwd.setText("");
				ntpwde.requestFocus();
				return;
			}
			if (!(Pattern.matches("^[가-힣]*$", ntname.getText()))) {
				JOptionPane.showMessageDialog(this, "이름은 한글로 입력하세요.");
				ntname.requestFocus();
				return;
			}
			if(!(ntphone2.getText().length()==4)||!(Pattern.matches("^[0-9]*$",ntphone2.getText()))){
				JOptionPane.showMessageDialog(this, "정확한 핸드폰 번호를 입력해주세요!");
				ntphone2.requestFocus();
				ntphone2.setText("");
				return;
			}
			if(!(ntphone3.getText().length()==4)||!(Pattern.matches("^[0-9]*$",ntphone2.getText()))){
				JOptionPane.showMessageDialog(this, "정확한 핸드폰 번호를 입력해주세요!");
				ntphone3.requestFocus();
				ntphone3.setText("");
				return;
			} else {// 모두 입력했다면
				// 저장빈 클래스에 양쪽공백을 제거해서 저장
				m2.setM_id(Main.id);
				m2.setM_name(ntname.getText().trim());
				m2.setM_pwd(ntpwd.getText().trim());
				m2.setM_phone(phonecom.getSelectedItem().toString()+"-"+ntphone2.getText().trim()+"-"+
						ntphone3.getText().trim());
				int re = dao.editOther(m2);
				if (re > 0) {// 회원수정 성공
					messageBox(this, ntname.getText() + " 님의 정보 정상적으로 수정완료");
					dispose();// 다이얼로그 박스를 닫음.

				} else {// 수정 실패후
					messageBox(this, "정보 수정 실패!");
				}
			}
		} else if (e.getSource() == calbtn) {
			dispose();// 가입/정보수정창을 닫음.
		}

	}
	public static void messageBox(Object obj, String m) {
		JOptionPane.showMessageDialog((Component) obj, m);
	}
}