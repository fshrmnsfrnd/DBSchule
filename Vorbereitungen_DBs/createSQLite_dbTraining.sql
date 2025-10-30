
    -- remove tables
    DROP TABLE if EXISTS L;
    DROP TABLE if EXISTS R;
    DROP TABLE if EXISTS SET_1;
    DROP TABLE if EXISTS SET_2;
    
	-- linke Tabelle
	CREATE TABLE L(
		a VARCHAR(10),
		b VARCHAR(10),
        c VARCHAR(10)
    );
    
 	-- rechte Tabelle   
 	CREATE TABLE R(
		c VARCHAR(10),
		d VARCHAR(10),
        e VARCHAR(10)
    );

	-- Tabelle SET 1 für Mengenabfragen
	CREATE TABLE SET_1(
		x VARCHAR(10),
		y VARCHAR(10),
        z VARCHAR(10)
    );   

	-- Tabelle SET 2 für Mengenabfragen
	CREATE TABLE SET_2(
		x VARCHAR(10),
		y VARCHAR(10),
        z VARCHAR(10)
    ); 
   
    -- Daten einfügen
    INSERT INTO L (a,b,c) VALUES ('a1','b1','c1');
	INSERT INTO L (a,b,c) VALUES ('a2','b2','c2');
	INSERT INTO L (a,b,c) VALUES ('a3','b3','c3');
    INSERT INTO L (a,b,c) VALUES ('a4','b4','c4');
	INSERT INTO L (a,b,c) VALUES ('a5','b5','c5');

    -- Daten einfügen
    INSERT INTO R (c,d,e) VALUES ('c1','d1','e1');
	INSERT INTO R (c,d,e) VALUES ('c2','d2','e2');
	INSERT INTO R (c,d,e) VALUES ('c3','d3','e3');
    INSERT INTO R (c,d,e) VALUES ('c6','d6','e6');
	INSERT INTO R (c,d,e) VALUES ('c7','d7','e7');
    
    -- Daten einfügen
    INSERT INTO SET_1 (x,y,z) VALUES ('x01','y01','z01');
	INSERT INTO SET_1 (x,y,z) VALUES ('x02','y02','z02');
	INSERT INTO SET_1 (x,y,z) VALUES ('x03','y03','z03');
    INSERT INTO SET_1 (x,y,z) VALUES ('x04','y04','z04');
	INSERT INTO SET_1 (x,y,z) VALUES ('x05','y05','z05');
    INSERT INTO SET_1 (x,y,z) VALUES ('x06','y06','z06');

    
	-- Daten einfügen
	INSERT INTO SET_2 (x,y,z) VALUES ('x01','y01','z01');
	INSERT INTO SET_2 (x,y,z) VALUES ('x02','y02','z02');
	INSERT INTO SET_2 (x,y,z) VALUES ('x03','y03','z03');
    INSERT INTO SET_2 (x,y,z) VALUES ('x07','y07','z07');
    INSERT INTO SET_2 (x,y,z) VALUES ('x08','y08','z08');
    INSERT INTO SET_2 (x,y,z) VALUES ('x09','y09','z09');   

