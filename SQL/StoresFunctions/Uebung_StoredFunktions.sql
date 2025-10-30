use test;

## AUFGABE 1
DROP FUNCTION IF EXISTS createCode;

DELIMITER ||
CREATE FUNCTION createCode(inDate DATE, zeichen VARCHAR(3))
RETURNS DOUBLE
BEGIN
	DECLARE kundenCode DOUBLE;
	SET kundenCode = ASCII(substr(zeichen, 1)) * 91;
    SET kundenCode = kundenCode + ASCII(substr(zeichen, 2)) * 91;
    SET kundenCode = kundenCode + ASCII(substr(zeichen, 3));
    SET kundenCode = kundenCode * 54 + week(inDate);
    SET kundenCode = kundenCode * 2300 + YEAR(inDate);
    RETURN kundenCode;
END
||
DELIMITER ;

#SELECT createCode('2025-06-23', 'GER');

## Aufgabe 2
DROP FUNCTION IF EXISTS biggestNumber;

DELIMITER ||
CREATE FUNCTION biggestNumber(num1 DOUBLE, num2 DOUBLE, num3 DOUBLE)
RETURNS DOUBLE
BEGIN
	DECLARE biggestNum DOUBLE;
	SET biggestNum = num1;
    IF(num2 > biggestNum) THEN
		SET biggestNum = num2;
	END IF;
	IF(num3 > biggestNum) THEN
		SET biggestNum = num3;
	END IF;
    RETURN biggestNum;
END
||
DELIMITER ;

#SELECT biggestNumber(2, 8.5, 1.2);

## Aufgabe 3
DROP FUNCTION IF EXISTS umdrehen;

DELIMITER ||
CREATE FUNCTION umdrehen(num INT)
RETURNS DECIMAL
BEGIN
	DECLARE newNumStr VARCHAR(20);
    DECLARE newNum DECIMAL;
    DECLARE numStr VARCHAR(20);
    
	SET numStr = CAST(num AS char(20));
    
    WHILE(length(numStr) > 0) DO
		SET newNumStr = CONCAT(newNumStr, RIGHT(numStr, 1));
		SET numStr = LEFT(numStr, length(numstr) -1);
    END WHILE;
    
    SET newNum = CAST(newNumStr AS DECIMAL);
    
    RETURN newNum;
END
||
DELIMITER ;

#SELECT umdrehen(1234);

## Aufgabe 4
DROP FUNCTION IF EXISTS isArmstrong;

DELIMITER ||
CREATE FUNCTION isArmstrong(num INT)
RETURNS BOOLEAN
BEGIN
	DECLARE armstrongNum INT DEFAULT 0;
    DECLARE inputNum INT DEFAULT num;
    DECLARE numLength INT;
    SET numLength = LENGTH(num);
	WHILE num > 0 DO
		SET armstrongNum = armstrongNum + pow((num % 10), numLength);
        SET num = FLOOR(num / 10);
	END WHILE;
    IF inputNum = armstrongNum THEN
		return true;
	ELSE 
		return false;
    END IF;
END
||
DELIMITER ;

DROP FUNCTION IF EXISTS nextArmstrong;

DELIMITER ||
CREATE FUNCTION nextArmstrong(num INT)
RETURNS INT
BEGIN
	WHILE isArmstrong(num) = false DO
		SET num = num + 1;
    END WHILE;
    RETURN num;
END
||
DELIMITER ;

#SELECT nextArmstrong(150);

## Aufgabe 5
DROP FUNCTION IF EXISTS queersumme;

DELIMITER ||
CREATE FUNCTION queersumme(num INT)
RETURNS INT
BEGIN
	DECLARE queersumme INT DEFAULT 0;
	WHILE num > 0 DO
		SET queersumme = queersumme + num % 10;
        SET num = FLOOR(num / 10);
	END WHILE;
    RETURN queersumme;
END
||
DELIMITER ;

#SELECT queersumme(1111);

## Aufgabe 6
DROP FUNCTION IF EXISTS dreiFaltenM;
DROP FUNCTION IF EXISTS dreiFaltenW;

DELIMITER ||
CREATE FUNCTION dreiFaltenM(brust FLOAT, bauch FLOAT, oberschenkel FLOAT, age FLOAT)
RETURNS FLOAT
BEGIN
	DECLARE k0 FLOAT DEFAULT 1.10938;
    DECLARE k1 FLOAT DEFAULT 0.0008267;
    DECLARE k2 FLOAT DEFAULT 0.0000016;
    DECLARE ka FLOAT DEFAULT 0.0002574;
    DECLARE S FLOAT DEFAULT brust + bauch + oberschenkel;
    
    RETURN ((4.95/(k0 - (k1 * S) + (k2 * pow(S, 2)) - (ka * age))) - 4.5) * 100;
END
||
DELIMITER ;

#SELECT dreiFaltenM(12, 20, 15, 25);

DELIMITER ||
CREATE FUNCTION dreiFaltenW(trizeps FLOAT, bauch FLOAT, hufte FLOAT, age FLOAT)
RETURNS FLOAT
BEGIN
	DECLARE k0 FLOAT DEFAULT 1.0994921;
    DECLARE k1 FLOAT DEFAULT 0.0009929;
    DECLARE k2 FLOAT DEFAULT 0.0000023;
    DECLARE ka FLOAT DEFAULT 0.0001392;
    DECLARE S FLOAT DEFAULT trizeps + bauch + hufte;
    
    RETURN ((4.95/(k0 - (k1 * S) + (k2 * pow(S, 2)) - (ka * age))) - 4.5) * 100;
END
||
DELIMITER ;

#SELECT dreiFaltenW(12, 20, 15, 25)

## Aufgabe 7
DROP FUNCTION IF EXISTS checkBrackets;

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
    
	WHILE counter <= length(inputText) DO
		SET currentLetter = SUBSTR(inputText, counter, 1);
		CASE
			WHEN currentLetter = "{" THEN SET openCurlyBracs = openCurlyBracs + 1;
        
        SET counter = counter + 1;
    END WHILE;
END
||
DELIMITER ;

#SELECT checkBrackets()

SELECT instr("{{", "{")