drop database if exists dbdemo;

# Aufgabe 1
create database dbdemo;
use dbdemo;

create table mitarb(
	nr INTEGER auto_increment not null auto_increment primary key,
    name varchar(100),
    vorname varchar(100),
    gehalt double
);

#Aufgabe2
INSERT INTO mitarb (nr, name, vorname, gehalt) 
VALUES
(23, "Klaubmann", "Anita", 4600.5),
(8, "Hutmann", "Peter", 2684.0),
(269, "Fischer", "Hans", NULL);

#Aufgabe 3
Select * from mitarb;

#Aufgabe 4
