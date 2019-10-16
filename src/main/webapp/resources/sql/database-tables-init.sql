CREATE DATABASE IF NOT EXISTS TEST_BANK;

USE TEST_BANK;

CREATE TABLE IF NOT EXISTS ROLE (
  id INT(11) NOT NULL AUTO_INCREMENT,
  'KEY' VARCHAR(100),
  PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS USER (
  id INT(11) NOT NULL AUTO_INCREMENT,
  'LOGIN' VARCHAR(255) NOT NULL,
  'PASSWORD' VARCHAR(255) NOT NULL,
  'EMAIL' VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS CURRENCY (
  id INT(11) NOT NULL AUTO_INCREMENT,
  'LOGIN' VARCHAR(255) NOT NULL,
  'PASSWORD' VARCHAR(255) NOT NULL,
  'EMAIL' VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=INNODB;