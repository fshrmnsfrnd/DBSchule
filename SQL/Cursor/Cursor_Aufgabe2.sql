USE dbts;
DROP PROCEDURE IF EXISTS sp_get_gesamt_pramie;

DELIMITER ||
CREATE PROCEDURE sp_get_gesamt_pramie(c_kunde_nr INT)
READS SQL DATA
BEGIN
	DECLARE finished BIT(1) DEFAULT 0;
    DECLARE c_ist_oeff_dienst BIT(1);
    DECLARE c_raten_pro_jahr TINYINT SIGNED;
    DECLARE c_basis_praemie DEC(6,2);
    DECLARE c_kunde_count_vertrag INT;
    DECLARE gesamt_praemie DEC(6,2) DEFAULT 0.0;
    DECLARE c_schlag DEC;
	DECLARE c_praemie DEC;
    DECLARE c_ist_kfz TINYINT;
    
    -- cursor
	DECLARE c_vertrag CURSOR FOR 
		SELECT ist_oeff_dienst, raten_pro_jahr, basis_praemie, ist_kfz FROM kundenvertraege WHERE kunde_nr = c_kunde_nr;
	-- handler
	DECLARE CONTINUE HANDLER FOR NOT FOUND Set finished = 1;
	OPEN c_vertrag;
	loop_cursor:LOOP
		FETCH c_vertrag INTO c_ist_oeff_dienst, c_raten_pro_jahr, c_basis_praemie, c_ist_kfz;
		IF finished = 1 THEN
			LEAVE loop_cursor;
		END IF;
    
		SET c_schlag = (CASE
						WHEN c_raten_pro_jahr >= 12 THEN 
							1.04
						WHEN c_raten_pro_jahr >= 4 THEN 
							1.02
						WHEN c_raten_pro_jahr = 2 THEN 
							1.0
						WHEN c_raten_pro_jahr = 1 THEN 
							0.95
						ELSE
							1
					END);
        
        SET c_praemie = c_schlag * c_basis_praemie;
        
        IF c_ist_oeff_dienst = 1 AND c_ist_kfz = 1 THEN
			SET c_praemie = 0.95 * c_praemie;
        END IF;
        
        SET gesamt_praemie = gesamt_praemie + c_praemie;
    END LOOP loop_cursor;
    CLOSE c_vertrag;
    
	SELECT c_kunde_nr, gesamt_praemie / 12;
END ||
DELIMITER ;

CALL sp_get_gesamt_pramie(1326);