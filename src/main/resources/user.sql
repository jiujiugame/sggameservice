CREATE DATABASE IF NOT EXISTS sggm;
USE sggm;

SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `sggm`.`user` ;
CREATE TABLE `user` (
`account_id` varchar(50) NOT NULL,
`token` varchar(50) NULL COMMENT 'token',
`token_type` varchar(30) NULL COMMENT 'token类型',
PRIMARY KEY (`account_id`) 
)ENGINE = InnoDB DEFAULT CHARSET=utf8;

