-- MySQL dump 10.13  Distrib 5.7.34, for Linux (x86_64)
--
-- Host: localhost    Database: cfms
-- ------------------------------------------------------
-- Server version	5.7.34-0ubuntu0.18.04.1

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
-- Table structure for table `CriminalPerson`
--

DROP TABLE IF EXISTS `CriminalPerson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CriminalPerson` (
  `cpID` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `date` varchar(255) NOT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `type` varchar(255) NOT NULL,
  `reportID` int(11) DEFAULT NULL,
  `cpStatus` varchar(255) NOT NULL,
  PRIMARY KEY (`cpID`),
  KEY `reportID` (`reportID`),
  CONSTRAINT `CriminalPerson_ibfk_1` FOREIGN KEY (`reportID`) REFERENCES `Report` (`rID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CriminalPerson`
--

LOCK TABLES `CriminalPerson` WRITE;
/*!40000 ALTER TABLE `CriminalPerson` DISABLE KEYS */;
INSERT INTO `CriminalPerson` VALUES (0,'ayse',102,'20.6.2021 2:8:33.517031','6669995533','Missing',13,'Searching'),(4,'Polat Alemdar',55,'19.06.2021','5550002222','Missing',4,'Not found'),(14,'jack',20,'20.6.2021 2:8:33.100315891','1112223344','Most Wanted',15,'APB Created, investigating'),(16,'fatma',42,'20.6.2021 2:8:33.107821397','6669995533','Missing',17,'Searching'),(18,'thomas',22,'20.6.2021 2:8:33.115111665','1112223344','Most Wanted',19,'APB Created, investigating'),(20,'hayriye',14,'20.6.2021 2:8:33.122238785','7770006644','Missing',21,'Searching'),(22,'arthur',27,'20.6.2021 2:8:33.126777964','2223334455','Most Wanted',23,'APB Created, investigating'),(24,'alex',7,'20.6.2021 2:8:33.136701286','8881117733','Missing',25,'Searching'),(26,'phoebe',86,'20.6.2021 2:8:33.142970242','33344445566','Most Wanted',27,'APB Created, investigating'),(57,'deneme1',27,'25.6.2021 3:41:34.596251150','556','Missing',58,'Searching');
/*!40000 ALTER TABLE `CriminalPerson` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Hotnews`
--

DROP TABLE IF EXISTS `Hotnews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Hotnews` (
  `hnID` int(11) NOT NULL AUTO_INCREMENT,
  `hnContent` varchar(255) NOT NULL,
  `hnDate` varchar(255) NOT NULL,
  `adminID` int(11) DEFAULT NULL,
  PRIMARY KEY (`hnID`),
  KEY `adminID` (`adminID`),
  CONSTRAINT `Hotnews_ibfk_1` FOREIGN KEY (`adminID`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Hotnews`
--

LOCK TABLES `Hotnews` WRITE;
/*!40000 ALTER TABLE `Hotnews` DISABLE KEYS */;
INSERT INTO `Hotnews` VALUES (1,'1000 kullaniciya ulasildi','20.6.2021 2:29:7.105653145',10),(2,'Guvenliginiz bizim icin önemli','20.6.2021 2:29:7.107477899',11),(3,'Bize geri donutlerinizi gonderin','20.6.2021 2:29:7.110504615',12),(4,'Sistem 2.0 a guncellendi','20.6.2021 2:29:7.112318447',10),(5,'IoT ile beraber suclular makalemizi inceleyin','20.6.2021 2:29:7.114850679',11),(6,'Bu ay toplamda 100 kisi yakalandi','20.6.2021 2:29:7.90611443',12);
/*!40000 ALTER TABLE `Hotnews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Report`
--

DROP TABLE IF EXISTS `Report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Report` (
  `rID` int(11) NOT NULL,
  `reportName` varchar(255) DEFAULT NULL,
  `reportLocation` varchar(255) DEFAULT NULL,
  `reportContent` varchar(255) DEFAULT NULL,
  `reportDate` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `cpID` int(11) DEFAULT NULL,
  `uID` int(11) NOT NULL,
  `rStatus` varchar(255) NOT NULL,
  PRIMARY KEY (`rID`),
  KEY `cpID` (`cpID`),
  KEY `uID` (`uID`),
  CONSTRAINT `Report_ibfk_2` FOREIGN KEY (`uID`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Report`
--

LOCK TABLES `Report` WRITE;
/*!40000 ALTER TABLE `Report` DISABLE KEYS */;
INSERT INTO `Report` VALUES (4,'Izmirde Kayip','Izmir','Izmirde aksam saatlerinde kaybolan kisi araniyor','19.06.2021','Missing People Report',4,1,'Started'),(5,'People Missing','izmir','Izmirde kaybolan birisi','17.06.2021','Missing People Report',4,1,'Started'),(13,'izmirde Hirsizlik','Cennet mahallesi','Mahallede kavga','20.6.2021 2:8:33.78011769','Missing People Report',0,6,'Started'),(15,'buyuculer kacti','Cennet mahallesi','Mahallede kavga','20.6.2021 2:8:33.100569648','Most Wanted Report',14,6,'Started'),(17,'Adanada kacirma','Kurtlar vadisi','Racon kestiler','20.6.2021 2:8:33.107887288','Missing People Report',16,7,'Started'),(19,'orgimmara baskin','Kurtlar vadisi','Racon kestiler','20.6.2021 2:8:33.115171204','Most Wanted Report',18,7,'Started'),(21,'Ugandada cinayet','Orgrimmar','Efsane kurtlar vadisi raconlari','20.6.2021 2:8:33.122312604','Missing People Report',20,8,'Started'),(23,'olanlara inanamiyacaksiniz','Orgrimmar','Efsane kurtlar vadisi raconlari','20.6.2021 2:8:33.126813004','Most Wanted Report',22,8,'Started'),(25,'ankarada soygun','Cennet mahallesi','Mahallede kavga','20.6.2021 2:8:33.136748982','Missing People Report',24,9,'Started'),(27,'buyuculer kacti','Cennet mahallesi','Mahallede kavga','20.6.2021 2:8:33.143023980','Most Wanted Report',26,9,'Started'),(58,'deneme1 kayip','izmir','deneme1 bulundu','25.6.2021 3:41:48.640995666','Missing People Report',57,7,'found'),(60,'deneme2','izmir','nerde bilinmiyor','25.6.2021 3:44:55.485600223','Complaint',-1,7,'Started'),(92,'deneme3','izmir','no','25.6.2021 3:47:50.105633897','Complaint',-1,7,'Started');
/*!40000 ALTER TABLE `Report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `id` int(11) NOT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `createdAt` varchar(255) DEFAULT NULL,
  `lastDate` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `loggedIn` char(1) DEFAULT 'F',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (0,'sedat','21a859a8873436b4149908273fae0e01','20.6.2021 1:14:22.741171258',NULL,'normal','F'),(1,'burak','f346a8dd3905996459ddd8edec7de68d','25.02','17.6.2021 23:14:2.1848716','normal','T'),(2,'ahmet','ccd970666edc2b8c40a446f7b9c77ec0','6.66','7.77','normal','F'),(3,'faruk','ac807d6248ed4a68197c817e8fd1f4a5','20.02','2.2','normal','F'),(6,'arthas','21a859a8873436b4149908273fae0e01','20.6.2021 1:14:22.825351371',NULL,'normal','F'),(7,'garrosh','37e8d3102e657871e01e4d8f20c0606e','20.6.2021 1:14:22.828616491',NULL,'normal','T'),(8,'ezgi','21a859a8873436b4149908273fae0e01','20.6.2021 1:14:22.830408283',NULL,'normal','F'),(9,'baris','21a859a8873436b4149908273fae0e01','20.6.2021 1:14:22.832612379',NULL,'normal','F'),(10,'servan','37e8d3102e657871e01e4d8f20c0606e','20.6.2021 1:14:22.836538428',NULL,'admin','T'),(11,'gulsah','37e8d3102e657871e01e4d8f20c0606e','20.6.2021 1:14:22.841283997',NULL,'admin','F'),(12,'serhat','ab6385c734591ca887b8463305f3e10c','20.6.2021 1:14:22.852511315',NULL,'admin','F');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserMessages`
--

DROP TABLE IF EXISTS `UserMessages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserMessages` (
  `mID` int(11) NOT NULL AUTO_INCREMENT,
  `senderUID` int(11) NOT NULL,
  `recieverUID` int(11) NOT NULL,
  `messageContent` varchar(255) DEFAULT NULL,
  `sentAt` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mID`),
  KEY `senderUID` (`senderUID`),
  KEY `recieverUID` (`recieverUID`),
  CONSTRAINT `UserMessages_ibfk_1` FOREIGN KEY (`senderUID`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `UserMessages_ibfk_2` FOREIGN KEY (`recieverUID`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserMessages`
--

LOCK TABLES `UserMessages` WRITE;
/*!40000 ALTER TABLE `UserMessages` DISABLE KEYS */;
INSERT INTO `UserMessages` VALUES (1,1,2,'Yo whatup','22.06'),(6,3,9,'Merhabalar nasilsin','20.6.2021 2:48:47.743533102'),(7,0,6,'Projeyi naptin','20.6.2021 2:48:47.758097701'),(8,3,9,'Son sedat peker videosunu izledin mi','20.6.2021 2:48:47.761641321'),(9,2,8,'Pazar isin var mi','20.6.2021 2:48:47.764367417'),(10,1,7,'Gelecek hakkinda hic endisem yok','20.6.2021 2:48:47.767236976'),(11,1,7,'Allah kurtarsin','20.6.2021 2:48:47.773639273'),(12,7,9,'hello baby','25.6.2021 3:59:5.469714253');
/*!40000 ALTER TABLE `UserMessages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserNotifications`
--

DROP TABLE IF EXISTS `UserNotifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserNotifications` (
  `mID` int(11) NOT NULL AUTO_INCREMENT,
  `senderUID` int(11) NOT NULL,
  `recieverUID` int(11) NOT NULL,
  `messageContent` varchar(255) DEFAULT NULL,
  `sentAt` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mID`),
  KEY `senderUID` (`senderUID`),
  KEY `recieverUID` (`recieverUID`),
  CONSTRAINT `UserNotifications_ibfk_1` FOREIGN KEY (`senderUID`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `UserNotifications_ibfk_2` FOREIGN KEY (`recieverUID`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserNotifications`
--

LOCK TABLES `UserNotifications` WRITE;
/*!40000 ALTER TABLE `UserNotifications` DISABLE KEYS */;
INSERT INTO `UserNotifications` VALUES (1,1,2,'Yo whatup','23.06'),(2,1,7,'5 yeni mesaj','20.6.2021 2:51:2.896443011'),(3,1,7,'Raporunuz guncellendi','20.6.2021 2:51:2.900338353'),(4,1,7,'Sedat peker yeni video cikardi','20.6.2021 2:51:2.903067175'),(5,1,7,'Guncelleme getirildi','20.6.2021 2:51:2.904690048'),(6,2,8,'Takip ettiginiz bir suclu yakaland?','20.6.2021 2:51:2.907387525'),(7,3,9,'Allah kurtarsin','20.6.2021 2:51:2.908885455');
/*!40000 ALTER TABLE `UserNotifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userFeedback`
--

DROP TABLE IF EXISTS `userFeedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userFeedback` (
  `fID` int(11) NOT NULL AUTO_INCREMENT,
  `fbContent` varchar(255) NOT NULL,
  `fbDate` varchar(255) NOT NULL,
  `uID` int(11) NOT NULL,
  `rating` int(11) DEFAULT NULL,
  PRIMARY KEY (`fID`),
  KEY `uID` (`uID`),
  CONSTRAINT `userFeedback_ibfk_1` FOREIGN KEY (`uID`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userFeedback`
--

LOCK TABLES `userFeedback` WRITE;
/*!40000 ALTER TABLE `userFeedback` DISABLE KEYS */;
INSERT INTO `userFeedback` VALUES (1,'Kullanimi cok kolay','20.6.2021 2:37:59.152031434',9,4),(2,'Arkadaslarima onerdim','20.6.2021 2:37:59.167644452',8,7),(3,'Mukkemel uygulama 10/10','20.6.2021 2:37:59.170452231',8,6),(4,'Cok hizli calisiyor','20.6.2021 2:37:59.172263504',7,1),(5,'istedigimiz yerden ulasabiliyoruz','20.6.2021 2:37:59.177497405',5,3),(6,'Sayesinde bir cok suclunun yakalanmasina yardim ettim','20.6.2021 2:37:59.181150477',9,0),(7,'kodu cok guzel yazilmis','25.6.2021 3:33:48.536185392',7,10);
/*!40000 ALTER TABLE `userFeedback` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-25 14:50:03
