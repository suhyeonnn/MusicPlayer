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

import com.melon.main.IdFind;
import com.melon.persistence.AlbumDAOImpl;

public class AlbumAdmin extends JFrame implements ActionListener{

	JMenuBar mb;
	JMenu a;
	JMenuItem m;
	JMenuItem quit;
	JMenuItem ediMain;
	JMenuItem addMusic;
	JMenuItem dltMusic;

	//static String[] albumNam = {"앨범명","가수"};//,"노래수","메인창노출"
	static String[] songNam = {"일련번호","앨범명","노래명","가수","발매일","스트리밍수"};
	static DefaultTableModel dt;
	static DefaultTableModel dt1;
	static JTable jt;
	static JTable jt1;
	JScrollPane jsp;
	JScrollPane jsp1;

	//검색창
	JPanel search=new JPanel();
	String[] comboName= {"전체","가수","앨범명"};
	JComboBox combo=new JComboBox(comboName);

	JTextField searchBox;
	JButton searchButton;


	Font f1=new Font("나눔바른펜",Font.PLAIN,15);
	AlbumDAOImpl dao=new AlbumDAOImpl();
	//앨범삭제기능 추가할지말지

	IdFind IF=new IdFind(null);

	public AlbumAdmin() {
		combo.setFont(f1);
		//상단 메뉴생성
		a = new JMenu("노래관리");
		//m = new JMenuItem("앨범관리");
		quit = new JMenuItem("종료");
		ediMain = new JMenuItem("메인창앨범편집");
		addMusic = new JMenuItem("노래추가");
		dltMusic = new JMenuItem("노래삭제");
		mb = new JMenuBar();
		a.setBackground(Color.white);
		mb.setBackground(Color.white);
		quit.setFont(f1);ediMain.setFont(f1);
		addMusic.setFont(f1);dltMusic.setFont(f1);
		quit.setBackground(Color.white);ediMain.setBackground(Color.WHITE);
		addMusic.setBackground(Color.WHITE);dltMusic.setBackground(Color.WHITE);
		a.add(dltMusic);
		a.add(addMusic);
		 a.add(ediMain);
		a.add(quit);
		
		//a.add(m);

		mb.add(a);
		setJMenuBar(mb);
		setTitle("관리창");

		ImageIcon icon=new ImageIcon("./img/DoMain/melon.png");///
		setIconImage(icon.getImage());//상단바 아이콘///

		dt = new DefaultTableModel(songNam,0) {
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


		/*dt1 = new DefaultTableModel(albumNam,0) {
          @Override
          public boolean isCellEditable(int row, int column) {
             return false;
          }
       };
       jt1=new JTable(dt1);
       jsp1=new JScrollPane(jt1);
       jt1.setAutoCreateRowSorter(true);
       TableRowSorter trs1 = new TableRowSorter(jt1.getModel());
       jt1.setRowSorter(trs);*/

		searchBox = new JTextField(10);// 검색 입력박스 크기
		searchButton = new JButton("검색");// 검색 입력박스 
		searchButton.setFont(f1);
		searchButton.setBackground(Color.white);
		Color b = new Color(0,205,60);
		search.setBackground(b);// 검색 영역 배경색
		search.add(combo);
		search.add(searchBox);
		search.add(searchButton);
		dao.SongSelectAll(dt); 
		// dao.AlbumSelectAll(dt1); 
		addMusic.addActionListener(this);
		dltMusic.addActionListener(this);
		ediMain.addActionListener(this);
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
		searchBox.registerKeyboardAction(this, "검색", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_FOCUSED);
		setDefaultCloseOperation(AlbumAdmin.DISPOSE_ON_CLOSE);

	}
	public static void main(String[] args) {
		new AlbumAdmin();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==quit) {
			dispose();
			new Manage();

		}
		if(e.getSource()==addMusic) {
			new Insert();
		}
		if (e.getSource() == dltMusic) {// 정보삭제
			int re = JOptionPane.showConfirmDialog(this, "삭제하시겠습니까?", " ", JOptionPane.YES_NO_OPTION);
			if (re == JOptionPane.YES_OPTION) {
				int row = jt.getSelectedRow();
				//System.out.println(row);
				if(row==-1) {
					IF.messageBox(this, "삭제할 노래를 선택해주세요");
				}else {
					Object obj = jt.getValueAt(row, 0);

					if (dao.delMusic(obj.toString()) > 0) {
						IF.messageBox(this, "노래 삭제");
						dao.SongSelectAll(dt);

					}
				}
			}
		}
		if (e.getActionCommand() == "검색" || e.getSource() == searchButton) {
			String fieldName = combo.getSelectedItem().toString();
			String field = null;
			if (searchBox.getText().trim().equals("")) {
				searchBox.requestFocus(); // 검색해서 커서옮김
				dao.SongSelectAll(dt);
			} else {
				if (fieldName.trim().equals("전체")) {
					dao.getUserFindAll(dt, searchBox.getText());
				} else if (fieldName.equals("가수")) {
					field ="m_singer";
					dao.getUserFind(dt, field, searchBox.getText());
				} else if (fieldName.equals("앨범명")) {
					field ="m_alname";
					dao.getUserFind(dt, field, searchBox.getText());
				}
			}
		}
		if(e.getSource()==ediMain) {
			new IsMain();
		}
	}

}