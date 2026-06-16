# drop Database
DROP DATABASE IF EXISTS dbtp26;
# create db
CREATE DATABASE IF NOT EXISTS dbtp26;

# change db
USE dbtp26;

CREATE TABLE import (
  KundenNr INT,
  Kunde  VARCHAR(200),
  BeraterID INT,
  Berater VARCHAR(200),
  Stundensatz DEC(10,2),
  Stunden INT
);


# Daten einfügen
INSERT INTO import(KundenNr,Kunde,BeraterID,Berater,Stundensatz,Stunden)
VALUES
(1,'Emil Schmidt',10,'Helena Meier',50.00,3),
(1,'Emil Schmidt',20,'Ingo Fuchs',45.00,5),
(1,'Emil Schmidt',30,'John Müller',60.00,7),
(1,'Emil Schmidt',40,'Elisabeth Schulz',30.00,8),
(2,'Hans Müller',20,'Ingo Fuchs',45.00,4),
(2,'Hans Müller',30,'John Müller',60.00,6),
(3,'Johanna Wedenig',10,'Helena Meier',50.00,2),
(3,'Johanna Wedenig',30,'John Müller',60.00,30),
(4,'Markus Hübner',10,'Helena Meier',50.00,10),
(4,'Markus Hübner',20,'Ingo Fuchs',45.00,5),
(4,'Markus Hübner',40,'Elisabeth Schulz',30.00,5),
(5,'Markus Maier',30,'John Müller',60.00,12);


CREATE TABLE kunde (
  k_nr INT NOT NULL PRIMARY KEY,
  k_vname   VARCHAR(200) NOT NULL,
  k_nname   VARCHAR(200) NOT NULL
);

CREATE TABLE berater (
  b_id  INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  b_vname  VARCHAR(200) NOT NULL,
  b_nname  VARCHAR(200) NOT NULL
);

CREATE TABLE beratungen (
  k_nr INT NOT NULL,
  b_id INT NOT NULL,
  stundensatz DECIMAL(10,2) NOT NULL,
  stunden INT NOT NULL,
  PRIMARY KEY (k_nr,b_id),
  CONSTRAINT fk_kunde FOREIGN KEY (k_nr) REFERENCEs kunde (k_nr),
  CONSTRAINT fk_berater FOREIGN KEY (b_id) REFERENCEs berater (b_id)
);


