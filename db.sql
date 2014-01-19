CREATE DATABASE  IF NOT EXISTS `onlinetutoring` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `onlinetutoring`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: onlinetutoring
-- ------------------------------------------------------
-- Server version	5.6.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `answers`
--

DROP TABLE IF EXISTS `answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answers` (
  `answer_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `question_id` bigint(20) DEFAULT NULL,
  `author_id` bigint(20) DEFAULT NULL,
  `score` bigint(20) DEFAULT NULL,
  `text` varchar(2500) DEFAULT NULL,
  `time_created` datetime DEFAULT NULL,
  `editor_id` bigint(20) DEFAULT NULL,
  `time_edited` datetime DEFAULT NULL,
  PRIMARY KEY (`answer_id`),
  KEY `fk_question_id_idx` (`question_id`),
  KEY `fk_author_id_idx` (`author_id`),
  KEY `fk_editor_id_idx` (`editor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answers`
--

LOCK TABLES `answers` WRITE;
/*!40000 ALTER TABLE `answers` DISABLE KEYS */;
INSERT INTO `answers` VALUES (12,13,1,1,'asdsdsa','2014-01-09 22:49:37',NULL,NULL),(18,13,1,0,'assdassadsadasd','2014-01-11 00:25:39',NULL,NULL),(19,3,1,0,'test answeer','2014-01-11 23:17:46',NULL,NULL),(20,15,1,0,'sdsadasdasd','2014-01-11 23:38:06',NULL,NULL),(21,12,1,0,'demo answre1','2014-01-13 09:56:02',NULL,NULL),(22,2,1,0,'sdasdasdasd','2014-01-13 21:27:29',NULL,NULL),(23,21,1,0,'asdasd','2014-01-16 10:06:06',NULL,NULL),(24,21,1,0,'sdadasdasdasdasdsa','2014-01-16 10:07:16',NULL,NULL),(25,21,1,0,'xczxczxczxcxczxcxzcxzc','2014-01-16 10:07:54',NULL,NULL);
/*!40000 ALTER TABLE `answers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_category`
--

DROP TABLE IF EXISTS `question_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question_category` (
  `question_category_id` bigint(20) NOT NULL,
  `question_category` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`question_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_category`
--

LOCK TABLES `question_category` WRITE;
/*!40000 ALTER TABLE `question_category` DISABLE KEYS */;
INSERT INTO `question_category` VALUES (1,'Computer Programming'),(2,'Music');
/*!40000 ALTER TABLE `question_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_tags`
--

DROP TABLE IF EXISTS `question_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question_tags` (
  `question_id` bigint(20) DEFAULT NULL,
  `tag_id` bigint(20) DEFAULT NULL,
  KEY `b_idx` (`tag_id`),
  KEY `fk_question_id` (`question_id`),
  CONSTRAINT `fk_question_id` FOREIGN KEY (`question_id`) REFERENCES `questions` (`question_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tagidd` FOREIGN KEY (`tag_id`) REFERENCES `tags` (`tag_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_tags`
--

LOCK TABLES `question_tags` WRITE;
/*!40000 ALTER TABLE `question_tags` DISABLE KEYS */;
INSERT INTO `question_tags` VALUES (13,8),(15,10),(18,55),(18,56),(12,57),(12,58),(20,59),(20,60),(21,61),(21,62),(22,63),(22,64);
/*!40000 ALTER TABLE `question_tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questions` (
  `question_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `question_title` varchar(125) DEFAULT NULL,
  `question_description` varchar(1024) DEFAULT NULL,
  `author_id` bigint(20) DEFAULT NULL,
  `time_created` datetime DEFAULT NULL,
  `editor_id` bigint(20) DEFAULT NULL,
  `time_edited` datetime DEFAULT NULL,
  `is_closed` int(11) NOT NULL DEFAULT '0',
  `category_id` bigint(20) NOT NULL,
  `score` bigint(20) DEFAULT NULL,
  `views` bigint(20) DEFAULT '0',
  PRIMARY KEY (`question_id`),
  KEY `fk_asked_by_idx` (`author_id`),
  KEY `g_idx` (`category_id`),
  CONSTRAINT `fk_category_id` FOREIGN KEY (`category_id`) REFERENCES `question_category` (`question_category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (1,'[CLOSED]-REASON[ NO REASON ]ass','asdasdasd',1,'2014-01-03 21:33:48',NULL,NULL,1,1,1,1),(2,'[CLOSED]-REASON[ TEST REASON ]JAVA HOME','ZZZZZZZZZZ',1,'2014-01-03 21:38:44',1,'2014-01-13 21:32:09',1,1,1,1),(3,'c#','sasas',1,'2014-01-03 21:41:16',NULL,NULL,0,1,1,1),(4,'too1','sd',1,'2014-01-03 22:00:38',NULL,NULL,0,1,0,0),(5,'w1','rere',1,'2014-01-03 22:05:29',NULL,NULL,0,1,0,1),(6,'s','asas',1,'2014-01-03 22:14:02',NULL,NULL,0,1,0,0),(12,'dfdsfghhg','descrtptio',1,'2014-01-04 00:32:36',1,'2014-01-13 22:11:56',0,1,0,0),(13,'[CLOSED]-REASON[ FGFGF ]sadsad','as',1,'2014-01-06 17:49:07',NULL,NULL,1,1,0,1),(15,'sadsad','as',1,'2014-01-06 18:57:30',NULL,NULL,0,1,0,2),(18,'[CLOSED]-REASON[ CLOSE ]changed','demo 3',1,'2014-01-13 04:37:08',1,'2014-01-13 09:41:27',1,1,0,0),(20,'insert','what is insert in sql',1,'2014-01-16 09:30:54',NULL,NULL,0,1,0,0),(21,'update','What is update in sql',1,'2014-01-16 09:31:33',NULL,NULL,0,1,0,0),(22,'array','How to write array in java',1,'2014-01-16 09:32:01',NULL,NULL,0,1,0,0);
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tags`
--

DROP TABLE IF EXISTS `tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tags` (
  `tag_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tag` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tags`
--

LOCK TABLES `tags` WRITE;
/*!40000 ALTER TABLE `tags` DISABLE KEYS */;
INSERT INTO `tags` VALUES (1,'Java Script'),(2,'Java Script'),(7,'Java Script'),(8,'Java Script'),(10,'Java Script'),(13,'demo tag1 '),(14,'demo tag2'),(15,'sadasdasdasd'),(16,'sadasdasdasd'),(17,'ddd'),(18,'ddd'),(19,'wwwww'),(20,'www'),(21,'wwwww'),(22,'www'),(23,'wwwww'),(24,'www'),(25,'wwwww'),(26,'www'),(27,'wwwww'),(28,'www'),(29,'wwwww'),(30,'www'),(31,'wwwww'),(32,'www'),(33,'wwwww'),(34,'www'),(35,'wwwww'),(36,'www'),(37,'wwwww'),(38,'www'),(39,'wwwww'),(40,'www'),(41,'wwwww'),(42,'www'),(43,'wwwww'),(44,'www'),(45,'wwwww'),(46,'www'),(47,'wwwww'),(48,'www'),(49,'wwwww'),(50,'www'),(51,'wwwww'),(52,'www'),(55,'demo tag1 '),(56,'demo tag2'),(57,'Java Script'),(58,'java'),(59,'sql'),(60,'sql'),(61,'sql'),(62,'sql'),(63,'java'),(64,'java');
/*!40000 ALTER TABLE `tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) DEFAULT NULL,
  `email_address` varchar(45) DEFAULT NULL,
  `password` varchar(256) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `roleId` bigint(20) DEFAULT NULL,
  `is_banded` int(11) NOT NULL DEFAULT '0',
  `reputation` bigint(20) DEFAULT '0',
  `loyality` bigint(20) DEFAULT '0',
  `is_pending` int(11) DEFAULT '0',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`),
  KEY `fk_role_id_idx` (`roleId`),
  CONSTRAINT `fk_role_id` FOREIGN KEY (`roleId`) REFERENCES `user_role` (`role_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Harshitha','harshdusilves@gmail.com','e462a855c9e5b9f0ee5561ec2a86dbe4b1c58feab96235d6be25a54912ab676f','2014-01-03 19:37:52',1,0,1,2,0),(2,'user','user@gmail.com','e462a855c9e5b9f0ee5561ec2a86dbe4b1c58feab96235d6be25a54912ab676f','2014-01-03 19:37:52',2,0,0,0,1),(3,'pendingtutor','tutor@gmail.com','e462a855c9e5b9f0ee5561ec2a86dbe4b1c58feab96235d6be25a54912ab676f','2014-01-12 10:55:29',3,0,0,0,1),(4,'user1','user1@gmail.com','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','2014-01-12 11:36:14',1,0,0,0,0),(7,'demotutor','demo@gmail.com','e462a855c9e5b9f0ee5561ec2a86dbe4b1c58feab96235d6be25a54912ab676f','2014-01-13 13:57:46',3,0,0,0,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `role_id` bigint(20) NOT NULL,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_MODERATOR'),(3,'ROLE_USER');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `view_question`
--

DROP TABLE IF EXISTS `view_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `view_question` (
  `view_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `question_id` bigint(20) DEFAULT NULL,
  `userid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`view_id`),
  KEY `fk_viewedby_idx` (`userid`),
  CONSTRAINT `fk_viewedby` FOREIGN KEY (`userid`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `view_question`
--

LOCK TABLES `view_question` WRITE;
/*!40000 ALTER TABLE `view_question` DISABLE KEYS */;
INSERT INTO `view_question` VALUES (1,17,1),(2,17,2),(3,1,2),(4,13,2),(5,15,2),(6,3,2),(7,15,1),(8,14,2),(9,5,2),(10,2,2);
/*!40000 ALTER TABLE `view_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voting_answer`
--

DROP TABLE IF EXISTS `voting_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `voting_answer` (
  `vote_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `answer_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`vote_id`),
  KEY `fk_answerId_idx` (`answer_id`),
  CONSTRAINT `fk_answerId` FOREIGN KEY (`answer_id`) REFERENCES `answers` (`answer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voting_answer`
--

LOCK TABLES `voting_answer` WRITE;
/*!40000 ALTER TABLE `voting_answer` DISABLE KEYS */;
INSERT INTO `voting_answer` VALUES (3,12,2,1),(4,19,2,1);
/*!40000 ALTER TABLE `voting_answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voting_question`
--

DROP TABLE IF EXISTS `voting_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `voting_question` (
  `vote_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `question_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`vote_id`),
  KEY `fk_questionid_idx` (`question_id`),
  CONSTRAINT `fk_questionid` FOREIGN KEY (`question_id`) REFERENCES `questions` (`question_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voting_question`
--

LOCK TABLES `voting_question` WRITE;
/*!40000 ALTER TABLE `voting_question` DISABLE KEYS */;
INSERT INTO `voting_question` VALUES (4,3,2,1),(5,1,2,1),(6,2,2,1);
/*!40000 ALTER TABLE `voting_question` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-01-16 10:59:40
