CREATE DATABASE IF NOT EXISTS dbOrdner;
USE dbOrdner;
CREATE TABLE `ordner`(
  `o_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(20) CHARACTER SET latin1 COLLATE latin1_german1_ci NOT NULL,
  `parent_id` int DEFAULT NULL REFERENCES Ordner.o_id
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO ordner (`o_id`,`name`,`parent_id`) VALUES ('0','kein Parent','0');
INSERT INTO ordner (`o_id`,`name`,`parent_id`) VALUES ('1','Dieser PC','0');
INSERT INTO ordner (`o_id`,`name`,`parent_id`) VALUES ('2','Musik','1');
INSERT INTO ordner (`o_id`,`name`,`parent_id`) VALUES ('3','Videos','1');
INSERT INTO ordner (`name`,`parent_id`) VALUES ('3D-Objekte','1');
INSERT INTO ordner (`name`,`parent_id`) VALUES ('Bilder','1');
INSERT INTO ordner (`name`,`parent_id`) VALUES ('Desktop','1');
INSERT INTO ordner (`name`,`parent_id`) VALUES ('Dokumente','1');
INSERT INTO ordner (`name`,`parent_id`) VALUES ('Downloads','1');

INSERT INTO ordner (`name`,`parent_id`) VALUES ('OpenBoard','2');
INSERT INTO ordner (`name`,`parent_id`) VALUES ('Aufzeichnungen','3');
INSERT INTO ordner (`name`,`parent_id`) VALUES ('Openboard','3');

SELECT grandparent.`name` as `Ordner Ebene 1`, parent.`name` as `Ordner Ebene 2`, child.`name` as `Ordner Ebene 3` FROM ordner as child 
JOIN ordner as parent ON child.parent_id = parent.o_id
JOIN ordner as grandparent ON parent.parent_id = grandparent.o_id;

SELECT e1.`name` as `Ordner Ebene 1`, e2.`name` as `Ordner Ebene 2`, e3.`name` as `Ordner Ebene 3` FROM ordner e1
LEFT OUTER JOIN ordner e2 ON e2.parent_id = e1.o_id
LEFT OUTER JOIN ordner e3 ON e3.parent_id = e2.o_id
WHERE e1.parent_id = 0 and e1.o_id <> 0;

