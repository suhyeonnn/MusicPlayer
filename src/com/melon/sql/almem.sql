create table albumD(
m_num number(38) primary key
,m_alname varchar(100) NOT NULL
,m_date varchar(100) NOT NULL
,m_singer varchar(200) NOT NULL
,m_musicName varchar(200) NOT NULL
,m_picFile varchar(200) NOT NULL
,m_musicFile varchar(300) NOT NULL
,m_count number(38) DEFAULT '0' NOT NULL
);

create sequence m_num
start with 1
increment by 1
nocache;

update albumD set m_musicFile='C:/Users/82103/Downloads/WORK/firstproject/music/밤편지.wav' where m_musicName='밤편지';
--'C:/Users/82103/Downloads/WORK/firstproject/music/'
--drop sequence m_num;
delete m_num=19 from AlbumD;
insert into albumD values(m_num.nextval,'좋은날','2018-08-08','아이유','아이유좋은날','좋은날','0');

select * from albumD;
select * from albumD;
--select m_picfile 
delete from albumD where m_num=20;

drop table MainTT;
--drop table memberTT;
select * from MainAlbum where a_ismain='0';
--memberT테이블 생성
create table memberTT(
m_id varchar2(50) primary key--아이디
,m_pwd varchar2(200)--회원비번
,m_name varchar2(200)--회원이름
,m_email varchar2(200)--이메일
,m_date date--가입날짜
,m_left number(38) DEFAULT '0' NOT NULL--남은 기간
,m_mymusic varchar2(200)
,m_phone varchar(200)
);
insert into memberTT values('admin','aaaaaa','관리자','susan4398@hanmail.net',null,1000,null,null);
select m_mymusic from memberTT where m_id='qqqqqq';
--update memberTT set m_mymusic='7,8'+(select m_mymusic from memberTT where m_id='qqqqqq');
update memberTT set m_left=30+(select m_left from memberTT where m_id='qkrtngus123');


create table memberTT(
m_id varchar2(50) primary key--아이디
,m_pwd varchar2(200)--회원비번
,m_name varchar2(200)--회원이름
,m_email varchar2(200)--이메일
,m_date date--가입날짜
,m_left number(38) DEFAULT '0' NOT NULL--남은 기간
,m_mymusic varchar2(200)
,m_phone varchar(200)
);

create table AdminTT(
m_id varchar2(50) primary key--아이디
,m_pwd varchar2(200)--회원비번
);
 

create table MainTT(
a_name varchar2(400) primary key--앨범이름
,a_num number(38)--고정값
);
drop table MainTT;

insert into MainTT values('admin',1);
ALTER TABLE MainAlbum 

ADD CONSTRAINT  a_name FOREIGN KEY (m_alname)

REFERENCES albumD(m_alname);

ALTER TABLE MainAlbum

ADD FOREIGN KEY (a_name)

REFERENCES albumD(m_alname);




--앨범명,가수명,발매일,노래수,메인
insert into MainAlbum values('장범준 3집','1');
insert into MainAlbum values('sleepless in __________','1');
insert into MainAlbum values('Spring','1');
insert into MainAlbum values('FLY HIGH PROJECT #2 `옥탑방`','1');
insert into MainAlbum values('White Wind','1');
insert into MainAlbum values('Our love is great','1');
insert into MainAlbum values('밤편지','0');
select * from MainTT;

delete from MainAlbum where a_name='zva';
update MainAlbum set a_ismain='0'
insert into AdminTT values('admin','aaaaaa');