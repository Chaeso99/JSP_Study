create table tblJoin(
	id char(20) primary key,
	pwd char(20) not null,
	name char(20) not null,
	email char(30),
	hp char(40), --홈페이지
	grade char(2) default '0' --일반 사용자와 admin 계정을 구분하기 위한 값. admin은 1임
);

create table tblGuestBook( --방명록
	num int primary key auto_increment,
	id char(20) not null, --누가 썼는지(원래는 tblJoin과 FK연결해야함)
	contents text,
	ip char(15) not null, --ip주소는 필수로 받아와야함(클라이언트의 ip임)
	regdate date,
	regtime datetime,
	secret char(2) default '0' --일반글은 0, 비밀글은 1
);

create table tblComment(
 	cnum int primary key auto_increment,
 	num int not null,
 	cid char(20) not null,
 	comment text,
 	cip char(15) not null,
 	cregDate date
);




