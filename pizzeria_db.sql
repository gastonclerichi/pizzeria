-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: pizzeria
-- ------------------------------------------------------
-- Server version	5.7.11-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `config_variable`
--

DROP TABLE IF EXISTS `config_variable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config_variable` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `valor` mediumtext,
  `descripcion` varchar(255) DEFAULT NULL,
  `pantalla_nombre` varchar(100) DEFAULT NULL,
  `pantalla_editable` tinyint(4) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_variable`
--

LOCK TABLES `config_variable` WRITE;
/*!40000 ALTER TABLE `config_variable` DISABLE KEYS */;
INSERT INTO `config_variable` VALUES (4,'APP_LOCALE_PAIS','CL',NULL,NULL,NULL,NULL,NULL),(5,'APP_LOCALE_IDIOMA','es',NULL,NULL,NULL,NULL,NULL),(6,'APP_VERSION','Versión Sprint 8',NULL,NULL,NULL,NULL,NULL),(19,'APP_SESSION_TIMEOUT','3600','Valor expresado en segundos',NULL,NULL,NULL,NULL),(134,'APP_TIMEZONE','America/Santiago',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `config_variable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido_cabecera`
--

DROP TABLE IF EXISTS `pedido_cabecera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido_cabecera` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `direccion` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `horario` varchar(5) DEFAULT NULL,
  `fecha_alta` date DEFAULT NULL,
  `monto_total` double DEFAULT NULL,
  `aplico_descuento` tinyint(1) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `last_modified_date` varchar(255) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido_cabecera`
--

LOCK TABLES `pedido_cabecera` WRITE;
/*!40000 ALTER TABLE `pedido_cabecera` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedido_cabecera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido_detalle`
--

DROP TABLE IF EXISTS `pedido_detalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido_detalle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_producto` varchar(255) NOT NULL,
  `id_pedido_cabecera` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precio_unitario` double DEFAULT NULL,
  `last_modified_date` varchar(255) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_detalle_pedido_producto_idx` (`id_producto`),
  KEY `fk_detalle_pedido_detalle_cabecera_idx` (`id_pedido_cabecera`),
  CONSTRAINT `fk_pedido_detalle_pedido_cabecera` FOREIGN KEY (`id_pedido_cabecera`) REFERENCES `pedido_cabecera` (`id`),
  CONSTRAINT `fk_pedido_detalle_producto` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido_detalle`
--

LOCK TABLES `pedido_detalle` WRITE;
/*!40000 ALTER TABLE `pedido_detalle` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedido_detalle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `id` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `descripcion_corta` varchar(45) DEFAULT NULL,
  `descripcion_larga` varchar(255) DEFAULT NULL,
  `precio_unitario` double DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES ('89efb206-2aa6-4e21-8a23-5765e3de1f31','Jamón y morrones','Pizza de jamón y morrones','Mozzarella, jamón, morrones y aceitunas verdes',550,'API','2022-05-28 23:26:00.0'),('e29ebd0c-39d2-4054-b0f4-ed2d0ea089a1','Palmitos','Pizza de Palmito','Mozzarella, jamón, Palmitos',600,'API','2022-05-29 14:09:04.0');
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sec_rol`
--

DROP TABLE IF EXISTS `sec_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sec_rol` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `bloqueado` bit(1) NOT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_sec_rol_nombre` (`nombre`),
  KEY `idx_sec_rol_nombre` (`nombre`),
  KEY `idx_sec_rol_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sec_rol`
--

LOCK TABLES `sec_rol` WRITE;
/*!40000 ALTER TABLE `sec_rol` DISABLE KEYS */;
INSERT INTO `sec_rol` VALUES (1,'Administrador','Tiene acceso a todas las funcionalidades del sistema',_binary '\0',NULL,NULL);
/*!40000 ALTER TABLE `sec_rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sec_usuario`
--

DROP TABLE IF EXISTS `sec_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sec_usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `bloqueado` bit(1) NOT NULL,
  `id_rol` int(11) NOT NULL,
  `reset_token` varchar(255) DEFAULT NULL,
  `expiry_date_token` datetime DEFAULT NULL,
  `activo` tinyint(4) DEFAULT NULL,
  `inicio_sesion_codigo` int(11) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_sec_usuario_usuario` (`usuario`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `fk_sec_usuario_rol` (`id_rol`),
  KEY `idx_sec_usuario_username` (`usuario`),
  KEY `idx_sec_usuario_email` (`email`),
  CONSTRAINT `fk_sec_usuario_sec_rol` FOREIGN KEY (`id_rol`) REFERENCES `sec_rol` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sec_usuario`
--

LOCK TABLES `sec_usuario` WRITE;
/*!40000 ALTER TABLE `sec_usuario` DISABLE KEYS */;
INSERT INTO `sec_usuario` VALUES (1,'admin','$2a$12$d6taRrVROKTr7ASP9JS8Neet6KnmP8k8GBZ0GCMwV9F.LMWjx1KtG','admin','admin',NULL,_binary '\0',1,NULL,NULL,1,2,NULL,NULL);
/*!40000 ALTER TABLE `sec_usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-30 11:05:18
