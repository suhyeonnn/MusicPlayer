package com.melon.admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.melon.domain.MemberVO;
import com.melon.main.IdFind;
import com.melon.persistence.MemberDAOImpl;


public class ControlMember_UI extends JFrame implements ActionListener{
   JMenuBar mb;
   MemberVO VO;
   MemberDAOImpl daoCon=new MemberDAOImpl();
   ControlMember_UI me;

   // 회원관리 메뉴
   JMenu m;
   JMenuItem update;
   JMenuItem delete;
   JMenuItem quit;
   //JMenuItem reset;
   JButton confirm;


   static String[] name = { "아이디", "이름","핸드폰","이메일","남은이용권일수","생성날짜"};
   static DefaultTableModel dt;
   static JTable jt;
   JScrollPane jsp;
   //검색창
   JPanel search=new JPanel();
   String[] comboName= {"전체","아이디","이름","핸드폰","이메일","남은이용권일수","생성날짜"};
   JComboBox combo=new JComboBox(comboName);

   JTextField searchBox;
   JButton searchButton;

   IdFind IF=new IdFind(null);
   Font f1=new Font("나눔바른펜",Font.PLAIN,15);

   public ControlMember_UI() {
      m = new JMenu("회원관리");
      delete = new JMenuItem("회원삭제");
      quit = new JMenuItem("종료");
      //reset = new JMenuItem("취소");
      delete.setBackground(Color.white);delete.setFont(f1);
      quit.setBackground(Color.white);quit.setFont(f1);
      //reset.setBackground(Color.white);reset.setFont(f1);
      m.add(delete);
      m.add(quit);
      //m.add(reset);
      m.setFont(f1);
      mb = new JMenuBar();
      m.setBackground(Color.white);
      mb.setBackground(Color.white);
      mb.add(m);
      setJMenuBar(mb);
      setTitle("회원관리");
      combo.setFont(f1);
      
      ImageIcon icon=new ImageIcon("./img/DoMain/melon.png");///
      setIconImage(icon.getImage());//상단바 아이콘///
      
      dt = new DefaultTableModel(name,0) {
         @Override
         public boolean isCellEditable(int row, int column) {
            return false;
         }
      };
      jt=new JTable(dt);
      jsp=new JScrollPane(jt);
      jt.setAutoCreateRowSorter(true);
      TableRowSorter trs = new TableRowSorter(jt.getModel());
      jt.setRowSorter(trs);

      searchBox = new JTextField(10);// 검색 입력박스 크기
      searchButton = new JButton("검색");// 검색 입력박스 
      searchButton.setFont(f1);
      searchButton.setBackground(Color.white);
      Color b = new Color(0,205,60);
      search.setBackground(b);// 검색 영역 배경색
      search.add(combo);
      search.add(searchBox);
      search.add(searchButton);
      delete.addActionListener(this);
      quit.addActionListener(this);
      searchButton.addActionListener(this);
      searchButton.setActionCommand("검색");

      add(jsp,"Center");// 프레임 위치 "중앙"
      add(search, "South");// 검색창 위치 "남쪽"
      setSize(800, 600);
      setVisible(true);
      setResizable(false);
      Dimension tsc = Toolkit.getDefaultToolkit().getScreenSize();// 화면 사이즈
      Dimension mySize = getSize();// 띄우는 창 사이즈
      setLocation(tsc.width / 2 - mySize.width / 2, tsc.height / 2 - mySize.height / 2);
      daoCon.userSelectAll(dt);      
      searchBox.registerKeyboardAction(this, "검색", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_FOCUSED);
      setDefaultCloseOperation(ControlMember_UI.DISPOSE_ON_CLOSE);
   }
   public static void main(String[] args) {
      new ControlMember_UI();//생성자 호출
   }
   @Override
   public void actionPerformed(ActionEvent e) {
      if (e.getActionCommand() == "검색" || e.getSource() == searchButton) {
         String fieldName = combo.getSelectedItem().toString();
         String field = null;
         if (searchBox.getText().trim().equals("")) {
            searchBox.requestFocus(); // 검색해서 커서옮김
            daoCon.userSelectAll(dt);
         } else {
            if (fieldName.trim().equals("전체")) {
               daoCon.getUserFindAll(dt, searchBox.getText());
            } else if (fieldName.equals("아이디")) {
               field ="m_id";
               daoCon.getUserFind(dt, field, searchBox.getText());
            } else if (fieldName.equals("이름")) {
               field ="m_name";
               daoCon.getUserFind(dt, field, searchBox.getText());
            } else if (fieldName.equals("이메일")) {
               field ="m_email";
               daoCon.getUserFind(dt, field, searchBox.getText());
            } else if (fieldName.equals("핸드폰")) {
               field ="m_phone";
               daoCon.getUserFind(dt, field, searchBox.getText());
            } else if (fieldName.equals("남은이용권일수")) {
               field ="m_left";
               daoCon.getUserFind(dt, field, searchBox.getText());
            } else if (fieldName.equals("생성날짜")) {
               field ="m_date";
               daoCon.getUserFind(dt, field, searchBox.getText());
            }
         }
      }
      if (e.getSource() == delete) {// 정보삭제
         int re = JOptionPane.showConfirmDialog(this, "삭제하시겠습니까?", " ", JOptionPane.YES_NO_OPTION);
         if (re == JOptionPane.YES_OPTION) {
            int row = jt.getSelectedRow();
            //System.out.println(row);
            if(row==-1) {
               IF.messageBox(this, "삭제할 회원을 선택해주세요");
            }else {
            Object obj = jt.getValueAt(row, 0);

            if (daoCon.delMember(obj.toString()) > 0) {
               IF.messageBox(this, "회원 삭제");
               daoCon.userSelectAll(dt);
            
            }
            }
         }
      }

      if (e.getSource() == quit) {
         dispose();
      }
   }
}