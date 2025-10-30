DROP TRIGGER IF EXISTS mitarbeiter_insert_trigger;
DROP TRIGGER IF EXISTS mitarbeiter_update_trigger;
DROP TRIGGER IF EXISTS mitarbeiter_delete_trigger;

delimiter || 
CREATE TRIGGER mitarbeiter_insert_trigger
BEFORE INSERT ON mitarbeiter FOR EACH ROW
BEGIN
	SET NEW.id = uuid();
    SET NEW.email = CONCAT(NEW.vorname, ".", NEW.nachname, "@firma.de");
    SET NEW.kuerzel = lcase((SUBSTRING(NEW.nachname, 1, 3), SUBSTRING(NEW.vorname, 1, 2)));
    SET NEW.version_nr = 1;
    SET NEW.erstellt_am = now();
    SET NEW.geaendert_am = now();
END; ||

CREATE TRIGGER mitarbeiter_update_trigger
BEFORE UPDATE ON mitarbeiter FOR EACH ROW
BEGIN
	INSERT INTO mitarbeiter_timeline(id, vorname, nachname, email, kuerzel, erstellt_am, geaendert_am, version_nr)
    VALUES (OLD.id, OLD.vorname, OLD.nachname, OLD.email, OLD.kuerzel, OLD.erstellt_am, OLD.geaendert_am, OLD.version_nr);
    
    SET NEW.version_nr = OLD.version_nr + 1;
    SET NEW.geaendert_am = now();
    
    SET NEW.erstellt_am = OLD.erstellt_am;
END; ||

CREATE TRIGGER mitarbeiter_delete_trigger
BEFORE DELETE ON mitarbeiter FOR EACH ROW
BEGIN
	INSERT INTO mitarbeiter_timeline(id, vorname, nachname, email, kuerzel, erstellt_am, geaendert_am, version_nr)
    VALUES (OLD.id, CONCAT("del_",OLD.vorname), CONCAT("del_",OLD.nachname), CONCAT("del_",OLD.email), CONCAT("del_",OLD.kuerzel), OLD.erstellt_am, now(), OLD.version_nr);
END; ||
delimiter ;