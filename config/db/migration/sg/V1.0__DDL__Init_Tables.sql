-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.12-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for auction
CREATE DATABASE IF NOT EXISTS `auction` /*!40100 DEFAULT CHARACTER SET utf16 */;
USE `auction`;

-- Dumping structure for table auction.accounts
DROP TABLE IF EXISTS `accounts`;
CREATE TABLE IF NOT EXISTS `accounts` (
  `id` varchar(100) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_timestamp` datetime NOT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_timestamp` datetime DEFAULT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `account_start_date` datetime DEFAULT NULL,
  `account_type` varchar(255) DEFAULT NULL,
  `currency` varchar(255) DEFAULT NULL,
  `last_active_date` datetime DEFAULT NULL,
  `account_status` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `value` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnjuop33mo69pd79ctplkck40n` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

-- Dumping structure for table auction.api_clients
DROP TABLE IF EXISTS `api_clients`;
CREATE TABLE IF NOT EXISTS `api_clients` (
  `id` varchar(100) NOT NULL,
  `access_key` varchar(512) NOT NULL,
  `application_code` varchar(255) DEFAULT NULL,
  `application_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

-- Dumping data for table auction.api_clients: 0 rows
/*!40000 ALTER TABLE `api_clients` DISABLE KEYS */;
/*!40000 ALTER TABLE `api_clients` ENABLE KEYS */;

-- Dumping structure for table auction.hibernate_sequence
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

-- Dumping data for table auction.hibernate_sequence: 2 rows
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES
	(18),
	(18);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;

-- Dumping structure for table auction.users
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_timestamp` datetime NOT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_timestamp` datetime DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `roles` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

