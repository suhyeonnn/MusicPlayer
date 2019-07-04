package com.melon.others;

import java.awt.BorderLayout;
import java.awt.Checkbox;
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
import com.melon.main.Main;
import com.melon.main.Player;
import com.melon.persistence.AlbumDAOImpl;
import com.melon.persistence.MemberDAOImpl;
public class MainChart extends JPanel implements ActionListener{

   //전역 변수 설정
   private JPanel topPanel; // 카테고리 라벨들을 붙일 패널
   private JLabel[] topLbl; // 카테고리 라벨들

   private ArrayList<AlbumVO> data;
   private AlbumDAOImpl dbc; 
   private MemberDAOImpl dao=new MemberDAOImpl();

   private JPanel centerPanel; // rowPanel 들을 붙일 패널


   private int chkCount = 0; // 체크박스를 선택한 개수를 담을 변수

   private JCheckBox[] chk; // 체크박스 
   private int[] ids;

   private static int chkID;
   private static LinkedList<Integer> chkIDs = new LinkedList<>();
   
   JPanel[] rowPanel;
   
   JCheckBox ch=new JCheckBox();
   
   JButton listen=new JButton("듣기");
   JButton jjim=new JButton("담기");
   Font f1=new Font("나눔바른펜",Font.PLAIN,15);
   Font f2=new Font("나눔바른펜",Font.PLAIN,20);
   Color c1=new Color(1,255,167);

   public MainChart() {
      //cont에서 사방 보더레이아웃으로 주위채우기
      setLayout(new BorderLayout());
      //setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
      setVisible(true);
      //JScrollPane bigs=new JScrollPane();
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
      SP.add(jjim);
      listen.addActionListener(this);
      jjim.addActionListener(this);
      //스크롤 그리드레이아웃,박스
      //topPanel 영역 ------------------------------------------
      topPanel = new JPanel();
      topPanel.setLayout(new GridLayout(1,6));
      topPanel.setBackground(Color.white);
      ch.setBackground(Color.WHITE);
      ch.addActionListener(this);
      topPanel.add(ch);
      topPanel.setBackground(Color.white);
      topLbl = new JLabel[5];
      //JCheckBox checkall=new JCheckBox();
      //topLbl[0].add(checkall);
      String[] topLblText = {"순위", "앨범사진", "노래제목", "가수명", "앨범명"};
      Font font = new Font("나눔바른펜", Font.BOLD, 15);
      for (int i = 1; i < topLbl.length; i++) {
         topLbl[i] = new JLabel();
         topLbl[i].setText(topLblText[i]+"\t");
         topLbl[i].setFont(font);
         topPanel.add(topLbl[i]);
      }
      bigP.add(topPanel,"North");

      //centerPanel 영역 ---------------------------------------------
      data = new ArrayList<>();
      dbc = new AlbumDAOImpl();
      centerPanel = new JPanel();
      //centerPanel.setLayout(new GridLayout(data.size(), 1, 0, 0));

      
      //bigbig.setLayout(new GridLayout(8, 1));
      centerPanel.setLayout(new GridLayout(0, 1,0,10));
      //centerPanel.setLayout(new FlowLayout());
      
      add(big_al);

      centerPanel.removeAll();
      centerPanel.setBackground(Color.WHITE);
      data.clear();
      chkIDs.clear();
      data = dbc.callMusic();
      rowPanel = new JPanel[data.size()];
     // chk = new JCheckBox();
      ids = new int[data.size()];
      chk= new JCheckBox[data.size()];
      for (int i = 0; i < data.size(); i++) {
         rowPanel[i] = new JPanel();
         rowPanel[i].setLayout(new GridLayout(1,7));

         Integer id = data.get(i).getNo();

         chk[i] = new JCheckBox();
         //chk.setBounds(120, 25, 50, 50);
         chk[i].setBackground(Color.white);
         chk[i].addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
               if(e.getStateChange() == e.SELECTED){
                  chkCount++;
                  chkIDs.add(id);
                  //System.out.println(chkIDs);

               }else if(e.getStateChange() == e.DESELECTED){
                  chkCount--;
                  chkIDs.remove(id);
                  //System.out.println(chkIDs);
               }                    

            }
         });

         String alName = data.get(i).getAlName();
         String singer = data.get(i).getSinger();
         //String date = data.get(i).getDate();
         String musicName = data.get(i).getMusicName();
         String pic = data.get(i).getPicFile();
         int countp = data.get(i).getCount();

         JLabel[] contentsLbl = new JLabel[5];
         for (int j = 0; j < contentsLbl.length; j++) {
            contentsLbl[j] = new JLabel();
         }

         Image img = new
               ImageIcon(pic).getImage();
         Image icb=img.getScaledInstance(50, 50,Image.SCALE_SMOOTH);
         ImageIcon ica=new ImageIcon(icb);

         contentsLbl[0].setText(i+1+".  ("+countp+"회 재생됨)");
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
         rowPanel[i].add(contentsLbl[0]);
         rowPanel[i].add(contentsLbl[1]);
         rowPanel[i].add(contentsLbl[2]);
         rowPanel[i].add(contentsLbl[3]);
         rowPanel[i].add(contentsLbl[4]);

         centerPanel.add(rowPanel[i]);
         JScrollPane bigbig=new JScrollPane(centerPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
         bigP.add(bigbig,"Center");
      }

   }    

   public int getChk(){
      return chkCount;
   }

   //public int getChkID(){
   //   if(chkIDs.size()==1){
   //      chkID = chkIDs.get(0);
   //   }
   //   return chkID;
   //}

   public LinkedList<Integer> getChkIDs(){
      return chkIDs;
   }


	   @Override
	   public void actionPerformed(ActionEvent e) {

	   	if(e.getSource()==jjim) {
	   		if(Main.id==null) {
	   			messageBox(this,"로그인 후 이용해주세요.");
	   		}else {//로그인 되어있는경우
	   			dao.addMymusic(Main.id,chkIDs);
	   			//tostring 이랑 subString
	   			if (dao.addMymusic(Main.id,chkIDs)> 0) {
	   				messageBox(this,  "노래가 추가되었습니다.");//dao.addMymusic(Main.id,chkIDs)+"개
	   			}else{
	   				messageBox(this,"노래 저장실패했습니다.");
	   			}
	   		}
	   	}else if(e.getSource()==listen) {
	   		if(Main.id==null) {
	   			messageBox(this,"로그인 후 이용해주세요.");
	   		}else {//로그인 되어있는경우
	   			if(dao.findinfo(Main.id).getM_left()<1) {//일수가 없는 경우
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
	   	if(e.getSource()==ch) {
	   		if(ch.isSelected()==true) {
	   			for (int i = 0; i < data.size(); i++) {
	   				chk[i].setSelected(true);
	   			}
	   		}else {
	   			for (int i = 0; i < data.size(); i++) {
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

