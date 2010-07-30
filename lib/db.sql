-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.35-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema ccm1
--

CREATE DATABASE IF NOT EXISTS ccm1;
USE ccm1;

--
-- Definition of table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `authority` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`authority`,`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `authorities`
--

/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` (`authority`,`username`) VALUES 
 ('ROLE_ADMIN','intesar@ymail.com'),
 ('ROLE_USER','intesar@ymail.com');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;


--
-- Definition of table `email_preference`
--

DROP TABLE IF EXISTS `email_preference`;
CREATE TABLE `email_preference` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `service_provider` varchar(255) DEFAULT NULL,
  `organization` varchar(255) NOT NULL,
  `email_or_phone` varchar(255) NOT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `create_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `email_preference`
--

/*!40000 ALTER TABLE `email_preference` DISABLE KEYS */;
/*!40000 ALTER TABLE `email_preference` ENABLE KEYS */;


--
-- Definition of table `email_time_preference`
--

DROP TABLE IF EXISTS `email_time_preference`;
CREATE TABLE `email_time_preference` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `organization` varchar(255) NOT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `report_time` smallint(6) NOT NULL,
  `create_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `email_time_preference`
--

/*!40000 ALTER TABLE `email_time_preference` DISABLE KEYS */;
/*!40000 ALTER TABLE `email_time_preference` ENABLE KEYS */;


--
-- Definition of table `membership_discounts`
--

DROP TABLE IF EXISTS `membership_discounts`;
CREATE TABLE `membership_discounts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `discount_percentage` double NOT NULL,
  `create_date` datetime NOT NULL,
  `service` varchar(255) NOT NULL,
  `create_user` varchar(255) NOT NULL,
  `membership_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_membership_discounts_membership_type` (`membership_type`),
  CONSTRAINT `FK_membership_discounts_membership_type` FOREIGN KEY (`membership_type`) REFERENCES `membership_types` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `membership_discounts`
--

/*!40000 ALTER TABLE `membership_discounts` DISABLE KEYS */;
/*!40000 ALTER TABLE `membership_discounts` ENABLE KEYS */;


--
-- Definition of table `membership_types`
--

DROP TABLE IF EXISTS `membership_types`;
CREATE TABLE `membership_types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `days_valid_for` int(11) NOT NULL,
  `create_date` datetime NOT NULL,
  `fee` double NOT NULL,
  `create_user` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `organization` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_membership_types_organization` (`organization`),
  CONSTRAINT `FK_membership_types_organization` FOREIGN KEY (`organization`) REFERENCES `organization` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `membership_types`
--

/*!40000 ALTER TABLE `membership_types` DISABLE KEYS */;
/*!40000 ALTER TABLE `membership_types` ENABLE KEYS */;


--
-- Definition of table `memberships`
--

DROP TABLE IF EXISTS `memberships`;
CREATE TABLE `memberships` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `email` varchar(255) NOT NULL,
  `start_date` datetime NOT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '0',
  `create_user` varchar(255) NOT NULL,
  `organization` varchar(255) DEFAULT NULL,
  `expiration_date` datetime NOT NULL,
  `membership_type` int(11) DEFAULT NULL,
  `membership_type_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `memberships`
--

/*!40000 ALTER TABLE `memberships` DISABLE KEYS */;
/*!40000 ALTER TABLE `memberships` ENABLE KEYS */;


--
-- Definition of table `organization`
--

DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization` (
  `name` varchar(255) NOT NULL,
  `register_date` varchar(255) DEFAULT NULL,
  `contact_email` varchar(255) DEFAULT NULL,
  `enabled` smallint(6) NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `product_version` varchar(255) NOT NULL,
  `zipcode` varchar(255) DEFAULT NULL,
  `licence_key` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `amount_paid` double NOT NULL,
  `register_type` varchar(255) NOT NULL,
  `expiration_date` date NOT NULL,
  `street` varchar(255) DEFAULT NULL,
  `register_agent` varchar(255) DEFAULT NULL,
  `country` varchar(255) NOT NULL,
  `create_agent` varchar(255) NOT NULL,
  `create_date` date DEFAULT NULL,
  `contact_name` varchar(255) DEFAULT NULL,
  `timings` varchar(255) DEFAULT NULL,
  `state_` varchar(255) NOT NULL,
  `print_email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `organization`
--

/*!40000 ALTER TABLE `organization` DISABLE KEYS */;
INSERT INTO `organization` (`name`,`register_date`,`contact_email`,`enabled`,`product_name`,`city`,`product_version`,`zipcode`,`licence_key`,`phone`,`amount_paid`,`register_type`,`expiration_date`,`street`,`register_agent`,`country`,`create_agent`,`create_date`,`contact_name`,`timings`,`state_`,`print_email`) VALUES 
 ('test',NULL,'intesar@ymail.com',1,'intesar@ymail.com','hyd','Silver Member',NULL,'ccm',NULL,0,'india','2009-10-28',NULL,NULL,'hyd','self',NULL,NULL,NULL,'intesar@ymail.com',NULL);
/*!40000 ALTER TABLE `organization` ENABLE KEYS */;


--
-- Definition of table `services`
--

DROP TABLE IF EXISTS `services`;
CREATE TABLE `services` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `organization` varchar(255) NOT NULL,
  `unit_price` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `services`
--

/*!40000 ALTER TABLE `services` DISABLE KEYS */;
INSERT INTO `services` (`id`,`description`,`name`,`organization`,`unit_price`) VALUES 
 (19,NULL,'print b&w','test',3),
 (20,NULL,'cool drink','test',10),
 (21,NULL,'copy b&w','test',1),
 (22,NULL,'print color','test',5),
 (23,NULL,'other','test',1),
 (24,NULL,'scan','test',5);
/*!40000 ALTER TABLE `services` ENABLE KEYS */;


--
-- Definition of table `system_lease`
--

DROP TABLE IF EXISTS `system_lease`;
CREATE TABLE `system_lease` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `total_minutes_used` bigint(20) DEFAULT NULL,
  `system` int(11) NOT NULL,
  `start_time` datetime NOT NULL,
  `is_finished` tinyint(1) NOT NULL DEFAULT '0',
  `payable_amount` double DEFAULT NULL,
  `service` varchar(255) DEFAULT NULL,
  `issue_agent` varchar(255) NOT NULL,
  `system_no` int(11) DEFAULT NULL,
  `lease_holder_name` varchar(255) DEFAULT NULL,
  `is_start_contract_notified` tinyint(1) DEFAULT '0',
  `amount_paid` double DEFAULT NULL,
  `is_end_contract_notified` tinyint(1) DEFAULT '0',
  `end_time` datetime DEFAULT NULL,
  `discounts` double DEFAULT NULL,
  `return_agent` varchar(255) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `system_lease`
--

/*!40000 ALTER TABLE `system_lease` DISABLE KEYS */;
INSERT INTO `system_lease` (`id`,`total_minutes_used`,`system`,`start_time`,`is_finished`,`payable_amount`,`service`,`issue_agent`,`system_no`,`lease_holder_name`,`is_start_contract_notified`,`amount_paid`,`is_end_contract_notified`,`end_time`,`discounts`,`return_agent`,`comments`) VALUES 
 (1,NULL,9,'2009-10-28 17:37:58',0,NULL,'computer 1','intesar@ymail.com',1,'mdshannan@gmail.com',1,NULL,0,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `system_lease` ENABLE KEYS */;


--
-- Definition of table `systems`
--

DROP TABLE IF EXISTS `systems`;
CREATE TABLE `systems` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lower_rate` double DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '0',
  `name` int(11) NOT NULL,
  `is_shutdown` tinyint(1) DEFAULT '0',
  `is_available` tinyint(1) NOT NULL DEFAULT '0',
  `create_user` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `rate` double NOT NULL,
  `mac_address` varchar(255) DEFAULT NULL,
  `ip_address` varchar(255) DEFAULT NULL,
  `expected_free_time` datetime DEFAULT NULL,
  `status_` varchar(255) DEFAULT NULL,
  `lower_minimum_minutes` int(11) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `minimum_minutes` int(11) NOT NULL,
  `current_user_email` varchar(255) DEFAULT NULL,
  `organization` varchar(255) NOT NULL,
  `start_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `systems`
--

/*!40000 ALTER TABLE `systems` DISABLE KEYS */;
INSERT INTO `systems` (`id`,`lower_rate`,`enabled`,`name`,`is_shutdown`,`is_available`,`create_user`,`description`,`create_date`,`rate`,`mac_address`,`ip_address`,`expected_free_time`,`status_`,`lower_minimum_minutes`,`comment`,`minimum_minutes`,`current_user_email`,`organization`,`start_time`) VALUES 
 (1,NULL,0,18,NULL,1,NULL,NULL,NULL,1,'test18',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (2,NULL,0,38,NULL,1,NULL,NULL,NULL,1,'test38',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (3,NULL,0,13,NULL,1,NULL,NULL,NULL,1,'test13',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (4,NULL,1,4,NULL,1,NULL,NULL,NULL,1,'test4',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (5,NULL,0,25,NULL,1,NULL,NULL,NULL,1,'test25',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (6,NULL,1,5,NULL,1,NULL,NULL,NULL,1,'test5',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (7,NULL,0,27,NULL,1,NULL,NULL,NULL,1,'test27',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (8,NULL,0,31,NULL,1,NULL,NULL,NULL,1,'test31',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (9,NULL,1,1,NULL,0,NULL,NULL,NULL,1,'test1',NULL,NULL,NULL,NULL,NULL,1,'mdshannan@gmail.com','test','2009-10-28 17:37:58'),
 (10,NULL,1,2,NULL,1,NULL,NULL,NULL,1,'test2',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (11,NULL,0,50,NULL,1,NULL,NULL,NULL,1,'test50',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (12,NULL,0,30,NULL,1,NULL,NULL,NULL,1,'test30',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (13,NULL,0,45,NULL,1,NULL,NULL,NULL,1,'test45',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (14,NULL,1,8,NULL,1,NULL,NULL,NULL,1,'test8',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (15,NULL,0,12,NULL,1,NULL,NULL,NULL,1,'test12',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (16,NULL,0,44,NULL,1,NULL,NULL,NULL,1,'test44',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (17,NULL,0,39,NULL,1,NULL,NULL,NULL,1,'test39',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (18,NULL,0,11,NULL,1,NULL,NULL,NULL,1,'test11',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (19,NULL,0,48,NULL,1,NULL,NULL,NULL,1,'test48',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (20,NULL,0,29,NULL,1,NULL,NULL,NULL,1,'test29',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (21,NULL,0,17,NULL,1,NULL,NULL,NULL,1,'test17',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (22,NULL,0,46,NULL,1,NULL,NULL,NULL,1,'test46',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (23,NULL,0,42,NULL,1,NULL,NULL,NULL,1,'test42',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (24,NULL,0,20,NULL,1,NULL,NULL,NULL,1,'test20',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (25,NULL,0,24,NULL,1,NULL,NULL,NULL,1,'test24',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (26,NULL,0,49,NULL,1,NULL,NULL,NULL,1,'test49',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (27,NULL,0,16,NULL,1,NULL,NULL,NULL,1,'test16',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (28,NULL,0,43,NULL,1,NULL,NULL,NULL,1,'test43',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (29,NULL,0,14,NULL,1,NULL,NULL,NULL,1,'test14',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (30,NULL,0,22,NULL,1,NULL,NULL,NULL,1,'test22',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (31,NULL,1,10,NULL,1,NULL,NULL,NULL,1,'test10',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (32,NULL,1,3,NULL,1,NULL,NULL,NULL,1,'test3',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (33,NULL,0,23,NULL,1,NULL,NULL,NULL,1,'test23',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (34,NULL,0,47,NULL,1,NULL,NULL,NULL,1,'test47',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (35,NULL,0,34,NULL,1,NULL,NULL,NULL,1,'test34',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (36,NULL,1,7,NULL,1,NULL,NULL,NULL,1,'test7',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (37,NULL,0,32,NULL,1,NULL,NULL,NULL,1,'test32',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (38,NULL,0,15,NULL,1,NULL,NULL,NULL,1,'test15',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (39,NULL,0,40,NULL,1,NULL,NULL,NULL,1,'test40',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (40,NULL,0,41,NULL,1,NULL,NULL,NULL,1,'test41',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (41,NULL,0,28,NULL,1,NULL,NULL,NULL,1,'test28',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (42,NULL,0,37,NULL,1,NULL,NULL,NULL,1,'test37',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (43,NULL,1,6,NULL,1,NULL,NULL,NULL,1,'test6',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (44,NULL,0,26,NULL,1,NULL,NULL,NULL,1,'test26',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (45,NULL,0,33,NULL,1,NULL,NULL,NULL,1,'test33',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (46,NULL,0,35,NULL,1,NULL,NULL,NULL,1,'test35',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (47,NULL,0,19,NULL,1,NULL,NULL,NULL,1,'test19',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (48,NULL,0,21,NULL,1,NULL,NULL,NULL,1,'test21',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (49,NULL,0,36,NULL,1,NULL,NULL,NULL,1,'test36',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL),
 (50,NULL,1,9,NULL,1,NULL,NULL,NULL,1,'test9',NULL,NULL,NULL,NULL,NULL,1,NULL,'test',NULL);
/*!40000 ALTER TABLE `systems` ENABLE KEYS */;


--
-- Definition of table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `home_phone` varchar(255) DEFAULT NULL,
  `role_` varchar(255) NOT NULL,
  `mobile_phone` varchar(255) DEFAULT NULL,
  `other_phone` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `passport_no` varchar(255) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '0',
  `voter_id` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `college_name` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `ration_card_no` varchar(255) DEFAULT NULL,
  `zipcode` varchar(255) DEFAULT NULL,
  `pan_card_no` varchar(255) DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `organization` varchar(255) DEFAULT NULL,
  `ssn` varchar(255) DEFAULT NULL,
  `state_` varchar(255) DEFAULT NULL,
  `driving_licence` varchar(255) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `pic` blob,
  `password` varchar(255) NOT NULL,
  `is_verified` tinyint(1) NOT NULL DEFAULT '0',
  `country` varchar(255) DEFAULT NULL,
  `verified_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`,`home_phone`,`role_`,`mobile_phone`,`other_phone`,`name`,`passport_no`,`enabled`,`voter_id`,`email`,`college_name`,`city`,`ration_card_no`,`zipcode`,`pan_card_no`,`create_user`,`dob`,`username`,`gender`,`organization`,`ssn`,`state_`,`driving_licence`,`create_date`,`comments`,`street`,`pic`,`password`,`is_verified`,`country`,`verified_by`) VALUES 
 (1,NULL,'admin',NULL,NULL,NULL,NULL,1,NULL,'intesar@ymail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'intesar@ymail.com',NULL,'test',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'164TjzLUGSxJTL8nXdFbVZozutL/oaUlPOI0KlV1qDQWItsOzehhN844Rm5hm/8W',0,NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;


--
-- Definition of table `users_light`
--

DROP TABLE IF EXISTS `users_light`;
CREATE TABLE `users_light` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `organization` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users_light`
--

/*!40000 ALTER TABLE `users_light` DISABLE KEYS */;
INSERT INTO `users_light` (`id`,`username`,`organization`) VALUES 
 (1,'intesar@ymail.com','test');
/*!40000 ALTER TABLE `users_light` ENABLE KEYS */;


--
-- Definition of table `users_pass`
--

DROP TABLE IF EXISTS `users_pass`;
CREATE TABLE `users_pass` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '0',
  `username` varchar(255) NOT NULL,
  `reset_code` varchar(255) NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users_pass`
--

/*!40000 ALTER TABLE `users_pass` DISABLE KEYS */;
INSERT INTO `users_pass` (`id`,`password`,`enabled`,`username`,`reset_code`,`create_date`) VALUES 
 (1,'2PcSfdm+lv++Cw6aTONSiA==',1,'intesar@ymail.com','rI2BHWFQZeDe26C2Qq+T/CyhU9sNlpK50Q2OktsTrRg=','2009-10-28 17:37:26');
/*!40000 ALTER TABLE `users_pass` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
