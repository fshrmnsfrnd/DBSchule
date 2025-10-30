use dbverein;

DROP PROCEDURE IF EXISTS mitglied_archivieren;

DELIMITER ||
CREATE PROCEDURE mitglied_archivieren
(IN m_id INT)
BEGIN
	START TRANSACTION;
		INSERT INTO archiv_mitglied (
			`m_id`,
			`Vorname`,
			`Nachname`,
			`Geburtsdatum`,
			`Geschlecht`,
			`Strasse`,
			`Ort_Id`,
			`Tel`,
			`Eintrittsdatum`)
		SELECT * FROM mitglied WHERE mitglied.m_id = m_id;
		
		INSERT INTO archiv_link_mitglied_sportart (
			M_Id,
			Sport_ID
		)
		SELECT * FROM link_mitglied_sportart WHERE link_mitglied_sportart.m_id = m_id;
    
		DELETE FROM link_mitglied_sportart WHERE link_mitglied_sportart.m_id = m_id;
		DELETE FROM mitglied WHERE mitglied.m_id = m_id;
	COMMIT;
    
    
END ||
DELIMITER ;

CALL mitglied_archivieren(1);