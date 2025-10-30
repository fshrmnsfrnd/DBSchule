use dbverein;

DROP PROCEDURE IF EXISTS mitglied_loeschen;

DELIMITER ||
CREATE PROCEDURE mitglied_loeschen
(IN m_id)
BEGIN
	DELETE FROM link_mitglied_sportart WHERE link_mitglied_sportart.m_id=m_id;
	DELETE FROM mitglied WHERE mitglied.m_id = m_id;
END
DELIMITER ;