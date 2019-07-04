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

public class IdBtn extends JPanel implements ActionListener {
	   Font f1=new Font("나눔바른펜",Font.PLAIN,15);
	   JPanel idpan01 =new JPanel(new BorderLayout());
	   //JPanel idpan02 =new JPanel(new BorderLayout());
	   JPanel idpan03 =new JPanel(new FlowLayout());
	   //JPanel pan04 =new JPanel(new FlowLayout());

	   JLabel label_name =new JLabel("아이디");
	   JLabel label_email=new JLabel("이메일");

	   JTextField txid=new JTextField(17);
	   JTextField txemail=new JTextField(17);

	   JButton findbtn =new JButton("찾  기");
	   JButton canbtn=new JButton("취  소");
	   JDialog jd;
	   public IdBtn(JDialog jd) {
	      this.jd=jd;
	      setLayout(new BorderLayout());

	      eventSet2();
	      panelSet2();
	   }//생성자

	   public void panelSet2() {
	      //폰트설정
	      findbtn.setFont(f1);findbtn.setBackground(Color.WHITE);findbtn.setFocusPainted(false);
	      canbtn.setFont(f1);canbtn.setBackground(Color.WHITE);canbtn.setFocusPainted(false);
	      label_name.setFont(f1);
	      label_email.setFont(f1);
	      txid.setFont(f1);
	      txemail.setFont(f1);


	      add("North",new JLabel());
	      add("South",new JLabel());
	      add("East",new JLabel());
	      add("West",new JLabel());
	      add("Center",idpan01);

	      idpan01.add("Center",idpan03);
	      idpan03.add(label_name);
	      idpan03.add(txid);
	      idpan03.add(label_email);
	      idpan03.add(txemail);   
	      idpan03.add(findbtn);
	      idpan03.add(canbtn);
	      idpan03.setBackground(Color.white);

	   }

	   public void eventSet2() {
	      findbtn.addActionListener(this);
	      canbtn.addActionListener(this);
	   }

	   @Override
	   public void actionPerformed(ActionEvent e) {
	      if(e.getSource()==findbtn) {//찾기
	         String idget=txid.getText();
	         String emailget=txemail.getText();

	         MemberDAOImpl daoM=new MemberDAOImpl();
	         //daoM.getId(idget, emailget)
	         SecondEmail send=new SecondEmail();
	         if(idget.equals("")) {
	            messageBox(this,"아이디를 입력해주세요.");
	            txid.requestFocus();
	         }else if(daoM.idCheck(idget)) {
	            messageBox(this,"등록되지 않은 아이디입니다!");
	            txid.setText("");
	            txid.requestFocus();
	         }else {
	            if(emailget.equals("")){
	               messageBox(this,"이메일을 입력해주세요.");
	               txemail.requestFocus();
	            }
	            else if(daoM.getPwd(idget, emailget)==null) {
	               messageBox(this, "이메일 정보가 일치하지않습니다.");
	               txemail.setText("");
	               txemail.requestFocus();
	            }else {
	               messageBox(this, "성공하였습니다.");
	               send.pwd_Email(idget, emailget);
	               reset();
	            }
	         }
	      }


	      if(e.getSource()==canbtn) {//닫기
	         jd.dispose();
	      }
	   }public static void main(String[] args) {
	   }//메인메소드 
	   public void reset() {
	      txid.setText("");
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