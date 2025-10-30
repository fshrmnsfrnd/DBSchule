-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: dbverein
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
-- Table structure for table `ort`
--

DROP TABLE IF EXISTS `ort`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ort` (
  `Ort_Id` int NOT NULL AUTO_INCREMENT,
  `PLZ` varchar(10) COLLATE latin1_german1_ci NOT NULL,
  `Ort` varchar(30) COLLATE latin1_german1_ci NOT NULL,
  PRIMARY KEY (`Ort_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ort`
--

LOCK TABLES `ort` WRITE;
/*!40000 ALTER TABLE `ort` DISABLE KEYS */;
INSERT INTO `ort` VALUES (1,'85368','Brugschlag'),(2,'85405','Nandlstadt'),(3,'85395','Attenkirchen'),(4,'85402','Kranzberg'),(5,'85395','Wolfersdorf'),(6,'85375','Neufarn'),(7,'85414','Kirchdorf'),(8,'84079','Bruckberg'),(9,'85406','Zolling'),(10,'85368','Moosburg'),(11,'85408','Daberg'),(12,'85419','Mauern'),(13,'85391','Allershausen'),(14,'85368','Wang'),(15,'85399','Halbergmoos'),(16,'85416','Langenbach'),(17,'85408','Gammelsdorf'),(18,'85417','Marzling'),(19,'85413','Hörgertshausen'),(20,'85410','Haag'),(21,'86150','Augsburg'),(22,'86159','Augsburg'),(23,'86161','Augsburg'),(24,'86343','Königsbrunn'),(25,'86399','Bobingen'),(26,'86179','Augsburg'),(27,'89404','Dillingen'),(28,'81739','München'),(29,'86830','Schwabmünchen');
/*!40000 ALTER TABLE `ort` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-17  9:11:14
