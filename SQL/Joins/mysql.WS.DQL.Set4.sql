use dbverein;

# Task 1
SELECT sportart.Sportart, (sportart.Beitrag * count(mitglied.M_Id)) as Einnahmen FROM sportart
LEFT JOIN link_mitglied_sportart ON sportart.Sport_Id = link_mitglied_sportart.Sport_ID
LEFT JOIN mitglied ON mitglied.M_Id = link_mitglied_sportart.M_Id
GROUP BY sportart.Sportart;

# Task 2
SELECT sportart.Sportart, count(mitglied.M_Id) as Mitgliederzahl FROM sportart
LEFT JOIN link_mitglied_sportart ON sportart.Sport_Id = link_mitglied_sportart.Sport_ID
LEFT JOIN mitglied ON mitglied.M_Id = link_mitglied_sportart.M_Id
GROUP BY sportart.Sportart;

# Task 3
SELECT 
sportart.Sportart, 
count(mitglied.M_Id) as Mitgliederzahl, 
round(AVG(truncate(datediff(now(), mitglied.Geburtsdatum) / 365.25, 0)), 1) as Durchschnittsalter
FROM sportart
LEFT JOIN link_mitglied_sportart ON sportart.Sport_Id = link_mitglied_sportart.Sport_ID
LEFT JOIN mitglied ON mitglied.M_Id = link_mitglied_sportart.M_Id
GROUP BY sportart.Sportart;

# Task 4
SELECT sportart.Sportart as Sportart, mitglied.Geschlecht as Geschlecht, count(mitglied.M_Id) as Anzahl 
FROM sportart
LEFT JOIN link_mitglied_sportart ON sportart.Sport_Id = link_mitglied_sportart.Sport_ID
LEFT JOIN mitglied ON mitglied.M_Id = link_mitglied_sportart.M_Id
GROUP BY Sportart, Geschlecht;

# Task 5
Select mitglied.M_Id, mitglied.Nachname, mitglied.Vorname, sum(sportart.Beitrag) as Monatsbeitrag FROM mitglied
JOIN link_mitglied_sportart ON link_mitglied_sportart.M_Id = mitglied.M_Id
JOIN sportart ON sportart.Sport_Id = link_mitglied_sportart.Sport_ID
GROUP BY mitglied.M_Id
ORDER BY Monatsbeitrag desc
LIMIT 10;

# Task 6
SELECT 
ort.PLZ, 
count(mitglied.M_Id) as AnzahlM, 
sum(sportart.Beitrag) as Einnahmen,
avg(sportart.Beitrag) as durchschntlEinnahmen
FROM ort
JOIN mitglied ON mitglied.Ort_Id = Ort.Ort_Id
JOIN link_mitglied_sportart ON link_mitglied_sportart.M_Id = mitglied.M_Id
JOIN sportart ON link_mitglied_sportart.Sport_ID = sportart.Sport_Id
GROUP BY PLZ
ORDER BY AnzahlM asc, Einnahmen asc;