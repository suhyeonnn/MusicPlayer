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
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import com.melon.domain.MemberVO;
import com.melon.persistence.MemberDAOImpl;

public class NewUesr_UI extends JFrame implements ActionListener {
   //패널
   private JPanel backpan01=  new JPanel(new BorderLayout(10,10));
   private JPanel intopanlow=  new JPanel(new BorderLayout());
   private JPanel mpan=        new JPanel(new BorderLayout());
   private JPanel lapan=        new JPanel(new GridLayout(6,1));
   private JPanel txtpan=       new JPanel(new GridLayout(6,1));
   private JPanel btncom=      new JPanel(new FlowLayout());
   private JPanel idfield=       new JPanel(new BorderLayout());
   private JPanel idfield_01=   new JPanel(new FlowLayout());
   private JPanel txtflow01=    new JPanel(new BorderLayout());
   private JPanel txtflow01_01=         new JPanel(new FlowLayout());
   private JPanel txtflow02=             new JPanel(new BorderLayout());
   private JPanel txtflow02_01=         new JPanel(new FlowLayout());
   private JPanel txtflow03=             new JPanel(new BorderLayout());
   private JPanel txtflow03_01=         new JPanel(new FlowLayout());
   private JPanel phonetxtpan=          new JPanel(new BorderLayout());
   private JPanel phonetxtpan_01=      new JPanel(new FlowLayout());
   private JPanel emailtxtpan=          new JPanel(new BorderLayout());
   private JPanel emailtxtpan_01=      new JPanel(new FlowLayout());

   //라벨     라벨은 설명을 출력할때쓰는 컴퍼넌트
   private JLabel nlid;
   private JLabel nlpwd;
   private JLabel nlpwde;
   private JLabel nlname;
   private JLabel nlphone;
   private JLabel nlemail;

   //텍스트 입력박스
   private JTextField ntid;
   private JPasswordField ntpwd;
   private JPasswordField ntpwde;
   private JTextField ntname;
   private JTextField ntemail;
   private JTextField ntemail02;
   private JTextField ntphone02;
   private JTextField ntphone03;

   //버튼
   private JButton newintobtn;
   private JButton overbtn;
   private JButton calbtn;

   //콤보박스
   String[] emailname= {"선택","naver.com","daum.net",
         "hotmail.com","nate.com","gmail.com","직접입력"};
   private JComboBox emailcom=new JComboBox(emailname);

   //폰번호 콤보박스
   String[] phoneName= {"010","011","016","019"};
   private JComboBox phonecom= new JComboBox(phoneName);

   MemberVO m=new MemberVO();
   MemberDAOImpl dao=new MemberDAOImpl();
   Font f1=new Font("나눔바른펜",Font.PLAIN,15);

   public NewUesr_UI() {//생성자
      setTitle("회원가입");
      emailcom.setFont(f1);
      //라벨 객체생성
      nlid=new JLabel("아이디");
      nlpwd=new JLabel("비밀번호");
      nlpwde=new JLabel("비밀번호 확인");
      nlname=new JLabel("이름");
      nlphone=new JLabel("휴대폰번호");
      nlemail=new JLabel("이메일");
      nlid.setFont(f1);
      nlpwd.setFont(f1);
      nlpwde.setFont(f1);
      nlname.setFont(f1);
      nlphone.setFont(f1);
      nlemail.setFont(f1);
      //입력박스
      ntid=new JTextField(17);
      ntpwd=new JPasswordField(17);
      ntpwde=new JPasswordField(17);
      ntname=new JTextField(17);
      ntemail=new JTextField(8);
      ntemail02=new JTextField(7);
      ntphone02=new JTextField(4);
      ntphone03 =new JTextField(4);
      ntid.setFont(f1);
      ntpwd.setFont(f1);
      ntpwde.setFont(f1);
      ntname.setFont(f1);
      ntphone02.setFont(f1);
      ntphone03.setFont(f1);
      ntemail.setFont(f1);
      ntemail02.setFont(f1);
      //버튼생성
      newintobtn=new JButton("가입");
      overbtn=new JButton("중복확인");
      calbtn=new JButton("취소");
      newintobtn.setFont(f1);
      overbtn.setFont(f1);
      calbtn.setFont(f1);
      phonecom.setFont(f1);
      overbtn.setBackground(Color.white);
      calbtn.setBackground(Color.WHITE);
      newintobtn.setBackground(Color.WHITE);

      //각 메서드 호출
      Panelnu_Set(); Eventnu_Set();

      setSize(420,320); setVisible(true);
      setResizable(false);
      ntemail02.setEditable(false);//비활성화
      ImageIcon icon1=new ImageIcon("./img/DoMain/melon.png");///
		setIconImage(icon1.getImage());//상단바 아이콘///
      Dimension tsc=Toolkit.getDefaultToolkit().getScreenSize();//화면사이즈
      Dimension mySize=getSize();//띄우는 창 크기
      setLocation(tsc.width/2-mySize.width/2,
            tsc.height/2-mySize.height/2);//정중앙 위치
   }//생성자

   //패널 배치
   public void Panelnu_Set() {
      backpan01.add("North",new JLabel());
      backpan01.add("South",new JLabel());
      backpan01.add("East",new JLabel());
      backpan01.add("West",new JLabel());//상하좌우 여백
      backpan01.add("Center",intopanlow);
      intopanlow.add("Center",mpan);
      intopanlow.setBackground(Color.WHITE);
      backpan01.setBackground(Color.WHITE);
      mpan.add("West",lapan);
      lapan.add(nlid); lapan.add(nlpwd);
      lapan.add(nlpwde);lapan.add(nlname);
      lapan.add(nlphone);
      lapan.add(nlemail);
      mpan.setBackground(Color.WHITE);
      lapan.setBackground(Color.WHITE);
      mpan.add("Center",txtpan);
      idfield.setBackground(Color.WHITE);
      txtpan.add(idfield);
      txtpan.setBackground(Color.white);
      idfield.add("West",idfield_01);idfield_01.setBackground(Color.WHITE);
      idfield_01.add(ntid); idfield_01.add(overbtn);
      txtpan.add(txtflow01);
      txtflow01.add("West",txtflow01_01);txtflow01_01.setBackground(Color.WHITE);
      txtflow01_01.add(ntpwd);
      txtpan.add(txtflow02);txtflow02.setBackground(Color.WHITE);
      txtflow01.setBackground(Color.WHITE);
      txtflow02.add("West",txtflow02_01);txtflow02_01.setBackground(Color.white);
      txtflow02_01.add(ntpwde);
      txtpan.add(txtflow03);txtflow03.setBackground(Color.WHITE);
      txtflow03.add("West",txtflow03_01);txtflow03_01.setBackground(Color.WHITE);
      txtflow03_01.add(ntname);
      txtpan.add(phonetxtpan);
      phonetxtpan.setBackground(Color.white);
      phonetxtpan.add("West",phonetxtpan_01);
      phonetxtpan_01.setBackground(Color.white);
      phonetxtpan_01.add(phonecom);
      phonetxtpan_01.add(new JLabel("-"));
      phonetxtpan_01.add(ntphone02);
      phonetxtpan_01.add(new JLabel("-"));
      phonetxtpan_01.add(ntphone03);
      txtpan.add(emailtxtpan);
      emailtxtpan.add("West",emailtxtpan_01);
      emailtxtpan_01.add(ntemail);
      emailtxtpan_01.add(new JLabel("@"));
      emailtxtpan_01.add(ntemail02);
      emailtxtpan_01.add(emailcom);
      emailtxtpan.setBackground(Color.WHITE);
      emailtxtpan_01.setBackground(Color.white);

      intopanlow.add("South",btncom);
      btncom.setBackground(Color.WHITE);
      btncom.add(newintobtn); btncom.add(calbtn);

      add(backpan01);
   }//Panelnu_Set()

   //입력박스 초기화   
   public void Reset() {
      ntid.setText(""); ntpwd.setText("");
      ntpwde.setText(""); ntname.setText("");
      ntemail.setText(""); 
      emailcom.setSelectedItem("선택");//콤보박스 해당항목을 선택하게 함
   }//Reset()

   //이벤트 등록
   public void Eventnu_Set() {
      newintobtn.addActionListener(this);
      overbtn.addActionListener(this);
      calbtn.addActionListener(this);
      emailcom.addActionListener(this);
   }//Eventnu_Set()

   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource() == overbtn) {//아이디 중복검색
         if((ntid.getText().length()<6 || ntid.getText().length() > 16) ||
               !(Pattern.matches("^[a-z]+[a-z0-9]*$",ntid.getText()))){
            /* Pattern.matches()정규표현식으로 아이디가 영문소문자+
             *    0~9까지 숫자 조합으로만 허용한다.
             */
            messageBox(this, "아이디는 영문소문자로 시작하는 "+
                  "6~16자리 영문소문자와 숫자로만 입력하세요!");
            ntid.requestFocus();//아이디 입력박스로 커서이동
            return;//메서드 종료=>이벤트 종료
         }
         if(dao.idCheck(ntid.getText().trim()) == true) {
            //오라클 디비로 부터 아이디 중복검색
            messageBox(this,"사용가능한 아이디입니다!");
            overbtn.setEnabled(false);//중복검색 버튼을 비활성
            ntid.setEditable(false);///////
            ntpwd.requestFocus();
         }else {
            messageBox(this,"이미 사용중인 아이디입니다!");
            ntid.setText(""); ntid.requestFocus();
         }//if else
      }//중복확인 

      //이메일 주소 선택
      if(e.getSource() == emailcom) {
         String str=(String)emailcom.getSelectedItem();//선
         //택된 콤보박스 항목이름을 가져옴.
         ntemail02.setText(str);//멜주소 입력박스에 출력
         ntemail02.setEditable(false);//멜주소 입력박스를 수
         //정못하게 비활성화
         if(str.equals("직접입력")) {
            ntemail02.setText("");
            ntemail02.setEditable(true);//활성화
            ntemail02.requestFocus();//포커스이동
         }else if(str.equals("선택")) {
            ntemail02.setText("");
            ntemail02.setEditable(false);
         }//if else if
      }

      //회원가입 저장
      if(e.getSource() == newintobtn) {
         /* copy begin */
         if (overbtn.isEnabled()==true) {
            //컴퍼넌트가 사용 가능한 경우는 true, 그렇지 않은 경우는 false
            messageBox(this, "아이디를 입력한 뒤 중복확인을 해주세요.");
            return;
         }
         if ((ntpwd.getText().length() < 6 || ntpwd.getText().length() > 16)
               || !(Pattern.matches("^[a-z0-9]*$", ntpwd.getText()))) {
            JOptionPane.showMessageDialog(this, "비밀번호는  " + "6~16자리 영문소문자와 숫자로만 입력하세요");
            ntpwd.setText("");
            ntpwd.requestFocus();
            return;
         }
         if (ntpwde.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "비밀번호확인을 입력하세요.");
            ntpwde.requestFocus();
            return;
         }
         if (!ntpwd.getText().equals(ntpwde.getText())) {
            JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.");
            ntpwde.setText("");
            ntpwd.setText("");
            ntpwd.requestFocus();
            return;
         }
         if (ntname.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "이름을 입력하세요.");
            ntname.requestFocus();
            return;
         }
         if((ntname.getText().length()<2||ntname.getText().length()>4)||!(Pattern.matches("^[ㄱ-ㅎ가-힣]*$",ntname.getText()))) {
            JOptionPane.showMessageDialog(this, "정확한 이름을 입력하세요.");
            ntname.setText("");
            ntname.requestFocus();
            return;
         }
         if (ntphone02.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "폰번호를 입력하세요.");
            ntphone02.requestFocus();
            return;
         }
         if(!(ntphone02.getText().length()==4)) {
            JOptionPane.showMessageDialog(this, "정확한 폰번호를 입력하세요.");
            ntphone02.requestFocus();
            ntphone02.setText("");
            return;
         }
         if (ntphone03.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "폰번호를 입력하세요.");
            ntphone03.requestFocus();
            return;
         }
         if(!(ntphone03.getText().length()==4)) {
            JOptionPane.showMessageDialog(this, "정확한 폰번호를 입력하세요.");
            ntphone03.requestFocus();
            ntphone03.setText("");

            return;
         }
         if (ntemail.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "이메일을 입력하세요.");
            ntemail.requestFocus();
            return;
         }
         if(!Pattern.matches("^[a-z0-9]*$", ntemail.getText())) {
            JOptionPane.showMessageDialog(this, "정확한 이메일을 입력하세요.");
            ntemail.requestFocus();
            ntemail.setText("");
            return;
         }else {//회원가입정보를 모두 입력한 경우
            String pwd=new String(ntpwd.getPassword());
            //비밀번호를 단일문자 배열로 가져온다.이것을
            //문자열객체 pwd로변경
            m.setM_id(ntid.getText().trim());//setter()메
            //서드에 아이디를 저장
            m.setM_pwd(pwd.trim());//trim() 은 양쪽공백 제
            //거
            m.setM_phone(phonecom.getSelectedItem().toString()+"-"+ntphone02.getText().trim()+"-"+
                  ntphone03.getText().trim());
            m.setM_name(ntname.getText().trim());
            m.setM_email(ntemail.getText().trim()+"@"+
                  ntemail02.getText().trim());

            if(dao.insertMember(m) > 0) {
               messageBox(this,ntid.getText()+"님 가입을 환영합니다!");
               Reset(); dispose();//다이얼로그 박스를 닫는다
            }//if
         }//if else
         /* copy end */
      }//회원가입 버튼 끝
      else if(e.getSource() == calbtn) {
         overbtn.setEnabled(true);//아이디 중복검색 버튼
         //활성화
         Reset(); dispose();
      }//취소 버튼 끝

   }//이벤트 발생했을때 호출

   //메시지 박스=>경고창
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