DROP TABLE tasks CASCADE CONSTRAINTS;

CREATE TABLE tasks(
fach varchar(30) NOT NULL,
erledigt boolean,
aufgabe varchar(50) NOT NULL,
datum date NOT NULL
);