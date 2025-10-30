/******************* MySQL, MariaDB SQL - Workshop ******************

    SQL-Befehls-Kategorie: DML
    
    Schülerlösungen
    
    Datei: mysql.WS.DML.Set1.L.sql 
    Name: <Nachname> <Vorname>
    Version: <Datum>

*************************************************************/

#task1
use dbverein;

UPDATE dbverein.mitglied set Nachname = "Huber-Schnabl" where M_id = 72;

SELECT * from dbverein.mitglied where M_id = 72;

#Task 2
INSERT INTO sportart (Sportart, Beitrag)
VALUES
("Freiklettern", 120.0),
("Inlineskating", 30);
Select * from sportart;

#Task 3
DELETE FROM link_mitglied_sportart
WHERE Sport_ID IN(SELECT Sport_Id from sportart WHERE Sportart = "Parcour");

DELETE FROM sportart
WHERE Sportart = "Parcour";

SELECT * FROM link_mitglied_sportart;
SELECT * FROM sportart;

#Task 4
UPDATE mitglied SET kuerzel =
CONCAT(
substr(if(mitglied.nachname is not null, mitglied.nachname, ''), 1, 3),
substr(if(mitglied.vorname is not null, mitglied.vorname, ''), 1, 2)
);
Select * from mitglied;

#Task 5
UPDATE mitglied SET ASSESS =
if(Geschlecht = 'w', 
DATEDIFF(curdate(), Eintrittsdatum) / DATEDIFF(curdate(), Geburtsdatum) * 100, 
DATEDIFF(curdate(), Eintrittsdatum) / DATEDIFF(curdate(), Geburtsdatum) * 80);
Select * from mitglied;

#Task 6
ALTER TABLE mitglied ADD Tel varchar(100);
UPDATE mitglied SET Tel = Telefonnummer;
UPDATE mitglied SET Telefonnummer = Tel;
ALTER table mitglied change Vorwahl Vorwahl varchar(30);

UPDATE mitglied SET Vorwahl =
substr(Telefonnummer, 1, instr(Telefonnummer, ' - '));

UPDATE mitglied SET Telefonnummer =
substr(Telefonnummer, instr(Telefonnummer, ' - ') + 2);

Select * from mitglied;
