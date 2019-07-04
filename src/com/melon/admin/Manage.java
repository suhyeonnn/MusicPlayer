package com.melon.admin;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.melon.main.Main;

public class Manage extends JFrame implements ActionListener{
	JPanel pan00=new JPanel(new BorderLayout());
	JPanel pan01=new JPanel(new BorderLayout());

	JButton adMusic=new JButton("음악관리");
	JButton adMember=new JButton("회원관리");
	Font f1=new Font("나눔바른펜",Font.PLAIN,15);
	Color c1=new Color(0,205,60);

	public Manage(){   
		setTitle("관리자창 ");
		setSize(280,150);
		setResizable(false);
		setLocationRelativeTo(null);
		//setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		pan00.setBackground(Color.white);
		panelSet(); 
		ImageIcon icon=new ImageIcon("./img/DoMain/melon.png");///
		setIconImage(icon.getImage());//상단바 아이콘///
		setVisible(true);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				new Main("admin");
			}



		});//내부 무명클래스 이벤트 처리*/}//기본생성자

	}
	public void panelSet() {
		pan00.add("North",new JLabel("   "));//상
		pan00.add("South",new JLabel("   "));//하
		pan00.add("East",new JLabel("        "));//좌
		pan00.add("West",new JLabel("        "));//우 바깥여백 설정
		pan00.add("Center",pan01);

		pan01.add("East",adMusic);
		pan01.add("West",adMember); 
		pan01.add("Center",new JLabel(" "));
		pan01.setBackground(Color.white);
		add(pan00);
		//버튼 설정
		adMusic.setBackground(c1);adMusic.setFont(f1);adMusic.setForeground(Color.white);adMusic.setBorderPainted(false);adMusic.setFocusPainted(false);
		adMember.setBackground(c1);adMember.setFont(f1);adMember.setForeground(Color.white);adMember.setBorderPainted(false);adMember.setFocusPainted(false);

		adMusic.addActionListener(this);
		adMember.addActionListener(this);
	}


	public static void main(String[] args) {
		new Manage();
	}//메인


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==adMember) {
			new ControlMember_UI();
		}else if(e.getSource()==adMusic) {
			new AlbumAdmin();
		}
	}//액션


}