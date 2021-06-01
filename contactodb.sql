/*
SQLyog Enterprise - MySQL GUI v8.1 
MySQL - 5.5.5-10.4.12-MariaDB : Database - contactodb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`contactodb` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `contactodb`;

/*Table structure for table `contacto` */

DROP TABLE IF EXISTS `contacto`;

CREATE TABLE `contacto` (
  `contactoId` int(11) NOT NULL AUTO_INCREMENT,
  `usuarioId` int(11) NOT NULL,
  `nombreContacto` varchar(250) NOT NULL,
  `telefono` varchar(25) NOT NULL,
  `email` varchar(250) NOT NULL,
  `imagenFileId` int(11) DEFAULT NULL,
  PRIMARY KEY (`contactoId`),
  KEY `FK_contacto` (`usuarioId`),
  KEY `FK_contacto_file` (`imagenFileId`),
  CONSTRAINT `FK_contacto` FOREIGN KEY (`usuarioId`) REFERENCES `usuario` (`usuarioId`),
  CONSTRAINT `FK_contacto_file` FOREIGN KEY (`imagenFileId`) REFERENCES `file` (`fileId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `contacto` */

/*Table structure for table `file` */

DROP TABLE IF EXISTS `file`;

CREATE TABLE `file` (
  `fileId` int(11) NOT NULL,
  `fileName` varchar(250) NOT NULL,
  `path` varchar(500) NOT NULL,
  `temporal` int(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`fileId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `file` */

/*Table structure for table `usuario` */

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
  `usuarioId` int(6) NOT NULL AUTO_INCREMENT,
  `nombreCompleto` varchar(250) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(500) NOT NULL,
  PRIMARY KEY (`usuarioId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `usuario` */

insert  into `usuario`(usuarioId,nombreCompleto,username,password) values (1,'Juan Perez','jperez','jperez');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
