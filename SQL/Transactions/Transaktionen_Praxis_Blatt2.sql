# Aufgabe 5

use dbverein;
#START TRANSACTION;
#	DELETE FROM link_mitglied_sportart;
    #SELECT * FROM link_mitglied_sportart;
#ROLLBACK;
    #SELECT * FROM link_mitglied_sportart;

# Aufgabe 6
#START TRANSACTION;
	#SELECT * FROM sportart;
	#INSERT INTO sportart (Sportart, Beitrag) VALUES ('Hundweittritt', -1);
	#SELECT * FROM sportart;
#COMMIT;
#SELECT * FROM sportart;
#DELETE FROM sportart WHERE Sportart = 'Hundweittritt';