DROP TABLE tasks CASCADE CONSTRAINTS;

CREATE TABLE tasks(
	fach varchar(10) NOT NULL,
	aufgabe varchar(50) NOT NULL,
	datum date NOT NULL NOT NULL,
	status VARCHAR(20)
);