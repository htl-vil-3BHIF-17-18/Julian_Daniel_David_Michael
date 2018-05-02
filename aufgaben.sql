CREATE TABLE tasks(
  id INTEGER NOT NULL,
	fach varchar(10) NOT NULL,
	aufgabe varchar(50) NOT NULL,
	datum date NOT NULL,
	status VARCHAR(20),
  CONSTRAINT pk PRIMARY KEY(id)
);