/******************* MySQL, MariaDB SQL - Workshop ******************

    SQL-Befehls-Kategorie: DQL
    
    Schülerlösungen
    
    Datei: mysql.WS.DQL.Set3.L.sql 
    Name: <Nachname> <Vorname>
    Version: <Datum>

*************************************************************/
use dbverein;

#task1
SELECT M_Id, Nachname, Vorname, Geburtsdatum, Geschlecht, Strasse, PLZ, ORT, Tel 
FROM mitglied, ort
WHERE mitglied.Ort_Id = ort.Ort_Id;

#task2
SELECT mitglied.M_Id, Nachname, Vorname, Geburtsdatum, truncate(datediff(now(), Geburtsdatum) / 360, 0) as `Alter` 
FROM mitglied 
JOIN link_mitglied_sportart ON mitglied.M_Id = link_mitglied_sportart.M_Id
JOIN sportart ON link_mitglied_sportart.Sport_Id = sportart.Sport_Id
WHERE sportart.Sportart = "Fußball"
HAVING `Alter` <= 55 AND `Alter` >= 40
ORDER BY `Alter` asc;

#task3
SELECT mitglied.M_Id, Sportart, Vorname, Nachname, Geburtsdatum, Geschlecht, Strasse, PLZ, Ort, Tel, Beitrag
FROM mitglied
JOIN link_mitglied_sportart ON mitglied.M_Id = link_mitglied_sportart.M_Id
JOIN sportart ON link_mitglied_sportart.Sport_Id = sportart.Sport_Id
JOIN ort ON mitglied.Ort_Id = ort.Ort_Id
ORDER BY Sportart asc, Vorname asc;

#task4
SELECT mitglied.M_Id, Nachname, Vorname, Geburtsdatum
FROM mitglied
LEFT JOIN link_mitglied_sportart ON link_mitglied_sportart.M_Id = mitglied.M_Id
WHERE link_mitglied_sportart.Sport_Id IS NULL;

#task5
SELECT sportart.Sport_ID, Sportart, Beitrag
FROM sportart
LEFT JOIN link_mitglied_sportart ON link_mitglied_sportart.Sport_Id = sportart.Sport_Id
WHERE link_mitglied_sportart.M_Id IS NULL;





