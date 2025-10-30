use dbverein;

#Task 1

Select M_Id, Nachname, Vorname, datediff(now(), Eintrittsdatum) as seit from mitglied order by seit;

#Task 2

SELECT M_Id, Nachname, Vorname, Geburtsdatum, truncate(datediff(now(), Geburtsdatum) / 360, 0) as `Alter` 
from mitglied
order by `Alter`;

#Task 3

SELECT M_Id, Nachname, Vorname, Geburtsdatum, truncate(datediff(now(), Geburtsdatum) / 360, 0) as `Alter` 
from mitglied
having `Alter` >= 20
order by `Alter` desc;

#Task 4

SELECT M_Id, Nachname, Vorname, DATE_FORMAT(Geburtsdatum, '%d.%M') as Geburtstag, truncate(datediff(now(), Geburtsdatum) / 360, 0) as `Alter` 
from mitglied
WHERE DATE_FORMAT(Geburtsdatum, '%M') = DATE_FORMAT(now(), '%M')
order by Geburtsdatum;

#Task 5
SELECT M_Id, Nachname, Vorname, Vorwahl, Telefonnummer FROM mitglied;