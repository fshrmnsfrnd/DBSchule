# Ihr Code
DELIMITER || 
CREATE FUNCTION NEXT_R_NR(inputDate date) 
RETURNS TEXT
DETERMINISTIC 
NO SQL
BEGIN
	DECLARE jahr VARCHAR(4) DEFAULT date_format(inputDate, "%Y");
    DECLARE monat VARCHAR(2) DEFAULT lpad(date_format(inputDate, "%m"), 2, '0');
    DECLARE tag VARCHAR(2) DEFAULT lpad(date_format(inputDate, "%d"), 2, '0');
    DECLARE zeit VARCHAR(6) DEFAULT date_format(now(), '%H%i%S') ;
    DECLARE rand VARCHAR(1) DEFAULT lpad(format(rand() * 10, 0), 2 '0'); 
    DECLARE pruefziffer VARCHAR(1) DEFAULT (jahr * 3 + monat * 7 + tag * 11 + zeit * 13 + rand * 17) % 10;
	RETURN concat(jahr, monat, tag, "-", zeit,  "-", rand, "-", pruefziffer);
END || 
DELIMITER ;

# Vorlage Batchtest 
SELECT 'Test: Batch-Generierung' AS Test;
SELECT NEXT_R_NR('2026-03-15') AS Rechnungsnummer
UNION ALL
SELECT NEXT_R_NR('2026-03-15')
UNION ALL
SELECT NEXT_R_NR('2026-03-15')
UNION ALL
SELECT NEXT_R_NR('2026-03-15')
UNION ALL
SELECT NEXT_R_NR('2026-03-15');