package com.melon.others;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.melon.domain.AlbumVO;
import com.melon.main.Main;
import com.melon.main.Player;
import com.melon.persistence.AlbumDAOImpl;
import com.melon.persistence.MemberDAOImpl;

public class SearchResult extends JPanel implements ActionListener{
	private JPanel topPanel; // 카테고리 라벨들을 붙일 패널
	private JLabel[] topLbl; // 카테고리 라벨들

	private ArrayList<AlbumVO> data;
	//private AlbumDAOImpl dbc; 
	private JPanel centerPanel; // rowPanel 들을 붙일 패널


	private int chkCount = 0; // 체크박스를 선택한 개수를 담을 변수

	private JCheckBox chk; // 체크박스 
	private int[] ids;

	private static int chkID;
	private static LinkedList<Integer> chkIDs = new LinkedList<>();

	JButton listen=new JButton("듣기");
	JButton jjim=new JButton("담기");

	AlbumDAOImpl dao=new AlbumDAOImpl();
	Font f2=new Font("나눔바른펜",Font.PLAIN,20);
	Color c1=new Color(1,255,167);
	MemberDAOImpl daoM=new MemberDAOImpl();

	public SearchResult() {

	}
	public SearchResult(String words) {
		
		JPanel big=new JPanel();
		JPanel ss=new JPanel();
		JLabel navi=new JLabel("검색결과");

		ss.add(navi);
		setLayout(new BorderLayout());
		//cont에서 사방 보더레이아웃으로 주위채우기
		setLayout(new BorderLayout());
		//setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		setVisible(true);
		//JScrollPane bigs=new JScrollPane();

		JPanel big_al=new JPanel();
		JPanel bigP=new JPanel();
		JPanel SP=new JPanel();
		JPanel EP=new JPanel();
		JPanel NP=new JPanel();
		JPanel WP=new JPanel();

		big_al.setLayout(new BorderLayout());
		big_al.add(SP,"South");
		SP.setBackground(Color.WHITE);
		big_al.add(EP,"East");
		WP.setBackground(Color.WHITE);
		big_al.add(NP,"North");
		NP.setBackground(Color.WHITE);
		big_al.add(WP,"West");
		EP.setBackground(Color.WHITE);
		big_al.add(bigP,"Center");
		bigP.setLayout(new BorderLayout());


		SP.add(listen);
		listen.addActionListener(this);
		SP.add(jjim);
		jjim.addActionListener(this);
		//스크롤 그리드레이아웃,박스
		//topPanel 영역 ------------------------------------------
		topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1,6));
		topPanel.setBackground(Color.white);
		topLbl = new JLabel[6];
		String[] topLblText = {"선택", "순위", "앨범사진", "노래제목", "가수명", "앨범명"};
		Font font = new Font("바탕체", Font.BOLD, 15);
		for (int i = 0; i < topLbl.length; i++) {
			topLbl[i] = new JLabel();
			topLbl[i].setText(topLblText[i]+"\t");
			topLbl[i].setFont(font);
			topPanel.add(topLbl[i]);
		}
		bigP.add(topPanel,"North");

		//centerPanel 영역 ---------------------------------------------
		data = new ArrayList<>();
		//dbc = new AlbumDAOImpl();
		centerPanel = new JPanel();
		//centerPanel.setLayout(new GridLayout(data.size(), 1, 0, 0));
		centerPanel.setLayout(new GridLayout(7, 5));
		bigP.add(centerPanel,"Center");
		add(big_al);
		//bigs.add(big_al);
		//add(bigs);
		//show method - db에서 음악 목록을 불러와서 뿌려준다.
		//public void showMethod(){
		centerPanel.removeAll();
		centerPanel.setBackground(Color.WHITE);
		data.clear();
		listen.setContentAreaFilled(false);
		jjim.setContentAreaFilled(false);
		listen.setBorderPainted(false);
		jjim.setBorderPainted(false);
		jjim.setFocusPainted(false);
		listen.setFocusPainted(false);
		jjim.setFont(f2);
		listen.setFont(f2);
		listen.setBackground(c1);
		jjim.setBackground(c1);
		listen.setBorderPainted(false);
		jjim.setBorderPainted(false);
		data = dao.mainFind(Main.cb.getSelectedItem().toString(),words);

		JPanel[] rowPanel = new JPanel[data.size()];
		chk = new JCheckBox();
		ids = new int[data.size()];
		for (int i = 0; i < data.size(); i++) {
			rowPanel[i] = new JPanel();
			rowPanel[i].setLayout(new GridLayout(1,7));

			Integer id = data.get(i).getNo();

			chk = new JCheckBox();
			//chk.setBounds(120, 25, 50, 50);
			chk.setBackground(Color.white);
			chk.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					if(e.getStateChange() == e.SELECTED){
						chkCount++;
						chkIDs.add(id);
						System.out.println(chkIDs);

					}else if(e.getStateChange() == e.DESELECTED){
						chkCount--;
						chkIDs.remove(id);
						System.out.println(chkIDs);
					}                    

				}
			});

			String alName = data.get(i).getAlName();
			String singer = data.get(i).getSinger();
			//String date = data.get(i).getDate();
			String musicName = data.get(i).getMusicName();
			String pic = data.get(i).getPicFile();
			//String title = data.get(i).getTitle();

			JLabel[] contentsLbl = new JLabel[5];
			for (int j = 0; j < contentsLbl.length; j++) {
				contentsLbl[j] = new JLabel();
			}

			Image img = new
					ImageIcon(pic).getImage();
			Image icb=img.getScaledInstance(40, 40,Image.SCALE_SMOOTH);
			ImageIcon ica=new ImageIcon(icb);

			contentsLbl[0].setText(i+1+"");
			contentsLbl[1].setIcon(ica);//이미지 넣기
			contentsLbl[2].setText(musicName);
			contentsLbl[3].setText(singer);
			contentsLbl[4].setText(alName);
			rowPanel[i].setBackground(Color.WHITE);
			rowPanel[i].add(chk);
			rowPanel[i].add(contentsLbl[0]);
			rowPanel[i].add(contentsLbl[1]);
			rowPanel[i].add(contentsLbl[2]);
			rowPanel[i].add(contentsLbl[3]);
			rowPanel[i].add(contentsLbl[4]);

			centerPanel.add(rowPanel[i]);
		}

	}    

	public int getChk(){
		return chkCount;
	}

	public int getChkID(){
		if(chkIDs.size()==1){
			chkID = chkIDs.get(0);
		}
		return chkID;
	}

	public LinkedList<Integer> getChkIDs(){
		return chkIDs;
	}
	//메시지 박스
	public static void messageBox(Object obj,String m) {
		JOptionPane.showMessageDialog((Component)obj,m);		
	}//messageBox()
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jjim) {
			if(Main.id==null) {
				messageBox(this,"로그인 후 이용해주세요.");
			}else {//로그인 되어있는경우
				daoM.addMymusic(Main.id,chkIDs);
				//tostring 이랑 subString
				if (daoM.addMymusic(Main.id,chkIDs)> 0) {
					messageBox(this,  "노래가 추가되었습니다.");//dao.addMymusic(Main.id,chkIDs)+"개
				}else{
					messageBox(this,"노래 저장실패했습니다.");
				}
			}
		}else if(e.getSource()==listen) {
			if(Main.id==null) {
				messageBox(this,"로그인 후 이용해주세요.");
			}else {//로그인 되어있는경우
				if(daoM.findinfo(Main.id).getM_left()<1) {//일수가 없는 경우
					messageBox(this,"구매후 이용해주세요.");
				}else{
					System.out.println(chkCount);
					if(chkCount==0) {//체크가 없는 경우
						messageBox(this,"체크박스를 확인해주세요.");
					}else {//체크 있는 경우
						new Player(chkIDs);
					}

				}
			}
		}

	}
}
