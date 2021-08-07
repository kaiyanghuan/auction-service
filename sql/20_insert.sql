INSERT INTO `users` (`id`, `address`, `age`, `name`, `created_by`, `created_timestamp`, `last_modified_by`, `last_modified_timestamp`) VALUES
	(1, 'Punggol', 25, 'Alex', 'SYSTEM', NOW(), NULL, NULL),
	(2, 'Bishan', 25, 'Sally', 'SYSTEM', NOW(), NULL, NULL),
	(3, 'Ang Mo Kio', 25, 'Gavin', 'SYSTEM', NOW(), NULL, NULL),
	(4, 'Yishun', 20, 'Jonathan', 'SYSTEM', NOW(), NULL, NULL),
	(5, 'Jurong', 50, 'Kelly', 'SYSTEM', NOW(), NULL, NULL),
	(6, 'Khatib', 26, 'Janice', 'SYSTEM', NOW(), NULL, NULL);

INSERT INTO `hibernate_sequence` (`next_val`) VALUES (7);

INSERT INTO `accounts` (`id`, `created_by`, `created_timestamp`, `last_modified_by`, `last_modified_timestamp`, `account_number`, `account_start_date`, `account_type`, `currency`, `last_active_date`, `account_status`, `user_id`, `value`) VALUES
	('7bd748cc-0d0d-4a7c-83d8-8b2c5942a51f', 'System', '2021-08-07 18:38:18', 'System', '2021-08-07 18:38:18', '08c8d79e-a1fc-4098-a174-510f161d6d7b', '2021-08-07 18:38:18', 'SAVINGS', 'SGD', '2021-08-07 18:38:18', 'ACTIVE', 2, 10000.00),
	('2c55ae37-a51c-41d4-ae04-06ea5f5761c7', 'System', '2021-08-07 18:38:22', 'System', '2021-08-07 18:38:22', '57659b71-e4ae-47ba-97b6-d7ac33218629', '2021-08-07 18:38:22', 'SAVINGS', 'SGD', '2021-08-07 18:38:22', 'PENDING', 1, 94124.00),
	('264832ac-254a-4685-8abd-b8572cb313ee', 'System', '2021-08-07 18:38:25', 'System', '2021-08-07 18:38:25', '017e55d4-ac2c-4127-802e-dcc8158ac98f', '2021-08-07 18:38:25', 'SAVINGS', 'SGD', '2021-08-07 18:38:25', 'ACTIVE', 4, 412412.00),
	('2b78ed67-0dd5-4222-9bdc-dfb4e89ab8c6', 'System', '2021-08-07 18:38:28', 'System', '2021-08-07 18:38:28', 'eb7bddcc-4e44-4839-871b-9e050abeea67', '2021-08-07 18:38:28', 'SAVINGS', 'SGD', '2021-08-07 18:38:28', 'FROZEN', 5, 151515.00),
	('a9c44709-c2ee-48f1-8447-6514fc0179cc', 'System', '2021-08-07 18:40:00', 'System', '2021-08-07 18:40:00', '3b6cb979-ef80-4da2-ba39-033a9ebaf027', '2021-08-07 18:40:00', 'SAVINGS', 'SGD', '2021-08-07 18:40:00', 'ACTIVE', 6, 5235161.00),
	('27527b23-6cfe-4620-beca-e021f0de6ec5', 'System', '2021-08-07 18:40:44', 'System', '2021-08-07 18:40:44', 'e74ebd25-297c-47c5-8da7-e9b86dbe7610', '2021-08-07 18:40:43', 'CURRENT', 'SGD', '2021-08-07 18:40:43', 'PENDING', 1, 62311.00),
	('1d90ec0c-98ac-4b09-960f-d0da8c30869a', 'System', '2021-08-07 18:40:50', 'System', '2021-08-07 18:40:50', '9000b793-eb21-4812-a353-c8d3c07e8cf4', '2021-08-07 18:40:50', 'CURRENT', 'SGD', '2021-08-07 18:40:50', 'CLOSED', 3, 0.00),
	('f0d88916-1925-495f-a6a1-b349200960ec', 'System', '2021-08-07 18:40:52', 'System', '2021-08-07 18:40:52', 'f9721364-80ae-4f83-baf4-7d207e301174', '2021-08-07 18:40:52', 'CURRENT', 'SGD', '2021-08-07 18:40:52', 'ACTIVE', 4, 89741.00),
	('9319318b-dd51-451e-9664-5ad97f2393ba', 'System', '2021-08-07 18:41:07', 'System', '2021-08-07 18:41:07', 'e460a647-c479-4350-b094-8f06d0ce65e8', '2021-08-07 18:41:07', 'GLOBAL_SAVINGS_ACCOUNT', 'USD', '2021-08-07 18:41:07', 'ACTIVE', 1, 637327.00),
	('8226844a-7650-4fde-b209-56c63b9a113d', 'System', '2021-08-07 18:41:10', 'System', '2021-08-07 18:41:10', '68e98390-f76c-4d91-8c55-858d0b2cbd39', '2021-08-07 18:41:10', 'GLOBAL_SAVINGS_ACCOUNT', 'HKD', '2021-08-07 18:41:10', 'ACTIVE', 1, 891741.00),
	('7dcaf17f-8e12-402d-a66b-1ff9e6b34d7a', 'System', '2021-08-07 18:41:31', 'System', '2021-08-07 18:41:31', 'c904cd68-c342-40f3-b94a-0d0aed34ecba', '2021-08-07 18:41:31', 'GLOBAL_SAVINGS_ACCOUNT', 'EUR', '2021-08-07 18:41:31', 'ACTIVE', 1, 45262.00);