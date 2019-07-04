package com.melon.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.melon.domain.AlbumVO;



public class AlbumDAOImpl {

	private ArrayList<AlbumVO> data = new ArrayList<>();

	String driver="oracle.jdbc.OracleDriver";
	//oracle.jdbc는 패키지명,OracleDriver는 오라클 jdbc드라이버 클래스명
	String url="jdbc:oracle:thin:@127.0.0.1:1521:xe";
	//오라클 접속주소, 1521은 포트번호, xe디비명
	String user="week";//오라클 접속 사용자
	String pwd="week";//접속 비번

	Connection con=null;//오라클 연결 con
	PreparedStatement pt=null;//쿼리문 수행 pt
	Statement st=null;//쿼리문 수행 st
	ResultSet rs=null;//select검색 결과 레코드를 저장할 rs
	String sql=null;//쿼리문 저장변수

	//public AlbumDAOImpl() {
	//try {
	//	Class.forName(driver);
	//	con=DriverManager.getConnection(url, user, pwd);
	//}catch(Exception e) {e.printStackTrace();}
	//}//기본생성자
	//연결닫기
	//public void dbClose() {
	//	try {
	//		if(rs!=null)rs.close();
	//		if(pt!=null)pt.close();
	//		if(st!=null)st.close();			
	//}catch(Exception e) {e.printStackTrace();}
	//}

	//음악 추가
	public int insertMusic(AlbumVO a){
		int re=-1;//실패시 반환값
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, pwd);

			String sql = "insert into albumD (m_num, m_alname, m_date, m_singer,m_musicname, m_picFile, m_musicFile, m_count) values (m_num.NEXTVAL,?, ?, ?, ?,?, ?,'0')";                    

			pt = con.prepareStatement(sql);
			pt.setString(1, a.getAlName());
			pt.setString(2, a.getDate());
			pt.setString(3, a.getSinger());
			pt.setString(4, a.getMusicName());
			pt.setString(5, a.getPicFile());
			pt.setString(6, a.getMusicFile());

			re=pt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{
				if(pt != null) pt.close();
				if(con != null) con.close();
			}catch(Exception e) {e.printStackTrace();}        				
		}
		return re;
	}

	/******/
	////검색
	public ArrayList<AlbumVO> mainFind(String fieldName, String findWord) {
		//System.out.println("컬럼명:"+fieldName);
		//System.out.println("검색어:"+findWord);

		String fieldName2=null;
		ArrayList<AlbumVO> findresult=new ArrayList<>();
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, pwd);

			if(fieldName.trim().equals("가수")) {
				fieldName2="m_singer";
			}else if(fieldName.trim().equals("앨범명")) {
				fieldName2="m_alname";
			}else if(fieldName.trim().equals("노래제목")) {
				fieldName2="m_musicname";
			}
			sql="select * from albumD where "+fieldName2+" like '%"+findWord+"%'";
			pt=con.prepareStatement(sql);
			rs=pt.executeQuery();

			while (rs.next()) {// next()는 검색된 다음레코드가 존재
				// 하면 참(true)
				AlbumVO al=new AlbumVO();
				al.setNo(rs.getInt(1));
				al.setAlName(rs.getString(2));
				al.setDate(rs.getString(3));
				al.setSinger(rs.getString(4));
				al.setMusicName(rs.getString(5));
				al.setPicFile(rs.getString(6));
				al.setMusicFile(rs.getString(7));
				al.setCount(rs.getInt(8));
				findresult.add(al);

			} // while
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{
				if(pt != null) pt.close();
				if(con != null) con.close();
			}catch(Exception e) {e.printStackTrace();}    
		}
		return findresult;
	}


	//일련번호
	public void numb(AlbumVO a){
		//int re=-1;//실패시 반환값
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, pwd);

			String sql = "select m_num from albumD";
			int re = 1;
			pt = con.prepareStatement(sql);
			rs=pt.executeQuery(sql);
			while(rs.next()) {
				re=rs.getInt(1)+1;
			}
			a.setNo(re);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{
				if(rs!=null)rs.close();
				if(pt != null) pt.close();
				if(con != null) con.close();
			}catch(Exception e) {e.printStackTrace();}   
		}
	}

	//일련번호로 노래정보 불러오기
	public AlbumVO fromnumb(int a){
		AlbumVO al=new AlbumVO();
		//int re=-1;//실패시 반환값
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, pwd);

			String sql = "select * from albumD where m_num=?";
			pt = con.prepareStatement(sql);
			pt.setInt(1, a);
			rs=pt.executeQuery();

			while (rs.next()) {// next()는 검색된 다음레코드가 존재
				// 하면 참(true)

				al.setNo(rs.getInt(1));
				al.setAlName(rs.getString(2));
				al.setDate(rs.getString(3));
				al.setSinger(rs.getString(4));
				al.setMusicName(rs.getString(5));
				al.setPicFile(rs.getString(6));
				al.setMusicFile(rs.getString(7));
				al.setCount(rs.getInt(8));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{
				if(rs!=null)rs.close();
				if(pt != null) pt.close();
				if(con != null) con.close();
			}catch(Exception e) {e.printStackTrace();}   
		}
		return al;
	}

	//노래리스트배열
	public ArrayList<AlbumVO> callMusic() {
		ArrayList<AlbumVO> music=new ArrayList<>();
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, pwd);

			sql = "select * from albumD order by m_count desc";
			st = con.createStatement();// 쿼리문 실행 객체 생성
			rs = st.executeQuery(sql);// 검색 쿼리문 실행해서 결과
			// 레코드 목록을 rs에 저장

			while (rs.next()) {// next()는 검색된 다음레코드가 존재
				// 하면 참(true)
				AlbumVO al=new AlbumVO();
				al.setNo(rs.getInt(1));
				al.setAlName(rs.getString(2));
				al.setDate(rs.getString(3));
				al.setSinger(rs.getString(4));
				al.setMusicName(rs.getString(5));
				al.setPicFile(rs.getString(6));
				al.setMusicFile(rs.getString(7));
				al.setCount(rs.getInt(8));
				music.add(al);

			} // while
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{
				if(rs!=null)rs.close();
				if(st != null) st.close();
				if(con != null) con.close();
			}catch(Exception e) {e.printStackTrace();}   
		}
		return music;
	}

	//앨범별리스트불러오기
	public ArrayList<AlbumVO> findMusics(String alnam) {
		ArrayList<AlbumVO> music=new ArrayList<>();
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, pwd);

			sql = "select * from albumD where m_alname=?";
			pt = con.prepareStatement(sql);// 쿼리문 실행 객체 생성
			pt.setString(1, alnam);
			rs = pt.executeQuery();// 검색 쿼리문 실행해서 결과
			// 레코드 목록을 rs에 저장

			while (rs.next()) {// next()는 검색된 다음레코드가 존재
				// 하면 참(true)
				AlbumVO al=new AlbumVO();
				al.setNo(rs.getInt(1));
				al.setAlName(rs.getString(2));
				al.setDate(rs.getString(3));
				al.setSinger(rs.getString(4));
				al.setMusicName(rs.getString(5));
				al.setPicFile(rs.getString(6));
				al.setMusicFile(rs.getString(7));
				al.setCount(rs.getInt(8));
				music.add(al);

			} // while
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{
				if(rs!=null)rs.close();
				if(pt != null) pt.close();
				if(con != null) con.close();
			}catch(Exception e) {e.printStackTrace();}   
		}
		return music;
	}


	public void countup(int no,int countp) {
		try{
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, pwd);
			sql="update AlbumD set m_count="+countp+"where m_num=?";
			pt=con.prepareStatement(sql);
			pt.setInt(1,no);			

			pt.executeUpdate();

		}catch(Exception e) {e.printStackTrace();}
		finally {try{
			if(rs!=null)rs.close();
			if(pt != null) pt.close();
			if(con != null) con.close();
		}catch(Exception e) {e.printStackTrace();}
		}
	}

	//앨범이름 불러오기
	public List<String> findAlbum() {
		List<String> albumlists=new LinkedList<>();
		List<String> albumlist = null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, pwd);

			sql = "select m_alname from albumD";
			pt = con.prepareStatement(sql);// 쿼리문 실행 객체 생성
			rs = pt.executeQuery();// 검색 쿼리문 실행해서 결과
			// 레코드 목록을 rs에 저장

			while (rs.next()) {// next()는 검색된 다음레코드가 존재
				// 하면 참(true)
				albumlists.add(rs.getString("m_alname"));
				//System.out.println(rs.getString("m_alname"));
			} // while
			albumlist = new ArrayList<String>(new HashSet<String>(albumlists));//중복값 제거

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{
				if(rs!=null)rs.close();
				if(pt != null) pt.close();
				if(con != null) con.close();
			}catch(Exception e) {e.printStackTrace();}   
		}
		return albumlist;
	}
	public int editMain(LinkedList<String> chkIDs) {//'1'저장하기//전부 0으로 바꾸고 '1'선택한것만 바꾸기		
		int re=0;//성공?
		
		//String ms=null;
		//List<Integer> items = new ArrayList<Integer>();
		//List<Integer> uniqueItems = null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, pwd);
			//String sql =// "select m_mymusic from memberTT where m_id=?";
			//"update memberTT set m_mymusic='"+list+"'+(select m_mymusic from memberTT where m_id=?)";                   
			sql="update MainTT set a_name='"+chkIDs+"'";
			pt=con.prepareStatement(sql);
			re=pt.executeUpdate();

			//pt.setString(1,id);
		}catch(Exception e) {e.printStackTrace();}
		finally {try{
			if(rs!=null)rs.close();
			if(pt != null) pt.close();
			if(con != null) con.close();
		}catch(Exception e) {e.printStackTrace();}
		}
		return re;//숫자개수세서 추가
	}
	
	public String callEditMain() {//'1'저장하기//전부 0으로 바꾸고 '1'선택한것만 바꾸기		
		String gg=null;
		//String ms=null;
		//List<Integer> items = new ArrayList<Integer>();
		//List<Integer> uniqueItems = null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, pwd);
			//String sql =// "select m_mymusic from memberTT where m_id=?";
			//"update memberTT set m_mymusic='"+list+"'+(select m_mymusic from memberTT where m_id=?)";                   
			sql="select * from MainTT where a_num=1";
			
			pt = con.prepareStatement(sql);
			rs=pt.executeQuery(sql);
			while(rs.next()) {
				gg=rs.getString("a_name");
			}
			
		}catch(Exception e) {e.printStackTrace();}
		finally {try{
			if(rs!=null)rs.close();
			if(pt != null) pt.close();
			if(con != null) con.close();
		}catch(Exception e) {e.printStackTrace();}
		}
		
		return gg;
	}
	public void SongSelectAll(DefaultTableModel dt) {
		try{
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, pwd);
			sql="select * from albumD";
			st=con.createStatement();
			rs=st.executeQuery(sql);

			for(int i=0;i<dt.getRowCount();){
				dt.removeRow(0);
			}
			while(rs.next()){
				//{"일련번호","앨범명","노래명","가수","발매일","스트리밍수"};
				String[] dataAA={
						rs.getString("m_num"),
						rs.getString("m_alname"),
						rs.getString("m_musicName"),
						rs.getString("m_singer"),
						rs.getString("m_date"),
						rs.getString("m_count")
				};
				dt.addRow(dataAA);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null)rs.close();
				if(st != null) st.close();
				if(con != null) con.close();
			}catch(Exception e) {e.printStackTrace();}
		}

	}//회원정보조회

	public int delMusic(String no) {
		int re=0;
		try{
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, pwd);
			sql="delete from AlbumD where m_num=?";
			pt=con.prepareStatement(sql);
			pt.setString(1,no);
			re=pt.executeUpdate();
		}catch(Exception e){e.printStackTrace();}
		finally {
			try{
				if(rs!=null)rs.close();
				if(pt != null) pt.close();
				if(con != null) con.close();
			}catch(Exception e) {e.printStackTrace();}   

		}//delMember() 	
	
		return re;
	}

	public void getUserFindAll(DefaultTableModel dt, String text) {
		try{
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, pwd);
			st=con.createStatement();
			sql="select * from AlbumD where m_alname like '%"+text+"%' or m_singer like '%"+text+"%'";
			rs=st.executeQuery(sql);

			for(int i=0;i<dt.getRowCount();){
				dt.removeRow(0);
			}
			while(rs.next()){
				Object data[]={
						rs.getString("m_num"),
						rs.getString("m_alname"),
						rs.getString("m_musicName"),
						rs.getString("m_singer"),
						rs.getString("m_date"),
						rs.getString("m_count") 

				};
				dt.addRow(data);
			}
		}catch(Exception e){
			e.printStackTrace();

		}finally{
			try{
				if(rs!=null)rs.close();
				if(pt != null) pt.close();
				if(con != null) con.close();
			}catch(Exception e) {e.printStackTrace();}
		}
	}

	public void getUserFind(DefaultTableModel dt, String fileName, String text) {
		try{
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, pwd);
			st=con.createStatement();
			sql="select * from AlbumD where "+fileName+" like '%"+text+"%'";
			rs=st.executeQuery(sql);

			for(int i=0;i<dt.getRowCount();){
				dt.removeRow(0);
			}
			while(rs.next()){
				Object data[]={
						rs.getString("m_num"),
						rs.getString("m_alname"),
						rs.getString("m_musicName"),
						rs.getString("m_singer"),
						rs.getString("m_date"),
						rs.getString("m_count") 

				};
				dt.addRow(data);
			}
		}catch(Exception e){
			e.printStackTrace();

		}finally{
			try{
				if(rs!=null)rs.close();
				if(pt != null) pt.close();
				if(con != null) con.close();
			}catch(Exception e) {e.printStackTrace();}
		}

	}
/*
	public int addAl(String a) {
			int re=-1;//실패시 반환값
			List<String> albumlists=new LinkedList<>();
			List<String> albumlist = null;
			
			try {
				Class.forName(driver);
				con=DriverManager.getConnection(url, user, pwd);

				sql = "select m_alname from albumD";
				pt = con.prepareStatement(sql);// 쿼리문 실행 객체 생성
				rs = pt.executeQuery();// 검색 쿼리문 실행해서 결과
				// 레코드 목록을 rs에 저장
				while(rs.next()){
					albumlists.add(rs.getString("m_alname"));
				}
				System.out.println(albumlists.contains(a));
				
				Boolean pe=albumlists.contains(a);
				
				
					String sql = "insert into MainAlbum (a_name,a_ismain) values (?,'0')";                    

					pt = con.prepareStatement(sql);
					pt.setString(1, a);
				System.out.println("이미포함X");	
				re=pt.executeUpdate();
				System.out.println(re);
				}
				

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try{
					if(pt != null) pt.close();
					if(con != null) con.close();
				}catch(Exception e) {e.printStackTrace();}        				
			}
			return re;
		}
*/
}
