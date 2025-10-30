-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mitglieder1
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `mitglieder`
--

DROP TABLE IF EXISTS `mitglieder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mitglieder` (
  `M_Id` int DEFAULT NULL,
  `Vorname` text,
  `Nachname` text,
  `Geburtsdatum` datetime DEFAULT NULL,
  `Geschlecht` text,
  `Tel` text,
  `Eintrittsdatum` datetime DEFAULT NULL,
  `ADR-ID` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mitglieder`
--

LOCK TABLES `mitglieder` WRITE;
/*!40000 ALTER TABLE `mitglieder` DISABLE KEYS */;
INSERT INTO `mitglieder` VALUES (66,'Tobias','Sirch','1980-11-29 00:00:00','m','08XBG - AB 66','1998-11-25 00:00:00',7),(112,'Vreni','Benz','1987-06-19 00:00:00','w','08XBG - AB 12','2005-06-14 00:00:00',9),(138,'Peter','Sichermann','1986-08-26 00:00:00','m','08XBG - AB 38','2004-08-21 00:00:00',6),(2,'Klaus','Maier','1982-02-20 00:00:00','m','08XND - AB 02','2000-02-16 00:00:00',10),(21,'Pumuckl','Malefiz','1982-08-27 00:00:00','m','08XND - AB 21','2000-08-22 00:00:00',11),(22,'Eder','Meister','1985-11-11 00:00:00','m','08XND - AB C0 22','2003-11-07 00:00:00',12),(59,'Gerd','Schmitz','1981-06-18 00:00:00','m','08XND - AB C0 59','1999-06-14 00:00:00',5),(185,'Karl','Malefiz','1984-04-08 00:00:00','m','08XND - AB C1 85','2002-04-04 00:00:00',11),(5,'Julia','Berger','1982-12-11 00:00:00','w','08XAT - AB C0 05','2000-12-06 00:00:00',8);
/*!40000 ALTER TABLE `mitglieder` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-17  9:11:12
