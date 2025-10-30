USE test;

DROP FUNCTION IF EXISTS testCursorInFunc;

DELIMITER ||

CREATE FUNCTION testCursorInFunc()
RETURNS VARCHAR(100)
READS SQL DATA
BEGIN
    DECLARE ende BOOLEAN DEFAULT FALSE;
    DECLARE c_verArt VARCHAR(100);
    DECLARE results VARCHAR(100) DEFAULT NULL;
    DECLARE cTest CURSOR FOR SELECT verArt FROM kundenvertraege;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET ende = true;
    
    OPEN cTest;
    c_while: WHILE ende = false DO
        FETCH cTest INTO c_verArt;
        SET results = CONCAT_WS(", ", results, c_verArt);
    END WHILE c_while;

    RETURN results;
END ||

DELIMITER;

SELECT testCursorInFunc ();