package com.melon.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.melon.domain.AlbumVO;
import com.melon.persistence.AlbumDAOImpl;
import com.melon.persistence.MemberDAOImpl;


public class OneAlbum extends JFrame implements ActionListener{

   //전역 변수 설정
   Font f1=new Font("나눔바른펜",Font.PLAIN,15);
   Font f2=new Font("나눔바른펜",Font.PLAIN,30);
   Font f3=new Font("나눔바른펜",Font.PLAIN,12);
   private JPanel topPanel; // 카테고리 라벨들을 붙일 패널
   private JLabel[] topLbl; // 카테고리 라벨들

   private ArrayList<AlbumVO> dataA = new ArrayList<>();
   private AlbumDAOImpl daoA= new AlbumDAOImpl(); 
   MemberDAOImpl daoM=new MemberDAOImpl();
   private JPanel centerPanel; // rowPanel 들을 붙일 패널


   private int chkCount = 0; // 체크박스를 선택한 개수를 담을 변수
   private JCheckBox chk; // 체크박스 
   //private int[] ids;

   private static int chkID;
   private static LinkedList<Integer> chkIDs = new LinkedList<>();
   
   JButton listen=new JButton("듣기");
   JButton jjim=new JButton("담기");
   

   public OneAlbum(String alnam) {//메인이든 앨범 선택했을때 OneAlbum(pic네임으로 앨범찾아서);
      ImageIcon icon=new ImageIcon("./img/DoMain/melone.png");
      setIconImage(icon.getImage());//상단바 아이콘
      listen.setContentAreaFilled(false);
      jjim.setContentAreaFilled(false);
      listen.setBorderPainted(false);
      jjim.setBorderPainted(false);
      jjim.setFocusPainted(false);
      listen.setFocusPainted(false);
      listen.setFont(f1);
      jjim.setFont(f1);
      setTitle("앨범창");
      setDefaultCloseOperation(OneAlbum.DISPOSE_ON_CLOSE);
      setSize(600,600);
      setLayout(new BorderLayout());
      Toolkit kit=Toolkit.getDefaultToolkit();
      Dimension screenSize=kit.getScreenSize();
      setLocation(screenSize.width/5,screenSize.height/7);
      //OneAlbum r=new OneAlbum("장범준 3집");
      //add(r);
      setVisible(true);

      dataA.clear();
      daoA.findMusics(alnam);
      dataA = daoA.findMusics(alnam);

      String alNa = dataA.get(0).getAlName();
      String singe = dataA.get(0).getSinger();
      String dat = dataA.get(0).getDate();
      String musicNam = dataA.get(0).getMusicName();
      String pi = dataA.get(0).getPicFile();

      Image im = new
            ImageIcon(pi).getImage();
      Image icd=im.getScaledInstance(180,180,Image.SCALE_SMOOTH);
      ImageIcon icf=new ImageIcon(icd);


      //ent 전체패널 안에 hea(North),big_al(South)
      JPanel ent=new JPanel();
      ent.setLayout(new BorderLayout());
      JPanel hea=new JPanel();
      
      //hea안에    보더로 감싸고 중앙에 
      JPanel aaa=new JPanel();
      JPanel bbb=new JPanel();
      JPanel SPp=new JPanel();
      JPanel EPp=new JPanel();
      JPanel NPp=new JPanel();
      JPanel WPp=new JPanel();

      aaa.setLayout(new BorderLayout());
      aaa.add(SPp,"South");
      SPp.setBackground(Color.WHITE);
      aaa.add(EPp,"East");
      WPp.setBackground(Color.WHITE);
      aaa.add(NPp,"North");
      NPp.setBackground(Color.WHITE);
      aaa.add(WPp,"West");
      EPp.setBackground(Color.WHITE);
      aaa.add(bbb,"Center");
      bbb.setLayout(new BorderLayout());

      hea.setLayout(new BorderLayout());
      JPanel wes=new JPanel();
      JLabel pc=new JLabel();
      pc.setIcon(icf);
      pc.setBackground(Color.white);
      wes.add(pc);
      wes.setBackground(Color.white);
      JPanel cenbig=new JPanel();
      cenbig.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
      JPanel cen=new JPanel();
      cenbig.add(cen,"Center");
      cen.setLayout(new BoxLayout(cen,BoxLayout.Y_AXIS));
      cen.setBackground(Color.WHITE);
      JLabel aln=new JLabel("앨범명:"+alNa);
      JLabel sin=new JLabel("가수명:"+singe);
      JLabel da=new JLabel("발매일:"+dat);
      aln.setFont(new Font("나눔바른펜",Font.BOLD,25));
      sin.setFont(new Font("나눔바른펜",Font.BOLD,20));
      da.setFont(new Font("나눔바른펜",Font.BOLD,20));
      cen.add(aln);//앨범명
      cen.add(sin);//가수명
      cen.add(da);//발매일
      //pc.setHorizontalAlignment(SwingConstants.CENTER);

      bbb.add(cen,"Center");
      bbb.add(wes,"West");
      hea.add(aaa);
      ent.add(hea,"North");



      //cont에서 사방 보더레이아웃으로 주위채우기
      //setLayout(new BorderLayout());
      JPanel centerPanel=new  JPanel();


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
      jjim.addActionListener(this);
      listen.addActionListener(this);
      //스크롤 그리드레이아웃,박스

      //topPanel 영역 ------------------------------------------
      topPanel = new JPanel();
      topPanel.setLayout(new GridLayout(1,6));
      topPanel.setBackground(Color.white);
      topLbl = new JLabel[5];
      String[] topLblText = {"선택","번호", "앨범사진", "노래제목", "가수명"};
      Font font = new Font("나눔바른펜", Font.BOLD, 15);
      for (int i = 0; i < topLbl.length; i++) {
         topLbl[i] = new JLabel();
         topLbl[i].setText(topLblText[i]+"\t");
         topLbl[i].setFont(font);
         topPanel.add(topLbl[i]);
      }
      bigP.add(topPanel,"North");

      //centerPanel 영역 ---------------------------------------------


      centerPanel = new JPanel();
      //centerPanel.setLayout(new GridLayout(data.size(), 1, 0, 0));
      centerPanel.setLayout(new GridLayout(7, 5));
      bigP.add(centerPanel,"Center");
      ent.add(big_al,"South");
      //bigs.add(big_al);
      //add(bigs);
      //show method - db에서 음악 목록을 불러와서 뿌려준다.
      //public void showMethod(){
      centerPanel.removeAll();
      centerPanel.setBackground(Color.WHITE);

      chkIDs.clear();
      //alnam불러오기
      //String album명----------------------------------------
      JPanel[] rowPanel = new JPanel[dataA.size()];
      chk = new JCheckBox();
      //ids = new int[data.size()];
      for (int i = 0; i < dataA.size(); i++) {
         rowPanel[i] = new JPanel();
         rowPanel[i].setLayout(new GridLayout(1,5));

         Integer id = dataA.get(i).getNo();

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

         String alName = dataA.get(i).getAlName();
         String singer = dataA.get(i).getSinger();
         String date = dataA.get(i).getDate();
         String musicName = dataA.get(i).getMusicName();
         String pic = dataA.get(i).getPicFile();
         //String title = data.get(i).getTitle();

         JLabel[] contentsLbl = new JLabel[5];
         for (int j = 0; j < contentsLbl.length; j++) {
            contentsLbl[j] = new JLabel();
         }

         Image img = new
               ImageIcon(pic).getImage();
         Image icb=img.getScaledInstance(40, 40,Image.SCALE_SMOOTH);
         ImageIcon ica=new ImageIcon(icb);

         contentsLbl[0].setText(i+1+""); contentsLbl[0].setFont(f1);
         contentsLbl[1].setIcon(ica);//이미지 넣기
         contentsLbl[2].setText(musicName); contentsLbl[2].setFont(f1);
         contentsLbl[3].setText(singer);    contentsLbl[3].setFont(f1);
         //contentsLbl[4].setText(alName);
         rowPanel[i].setBackground(Color.WHITE);
         rowPanel[i].add(chk);
         rowPanel[i].add(contentsLbl[0]);
         rowPanel[i].add(contentsLbl[1]);
         rowPanel[i].add(contentsLbl[2]);
         rowPanel[i].add(contentsLbl[3]);
         //rowPanel[i].add(contentsLbl[4]);

         centerPanel.add(rowPanel[i]);
      }
      
      add(ent,"Center");
      setVisible(true);
      ImageIcon icon1=new ImageIcon("./img/DoMain/melon.png");///
		setIconImage(icon1.getImage());//상단바 아이콘///
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

   public static void main(String[] args) {
      
   }

   @Override
   public void actionPerformed(ActionEvent e) {

      if(e.getSource()==jjim) {
         System.out.println("확인");
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
   public static void messageBox(Object obj, String m) {
      JOptionPane.showMessageDialog((Component) obj, m);
   }

}