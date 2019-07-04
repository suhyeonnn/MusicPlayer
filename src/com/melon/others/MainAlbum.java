

package com.melon.others;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.melon.domain.AlbumVO;
import com.melon.main.OneAlbum;
import com.melon.persistence.AlbumDAOImpl;

public class MainAlbum extends JPanel {
	JLabel maL;
	JPanel mainAl,mainSideCut,mainCenter;
	
	Font f1=new Font("나눔바른펜",Font.PLAIN,15);
	Font f2=new Font("나눔바른펜",Font.PLAIN,20);
	AlbumDAOImpl dao=new AlbumDAOImpl();

	public MainAlbum() {
		maL=new JLabel("멜론앨범");
		maL.setFont(f2);

		
		
		setBackground(Color.WHITE);
		mainSideCut=new JPanel(new BorderLayout(0,0));
		add(mainSideCut,"Center");
		mainAl=new JPanel(new BorderLayout());
		mainSideCut.add(mainAl,"North");
		mainAl.setBackground(Color.WHITE);
		mainSideCut.setBackground(Color.WHITE);
		mainAl.add(maL,"West");
		mainSideCut.add("East", new JLabel());
		mainSideCut.add("West", new JLabel());
		
		mainSideCut.add("South", new JLabel());
		mainCenter=new JPanel();

		
		
		
		JScrollPane bigbig=new JScrollPane(mainCenter,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		mainCenter.setLayout(new GridLayout(0,2,90,30));
		mainSideCut.add(bigbig,"Center");
		mainCenter.setBackground(Color.WHITE);
		/*
		JPanel[] rowPanel = new JPanel[dao.findAlbum().size()];
		Smallpan[] smallpan=new Smallpan[dao.findAlbum().size()];
		
		mainCenter.removeAll();
		mainCenter.setBackground(Color.WHITE);
	     
		for(int i=0;i<6;i++) {
			rowPanel[i] = new JPanel();
			rowPanel[i].setLayout(new BorderLayout());
			rowPanel[i].setBackground(Color.WHITE);
			rowPanel[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
			String addAl=dao.findAlbum().get(i);
			smallpan[i] =new Smallpan(addAl);
			smallpan[i].addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					new OneAlbum(addAl);
				}

			});
			rowPanel[i].add(smallpan[i],"West");
			mainCenter.add(rowPanel[i]);
	  */


		
		JPanel[] rowPanel = new JPanel[dao.findAlbum().size()];
		Smallpan[] smallpan=new Smallpan[dao.findAlbum().size()];
		for(int i=0;i<6;i++) {
			rowPanel[i] = new JPanel();
			rowPanel[i].setLayout(new BorderLayout());
			rowPanel[i].setBackground(Color.WHITE);
			rowPanel[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
			String addAl=dao.findAlbum().get(i);
			smallpan[i] =new Smallpan(addAl);
			smallpan[i].addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					new OneAlbum(addAl);
				}

			});
			rowPanel[i].add(smallpan[i],"West");
			mainCenter.add(rowPanel[i]);
		}
		
	

	


}
class Smallpan extends JPanel{
	AlbumDAOImpl dao=new AlbumDAOImpl();
	ArrayList<AlbumVO> data=new ArrayList<AlbumVO>();

	Smallpan(String alnam){
		setLayout(new BorderLayout());
		//그림 넣을 왼쪽패널
		JPanel west=new JPanel();
		//앨범설명 넣을 오른쪽 패널
		JPanel center=new JPanel();

		//alnam데이터 불러오기
		data.clear();
		data=dao.findMusics(alnam);

		String alName = data.get(0).getAlName();
		String singer = data.get(0).getSinger();
		String date = data.get(0).getDate();
		String musicName = data.get(0).getMusicName();
		String pic = data.get(0).getPicFile();
		int munum=data.size();
		

		//패널 구성하기 왼쪽
		Image im = new ImageIcon(pic).getImage();
		Image icd=im.getScaledInstance(140,140,Image.SCALE_SMOOTH);
		ImageIcon icf=new ImageIcon(icd);

		JLabel pical=new JLabel();
		pical.setIcon(icf);
		west.setBackground(Color.WHITE);
		west.add(pical);
		add(west,"West");

		center.setLayout(new BoxLayout(center,BoxLayout.Y_AXIS));
		JLabel alNameL=new JLabel();
		JLabel singerL=new JLabel();
		JLabel dateL=new JLabel();   
		JLabel munumL=new JLabel();
		alNameL.setText(alName);
		alNameL.setFont(new Font("나눔바른펜",Font.BOLD,25));
		singerL.setText(singer);
		singerL.setFont(new Font("나눔바른펜",Font.BOLD,20));
		dateL.setText(date);
		dateL.setFont(new Font("나눔바른펜",Font.BOLD,18));
		munumL.setText(Integer.toString(munum)+"곡");
		center.add(alNameL);
		center.add(singerL);
		center.add(dateL);
		center.add(munumL);
		center.setBackground(Color.WHITE);
		add(center,"Center");
	}
}
}

