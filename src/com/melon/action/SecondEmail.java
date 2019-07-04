package com.melon.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.melon.persistence.MemberDAOImpl;

public class SecondEmail { //전역변수 사용 안함
   
	//action
   public void Name_Email(String name,String email){
         Properties p = System.getProperties();
          p.put("mail.smtp.starttls.enable", "true"); //gmail은 무조건 true로 고정 2
          p.put("mail.smtp.host", "smtp.gmail.com");  //smtp 서버 호스트 4
          p.put ("mail.smtp.ssl.trust", "smtp.gmail.com");//3
          p.put("mail.smtp.auth","true");            //gmail은 무조건 true 고정 1
          p.put("mail.smtp.port", "587");            //gmil 포트 5
             
          Authenticator auth = new MyAuthentication();//구글계정인증
          
          //session 생성 및 Mimessage생성
          Session session = Session.getDefaultInstance(p, auth);
          MimeMessage msg = new MimeMessage(session);
          
          
          MemberDAOImpl md=new MemberDAOImpl();
          //List<String> getlist=md.getIdList(name, email);
          //String sc ="";
          //if(getlist.size()>0){
          //   for(int i=0;i<getlist.size();i++){
          //      sc+=(String)getlist.get(i)+" ";
          //  }
          //}
          String sc=md.getId(name, email);
          
          String charSet = "UTF-8" ;
          
          try{
              msg.setSentDate(new Date());//편지 보낸시간
              String fromName = "Manager" ;
              InternetAddress from = new InternetAddress() ;
              try{
              from = new InternetAddress(new String(fromName.getBytes(charSet), 
            		"8859_1") + "<susan4398@gmail.com>");//송신자 설정
            
              }catch(UnsupportedEncodingException uee){
              uee.printStackTrace();
              }
              msg.setFrom(from);//이메일 발신자
              InternetAddress to = new InternetAddress(email);
              msg.setRecipient(Message.RecipientType.TO, to);
              msg.setSubject(name+" 님의 아이디 정보를 발송해 드립니다.", charSet);
              msg.setText(name+" 님의 아이디는 "+sc+"입니다.", "euc-kr");
              //본문이 깨져서 국내용 유니코드로 변경하였음
              msg.setHeader("content-Type", "text/html"); //이메일 헤더가 본문내용으로 발송
               
              javax.mail.Transport.send(msg);
              
          }catch (AddressException addr_e) {
              addr_e.printStackTrace();
          }catch (MessagingException msg_e) {
              msg_e.printStackTrace();
          }
      }

   public void pwd_Email(String id, String email) {
      Properties p = System.getProperties();
          p.put("mail.smtp.starttls.enable", "true");
          p.put("mail.smtp.host", "smtp.gmail.com"); 
          p.put("mail.smtp.auth","true");            
          p.put("mail.smtp.port", "587");            
             
          Authenticator auth = new MyAuthentication();
          Session session = Session.getDefaultInstance(p, auth);
          MimeMessage msg = new MimeMessage(session);
          
          MemberDAOImpl md=new MemberDAOImpl();
          String getlist=md.getPwd(id, email);
          String sc =getlist.toString();
          String charSet = "UTF-8" ;
          try{
              msg.setSentDate(new Date());
              String fromName = "관리자" ;
              InternetAddress from = new InternetAddress() ;
              try{
                  from = new InternetAddress(new String(fromName.getBytes(charSet), 
                		  "8859_1") + "<susan4398@gmail.com>");
                 
                  }catch(UnsupportedEncodingException uee){
                  uee.printStackTrace();
                  }
              msg.setFrom(from);
              InternetAddress to = new InternetAddress(email);
              msg.setRecipient(Message.RecipientType.TO, to);
              msg.setSubject(id+" 님의 정보를 발송해 드립니다.", "UTF-8");
              msg.setText(id+" 님의 비밀번호는 "+sc+" 입니다.", "euc-kr");
              msg.setHeader("content-Type", "text/html"); //이메일 헤더가 본문내용으로 발송
               
              javax.mail.Transport.send(msg);
              
          }catch (AddressException addr_e) {
              addr_e.printStackTrace();
          }catch (MessagingException msg_e) {
              msg_e.printStackTrace();
          }
   }
}

class MyAuthentication extends Authenticator {//이메일 발송관련 소스 수정주의
    
    PasswordAuthentication pa;
    
 
    public MyAuthentication(){
         
        String id = "susan4398@gmail.com";
        String pw = "kklyxipqljunmfog"; 
        pa = new PasswordAuthentication(id, pw);
      
    }
    public PasswordAuthentication getPasswordAuthentication() {
        return pa;
    }
}
