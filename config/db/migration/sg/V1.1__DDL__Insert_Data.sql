-- Dumping data for table auction.accounts: 23 rows
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` (`id`, `created_by`, `created_timestamp`, `last_modified_by`, `last_modified_timestamp`, `account_number`, `account_start_date`, `account_type`, `currency`, `last_active_date`, `account_status`, `user_id`, `value`) VALUES
	('7bd748cc-0d0d-4a7c-83d8-8b2c5942a51f', 'System', '2021-08-07 18:38:18', 'System', '2021-08-07 18:38:18', '08c8d79e-a1fc-4098-a174-510f161d6d7b', '2021-08-07 18:38:18', 'SAVINGS', 'SGD', '2021-08-07 18:38:18', 'ACTIVE', 2, 10000.00),
	('2c55ae37-a51c-41d4-ae04-06ea5f5761c7', 'System', '2021-08-07 18:38:22', 'System', '2021-08-07 21:25:52', '57659b71-e4ae-47ba-97b6-d7ac33218629', '2021-08-07 18:38:22', 'SAVINGS', 'SGD', '2021-08-07 21:25:52', 'ACTIVE', 1, 94124.00),
	('264832ac-254a-4685-8abd-b8572cb313ee', 'System', '2021-08-07 18:38:25', 'System', '2021-08-07 18:38:25', '017e55d4-ac2c-4127-802e-dcc8158ac98f', '2021-08-07 18:38:25', 'SAVINGS', 'SGD', '2021-08-07 18:38:25', 'ACTIVE', 4, 412412.00),
	('2b78ed67-0dd5-4222-9bdc-dfb4e89ab8c6', 'System', '2021-08-07 18:38:28', 'System', '2021-08-07 18:38:28', 'eb7bddcc-4e44-4839-871b-9e050abeea67', '2021-08-07 18:38:28', 'SAVINGS', 'SGD', '2021-08-07 18:38:28', 'FROZEN', 5, 151515.00),
	('a9c44709-c2ee-48f1-8447-6514fc0179cc', 'System', '2021-08-07 18:40:00', 'System', '2021-08-07 18:40:00', '3b6cb979-ef80-4da2-ba39-033a9ebaf027', '2021-08-07 18:40:00', 'SAVINGS', 'SGD', '2021-08-07 18:40:00', 'ACTIVE', 6, 5235161.00),
	('27527b23-6cfe-4620-beca-e021f0de6ec5', 'System', '2021-08-07 18:40:44', 'System', '2021-08-07 18:40:44', 'e74ebd25-297c-47c5-8da7-e9b86dbe7610', '2021-08-07 18:40:43', 'CURRENT', 'SGD', '2021-08-07 18:40:43', 'PENDING', 1, 62311.00),
	('1d90ec0c-98ac-4b09-960f-d0da8c30869a', 'System', '2021-08-07 18:40:50', 'System', '2021-08-07 18:40:50', '9000b793-eb21-4812-a353-c8d3c07e8cf4', '2021-08-07 18:40:50', 'CURRENT', 'SGD', '2021-08-07 18:40:50', 'CLOSED', 3, 0.00),
	('f0d88916-1925-495f-a6a1-b349200960ec', 'System', '2021-08-07 18:40:52', 'System', '2021-08-07 18:40:52', 'f9721364-80ae-4f83-baf4-7d207e301174', '2021-08-07 18:40:52', 'CURRENT', 'SGD', '2021-08-07 18:40:52', 'ACTIVE', 4, 89741.00),
	('9319318b-dd51-451e-9664-5ad97f2393ba', 'System', '2021-08-07 18:41:07', 'System', '2021-08-07 18:41:07', 'e460a647-c479-4350-b094-8f06d0ce65e8', '2021-08-07 18:41:07', 'GLOBAL_SAVINGS_ACCOUNT', 'USD', '2021-08-07 18:41:07', 'ACTIVE', 1, 637327.00),
	('8226844a-7650-4fde-b209-56c63b9a113d', 'System', '2021-08-07 18:41:10', 'System', '2021-08-07 18:41:10', '68e98390-f76c-4d91-8c55-858d0b2cbd39', '2021-08-07 18:41:10', 'GLOBAL_SAVINGS_ACCOUNT', 'HKD', '2021-08-07 18:41:10', 'ACTIVE', 1, 891741.00),
	('7dcaf17f-8e12-402d-a66b-1ff9e6b34d7a', 'System', '2021-08-07 18:41:31', 'System', '2021-08-07 18:41:31', 'c904cd68-c342-40f3-b94a-0d0aed34ecba', '2021-08-07 18:41:31', 'GLOBAL_SAVINGS_ACCOUNT', 'EUR', '2021-08-07 18:41:31', 'ACTIVE', 1, 45262.00),
	('2ed3664e-28a5-4409-9987-68b666dc3683', 'janice', '2021-09-23 20:21:38', 'janice', '2021-09-23 20:21:38', 'd331d6f3-63b1-4f1f-ba68-d3cc085fa685', '2021-09-23 20:21:38', 'GLOBAL_SAVINGS_ACCOUNT', 'JPY', '2021-09-23 20:21:38', 'PENDING', 5, 0.00),
	('9873e503-8ea5-4008-a508-f22c1f361e39', 'janice', '2021-09-23 20:21:49', 'janice', '2021-09-23 20:21:49', 'e0004232-57a6-43f0-afac-168fd357c794', '2021-09-23 20:21:49', 'GLOBAL_SAVINGS_ACCOUNT', 'USD', '2021-09-23 20:21:49', 'PENDING', 5, 0.00),
	('9e918648-45ab-44e7-8a29-b794102772d5', 'janice', '2021-09-23 20:21:51', 'janice', '2021-09-23 20:21:51', 'f289644c-ded8-4c42-b0d5-5527eeb5b97e', '2021-09-23 20:21:51', 'GLOBAL_SAVINGS_ACCOUNT', 'HKD', '2021-09-23 20:21:51', 'PENDING', 5, 0.00),
	('97850d8f-0972-413b-a5a8-f6641b86d2f8', 'janice', '2021-09-23 20:21:54', 'janice', '2021-09-23 20:21:54', 'af4d2aaa-73c2-4461-b595-bb4b5b93dfce', '2021-09-23 20:21:54', 'GLOBAL_SAVINGS_ACCOUNT', 'CHY', '2021-09-23 20:21:54', 'PENDING', 5, 0.00),
	('58af3ab1-bfd4-461f-89e0-9ccd37cdabc1', 'janice', '2021-09-23 20:21:58', 'janice', '2021-09-23 20:21:58', '09f18b7d-1e2f-48dc-b4f6-3e685f28761b', '2021-09-23 20:21:58', 'GLOBAL_SAVINGS_ACCOUNT', 'MYR', '2021-09-23 20:21:58', 'PENDING', 5, 0.00),
	('520a875b-220a-404a-aaec-38325560c06e', 'janice', '2021-09-23 20:22:05', 'janice', '2021-09-23 20:22:05', '9570b303-eb0b-49d5-9a7e-af23c0d6ae63', '2021-09-23 20:22:05', 'GLOBAL_SAVINGS_ACCOUNT', 'EUR', '2021-09-23 20:22:05', 'PENDING', 5, 0.00),
	('10ac7e12-407a-466c-baed-0f06364393e5', 'janice', '2021-09-23 20:23:11', 'janice', '2021-09-23 20:23:11', '1cbe4618-3394-465b-8fd1-8a2c9dddfe19', '2021-09-23 20:23:11', 'GLOBAL_SAVINGS_ACCOUNT', 'EUR', '2021-09-23 20:23:11', 'PENDING', 2, 0.00),
	('6d5bc86d-fb3d-4928-9bd2-c8d7ee26be6b', 'janice', '2021-09-23 20:23:13', 'janice', '2021-09-23 20:23:13', '27b38aa1-2896-435c-8f98-948bda0cf6b2', '2021-09-23 20:23:13', 'GLOBAL_SAVINGS_ACCOUNT', 'SGD', '2021-09-23 20:23:13', 'PENDING', 2, 0.00),
	('0d65e5c6-e723-4ab3-b58a-eeab889a3c85', 'janice', '2021-09-23 20:23:15', 'janice', '2021-09-23 20:23:15', '0fddaead-d525-40d8-b963-b22479f7fb83', '2021-09-23 20:23:15', 'GLOBAL_SAVINGS_ACCOUNT', 'USD', '2021-09-23 20:23:15', 'PENDING', 2, 0.00),
	('5d77d920-1752-4f61-91b1-af5e270a501c', 'janice', '2021-09-23 20:23:18', 'janice', '2021-09-23 20:23:18', '6113122b-b541-4344-bf01-9b33a2cc9c00', '2021-09-23 20:23:18', 'GLOBAL_SAVINGS_ACCOUNT', 'HKD', '2021-09-23 20:23:18', 'PENDING', 2, 0.00),
	('c37296e9-deaa-4ea6-9d18-89d5d2edddca', 'janice', '2021-09-23 20:23:22', 'janice', '2021-09-23 20:23:22', 'a19a4cf1-92d7-414a-aca4-096c46a91835', '2021-09-23 20:23:22', 'GLOBAL_SAVINGS_ACCOUNT', 'JPY', '2021-09-23 20:23:22', 'PENDING', 2, 0.00),
	('ff327c79-887a-48fc-8a0e-2264f9a57690', 'janice', '2021-09-23 20:28:26', 'janice', '2021-09-23 20:28:26', '7f560b93-55c8-41b0-a635-5dc47a71aad9', '2021-09-23 20:28:26', 'GLOBAL_SAVINGS_ACCOUNT', 'JPY', '2021-09-23 20:28:26', 'PENDING', 2, 0.00);
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;

-- Dumping data for table auction.users: 9 rows
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `created_by`, `created_timestamp`, `last_modified_by`, `last_modified_timestamp`, `address`, `age`, `name`, `password`, `permission`, `roles`, `username`) VALUES
	(1, 'SYSTEM', '2021-08-07 18:38:12', NULL, NULL, 'Punggol', 25, 'Alex Tan', '$2a$10$3juMG1SoyF2qlBYX5T0TN.zeZ2LO3SiAqt64ZefrQm69be6mzXFe.', 'ACCESS_USERS, ACCESS_ACCOUNTS', 'APPROVER', 'alex'),
	(2, 'SYSTEM', '2021-08-07 18:38:12', NULL, NULL, 'Bishan', 25, 'Sally Tang', '$2a$10$3juMG1SoyF2qlBYX5T0TN.zeZ2LO3SiAqt64ZefrQm69be6mzXFe.', 'ACCESS_USERS', 'USER', 'sally'),
	(3, 'SYSTEM', '2021-08-07 18:38:12', NULL, NULL, 'Ang Mo Kio', 25, 'Gavin Wong', '$2a$10$3juMG1SoyF2qlBYX5T0TN.zeZ2LO3SiAqt64ZefrQm69be6mzXFe.', 'ACCESS_USERS', 'USER', 'gavin'),
	(4, 'SYSTEM', '2021-08-07 18:38:12', NULL, NULL, 'Yishun', 20, 'Jonathan Ong', '$2a$10$3juMG1SoyF2qlBYX5T0TN.zeZ2LO3SiAqt64ZefrQm69be6mzXFe.', 'ACCESS_USERS', 'USER', 'jonthan'),
	(5, 'SYSTEM', '2021-08-07 18:38:12', NULL, NULL, 'Jurong', 50, 'Kelly Lim', '$2a$10$3juMG1SoyF2qlBYX5T0TN.zeZ2LO3SiAqt64ZefrQm69be6mzXFe.', 'ACCESS_USERS, ACCESS_ACCOUNTS', 'USER', 'kelly'),
	(6, 'SYSTEM', '2021-08-07 18:38:12', NULL, NULL, 'Khatib', 26, 'Janice Lim', '$2a$10$3juMG1SoyF2qlBYX5T0TN.zeZ2LO3SiAqt64ZefrQm69be6mzXFe.', 'ACCESS_ACCOUNTS_APPROVAL, ACCESS_ACCOUNTS', 'ADMIN', 'janice');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;