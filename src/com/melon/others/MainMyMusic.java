package com.melon.others;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import com.melon.domain.AlbumVO;
import com.melon.domain.MemberVO;
import com.melon.main.Main;
import com.melon.persistence.AlbumDAOImpl;
import com.melon.persistence.MemberDAOImpl;
public class MainMyMusic extends JPanel implements ActionListener{

	
	private JPanel topPanel; // 카테고리 라벨들을 붙일 패널
	private JLabel[] topLbl; // 카테고리 라벨들
	private JPanel centerPanel; // rowPanel 들을 붙일 패널

	private MemberVO dataM=new MemberVO();
	private MemberDAOImpl daoM=new MemberDAOImpl();


	private int chkCount = 0; // 체크박스를 선택한 개수를 담을 변수
	private JCheckBox ch=new JCheckBox();
	private JCheckBox[] chk;
	private int[] ids;
	private static int chkID;
	private static LinkedList<Integer> chkIDs = new LinkedList<>();
	List<Integer> list=new ArrayList<>();

	private JButton listen=new JButton("듣기");
	private JButton dlt=new JButton("삭제");

	Font f1=new Font("나눔바른펜",Font.PLAIN,15);
	Font f2=new Font("나눔바른펜",Font.PLAIN,20);
	Color c1=new Color(1,255,167);

	public MainMyMusic(String id) {
		//cont에서 사방 보더레이아웃으로 주위채우기
		setLayout(new BorderLayout());
		setVisible(true);
		listen.setContentAreaFilled(false);
		dlt.setContentAreaFilled(false);
		listen.setBorderPainted(false);
		dlt.setBorderPainted(false);
		dlt.setFocusPainted(false);
		listen.setFocusPainted(false);
		dlt.setFont(f2);
		listen.setFont(f2);
		listen.setBackground(c1);
		dlt.setBackground(c1);
		listen.setBorderPainted(false);
		dlt.setBorderPainted(false);

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
		SP.add(dlt);
		dlt.addActionListener(this);
		

		//topPanel 영역 ------------------------------------------
		topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1,6));
		topPanel.setBackground(Color.white);
		topLbl = new JLabel[4];
		ch.setBackground(Color.WHITE);
		ch.addActionListener(this);
		topPanel.add(ch);
		topPanel.setBackground(Color.white);
		String[] topLblText = {"앨범사진", "노래제목", "가수명", "앨범명"};
		Font font = new Font("나눔바른펜", Font.BOLD, 15);
		for (int i = 0; i < topLbl.length; i++) {
			topLbl[i] = new JLabel();
			topLbl[i].setText(topLblText[i]+"\t");
			topLbl[i].setFont(font);
			topPanel.add(topLbl[i]);
		}
		bigP.add(topPanel,"North");

		//centerPanel 영역 ---------------------------------------------
		AlbumVO dataA = new AlbumVO();
		AlbumDAOImpl daoA = new AlbumDAOImpl(); 

		centerPanel = new JPanel();
		//centerPanel.setLayout(new GridLayout(data.size(), 1, 0, 0));

		JScrollPane bigbig=new JScrollPane(centerPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		centerPanel.setLayout(new GridLayout(0, 1,0,10));
		bigP.add(bigbig,"Center");
		add(big_al);

	     chkIDs.clear();
		//add(bigbig);
		//bigs.add(big_al);
		//add(bigs);
		//show method - db에서 음악 목록을 불러와서 뿌려준다.
		//public void showMethod(){
		centerPanel.removeAll();
		centerPanel.setBackground(Color.WHITE);

		System.out.println("아이디값"+Main.id);

		if(id==null) {
			JLabel idnull=new JLabel("먼저 로그인 해주세요");
			idnull.setFont(f2);
			centerPanel.add(idnull);
		}else {
			dataM=daoM.findinfo(id);
			String myms = dataM.getM_mymusic();
			if(myms==null) {
				JLabel mymsnull=new JLabel("노래 추가해주세요");
				mymsnull.setFont(f2);
				centerPanel.add(mymsnull);
			}else {
				String mymss=myms.substring(1, myms.length()-1).trim();				
				StringTokenizer tokens=new StringTokenizer(mymss.trim(),",");
				while(tokens.hasMoreTokens()) {
					int a=Integer.parseInt(tokens.nextToken().trim());
					list.add(a);
				}
				JPanel[] rowPanel = new JPanel[list.size()];
				chk= new JCheckBox[list.size()];
				ids = new int[list.size()];

				for(int i=0;i<list.size();i++) {
					//dataA.clear();
					int n=list.get(i);
					dataA = daoA.fromnumb(n);
					//for (int i = 0; i < data.size(); i++) {
					rowPanel[i] = new JPanel();
					rowPanel[i].setLayout(new GridLayout(1,7));

					Integer muid = dataA.getNo();

					chk[i]=new JCheckBox();
					chk[i].setBackground(Color.white);
					chk[i].addItemListener(new ItemListener() {

						@Override
						public void itemStateChanged(ItemEvent e) {
							if(e.getStateChange() == e.SELECTED){
								chkCount++;
								chkIDs.add(muid);
								System.out.println(chkIDs);
							}else if(e.getStateChange() == e.DESELECTED){
								chkCount--;
								chkIDs.remove(muid);
								System.out.println(chkIDs);
							}                    
						}
					});
					String alName = dataA.getAlName();
					String singer = dataA.getSinger();
					//String date = data.get(i).getDate();
					String musicName = dataA.getMusicName();
					String pic = dataA.getPicFile();
					int countp = dataA.getCount();

					JLabel[] contentsLbl = new JLabel[5];
					for (int j = 0; j < contentsLbl.length; j++) {
						contentsLbl[j] = new JLabel();
					}

					Image img = new
							ImageIcon(pic).getImage();
					Image icb=img.getScaledInstance(40, 40,Image.SCALE_SMOOTH);
					ImageIcon ica=new ImageIcon(icb);

					//contentsLbl[0].setText(i+1+" "+countp+"회 재생됨");
					contentsLbl[1].setIcon(ica);//이미지 넣기
					contentsLbl[2].setText(musicName);
					contentsLbl[3].setText(singer);
					contentsLbl[4].setText(alName);
					contentsLbl[0].setFont(f1);
					contentsLbl[2].setFont(f1);
					contentsLbl[3].setFont(f1);
					contentsLbl[4].setFont(f1);
					rowPanel[i].setBackground(Color.WHITE);
					rowPanel[i].add(chk[i]);
					//rowPanel[i].add(contentsLbl[0]);
					rowPanel[i].add(contentsLbl[1]);
					rowPanel[i].add(contentsLbl[2]);
					rowPanel[i].add(contentsLbl[3]);
					rowPanel[i].add(contentsLbl[4]);

					centerPanel.add(rowPanel[i]);
				}
			}
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==dlt) {
			if(Main.id==null) {
				messageBox(this,"로그인 후 이용해주세요.");
			}else {
				if(e.getSource()==dlt) {
					daoM.deleteMymusic(Main.id,chkIDs);
					//tostring 이랑 subString
					if (daoM.deleteMymusic(Main.id,chkIDs)> 0) {
						//this.removeAll();
						messageBox(this, "노래가 삭제되었습니다.");//daoM.deleteMymusic(Main.id,chkIDs)+"개 
						this.revalidate();
						this.repaint();
					}else{
						messageBox(this,"노래 저장실패했습니다.");
					}

				}
			}
		}
		if(e.getSource()==ch) {
			System.out.println("체크:"+list.size());
			if(ch.isSelected()==true) {
				for (int i = 0; i < list.size(); i++) {
					chk[i].setSelected(true);
				}
			}else {
				for (int i = 0; i < list.size(); i++) {
					chk[i].setSelected(false);
				}
			}
		}
	}	
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