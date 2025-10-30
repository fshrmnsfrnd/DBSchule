CREATE DATABASE IF NOT EXISTS dbDemoTree;

Use dbDemoTree;
-- MySQL dump 10.13  Distrib 8.0.22, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: dbVerein
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tree_mitglied`
--

DROP TABLE IF EXISTS `tree_mitglied`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;

CREATE TABLE `tree_mitglied` (
  `m_id` int NOT NULL DEFAULT '0',
  `vorname` varchar(20) CHARACTER SET latin1 COLLATE latin1_german1_ci NOT NULL,
  `nachname` varchar(20) CHARACTER SET latin1 COLLATE latin1_german1_ci NOT NULL,
  `parent_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tree_mitglied`
--

LOCK TABLES `tree_mitglied` WRITE;
/*!40000 ALTER TABLE `tree_mitglied` DISABLE KEYS */;
INSERT INTO `tree_mitglied` VALUES (1,'Hans','Huber',1),(2,'Klaus','Maier',1),(3,'Inge','Schmidt',1),(4,'Anita','Klaubmann',1),(5,'Julia','Berger',6),(6,'Otto','Fischer',1),(7,'Peter','Hutmann',2),(8,'Vera','Köhler',3),(9,'Manuela','Fiedmann',4),(10,'Walter','Fildler',6),(11,'Ulrich','Becker',2),(12,'Petra','Müller',2),(13,'Karl','Seifert',3),(14,'Friedrich','Klein',4),(15,'Maria','Brucker',6),(16,'Josef','Groß',2),(17,'Georg','Frohmann',2),(18,'Sieglinde','Liebel',3),(19,'Susanne','Glotz',4),(20,'Martin','Glanzmann',6),(21,'Pumuckl','Malefiz',2),(22,'Eder','Meister',2),(23,'Andreas','Sebald',3),(24,'Martina','Kirchner',4),(25,'Karl-Heinz','Seefried',6),(26,'Elfriede','Schneider',2),(27,'Eva','Birkner',2),(28,'Erika','Niederlechner',3),(29,'Winfried','Zach',4),(30,'Jürgen','Mayer',6),(31,'Anton','Ebert',2),(32,'Elisabeth','Hintermayer',2),(33,'Markus','Engelhardt',3),(34,'Winfried','Ulmann',4),(35,'Josephine','Reissner',6),(36,'Christine','Wohlhüter',2),(37,'Irmgard','Deichsel',2),(38,'Richard','Tuschler',3),(39,'Gabriele','Oswald',4),(40,'Gerhard','Hubich',6),(41,'Irene','Pohlmann',2),(42,'Markus','Lechner',2),(43,'Klaus','Bach',3),(44,'Maria','Kunst',4),(45,'Peter','Fluß',6),(46,'Dieter','Strom',2),(47,'Marianne','See',2),(48,'Rosa','Himmel',3),(49,'Josef','Bach',4),(50,'Werner','Weiher',6),(51,'Christina','Meer',2),(52,'Maximilian','Altenburger',2),(53,'Willi','Bogner',3),(54,'Helmut','Braun',4),(55,'Jasmin','Schwarz',6),(56,'Albertine','Weiß',2),(57,'Wolfgang','Bochtler',2),(58,'Albert','Fesl',3),(59,'Gerd','Schmitz',4),(60,'Stefan','Zorn',6),(61,'Ulrike','Amann',2),(62,'Claudia','Späth',2),(63,'Bernd','Hecht',3),(64,'Günther','Stein',4),(65,'Hans','Schmitz',6),(66,'Tobias','Sirch',2),(67,'Volker','Schäfer',2),(68,'Florian','Bauer',3),(69,'Martina','Meier',4),(70,'Silvia','Achter',6),(71,'Andrea','Eder',2),(72,'Maria','Huber',2),(73,'Albert','Loy',3),(74,'Lena','Hauser',4),(75,'Wolfgang','Bayer',6),(76,'Susanne','Biller',2),(77,'Lisa','Klein',2),(78,'Klaus','Lutz',3),(79,'Petra','Renz',4),(80,'Stefan','Ott',6),(81,'Gertraud','Reim',2),(82,'Karl','Richter',2),(83,'Dieter','Schuster',3),(84,'Manfred','Pfeifer',4),(85,'Helga','Ruf',6),(86,'Arno','Ritzer',2),(87,'Elise','Ruile',2),(88,'Rainer','Kraus',3),(89,'Holger','Frisch',4),(90,'Peter','Lang',6),(91,'Petra','Kurz',2),(92,'Magdalena','Hübsch',2),(93,'Dracula','Häßlich',3),(94,'Lena','Schön',4),(95,'Klaus','Furz',6),(96,'Sepp','Anfang',2),(97,'Günter','Ende',2),(98,'Karl','Mitte',3),(99,'Gerda','Eins',4),(100,'Hans','Letzt',6),(101,'Geraldine','Huber',2),(102,'Daniela','Meier',2),(103,'Traudl','Mayr',3),(104,'Josef','Huber',4),(105,'Heinz','Mai',6),(106,'Bernd','Meyer',2),(107,'Martin','Neu',2),(108,'Walter','Alt',3),(109,'Jörg','Peters',4),(110,'Walli','Peer',6),(111,'Manfred','Renz',2),(112,'Vreni','Benz',2),(113,'Mercedes','Daimler',3),(114,'Otto','Diesel',4),(115,'Ottilie','Motor',6),(116,'Franz','Stecker',2),(117,'Calda','Winter',2),(118,'Werner','Sommer',3),(119,'Walter','Braun',4),(120,'Klaus','Fromm',6),(121,'Christa','Träumer',2),(122,'Thea','Biermann',2),(123,'Helmut','Mayer',3),(124,'Maike','Bommer',4),(125,'Kathrin','Altmann',6),(126,'Karl','Neufrau',2),(127,'Theodor','Weinfrau',2),(128,'Gunther','Durstig',3),(129,'Gundula','Hungrig',4),(130,'Maria','Windisch',6),(131,'Klaus','Maurer',2),(132,'Robert','Graf',2),(133,'Franz','Münzer',3),(134,'Anna','Ortmann',4),(135,'Georg','Hackl',6),(136,'Dieter','Nuller',2),(137,'Rosa','Sachs',2),(138,'Peter','Sichermann',3),(139,'Marianne','Groß',4),(140,'Xaver','Mischer',6),(141,'Ulf','Kellermann',2),(142,'Ulrike','Kellerfrau',2),(143,'Ute','Kindler',3),(144,'Reinhard','Jeschke',4),(145,'Beate','Sonnig',6),(146,'Dieter','Frostig',2),(147,'Rosa','Blumig',2),(148,'Maria','Germania',3),(149,'Carl-Friedrich','Kalkulus',4),(150,'Josef','Spieking',6),(151,'Daniel','Tüftel',2),(152,'Franz','Zähler',2),(153,'Marie','Nenner',3),(154,'Wolfgang','Goethschi',4),(155,'Eusebia','Bavaria',6),(156,'Klara','Hiermi',2),(157,'Rosemarie','Siemie',2),(158,'Hanna','Börse',3),(159,'Klaus','Manny',4),(160,'Hans','Käsch',6),(161,'Jan','Spring',2),(162,'Johanna','Hüpf',2),(163,'Gustav','Überallem',3),(164,'Traugott','Glaube',4),(165,'Gottfried','Bastel',6),(166,'Bastian','Strom',2),(167,'Fridolin','Seel',2),(168,'Eva','Blume',3),(169,'Franz','Pinke',4),(170,'Eva','Pinke',6),(171,'Gerhard','Noir',2),(172,'Gerda','Blanc',2),(173,'Ludwig','Maler',3),(174,'Barbara','Palette',4),(175,'Friedrich','Pinsel',6),(176,'Edgar','Histoire',2),(177,'Werner','Genau',2),(178,'Amadeus','Tralala',3),(179,'Hans','Mayer',4),(180,'Lena','Huber',6),(181,'Bettina','Blume',2),(182,'Egon','Baum',2),(183,'Wilhelm','Busch',3),(184,'Gertraud','Mathe',4),(185,'Karl','Malefiz',6),(186,'Paul','Weis',2),(187,'Tobias','Kinler',2),(188,'Kilian','Mathe',3),(189,'Ute','Buche',4),(190,'Hans','Trux',6),(191,'Gerda','Bach',2),(192,'Wolfgang','Mathe',2),(193,'Magdalena','Mathe',3),(195,'Heinz','Bayerlein',6),(198,'Hubert','Kalusen',3),(199,'Helmut','Schön',4),(200,'Sepp','Maier',6),(201,'Heiner','Schustermann',1),(202,'Detlev','Schrempp',2),(203,'Hashimoto','Tatami',3),(204,'Xaver','Wiener',4);
/*!40000 ALTER TABLE `tree_mitglied` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-22  9:40:57
