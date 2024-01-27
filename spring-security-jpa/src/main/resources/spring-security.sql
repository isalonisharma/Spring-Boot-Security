-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.18-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             12.2.0.6576
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for spring-security
CREATE DATABASE IF NOT EXISTS `spring-security` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `spring-security`;

-- Dumping structure for table spring-security.role
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table spring-security.role: ~2 rows (approximately)
DELETE FROM `role`;
INSERT INTO `role` (`id`, `name`) VALUES
	(1, 'USER'),
	(2, 'ADMIN');

-- Dumping structure for table spring-security.role_seq
CREATE TABLE IF NOT EXISTS `role_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table spring-security.role_seq: ~0 rows (approximately)
DELETE FROM `role_seq`;
INSERT INTO `role_seq` (`next_val`) VALUES
	(1);

-- Dumping structure for table spring-security.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL DEFAULT b'1',
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=utf8;

-- Dumping data for table spring-security.user: ~2 rows (approximately)
DELETE FROM `user`;
INSERT INTO `user` (`id`, `active`, `password`, `username`) VALUES
	(1, b'1', '$2a$12$HVR2mPUA4xlzDV7xkVbg1uegi1eLha/LAxpVaFOVNXpnzYln9IDuW', 'user'),
	(2, b'1', '$2a$12$O7qfJbgGmWc23aD06z9RdOlmAcix.4UfiTTWFVGrf5.pvW.NEOH8S', 'admin');

-- Dumping structure for table spring-security.user_role
CREATE TABLE IF NOT EXISTS `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` bigint(20) NOT NULL,
  `user` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_user_role_user` (`user`),
  KEY `FK_user_role_role` (`role`),
  CONSTRAINT `FK_user_role_role` FOREIGN KEY (`role`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_user_role_user` FOREIGN KEY (`user`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=utf8;

-- Dumping data for table spring-security.user_role: ~2 rows (approximately)
DELETE FROM `user_role`;
INSERT INTO `user_role` (`id`, `role`, `user`) VALUES
	(1, 1, 1),
	(2, 2, 2);

-- Dumping structure for table spring-security.user_role_seq
CREATE TABLE IF NOT EXISTS `user_role_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table spring-security.user_role_seq: ~1 rows (approximately)
DELETE FROM `user_role_seq`;
INSERT INTO `user_role_seq` (`next_val`) VALUES
	(251);

-- Dumping structure for table spring-security.user_seq
CREATE TABLE IF NOT EXISTS `user_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table spring-security.user_seq: ~1 rows (approximately)
DELETE FROM `user_seq`;
INSERT INTO `user_seq` (`next_val`) VALUES
	(251);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
