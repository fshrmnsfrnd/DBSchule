USE dbverein;
DROP PROCEDURE IF EXISTS sp_cursor_demo;

DELIMITER ||
CREATE PROCEDURE sp_cursor_demo(percentage INT)
READS SQL DATA
BEGIN
    DECLARE finished BIT(1) DEFAULT 0;
	DECLARE c_sport_id INT;
	DECLARE c_beitrag DEC(6,2);
	DECLARE c_sportart VARCHAR(100);
	DECLARE beitrag_new DEC(6,2);
	DECLARE ts timestamp DEFAULT NOW();
    
	-- cursor
	DECLARE c_daten CURSOR FOR SELECT sport_id, sportart, beitrag from sportart;
	-- handler
	DECLARE CONTINUE HANDLER FOR NOT FOUND Set finished = 1;
    
	OPEN c_daten;
	loop_cursor:LOOP
		FETCH c_daten INTO c_sport_id,c_sportart,c_beitrag;
		IF finished = 1 THEN
			LEAVE loop_cursor;
		END IF;
        
		SET beitrag_new = c_beitrag * (1 + percentage/100);
		UPDATE sportart SET beitrag = beitrag_new WHERE sport_id = c_sport_id;
		INSERT INTO 
			sportart_hist (sport_id,sportart,beitrag_old,beitrag_new,ts)
		VALUES
			(c_sport_id,c_sportart,c_beitrag,beitrag_new, ts);
            
	END LOOP loop_cursor;
	CLOSE c_daten;
END ||
DELIMITER ;

CALL sp_cursor_demo(2);