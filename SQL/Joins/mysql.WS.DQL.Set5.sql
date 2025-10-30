use dbverein;

# Task 1
SELECT Sport_ID
FROM Sportart
WHERE Sport_ID NOT IN (SELECT Sport_ID FROM link_mitglied_sportart);

# Task 2
SELECT M_Id, Nachname, Vorname, Geburtsdatum 
FROM mitglied 
WHERE M_Id NOT IN (SELECT M_Id FROM link_mitglied_sportart);

# Task 3
SELECT Sportart, (count((SELECT Sport_ID FROM link_mitglied_sportart)) * Beitrag) AS Einnahmen, 
((
	SELECT sum((
				SELECT count(m_id) FROM link_mitglied_sportart WHERE link_mitglied_sportart.Sport_ID = sportart.Sport_ID
                ) * Beitrag) AS SportartGesamt
	FROM link_mitglied_sportart, sportart
	WHERE sportart.Sport_ID = link_mitglied_sportart.Sport_ID
	)
	/ 100 *
	(count((SELECT Sport_ID FROM link_mitglied_sportart)) * Beitrag)
) AS AnteilAnGesamt, 
SUM((
	SELECT (count(M_Id) * Beitrag) AS SportartGesamt
	FROM link_mitglied_sportart, sportart
	WHERE sportart.Sport_ID = link_mitglied_sportart.Sport_ID
)) AS Gesamt
FROM sportart;

# Task 4
SELECT sportart.Sportart, mitglied.Geschlecht, (mitglied.M_Id) AS Anzahl, (mitglied.M_Id / 100 * mitglied.M_Id) as AnteilAnGesamt 
FROM sportart
JOIN link_mitglied_sportart ON sportart.Sport_ID = link_mitglied_sportart.Sport_ID
JOIN mitglied ON link_mitglied_sportart.M_Id = mitglied.M_Id
GROUP BY sportart.Sportart, mitglied.Geschlecht;

# Task 5
SELECT M_Id, Nachname, Vorname, Strasse, PLZ, Ort, Tel
FROM mitglied
INNER JOIN ort on mitglied.Ort_Id = ort.Ort_Id
WHERE mitglied.Ort_Id = (SELECT Ort_Id FROM mitglied WHERE M_Id = 14);

#Task 6
SELECT mitglied.M_Id, Nachname, Vorname, Geburtsdatum, Sportart
FROM mitglied
INNER JOIN link_mitglied_sportart ON mitglied.M_Id = link_mitglied_sportart.M_Id
INNER JOIN sportart ON link_mitglied_sportart.Sport_ID = sportart.Sport_Id
WHERE link_mitglied_sportart.Sport_ID IN (SELECT Sport_ID FROM link_mitglied_sportart WHERE M_Id = 1);


