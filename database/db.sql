--create tables

CREATE TABLE PATIENT(
	NAME VARCHAR(50) NOT NULL,
	UUID VARCHAR(23) NOT NULL,
	EMAIL VARCHAR(100) NOT NULL,
	PHONENO VARCHAR(15) NOT NULL,
	ENCRYPTED_PASSWORD VARCHAR(80) NOT NULL,
	SALT VARCHAR(10) NOT NULL,
	PRIMARY KEY (EMAIL)
);

CREATE TABLE DOCTOR
(NAME VARCHAR(30) NOT NULL,
UUID VARCHAR(30) NOT NULL,
EMAIL VARCHAR(30) NOT NULL,
PRACTICENAME VARCHAR(30) NOT NULL,
PHONENO VARCHAR(30) NOT NULL,
ADDRESS VARCHAR(50) NOT NULL,
ENCRYPTED_PASSWORD VARCHAR(50) NOT NULL,
SALT VARCHAR(30) NOT NULL,
PRIMARY KEY(EMAIL));

CREATE TABLE APPOINTMENT
(PEMAIL VARCHAR(30) NOT NULL,
DEMAIL VARCHAR(30) NOT NULL,
DATETIME DATE NOT NULL,
FOREIGN KEY(PEMAIL) REFERENCES PATIENT(EMAIL),
FOREIGN KEY(DEMAIL) REFERENCES DOCTOR(EMAIL));

--insert sample data

INSERT INTO PATIENT 
(FNAME, LNAME, PHONENO, ADDRESS, PASSWORD)
VALUES
('paul', 'williams', 'paul.scott.williams@gmail.com', '4406792783', '440 East Countryview Columbus OH 43210', 'we should encrypt passwords'),
('roger', 'federer', 'rfederer@gmail.com', '1234556789', '123 Broadway Seattle WA 76543', 'password should be encrypted'),
('harry', 'potter', 'hpotter@gmail.com', '8765432109', '4 Privet Drive Whinging Sy 00000', 'so they should be stored that way');

INSERT INTO DOCTOR
(FNAME, LNAME, EMAIL, PRACTICENAME, ADDRESS, PHONENO, PASSWORD)
VALUES
('Davy', 'Crockett', 'dcrockett@scurvy.com', 'Happy Smiles Dentists', '4406881234', '3401 Morse Road Crossing Columbus OH 43219', 'we should encrypt passwords'),
('Daniel', 'Boone', 'dboone@mumps.com', '1634556789', 'Daniel Boone Chiropractic', '90210 Broadstreet Montgomery AL 72543', 'password should be encrypted'),
('harry', 'caray', 'hcaray@dead.com', '8765432101', 'Harry Caray the Gynecologist', '4873 Wayside drive, Chicago IL 12345', 'so they should be stored that way');

INSERT INTO APPOINTMENT
(PEMAIL, DEMAIL, DATETIME)
VALUES
('paul.scott.williams@gmail.com', 'dcrockett@scurvy.com', '2016-10-10 08:00:00'),
('rfederer@gmail.com', 'dboone@mumps.com', '2016-10-10 08:00:00'),
('paul.scott.williams@gmail.com', 'hcaray@dead.com', '2016-10-10 09:00:00')

--create patient transaction, should fail if email is redundant
INSERT INTO patient
(FNAME, LNAME, EMAIL, PHONENO, PASSWORD)
VALUES
(#(fname), #(lname), #(email), #(phoneno), #(password));


--create appointment, should fail if datetime and demail are redundant
INSERT INTO appointment
(PEMAIL, DEMAIL, DATETIME)
VALUES
(#(pemail), #(demail), #(datetime));


--get appointments

SELECT DATETIME
FROM APPOINTMENT
WHERE PEMAIL = #(pemail)
AND DEMAIL = #(demail);


--get doctors/get locations

SELECT * FROM 
FROM DOCTOR
WHERE PRACTICENAME = #(PRACTICENAME)
AND DOCTORNAME = #(DOCTORNAME);


--delete appointment

DELETE FROM APPOINTMENT
WHERE PEMAIL = #(user)
AND DATETIME = #(DATETIME OF APPOINTMENT);
