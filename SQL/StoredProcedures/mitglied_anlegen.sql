use dbverein;

DROP PROCEDURE IF EXISTS mitglied_anlegen;

DELIMITER ||
CREATE PROCEDURE mitglied_anlegen 
(IN vorname TEXT, IN nachname TEXT, IN gebdate DATETIME, IN gender varchar(1), IN strasse TEXT, IN Ort_Id INT, IN Tel INT, IN sportart_id INT)
BEGIN
	INSERT INTO mitglied
		(`Vorname`,
		`Nachname`,
		`Geburtsdatum`,
		`Geschlecht`,
		`Strasse`,
		`Ort_Id`,
		`Tel`,
		`Eintrittsdatum`)
		VALUES
		(vorname,
		nachname,
		gebdate>,
		gender,
		strasse,
		Ort_Id,
		Tel,
		now()
        );
	
	DECLARE m_id;
    SET m_id = SELECT m_id FROM mitglied LIMIT 1 ORDER BY Eintrittsdatum DESC;
    
    INSERT INTO link_mitglied_sportart (M_Id, Sport_ID)
    VALUES (m_id, sportart_id)

END
DELIMITER ;