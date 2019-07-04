package com.melon.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class IdFind extends JPanel implements ActionListener {
	   JPanel idpan01 =new JPanel(new BorderLayout());
	   //JPanel idpan02 =new JPanel(new BorderLayout());
	   JPanel idpan03 =new JPanel(new FlowLayout());
	   //JPanel pan04 =new JPanel(new FlowLayout());

	   JLabel label_name =new JLabel("이  름");
	   JLabel label_email=new JLabel("이메일");

	   JTextField txname=new JTextField(17);
	   JTextField txemail=new JTextField(17);

	   JButton findbtn =new JButton("찾  기");
	   JButton canbtn=new JButton("취  소");
	   JDialog jd;
	   Font f1=new Font("나눔바른펜",Font.PLAIN,15);
	   MemberDAOImpl daoM=new MemberDAOImpl();

	   public IdFind(JDialog jd) {
	      findbtn.setFont(f1);findbtn.setBackground(Color.WHITE);findbtn.setFocusPainted(false);
	      canbtn.setFont(f1);canbtn.setBackground(Color.WHITE);canbtn.setFocusPainted(false);
	      txemail.setFont(f1);
	      txname.setFont(f1);
	      label_email.setFont(f1);
	      label_name.setFont(f1);

	      this.jd=jd;

	      setLayout(new BorderLayout());
	      //setSize(299,199);
	      //setVisible(true);

	      eventSet2();
	      panelSet2();
	      idpan03.setBackground(Color.white);
	   }//생성자

	   public void panelSet2() {
	      add("North",new JLabel());
	      add("South",new JLabel());
	      add("East",new JLabel());
	      add("West",new JLabel());
	      add("Center",idpan01);


	      idpan01.add("Center",idpan03);
	      idpan03.add(label_name);
	      idpan03.add(txname);
	      idpan03.add(label_email);
	      idpan03.add(txemail);   
	      idpan03.add(findbtn);
	      idpan03.add(canbtn);
	   }

	   public void eventSet2() {
	      findbtn.addActionListener(this);
	      canbtn.addActionListener(this);
	   }

	   @Override
	   public void actionPerformed(ActionEvent e) {
	      if(e.getSource()==findbtn) {//찾기
	         String nameget=txname.getText().trim();
	         String emailget=txemail.getText().trim();
	         //daoM.getId(idget, emailget)
	         SecondEmail send=new SecondEmail();
	         if(nameget.equals("")) {
	            messageBox(this,"이름을 입력하세요.");
	            txname.setText("");//이름 입력박스 초기화
	            txname.requestFocus();
	         }else if(daoM.nameCheck(nameget)){
	            messageBox(this,"등록되지 않은 이름입니다.!");                                      /***/
	            reset();
	            txname.requestFocus();
	         }else {
	            if(emailget.equals("")){
	               messageBox(this,"이메일을 입력하세요.");
	               txemail.setText("");//이메일 입력박스 초기화
	               txemail.requestFocus();
	            }
	            else if(daoM.getId(nameget, emailget)==null) {
	               messageBox(this, "이메일이 일치하지않습니다.");
	               txemail.setText("");//이메일 입력박스 초기화
	               txemail.requestFocus();
	            }else {
	               messageBox(this, "성공하였습니다.");
	               send.Name_Email(nameget, emailget);
	               reset();
	            }
	         }


	      }
	      if(e.getSource()==canbtn) {//닫기
	         jd.dispose();
	      }
	   }
	   public void reset() {
	      txname.setText("");
	      txemail.setText("");
	   }//reset()

	   public static void messageBox(Object obj, String m) {
	      UIManager.put("OptionPane.background", Color.white);
	      UIManager.put("Panel.background", Color.white);
	      UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
	            "나눔바른펜", Font.BOLD, 18)));  
	      UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font(  
	            "나눔바른펜", Font.BOLD, 13))); 
	      JOptionPane.showMessageDialog((Component) obj, m);

	   }


	}