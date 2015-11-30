
create table patient(
	name varchar(50) not null,
	uuid varchar(40) not null,
	email varchar(100) not null,
	phoneno varchar(15) not null,
	hash varchar(80) not null,
	primary key(email)
);


create table doctor(
doctorname varchar(100) not null,
email varchar(100) not null,
address varchar(100) not null,
practicename varchar(100) not null,
primary key(email)
);

INSERT INTO  doctor VALUES
('Dr. Smith', 'drsmith@gmail.com', '3401 morse road crossing, columbus OH 43215', 'All Smiles Dentists');

INSERT INTO  doctor VALUES
('Dr. West', 'drwest@gmail.com', '1984 N. High Street, Columbus OH 43210', 'Adam West the Chiropractor');

INSERT INTO PATIENT VALUES
('Dan','f05bd060-875f-11e5-b205-57a99bffdd30', 'dan@gmail.com', '5823128', '5f4dcc3b5aa765d61d8327deb882cf99');
create table appointment(
date timestamp not null,
doctoremail varchar(100) not null,
uuid varchar(100) not null
);


insert into appointment (date, doctoremail, uuid) values
('2015-12-04 09:00:00', 'drsmith@gmail.com', 'f05bd060-875f-11e5-b205-57a99bffdd30'),
('2015-12-04 11:00:00', 'drwest@gmail.com', 'f05bd060-875f-11e5-b205-57a99bffdd30'),
('2015-12-04 10:00:00', 'drsmith@gmail.com', 'f05bd060-875f-11e5-b205-57a99bffdd30');





INSERT INTO doctor(doctorname, email, address, practicename, phoneno) 
VALUES('Mark T. Herbert, MD', 'markt@gmail.com', '777 W State St Columbus, OH 43222', 'ID Med Inc', '6142790808'),
VALUES('Ruth R. Mullowney-Agra, MD', 'ruth@gmail.com', '777 W State St Columbus, OH 43222', 'ID Med Inc', '6142790808'),
VALUES('Denise R. Taylor, MD', 'denise@gmail.com', '777 W State St Columbus, OH 43222', 'ID Med Inc', '6142790808'),
VALUES('John Mytinger, MD', 'johnm@gmail.com', '700 Childrens Dr Columbus, OH 43205','Nationwide Childrens Hospital Neurology', '6147224625'),
VALUES('Ann M. Pakalnis, MD', 'pakalnis@gmail.com', '700 Childrens Dr Columbus, OH 43205','Nationwide Childrens Hospital Neurology', '6147224625'),
VALUES('Geoffrey L. Heyer, MD', 'geoffrey@gmail.com', '700 Childrens Dr Columbus, OH 43205','Nationwide Childrens Hospital Neurology', '6147224625'),
VALUES('Warren D. Lo, MD', 'warrend@gmail.com', '700 Childrens Dr Columbus, OH 43205','Nationwide Childrens Hospital Neurology', '6147224625'),
VALUES('Richard J. Seidt II, MD', 'richardj@gmail.com', '41 S High Street Columbus OH 43215','Downtown Physicians Inc', '3495292349'),
VALUES('William A. Mains, MD', 'williammains@gmail.com', '41 S High Street Columbus OH 43215','Downtown Physicians Inc', '3495292349'),
VALUES('Richard J. Seidt II, MD', 'richardj@gmail.com', '41 S High Street Columbus OH 43215','Downtown Physicians Inc', '3495292349'),
VALUES('Christine B. Adamcak, MD', 'christine@gmail.com', '7657 E Main St Reynoldsburg, OH 43068', 'Mount Caramel Medical Group', '1234567890'),
VALUES('William J Esterline, MD', 'esterline@gmail.com', '7657 E Main St Reynoldsburg, OH 43068', 'Mount Caramel Medical Group', '1234567890'),
VALUES('Noah J. Jones, MD', 'noahj@gmail.com', '7657 E Main St Reynoldsburg, OH 43068', 'Mount Caramel Medical Group', '1234567890'),
VALUES('Ketki P. Mougey, MD', 'ketkip@gmail.com', '7657 E Main St Reynoldsburg, OH 43068', 'Mount Caramel Medical Group', '1234567890'),
VALUES('Lisa M. Peterson, MD', 'lisampeterson@gmail.com', '7657 E Main St Reynoldsburg, OH 43068', 'Mount Caramel Medical Group', '1234567890'),
VALUES('Stephen Vincent, MD', 'stephenvincent@gmail.com', '7657 E Main St Reynoldsburg, OH 43068', 'Mount Caramel Medical Group', '1234567890'),