package com.melon.domain;


public class MemberVO {//데이터 저장클래스
   /*테이블 컬럼명과 변수명을 되도록이면 일치시킨다.*/
   private String m_id;
   private String m_pwd;
   private String m_name;
   private String m_email;
   private String m_date;
   private int m_left;
   private String m_mymusic;
   private String m_phone;
  
   
   public String getM_phone() {
	return m_phone;
}
public void setM_phone(String m_phone) {
	this.m_phone = m_phone;
}
public String getM_mymusic() {
	return m_mymusic;
}
public void setM_mymusic(String m_mymusic) {
	this.m_mymusic = m_mymusic;
}
public int getM_left() {
	return m_left;
}
public void setM_left(int m_left) {
	this.m_left = m_left;
}
public String getM_id() {
      return m_id;
   }
   public void setM_id(String m_id) {
      this.m_id = m_id;
   }
   public String getM_pwd() {
      return m_pwd;
   }
   public void setM_pwd(String m_pwd) {
      this.m_pwd = m_pwd;
   }
   public String getM_name() {
      return m_name;
   }
   public void setM_name(String m_name) {
      this.m_name = m_name;
   }
   public String getM_email() {
      return m_email;
   }
   public void setM_email(String m_email) {
      this.m_email = m_email;
   }
   public String getM_date() {
      return m_date;
   }
   public void setM_date(String m_date) {
      this.m_date = m_date;
   }
   
}