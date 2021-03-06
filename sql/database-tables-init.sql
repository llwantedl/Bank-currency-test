CREATE DATABASE IF NOT EXISTS TESTBANK;

USE TESTBANK;

CREATE TABLE IF NOT EXISTS ROLE (
  id INT(11) NOT NULL AUTO_INCREMENT,
  `key` VARCHAR(100) UNIQUE,
  PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS CURRENCY (
  id INT(11) NOT NULL AUTO_INCREMENT,
  `key` VARCHAR(255) UNIQUE NOT NULL,
  PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS USER (
  id INT(11) NOT NULL AUTO_INCREMENT,
  `currency_id` INT(11) NOT NULL,
  `login` VARCHAR(255) UNIQUE NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) UNIQUE NOT NULL,
  CONSTRAINT currency_id_fk FOREIGN KEY (`currency_id`) REFERENCES CURRENCY (id),
  PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS WALLET (
  id INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `wallet_key` VARCHAR(255) UNIQUE NOT NULL,
  `balance` DECIMAL(29, 2) DEFAULT 0,
  `block` TINYINT(1) DEFAULT FALSE,
  CONSTRAINT user_wallet_id_fk FOREIGN KEY (`user_id`) REFERENCES USER (id),
  PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS OPERATION (
  id INT(11) NOT NULL AUTO_INCREMENT,
  `op_key` VARCHAR(255) UNIQUE NOT NULL,
  `commission` DECIMAL(29,2) DEFAULT 0,
  `source_wallet_id` INT(11),
  `destination_wallet_id` INT(11),
  `amount` DECIMAL(29, 2) DEFAULT 0,
  `transfer_date` TIMESTAMP,
  `rate` DECIMAL(29,2),
  CONSTRAINT operation_wallet_source_id_fk FOREIGN KEY (`source_wallet_id`) REFERENCES WALLET (id),
  CONSTRAINT operation_wallet_destination_id_fk FOREIGN KEY (`destination_wallet_id`) REFERENCES WALLET (id),
  PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS USER_ROLE (
  user_id INT(11) NOT NULL,
  role_id INT(11) NOT NULL,
  CONSTRAINT user_role_user_id_fk FOREIGN KEY (user_id) REFERENCES USER (id),
  CONSTRAINT user_role_role_id_fk FOREIGN KEY (role_id) REFERENCES ROLE (id)
) ENGINE=INNODB;
