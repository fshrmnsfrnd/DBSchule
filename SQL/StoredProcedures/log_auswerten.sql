use test;

DELIMITER ||
CREATE PROCEDURE readLog(IN line VARCHAR(40), 
						OUT serverName VARCHAR(10), OUT volumeName VARCHAR(10), OUT size INT, OUT freeSpace INT)
BEGIN
    SET serverName = trim(substring(line, 1, 10));
    SET volumeName = trim(substring(line, 11, 20));
    SET size = cast(trim(substring(line, 21, 28)) AS DOUBLE);
    SET freeSpace = cast(trim(substring(line, 31, 38)) AS DOUBLE);
END
||
DELIMITER ;

CALL readLog('SE7380    HD03      00000600  00000228', @serverName, @volumeName , @size, @freeSpace);

SELECT @serverName, @volumeName , @size, @freeSpace;

DROP PROCEDURE readLog;