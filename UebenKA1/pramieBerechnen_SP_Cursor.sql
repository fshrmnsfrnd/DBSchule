USE test;

DROP FUNCTION IF EXISTS pramieBerechnen;
DROP PROCEDURE IF EXISTS pramieBerechnen;

DELIMITER ||
CREATE PROCEDURE pramieBerechnen(kunde_nr INT)
#RETURNS DEC(6,2)
READS SQL DATA
BEGIN
	# Variables
	DECLARE beitragProMonat DECIMAL(6,2) DEFAULT 0;
    DECLARE c_kunde_nr int(11);
	DECLARE c_ist_oeff_dienst bit(1);
	DECLARE c_ist_kfz bit(1);
	DECLARE c_verArt varchar(100);
	DECLARE c_raten_pro_jahr tinyint(4);
	DECLARE c_basis_praemie DECIMAL(6,2);
    DECLARE count_vertrage INT DEFAULT 0;
    
    # Cursor
    
    DECLARE finished BIT(1) DEFAULT 0;
    DECLARE c_vertrag CURSOR FOR (SELECT kunde_nr, ist_oeff_dienst, ist_kfz, verArt, raten_pro_jahr, basis_praemie FROM kundenvertraege WHERE kundenvertraege.kunde_nr = kunde_nr);
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET finished = 1;
    OPEN c_vertrag;
        loop_cursor:LOOP
			FETCH c_vertrag INTO c_kunde_nr, c_ist_oeff_dienst, c_ist_kfz, c_verArt, c_raten_pro_jahr, c_basis_praemie;
            
            IF finished = 1 THEN
				LEAVE loop_cursor;
			END IF;
			
            # Zahlungsweise
			CASE c_raten_pro_jahr
				WHEN 1 THEN
					SET c_basis_praemie = c_basis_praemie * 0.95;
				WHEN 4 THEN
					SET c_basis_praemie = c_basis_praemie * 1.02;
				WHEN 12 THEN
					SET c_basis_praemie = c_basis_praemie * 1.04;
				ELSE
					SET c_basis_praemie = c_basis_praemie;
			END CASE;
			
			#Offentlicher Dienst
			IF c_ist_oeff_dienst = 1 AND c_ist_kfz = 1 THEN
				SET c_basis_praemie = c_basis_praemie * 0.83;
			END IF;
            
            SET count_vertrage = count_vertrage + 1;
            SET beitragProMonat = beitragProMonat + c_basis_praemie;
        END LOOP;
    CLOSE c_vertrag;
    
    IF count_vertrage >= 5 THEN
		SET beitragProMonat = beitagProMonat * 0.95;
	END IF;
    
    SELECT beitragProMonat;
END ||
DELIMITER ;

CALL pramieBerechnen(1326);