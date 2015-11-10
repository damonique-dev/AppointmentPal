
CREATE TABLE APPOINTMENTPAL.USER(
	NAME VARCHAR(50) NOT NULL,
	UUID VARCHAR(23) NOT NULL,
	EMAIL VARCHAR(100) NOT NULL,
	PHONENO VARCHAR(15) NOT NULL,
	HASH VARCHAR(80) NOT NULL,

);

--i used the application to create user entries 


create table appointmentpal.doctor(
doctorname varchar(100) not null,
email varchar(100) not null,
address varchar(100) not null,
practicename varchar(100) not null);


insert into appointmentpal.doctor (doctorname, email, address, practicename) values(
"Dr. Smith", "drsmith@gmail.com", "3401 morse road crossing, columbus OH 43215", "Dr. Smith's Smiles");

insert into appointmentpal.doctor (doctorname, email, address, practicename) values(
"Dr. Adam Smith", "drwest@gmail.com", "1984 N. High Street, Columbus OH 43210", "Adam the Chiropractor");



create table appointmentpal.appointment (
date DATETIME not null,
doctoremail varchar(100) not null,
uuid varchar(100) not null,
doctorname varchar(100) not null,
location varchar(100) not null);

insert into appointmentpal.appointment (date, doctoremail, uuid, doctorname, location) values
("2015-12-04 09:00:00", "drsmith@gmail.com", "f05bd060-875f-11e5-b205-57a99bffdd30", "Dr. Smith", "3401 morse road crossing, columbus OH 43215"),
("2015-12-04 11:00:00", "drwest@gmail.com", "f05bd060-875f-11e5-b205-57a99bffdd30", "Dr. Adam Smith", "1984 N. High Street, Columbus OH 43210"),
("2015-12-04 10:00:00", "drsmith@gmail.com", "f05bd060-875f-11e5-b205-57a99bffdd30", "Dr. Smith", "3401 morse road crossing, columbus OH 43215");