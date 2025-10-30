use test;

#DROP FUNCTION checkBrackets;

DELIMITER ||
CREATE FUNCTION checkBrackets(inputText VARCHAR(2000))
RETURNS BOOLEAN
BEGIN
	DECLARE openCurlyBracs INT DEFAULT 0;
    DECLARE closingCurlyBracs INT DEFAULT 0;
    DECLARE openSquareBracs INT DEFAULT 0;
    DECLARE closingSquareBracs INT DEFAULT 0;
    DECLARE openRoundBracs INT DEFAULT 0;
    DECLARE closingRoundBracs INT DEFAULT 0;
    DECLARE counter INT DEFAULT 0;
    DECLARE currentLetter char(1);
    
    DECLARE curlyBracsError CONDITION FOR SQLSTATE '50000';
    DECLARE squareBracsError CONDITION FOR SQLSTATE '50000';
    DECLARE roundBracsError CONDITION FOR SQLSTATE '50000';
    
	WHILE counter <= length(inputText) DO
		SET currentLetter = SUBSTR(inputText, counter, 1);
		CASE
			WHEN currentLetter = "{" THEN SET openCurlyBracs = openCurlyBracs + 1;
			WHEN currentLetter = "}" THEN SET closingCurlyBracs = closingCurlyBracs + 1;
            WHEN currentLetter = "[" THEN SET openSquareBracs = openSquareBracs + 1;
            WHEN currentLetter = "]" THEN SET closingSquareBracs = closingSquareBracs + 1;
            WHEN currentLetter = "(" THEN SET openRoundBracs = openRoundBracs + 1;
            WHEN currentLetter = ")" THEN SET closingRoundBracs = closingRoundBracs + 1;
            ELSE SET counter = counter;
		END CASE;
		SET counter = counter + 1;
        SELECT counter;
	END WHILE;
    IF openCurlyBracs != closingCurlyBracs THEN
		SIGNAL curlyBracsError SET MESSAGE_TEXT = 'Anzahl stimmt nicht';
    END IF;
    IF openSquareBracs != closingSquareBracs THEN
		SIGNAL curlyBracsError SET MESSAGE_TEXT = 'Anzahl stimmt nicht';
    END IF;
    IF openCurlyBracs != closingCurlyBracs THEN
		SIGNAL curlyBracsError SET MESSAGE_TEXT = 'Anzahl stimmt nicht';
    END IF;
    return true;
END;
||
DELIMITER ;

# SELECT checkBrackets('()[]{')