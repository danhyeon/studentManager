CREATE DATABASE  IF NOT EXISTS `studentmanager` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `studentmanager`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: studentmanager
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `sno` int NOT NULL AUTO_INCREMENT,
  `name` char(6) NOT NULL,
  `birth` date NOT NULL,
  `gender` char(2) NOT NULL,
  `phoneNumber` char(11) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `teamNumber` smallint DEFAULT NULL,
  `seatNumber` smallint DEFAULT NULL,
  PRIMARY KEY (`sno`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (1,'박희람','1990-02-26','남','01056291826','parkeryangga@naver.com',NULL,14),(2,'구은서','1992-03-27','여','01068874321','supreme327@naver.com',NULL,15),(3,'권동협','1995-02-23','남','01051893995','myvj123@naver.com',NULL,17),(4,'박해규','1998-03-07','여','01024566437','0qkrgorb0@naver.com',NULL,13),(5,'이현민','1994-05-16','남','01063317054','sangrock510@naver.com',NULL,28),(6,'변경원','0217-06-24','여','01073239410','qusruddnjs25@naver.com',NULL,4),(8,'박경호','1992-01-22','남','01022743066','ossetian@naver.com',NULL,3),(9,'박다슬','1994-03-26','여','01021129363','aot1212@naver.com',NULL,9),(10,'박주현','1997-01-08','여','01097076818','zzjuju12@naver.com',NULL,24),(11,'신재구','1994-06-30','남','01056416022','worntls94@gmail.com',NULL,1),(12,'안서희','1994-08-02','여','01039186793','sktalk3318@gmail.com',NULL,21),(13,'양유진','1994-08-02','여','01067997275','fullmoon158@naver.com',NULL,11),(14,'옥다경','1994-08-02','여','01020702209','ekrud926@naver.com',NULL,2),(15,'우병규','1994-08-22','남','01063611920','dnqudrb94@naver.com',NULL,10),(16,'이상봉','1992-07-23','남','01025187296','halro92@naver.com',NULL,32),(17,'이진석','1993-12-07','남','01058853374','wlstmrdl93@naver.com',NULL,26),(18,'이혜영','1995-07-27','여','01068590955','ly707278@gmail.com',NULL,20),(19,'장지민','0138-10-17','여','01084809818','wa9818@naver.com',NULL,23),(20,'정보성','1996-12-01','남','01051199637','wjdqhtjd12@naver.com',NULL,30),(21,'정성욱','1997-01-29','남','01028337684','deas124@gmail.com',NULL,5),(22,'정수인','1997-04-01','여','01025081207','l3esde67@naver.com',NULL,22),(23,'천홍근','1999-05-08','남','01051347696','cbc7697@gmail.com',NULL,19),(24,'한동진','1997-03-10','여','01040106013','handj0310@gmail.com',NULL,16),(25,'박은진','1998-01-01','여','01055124219','obliviscor29@naver.com',NULL,18),(26,'장영민','2000-01-01','남','01071461142','oxox123456@naver.com',NULL,29),(34,'진성록','1997-02-21','남','01038586661','jk3858@naver.com',NULL,7);
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-11 12:43:22
