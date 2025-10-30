use test;

DELIMITER ||
CREATE PROCEDURE createCode(IN inDate DATE, IN zeichen VARCHAR(3), OUT kundenCode DOUBLE)
BEGIN
	SET kundenCode = ASCII(substr(zeichen, 1)) * 91;
    SET kundenCode = kundenCode + ASCII(substr(zeichen, 2)) * 91;
    SET kundenCode = kundenCode + ASCII(substr(zeichen, 3));
    SET kundenCode = kundenCode * 54 + DATEPART(wk, inDate);
    SET kundenCode = kundenCode * 2300 + YEAR(inDate);
END
||
DELIMITER ;

CALL createCode('2025-06-23', 'GER', @code);

SELECT @code;

DROP PROCEDURE createCode;