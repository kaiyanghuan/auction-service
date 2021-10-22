-- Dumping structure for table auction.accounts
CREATE TABLE IF NOT EXISTS `accounts` (
  `id` binary(255) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_timestamp` datetime NOT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_timestamp` datetime DEFAULT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `account_start_date` datetime DEFAULT NULL,
  `account_type` varchar(50) DEFAULT NULL,
  `currency` varchar(255) DEFAULT NULL,
  `freeze` bit(1) DEFAULT NULL,
  `last_active_date` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `account_status` varchar(255) DEFAULT NULL,
  `value` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnjuop33mo69pd79ctplkck40n` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

-- Dumping structure for table auction.hibernate_sequence
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf16;

-- Dumping structure for table auction.users
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
  `permissions` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16;