CREATE DATABASE  IF NOT EXISTS dbKuchen;

USE dbKuchen;

DROP TABLE IF EXISTS kuchenverkaeufe;

CREATE TABLE kuchenverkaeufe (
  id int NOT NULL,
  vorname VARCHAR(50) NOT NULL,
  verkaeufe double DEFAULT 0.00,
  datum DATE
);

--
-- data for table `kuchenverkaeufe`
--

INSERT INTO `kuchenverkaeufe` VALUES 
(1,'Natalie',19.22,'2011-12-23'),
(2,'Verena',4.5,'2011-12-23'),
(3,'Magdalena',0,'2011-12-23'),
(4,'Lisa',16.23,'2011-12-23'),
(5,'Natalie',7.77,'2011-12-27'),
(6,'Verena',34.19,'2011-12-27'),
(7,'Magdalena',2.6,'2011-12-27'),
(8,'Lisa',3.7,'2011-12-27'),
(9,'Natalie',26.8,'2011-12-28'),
(10,'Verena',8.78,'2011-12-28'),
(11,'Lisa',13.6,'2011-12-28'),
(12,'Magdalena',2.4,'2011-12-28'),
(13,'Natalie',0,'2011-12-29'),
(14,'Verena',2.6,'2011-12-29'),
(15,'Rosa',32,'2011-12-29'),
(16,'Clarissa',0,'2011-12-29'),
(17,'Natalie',15.39,'2011-12-30'),
(18,'Verena',3.45,'2011-12-30'),
(19,'Magdalena',24.29,'2011-12-30'),
(20,'Verena',16.2,'2011-12-31'),
(21,'Natalie',8.05,'2011-12-31'),
(22,'Verena',43.21,'2012-01-02'),
(23,'Verena',1.62,'2012-01-03'),
(24,'Magdalena',9.5,'2012-01-03'),
(25,'Natalie',18.96,'2012-01-03'),
(26,'Verena',32.03,'2012-01-04'),
(27,'Lisa',18.95,'2012-01-04'),
(28,'Rosa',11.27,'2012-01-04'),
(29,'Clarissa',60.33,'2012-01-04');

