USE test;

DROP FUNCTION IF EXISTS zahlUmdrehen;

DELIMITER ||
CREATE FUNCTION zahlUmdrehen(num INT)
RETURNS INT
DETERMINISTIC
BEGIN
	DECLARE numAsStr TEXT;
	DECLARE revNumStr TEXT DEFAULT '';
	SET numAsStr = CAST(num AS CHAR);
	reverse_loop: REPEAT
		SET revNumStr = CONCAT(revNumStr, RIGHT(numAsStr, 1));
		SET numAsStr = SUBSTRING(numAsStr, 1, LENGTH(numAsStr) - 1);
	UNTIL LENGTH(numAsStr) = 0
	END REPEAT reverse_loop;
	RETURN CAST(revNumStr AS INT);
END ||
DELIMITER ;

SELECT zahlUmdrehen(1234);