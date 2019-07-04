package com.melon.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import com.melon.action.SecondEmail;
import com.melon.persistence.MemberDAOImpl;

public class SearchService extends JDialog implements ActionListener {
   JPanel pan01 =new JPanel(new BorderLayout());
   JPanel pan02 =new JPanel(new BorderLayout(1,5));
   JPanel pan03 = new JPanel(new GridLayout(1,2));
      
   JButton idBtn=new JButton("아이디찾기");
   JButton pwdBtn=new JButton("비번찾기");
   Color c1=new Color(178,187,187);
   Font f1=new Font("나눔바른펜",Font.PLAIN,15);
   Font f2=new Font("나눔바른펜",Font.PLAIN,30);
   Font f3=new Font("나눔바른펜",Font.PLAIN,12);
   public SearchService() {
   setTitle("아이디/비번찾기");
   setSize(230,170);
   
   setResizable(false);
   setLocationRelativeTo(null);
   setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
   eventSet();
   panelSet();
   
   setVisible(true);

   }//생성자
   
   public void panelSet() {
      ImageIcon icon=new ImageIcon("./img/DoMain/melone.png");
         setIconImage(icon.getImage());//상단바 아이콘
      idBtn.setBackground(Color.white);
      idBtn.setFont(f3);
      pwdBtn.setBackground(c1);
      pwdBtn.setFont(f3);
      pan02.setBackground(Color.white);
      pan01.add("North",new JLabel());//상
      pan01.add("South",new JLabel());//하
      pan01.add("East",new JLabel(" "));//좌
      pan01.add("West",new JLabel(" "));//우 바깥여백 설정
      
      pan01.add("Center",pan02);
      pan02.add("North",pan03);
      pan03.add(idBtn);
      pan03.add(pwdBtn);
      pan02.add("Center",new IdFind(this));
      
      add(pan01);//프레임에 패널추가
      //add(new IdFind());//프레임에 패널추가
   }//패널배치
   public void eventSet(){
      idBtn.addActionListener(this);
      pwdBtn.addActionListener(this);
   }//이벤트 등록
   
   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource()==idBtn) {//아이디창
         //System.out.println("아이디");
         pan02.removeAll();
         pan02.add("North",pan03);
         idBtn.setBackground(Color.white);
         pwdBtn.setBackground(c1);
         pan02.add("Center",new IdFind(this));
         pan02.revalidate();
         pan02.repaint();
      }
      if(e.getSource()==pwdBtn) {
         //System.out.println("비밀번호를찾아야한다");
         pan02.removeAll();
         pan02.add("North",pan03);
         pwdBtn.setBackground(Color.white);
         idBtn.setBackground(c1);
         pan02.add("Center",new IdBtn(this));
         pan02.revalidate();
         pan02.repaint();
      }
      
   }//버튼클릭했을때 호출
   public static void main(String[] args) {
      new SearchService();//생성자호출
   }//메인메소드 
}

