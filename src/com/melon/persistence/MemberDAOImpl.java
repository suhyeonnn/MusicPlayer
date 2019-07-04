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
import java.util.StringTokenizer;

import javax.swing.table.DefaultTableModel;

import com.melon.domain.AdminVO;
import com.melon.domain.MemberVO;



public class MemberDAOImpl {//회원관리 jdbc클래스
	String driver="oracle.jdbc.driver.OracleDriver";
	//oracle.jdbc.driver는 패키지명,OracleDriver는 오라클
	//jdbc드라이버 클래스명
	String url="jdbc:oracle:thin:@localhost:1521:xe";
	/* 오라클 접속주소, localhost는 모든 내자신 컴퓨터,
	 * 1521은 오라클 연결 포트번호, xe는 디비명		  
	 */
	String user="week";//오라클 접속 사용자
	String pwd="week";//비번

	Connection con=null;//오라클 디비 연결 con
	Statement st=null;//쿼리문 수행 st  
	PreparedStatement pt=null;//쿼리문 수행 pt
	/* 쿼리문 수행하는 Statement와 PreparedStatement 차이점
	 *  1.많이 사용되는 PreparedStatement는 미리 쿼리문을 컴
	 *  파일하기 때문에 실행속도가 빠르다.그리고 가독성도 좋
	 *  다. 단점으로 코드라인이 길어진다.
	 *  2.Statement는 쿼리문에 직접값을 할당하기 때문에 가독
	 *  성이 떨어지고 속도가 느리다.잘 사용안된다.
	 *  3.오라클 저장프로시저를 호출해서 실행할려면 
	 *  CallableStatement을 사용한다.
	 */
	ResultSet rs=null;//select문 실행결과 검색 레코드를 저장
	//할 rs
	String sql=null;//쿼리문 저장변수

	//public MemberDAOImpl() {
	//	try {
	//	Class.forName(driver);
	//	con=DriverManager.getConnection(url, user, pwd);
	//}catch(Exception e) {e.printStackTrace();}
	//}//기본생성자
	//연결닫기
	//public void dbClose() {
	//	try {
	//	if(rs!=null)rs.close();
	//	if(pt!=null)pt.close();
	//	if(st!=null)st.close();
	//}catch(Exception e) {e.printStackTrace();}
	//}

	//아이디 중복검색
	public boolean idCheck(String id) {
		boolean re=true;//중복아이디가 없는 경우 반환값
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, pwd);
			sql="select * from memberTT where m_id=?";
			//아이디를 기준으로 회원정보를 검색
			pt=con.prepareStatement(sql);//미리 쿼리문을 컴파일하
			//여 쿼리문 실행 객체 pt생성
			pt.setString(1,id);//쿼리문 첫번째 물음표에 문장열로
			//아이디 저장
			rs=pt.executeQuery();//select문 수행후 검색 결과 레코드를 rs에 저장시킴
			if(rs.next()) {//검색된 회원정보가 있다면 참
				re=false;//중복아이디가 있는 경우 반환값
			}//if
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try{
				if(rs!=null)rs.close();
				if(pt != null) pt.close();
				if(con != null) con.close();
			}catch(Exception e) {e.printStackTrace();}   
		}
		return re;
	}//idCheck()

	//회원저장
	public int insertMember(MemberVO m) {
		int re=-1;//가입실패시 반환값
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, pwd);
			sql="insert into memberTT values(?,?,?,?,sysdate,'0',null,?)";
			pt=con.prepareStatement(sql);
			pt.setString(1, m.getM_id());
			pt.setString(2, m.getM_pwd());
			pt.setString(3, m.getM_name());
			pt.setString(4, m.getM_email());
			pt.setString(5, m.getM_phone());

			re=pt.executeUpdate();//저장쿼리문 수행후 레코드 행의 개수 반환
		}catch(Exception e) {e.printStackTrace();}
		finally {try{
			if(rs!=null)rs.close();
			if(pt != null) pt.close();
			if(con != null) con.close();
		}catch(Exception e) {e.printStackTrace();}   
		}
		return re;
	}
	//로그인 인증체크
	public MemberVO loginCheck(String id) {
		MemberVO db_id=null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, pwd);
			sql="select * from memberTT where m_id=?";
			pt=con.prepareStatement(sql);
			pt.setString(1, id);
			rs=pt.executeQuery();
			if(rs.next()) {
				db_id=new MemberVO();
				db_id.setM_id(rs.getString("m_id"));
				db_id.setM_pwd(rs.getString("m_pwd"));
			}
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try{
				if(rs!=null)rs.close();
				if(pt != null) pt.close();
				if(con != null) con.close();
			}catch(Exception e) {e.printStackTrace();}   
		}
		return db_id;
	}
	//기간 합산
	public void leftplus(String id,int day) {
		int ms=0;
		try {
			Class.forName(driver);//"update memberTT set m_left="+day+"(where m_id=?";
			con=DriverManager.getConnection(url, user, pwd);
			sql="select m_left from memberTT where m_id=?";
			pt=con.prepareStatement(sql);
			pt.setString(1,id);
			rs=pt.executeQuery();
			if(rs.next()) {
				ms=rs.getInt("m_left");
			}
			sql="update memberTT set m_left="+(day+ms)+"where m_id=?";
			pt=con.prepareStatement(sql);
			pt.setString(1,id);

			pt.executeUpdate();

		}catch(Exception e) {e.printStackTrace();}
		finally {try{
			if(rs!=null)rs.close();
			if(pt != null) pt.close();
			if(con != null) con.close();
		}catch(Exception e) {e.printStackTrace();}
		}
	}

	//회원정보 구해오기//마이리스트 조회
	public MemberVO findinfo(String id) {
		MemberVO meminfo=null;
		//int num = 0;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, pwd);
			sql="select * from memberTT where m_id=?";
			pt=con.prepareStatement(sql);
			pt.setString(1, id);
			rs=pt.executeQuery();

			if(rs.next()) {
				meminfo=new MemberVO();
				meminfo.setM_id(rs.getString("m_id"));
				meminfo.setM_pwd(rs.getString("m_pwd"));
				meminfo.setM_date(rs.getString("m_date"));
				meminfo.setM_email(rs.getString("m_email"));
				meminfo.setM_left(rs.getInt("m_left"));
				meminfo.setM_mymusic(rs.getString("m_mymusic"));
				meminfo.setM_name(rs.getString("m_name"));
				meminfo.setM_phone(rs.getString("m_phone"));

			} // while
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{
				if(rs!=null)rs.close();
				if(pt != null) pt.close();
				if(con != null) con.close();
			}catch(Exception e) {e.printStackTrace();
			}  
		}
		return meminfo;
	}

	//마이리스트 추가 ////실패시 추가
	public int addMymusic(String id,LinkedList<Integer> list){
		int re=0;//성공한 개수
		String ms=null;
		List<Integer> items = new ArrayList<Integer>();
		List<Integer> uniqueItems = null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, pwd);
			//String sql =// "select m_mymusic from memberTT where m_id=?";
			//"update memberTT set m_mymusic='"+list+"'+(select m_mymusic from memberTT where m_id=?)";                   
			sql="select m_mymusic from memberTT where m_id=?";
			pt=con.prepareStatement(sql);
			pt.setString(1,id);
			rs=pt.executeQuery();
			if(rs.next()) {
				ms=rs.getString("m_mymusic");
				System.out.println("원래 마이리스트:"+ms);
				if(ms==null) {
					uniqueItems= new ArrayList<Integer>(new HashSet<Integer>(list));
				}else {
					String before=ms.toString();
					String after=before.substring(1, before.length()-1).trim();
					System.out.println("after="+after);
					StringTokenizer tokens=new StringTokenizer(after.trim(),",");
					while(tokens.hasMoreTokens()) {

						int a;
						a=Integer.parseInt(tokens.nextToken().trim());
						items.add(a);
					}
					items.addAll(list);
					uniqueItems = new ArrayList<Integer>(new HashSet<Integer>(items));//중복값 제거
					System.out.println(uniqueItems);
				}
				//Class.forName(driver);
				//con=DriverManager.getConnection(url, user, pwd);
				sql="update memberTT set m_mymusic='"+uniqueItems+"'where m_id=?";
				pt=con.prepareStatement(sql);
				pt.setString(1,id);

				re=pt.executeUpdate();
			}
		}catch(Exception e) {e.printStackTrace();}
		finally {try{
			if(rs!=null)rs.close();
			if(pt != null) pt.close();
			if(con != null) con.close();
		}catch(Exception e) {e.printStackTrace();}
		}
		return re;//숫자개수세서 추가
	}


	//마이스트 삭제 ////실패시 추가
	public int deleteMymusic(String id,LinkedList<Integer> list){
		int re=0;//성공한 개수
		String ms=null;
		List<Integer> items = new ArrayList<Integer>();
		List<Integer> uniqueItems = null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, pwd);
			//String sql =// "select m_mymusic from memberTT where m_id=?";
			//"update memberTT set m_mymusic='"+list+"'+(select m_mymusic from memberTT where m_id=?)";                   
			sql="select m_mymusic from memberTT where m_id=?";
			pt=con.prepareStatement(sql);
			pt.setString(1,id);
			rs=pt.executeQuery();
			if(rs.next()) {
				ms=rs.getString("m_mymusic");
				//System.out.println("원래 마이리스트:"+ms);
				String before=ms.toString();
				String after=before.substring(1, before.length()-1).trim();
				System.out.println("after="+after);
				StringTokenizer tokens=new StringTokenizer(after.trim(),",");
				while(tokens.hasMoreTokens()) {

					int a;
					a=Integer.parseInt(tokens.nextToken().trim());
					items.add(a);
				}//원래 마이리스트 리스트에 추가
				items.removeAll(list);
				uniqueItems = new ArrayList<Integer>(new HashSet<Integer>(items));//중복값 제거
				System.out.println(uniqueItems);

				//Class.forName(driver);
				//con=DriverManager.getConnection(url, user, pwd);
				sql="update memberTT set m_mymusic='"+uniqueItems+"'where m_id=?";
				pt=con.prepareStatement(sql);
				pt.setString(1,id);

				re=pt.executeUpdate();
			}
		}catch(Exception e) {e.printStackTrace();}
		finally {try{
			if(rs!=null)rs.close();
			if(pt != null) pt.close();
			if(con != null) con.close();
		}catch(Exception e) {e.printStackTrace();}
		}
		return re;//숫자개수세서 추가
	}


	public void userSelectAll(DefaultTableModel dt) {
		try{
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, pwd);
			sql="select * from memberTT";
			st=con.createStatement();
			rs=st.executeQuery(sql);

			for(int i=0;i<dt.getRowCount();){
				dt.removeRow(0);
			}
			while(rs.next()){

				String[] data={
						rs.getString("m_id"),
						rs.getString("m_name"),
						rs.getString("m_phone"),
						rs.getString("m_email"),
						rs.getString("m_left"),
						rs.getString("m_date")

				};
				dt.addRow(data);
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
	public int editOther(MemberVO m) {
		int re=-1;
		try{
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, pwd);
			sql="update memberTT set m_name=?,m_pwd=?,m_phone=? where m_id=?";
			pt=con.prepareStatement(sql);
			pt.setString(1, m.getM_name());
			pt.setString(2, m.getM_pwd());
			pt.setString(3, m.getM_phone());
			pt.setString(4, m.getM_id());

			re=pt.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null)rs.close();
				if(pt != null) pt.close();
				if(con != null) con.close();
			}catch(Exception e) {e.printStackTrace();}
		}
		return re;
	}//수정한값 저장
	public List<String> getlist(String id) {
		List<String> glist=new ArrayList<>();
		try{
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, pwd);
			sql="select * from memberDAO where m_id=?";
			pt=con.prepareStatement(sql);
			pt.setString(1, id);

			pt.getConnection().prepareStatement(sql);
			rs=pt.executeQuery();

			while(rs.next()){
				glist.add(rs.getString("m_pwd"));
				glist.add(rs.getString("m_name"));
				glist.add(rs.getString("m_email"));
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
		return glist;
	}



	public String getPwd(String id, String email) {
		String db_pwd=null;

		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, pwd);
			sql="select m_pwd from memberTT where m_id=? AND m_email=?";
			pt=con.prepareStatement(sql);
			pt.setString(1, id);
			pt.setString(2, email);
			rs=pt.executeQuery();

			if(rs.next()) {
				db_pwd=rs.getString("m_pwd");

			}
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try{
				if(rs!=null)rs.close();
				if(pt != null) pt.close();
				if(con != null) con.close();
			}catch(Exception e) {e.printStackTrace();}   
		}
		return db_pwd;
	}


	public String getId(String name, String email) {//이름과 이메일 일치여부 확인후 아이디 반환
		String db_id=null;

		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, pwd);
			sql="select m_id from memberTT where m_name=? AND m_email=?";
			pt=con.prepareStatement(sql);
			pt.setString(1, name);
			pt.setString(2, email);
			rs=pt.executeQuery();

			if(rs.next()) {
				db_id=rs.getString("m_id");

			}
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try{
				if(rs!=null)rs.close();
				if(pt != null) pt.close();
				if(con != null) con.close();
			}catch(Exception e) {e.printStackTrace();}   
		}
		return db_id;
	}
	public void getUserFind(DefaultTableModel dt, String fileName, String text) {
		try{
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, pwd);
			st=con.createStatement();
			sql="select * from memberTT where "+fileName+" like '%"+text+"%'";
			rs=st.executeQuery(sql);

			for(int i=0;i<dt.getRowCount();){
				dt.removeRow(0);
			}
			while(rs.next()){
				Object data[]={
						rs.getString("m_id"),
						rs.getString("m_name"),
						rs.getString("m_phone"),
						rs.getString("m_email"),
						rs.getString("m_left"),
						rs.getString("m_date") 

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
	public void getUserFindAll(DefaultTableModel dt, String text) {
		try{
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, pwd);
			st=con.createStatement();
			sql="select * from memberTT where m_id like '%"+text+"%' or m_name like '%"+text+"%' or m_phone like'%"+text+"%' or m_email like '%"+text+"%' or m_left like'%"+text+"%' or m_date like '%"+text+"%'";
			rs=st.executeQuery(sql);

			for(int i=0;i<dt.getRowCount();){
				dt.removeRow(0);
			}
			while(rs.next()){
				Object data[]={
						rs.getString("m_id"),
						rs.getString("m_name"),
						rs.getString("m_phone"),
						rs.getString("m_email"),
						rs.getString("m_left"),
						rs.getString("m_date") 

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

	//관리자로그인 인증체크
	public AdminVO adLoginCheck(String id) {
		AdminVO db_id=null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, pwd);
			sql="select * from adminTT where m_id=?";
			pt=con.prepareStatement(sql);
			pt.setString(1, id);
			rs=pt.executeQuery();
			if(rs.next()) {
				db_id=new AdminVO();
				db_id.setM_id(rs.getString("m_id"));
				db_id.setM_pwd(rs.getString("m_pwd"));
			}
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try{
				if(rs!=null)rs.close();
				if(pt != null) pt.close();
				if(con != null) con.close();
			}catch(Exception e) {e.printStackTrace();}   
		}
		return db_id;
	}

	public boolean nameCheck(String name) {
		boolean re=true;//중복이름이 없는 경우 반환값
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, pwd);
			sql="select * from memberTT where m_name=?";
			//아이디를 기준으로 회원정보를 검색
			pt=con.prepareStatement(sql);//미리 쿼리문을 컴파일하
			//여 쿼리문 실행 객체 pt생성
			pt.setString(1,name);//쿼리문 첫번째 물음표에 문장열로
			//이름 저장
			rs=pt.executeQuery();//select문 수행후 검색 결과 레코드를 rs에 저장시킴
			if(rs.next()) {//검색된 회원정보가 있다면 참
				re=false;//중복이름이 있는 경우 반환값
			}//if
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try{
				if(rs!=null)rs.close();
				if(pt != null) pt.close();
				if(con != null) con.close();
			}catch(Exception e) {e.printStackTrace();}   
		}
		return re;
	}

	//회원삭제
	public int delMember(String id){
		int re=-1;
		try{
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, pwd);
			sql="delete from memberTT where m_id=?";
			pt=con.prepareStatement(sql);
			pt.setString(1,id);
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
}