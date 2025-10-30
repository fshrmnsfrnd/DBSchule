use test;

DELIMITER ||
CREATE PROCEDURE farbVerbrauch(IN innerRadius FLOAT, IN anzahlCDs INT,
								OUT gelb FLOAT, OUT rot FLOAT, OUT grau FLOAT, OUT schwarz FLOAT)
BEGIN
	DECLARE pi FLOAT DEFAULT 3.142;
    DECLARE outerRadius FLOAT DEFAULT 1.18;
    DECLARE lochRadius FLOAT DEFAULT 0.18;
    
	DECLARE lochA FLOAT;
    DECLARE innerA FLOAT;
    DECLARE outerA FLOAT;
	
    SET lochA = pi * lochRadius * lochRadius;
    SET innerA = (pi * innerRadius * innerRadius) - lochA;
    SET outerA = (pi * outerRadius * outerRadius) - innerA - lochA;
    
    SET gelb = outerA;
    SET rot = innerA / 2;
    SET grau = (innerA / 2) * 0.95;
    SET schwarz = (innerA / 2) * 0.05;
    
END||
DELIMITER ;

CALL farbVerbrauch(1.0, 3, @v_gelb, @v_rot, @v_grau, @v_schwarz);

SELECT @v_gelb, @v_rot, @v_grau, @v_schwarz;