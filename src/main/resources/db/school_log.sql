CREATE DATABASE  IF NOT EXISTS `school_log` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `school_log`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: school_log
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `lessons`
--

DROP TABLE IF EXISTS `lessons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lessons` (
  `lesson_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `unit_id` int NOT NULL,
  `day` varchar(45) NOT NULL,
  `subject` varchar(45) NOT NULL,
  `class` int NOT NULL,
  `nr` int NOT NULL,
  `isModified` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`lesson_id`),
  UNIQUE KEY `lesson_id_UNIQUE` (`lesson_id`),
  KEY `fk_Lessons_User1_idx` (`user_id`),
  KEY `fk_Lessons_Unit1_idx` (`unit_id`),
  CONSTRAINT `fk_Lessons_Unit1` FOREIGN KEY (`unit_id`) REFERENCES `units` (`unit_id`),
  CONSTRAINT `fk_Lessons_User1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lessons`
--

LOCK TABLES `lessons` WRITE;
/*!40000 ALTER TABLE `lessons` DISABLE KEYS */;
INSERT INTO `lessons` VALUES (1,1,1,'Monday','Math',104,1,0),(2,1,1,'Monday','Math',104,2,1),(3,1,2,'Monday','Math',117,3,1),(4,1,3,'Monday','Math',104,4,0),(5,1,3,'Monday','Math',117,5,1),(6,1,3,'Tuesday','Math',104,5,0),(7,1,3,'Tuesday','Math',104,6,0),(8,1,2,'Tuesday','Math',117,7,0),(9,1,2,'Tuesday','Math',104,8,0),(10,1,1,'Tuesday','Math',117,9,0),(11,1,1,'Wednesday','Math',104,3,0),(12,1,2,'Wednesday','Math',104,4,0),(13,1,3,'Wednesday','Math',117,5,0),(14,1,2,'Wednesday','Math',104,6,0),(15,1,1,'Wednesday','Math',117,7,0),(16,1,1,'Thursday','Math',104,1,0),(17,1,2,'Thursday','Math',104,2,0),(18,1,3,'Thursday','Math',117,5,0),(19,1,1,'Thursday','Math',104,6,0),(20,1,1,'Thursday','Math',117,7,0),(21,1,1,'Friday','Math',104,1,0),(22,1,2,'Friday','Math',104,2,0),(23,1,3,'Friday','Math',117,8,0),(24,1,3,'Friday','Math',104,9,0),(25,1,3,'Friday','Math',117,10,0),(26,2,3,'Monday','Biology',201,1,0),(27,2,3,'Monday','Biology',201,2,0),(28,2,1,'Monday','Biology',101,3,0),(29,1,2,'Monday','English',100,4,1),(30,2,2,'Monday','Biology',101,5,0),(31,2,1,'Tuesday','Biology',201,5,0),(32,2,1,'Tuesday','Biology',201,6,0),(33,2,3,'Tuesday','Biology',101,7,0),(34,2,3,'Tuesday','Biology',201,8,0),(35,2,2,'Tuesday','Biology',101,9,0),(36,2,2,'Wednesday','Biology',201,3,0),(37,2,1,'Wednesday','Biology',201,4,0),(38,2,2,'Wednesday','Biology',101,5,0),(39,2,3,'Wednesday','Biology',201,6,0),(40,2,3,'Wednesday','Biology',101,7,0),(41,2,2,'Thursday','Biology',201,1,0),(42,2,3,'Thursday','Biology',201,2,0),(43,2,1,'Thursday','Biology',101,5,0),(44,2,2,'Thursday','Biology',201,6,0),(45,2,2,'Thursday','Biology',101,7,0),(46,2,3,'Friday','Biology',201,1,0),(47,2,1,'Friday','Biology',201,2,0),(48,2,2,'Friday','Biology',101,8,0),(49,2,1,'Friday','Biology',201,9,0),(50,2,1,'Friday','Biology',101,10,0);
/*!40000 ALTER TABLE `lessons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `units`
--

DROP TABLE IF EXISTS `units`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `units` (
  `unit_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`unit_id`),
  UNIQUE KEY `unit_id_UNIQUE` (`unit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `units`
--

LOCK TABLES `units` WRITE;
/*!40000 ALTER TABLE `units` DISABLE KEYS */;
INSERT INTO `units` VALUES (1,'1BT'),(2,'3BT'),(3,'4BT');
/*!40000 ALTER TABLE `units` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_has_unit`
--

DROP TABLE IF EXISTS `user_has_unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_has_unit` (
  `user_id` int NOT NULL,
  `unit_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`unit_id`),
  KEY `fk_User_has_Unit_Unit1_idx` (`unit_id`),
  KEY `fk_User_has_Unit_User_idx` (`user_id`),
  CONSTRAINT `fk_User_has_Unit_Unit1` FOREIGN KEY (`unit_id`) REFERENCES `units` (`unit_id`),
  CONSTRAINT `fk_User_has_Unit_User` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_has_unit`
--

LOCK TABLES `user_has_unit` WRITE;
/*!40000 ALTER TABLE `user_has_unit` DISABLE KEYS */;
INSERT INTO `user_has_unit` VALUES (6,1),(7,2),(8,3);
/*!40000 ALTER TABLE `user_has_unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role` enum('TEACHER','ADMIN','STUDENT') DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'jankowalski@gmail.com','password1','TEACHER'),(2,'adammazur@email.com','password2','TEACHER'),(3,'karolpodlesny@email.com','password3','TEACHER'),(4,'michalnowak@email.com','password4','TEACHER'),(5,'fabianradkowski@email.com','password5','TEACHER'),(6,'john.doe@gmail.com','password1','STUDENT'),(7,'jane.smith@gmail.com','password2','STUDENT'),(8,'alex.jones@gmail.com','password3','STUDENT'),(9,'admin','12345678','ADMIN');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-18  1:39:59
