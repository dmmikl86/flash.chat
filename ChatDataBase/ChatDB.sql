CREATE DATABASE `users` ;

USE `users`;

DROP TABLE IF EXISTS `usersdata`;

CREATE TABLE `usersdata` (
  `UserName` text,
  `Password` text,
  `Time` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
