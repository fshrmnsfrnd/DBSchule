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
-- Table structure for table `link_mitglied_sportart`
--

DROP TABLE IF EXISTS `link_mitglied_sportart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `link_mitglied_sportart` (
  `M_Id` int NOT NULL,
  `Sport_ID` int NOT NULL,
  PRIMARY KEY (`M_Id`,`Sport_ID`),
  KEY `Link_Mitglied_Sportart_S_ID` (`Sport_ID`),
  CONSTRAINT `Link_Mitglied_Sportart_M_ID` FOREIGN KEY (`M_Id`) REFERENCES `mitglied` (`M_Id`),
  CONSTRAINT `Link_Mitglied_Sportart_S_ID` FOREIGN KEY (`Sport_ID`) REFERENCES `sportart` (`Sport_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `link_mitglied_sportart`
--

LOCK TABLES `link_mitglied_sportart` WRITE;
/*!40000 ALTER TABLE `link_mitglied_sportart` DISABLE KEYS */;
INSERT INTO `link_mitglied_sportart` VALUES (1,1),(2,1),(6,1),(7,1),(8,1),(12,1),(13,1),(15,1),(16,1),(17,1),(20,1),(22,1),(34,1),(40,1),(43,1),(45,1),(46,1),(49,1),(50,1),(52,1),(53,1),(54,1),(57,1),(58,1),(59,1),(60,1),(63,1),(64,1),(65,1),(66,1),(67,1),(68,1),(73,1),(75,1),(78,1),(80,1),(82,1),(83,1),(84,1),(86,1),(88,1),(89,1),(90,1),(93,1),(95,1),(96,1),(97,1),(98,1),(100,1),(104,1),(105,1),(106,1),(107,1),(108,1),(109,1),(111,1),(114,1),(116,1),(118,1),(119,1),(120,1),(123,1),(126,1),(128,1),(131,1),(132,1),(133,1),(135,1),(136,1),(138,1),(140,1),(141,1),(144,1),(146,1),(149,1),(150,1),(151,1),(152,1),(154,1),(159,1),(160,1),(161,1),(163,1),(164,1),(165,1),(166,1),(167,1),(169,1),(171,1),(173,1),(175,1),(176,1),(177,1),(178,1),(179,1),(182,1),(183,1),(185,1),(186,1),(187,1),(188,1),(190,1),(192,1),(3,2),(4,2),(5,2),(9,2),(10,2),(11,2),(14,2),(18,2),(19,2),(44,2),(49,2),(53,2),(65,2),(66,2),(67,2),(72,2),(83,2),(85,2),(89,2),(135,2),(163,2),(180,2),(23,3),(24,3),(25,3),(26,3),(27,3),(30,3),(31,3),(32,3),(33,3),(36,3),(37,3),(39,3),(41,3),(42,3),(56,3),(94,3),(98,3),(102,3),(105,3),(116,3),(119,3),(125,3),(127,3),(131,3),(166,3),(169,3),(173,3),(181,3),(198,3),(1,4),(2,4),(4,4),(6,4),(10,4),(11,4),(12,4),(13,4),(26,4),(29,4),(47,4),(51,4),(55,4),(61,4),(62,4),(63,4),(74,4),(77,4),(79,4),(81,4),(87,4),(92,4),(96,4),(99,4),(104,4),(109,4),(113,4),(120,4),(121,4),(122,4),(124,4),(126,4),(128,4),(129,4),(132,4),(133,4),(136,4),(137,4),(138,4),(139,4),(141,4),(144,4),(146,4),(149,4),(151,4),(152,4),(164,4),(168,4),(170,4),(180,4),(185,4),(188,4),(190,4),(191,4),(193,4),(28,7),(57,7),(58,7),(60,7),(68,7),(70,7),(73,7),(75,7),(80,7),(82,7),(84,7),(86,7),(88,7),(115,7),(148,7),(153,7),(156,7),(157,7),(168,7),(184,7),(189,7),(48,8),(50,8),(52,8),(54,8),(74,8),(78,8),(97,8),(100,8),(103,8),(106,8),(108,8),(111,8),(112,8),(114,8),(117,8),(134,8),(140,8),(142,8),(145,8),(150,8),(172,8),(174,8),(183,8),(192,8),(4,9),(13,9),(27,9),(33,9),(36,9),(38,9),(42,9),(44,9),(45,9),(48,9),(59,9),(69,9),(71,9),(76,9),(91,9),(93,9),(94,9),(101,9),(107,9),(110,9),(118,9),(123,9),(130,9),(135,9),(143,9),(147,9),(154,9),(159,9),(160,9),(161,9),(165,9),(167,9),(171,9),(172,9),(174,9),(175,9),(176,9),(177,9),(182,9),(187,9),(22,10),(29,10),(38,10),(42,10),(51,10),(55,10),(63,10),(70,10),(79,10),(85,10),(90,10),(91,10),(95,10),(153,10),(155,10),(156,10),(157,10),(170,10),(179,10),(186,10),(24,11),(28,11),(35,11),(39,11),(41,11),(61,11),(62,11),(87,11),(158,11),(162,11);
/*!40000 ALTER TABLE `link_mitglied_sportart` ENABLE KEYS */;
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
