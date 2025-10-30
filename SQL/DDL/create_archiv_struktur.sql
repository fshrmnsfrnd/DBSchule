use dbverein;

DROP TABLE IF EXISTS archiv_mitglied;
DROP TABLE IF EXISTS archiv_link_mitglied_sportart;

CREATE TABLE archiv_mitglied (
	M_Id int(11) PRIMARY KEY,
	Vorname varchar(20),
	Nachname varchar(20), 
	Geburtsdatum datetime, 
	Geschlecht varchar(1),
	Strasse varchar(30),
	Ort_Id int(11),
	Tel varchar(20),
	Eintrittsdatum datetime
);

CREATE TABLE archiv_link_mitglied_sportart(
	M_Id int(11),
	Sport_ID int(11)
);