package com.melon.others;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.melon.domain.AlbumVO;
import com.melon.main.Main;
import com.melon.main.OneAlbum;
import com.melon.main.Player;
import com.melon.main.SamplePlayer;
import com.melon.persistence.AlbumDAOImpl;
import com.melon.persistence.MemberDAOImpl;

public class MainCont extends JPanel {
	static JPanel albumP,chartP;
	JPanel big;
	JPanel big_al;

	ImageIcon albumPic[]=new ImageIcon[6];

	JPanel SP=new JPanel();
	JPanel EP=new JPanel();
	JPanel NP=new JPanel();
	JPanel WP=new JPanel();
	JPanel pn=new JPanel();
	JButton[] button = new JButton [6];
	AlbumVO a=new AlbumVO();

	private ArrayList<AlbumVO> dataA;
	AlbumDAOImpl daoA=new AlbumDAOImpl();

	MemberDAOImpl daoM=new MemberDAOImpl();
	Font f1=new Font("나눔바른펜",Font.PLAIN,15);
	Font f2=new Font("나눔바른펜",Font.PLAIN,30);
	Color c1=new Color(0,214,59);
	public MainCont() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		albumP=new JPanel();
		chartP=new JPanel();
		big=new JPanel();
		big.setLayout(new BoxLayout(big,BoxLayout.X_AXIS));
		big_al=new JPanel();
		big_al.setLayout(new BorderLayout());
		big_al.add(SP,"South");
		SP.setBackground(Color.WHITE);
		big_al.add(EP,"East");
		WP.setBackground(Color.WHITE);
		big_al.add(NP,"North");
		NP.setBackground(Color.WHITE);
		big_al.add(WP,"West");
		EP.setBackground(Color.WHITE);
		big_al.add(albumP,"Center");
		//albumP만들기
		//centerLayout(big,albumP);

		albumP.setLayout(new BorderLayout());
		JPanel albumPLab=new JPanel();
		JPanel albumPPic=new JPanel();
		albumPLab.setBackground(Color.WHITE);
		albumPPic.setBackground(Color.WHITE);
		JLabel alL=new JLabel("<최신앨범>");
		albumPLab.add(alL);
		albumP.add(albumPLab,"North");
		alL.setFont(f2);
		alL.setForeground(c1);
		albumPPic.setLayout(new GridLayout(3,2,10,10));
		albumPic=new ImageIcon[6];
		//JButton[] button = new JButton [6];
		//System.out.println(daoA.callEditMain());
		String alnam=daoA.callEditMain();
		List<String> items = new ArrayList<String>();
		String after=alnam.substring(1, alnam.length()-1).trim();
		//System.out.println("after="+after);
		StringTokenizer tokens=new StringTokenizer(after.trim(),",");
		while(tokens.hasMoreTokens()) {

			String a;
			a=tokens.nextToken().trim();
			items.add(a);
		}
		for(int i=0;i<6;i++) {
			
			button[i]=new JButton();
			String alalnam=items.get(i);
			String picnam=daoA.findMusics(items.get(i)).get(0).getPicFile();
			Image img = new
		               ImageIcon(picnam).getImage();
		        Image icb=img.getScaledInstance(120, 120,Image.SCALE_SMOOTH);
		         ImageIcon ica=new ImageIcon(icb);
			albumPic[i]=ica;
			button[i].setIcon(albumPic[i]);
			button[i].setBackground(Color.WHITE);
			albumPPic.add(button[i]);

			button[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					new OneAlbum(alalnam);
				
					
				}
			});
		}


		albumP.add(albumPPic,"Center");
		big.add(big_al);

		JPanel SSP=new JPanel();
		JPanel EEP=new JPanel();
		JPanel NNP=new JPanel();
		JPanel WWP=new JPanel();

		JPanel big_ch;
		big_ch=new JPanel();
		big_ch.setLayout(new BorderLayout());
		big_ch.add(SSP,"South");
		SSP.setBackground(Color.WHITE);
		big_ch.add(EEP,"East");
		WWP.setBackground(Color.WHITE);
		big_ch.add(NNP,"North");
		NNP.setBackground(Color.WHITE);
		big_ch.add(WWP,"West");
		EEP.setBackground(Color.WHITE);
		big_ch.add(chartP,"Center");

		JPanel head=new JPanel();
		JPanel mid=new JPanel();
		//JPanel =new JPanel();


		JLabel ch=new JLabel("<멜론차트>");
		ch.setFont(f2);
		ch.setForeground(Color.GREEN);

		ch.setFont(f2);
		ch.setForeground(c1);

		dataA = new ArrayList<>();
		AlbumDAOImpl dbc = new AlbumDAOImpl();
		//JPanel mchar = new JPanel();
		//mchar.setLayout(new GridLayout(10, 3));

		chartP.setLayout(new BorderLayout());
		head.add(ch);
		head.setBackground(Color.white);
		chartP.add(head,"North");



		//show method - db에서 음악 목록을 불러와서 뿌려준다.
		//public void showMethod(){

		chartP.setBackground(Color.WHITE);
		dataA.clear();
		dataA = dbc.callMusic();
		mid.setLayout(new GridLayout(10, 4));
		JPanel[] rowPanel = new JPanel[dataA.size()];
		for (int i = 0; i < 10; i++) {//메인 콘트 노래순위 10위까지
			rowPanel[i] = new JPanel();

			rowPanel[i].setLayout(new GridLayout(0,4));
			//ArrayList<AlbumVO> store=new ArrayList<>();

			Integer numid = dataA.get(i).getNo();
			String alName = dataA.get(i).getAlName();
			String singer = dataA.get(i).getSinger();
			//String date = data.get(i).getDate();
			String musicName = dataA.get(i).getMusicName();
			//String  = data.get(i).getAlName();
			//String title = data.get(i).getTitle();

			JLabel[] contentsLbl = new JLabel[5];
			for (int j = 0; j < contentsLbl.length; j++) {
				contentsLbl[j] = new JLabel();
			}

			contentsLbl[0].setText(i+1+"");
			contentsLbl[1].setText(musicName);
			contentsLbl[2].setText(singer);
			contentsLbl[0].setFont(f1);
			contentsLbl[1].setFont(f1);
			contentsLbl[2].setFont(f1);

			ImageIcon play=new ImageIcon("./img/player/play.jpg");
			JButton ne=new JButton();
			ne.setIcon(play);
			ne.setBorderPainted(false);
			ne.setContentAreaFilled(false);
			rowPanel[i].setBackground(Color.WHITE);
			rowPanel[i].add(contentsLbl[0]);
			rowPanel[i].add(contentsLbl[1]);
			rowPanel[i].add(contentsLbl[2]);
			rowPanel[i].add(ne);
			ne.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(Main.id==null) {
						//messageBox(this, "로그인 후 사용해주세요!");
						new SamplePlayer(numid);
						System.out.println(numid);
					}else if(daoM.findinfo(Main.id).getM_left()==0){//아이디의 m_남은날이 =0일때
						new SamplePlayer(numid);
						//new SamplePlayer();//버튼에 맞는 미리듣기곡 불러오기 fromnumb일련번호로
					}else{//아이디의 남은날이 1이상 일때
						new Player(numid);
					}
				}
			});
			mid.add(rowPanel[i]);
		}
		mid.setBackground(Color.white);
		chartP.add(mid,"Center");
		big_ch.add(chartP);
		big.add(big_ch);
		add(big);

	}

	/*static void centerLayout(JPanel a,JPanel pn) {
      a = null;
      JPanel SP,WP,NP,EP,CP;
      a.setLayout(new BorderLayout());
      SP=new JPanel();
      EP=new JPanel();
      NP=new JPanel();
      WP=new JPanel();
      pn=new JPanel();
      a.add(SP,"South");
      a.add(EP,"East");
      a.add(NP,"North");
      a.add(WP,"West");
      a.add(pn,"Center");

   }*/






	//버튼이미지이름으로 정보 불러옴
	/*@Override
   public void actionPerformed(ActionEvent e) {
      for(int i=0;i<6;i++) {
         if(e.getSource()==button[i]) {
            //String num = ntnumber.getText().toString();
            button[i].getIcon();//이미지 아이콘 이름 찾아서 

            dao.findMusics()
            a.setAlName(ntid.getText().trim());
            a.setDate(ntpwd.getText().trim());
            a.setSinger(ntname.getText().trim());
            a.setMusicName(ntemail.getText().trim());//
            a.setPicFile(ntpwde.getText().trim());
            a.setMusicFile(ntemail02.getText().trim());//
            if(dao.insertMusic(a)>0) {
               messageBox(this,"성공하였습니다.");
               //reset();
               dispose();//다이얼로그 박스를 닫는다.
            }else {
               messageBox(this,"다시입력해주세요.");
            }
         }
      }



   }*/
	// 메세지 박스
	public static void messageBox(Object obj, String m) {
		JOptionPane.showMessageDialog((Component) obj, m);
	}
}




