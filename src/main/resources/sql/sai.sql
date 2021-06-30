-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sai
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ums_resource`
--

DROP TABLE IF EXISTS `ums_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ums_resource` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `path` varchar(64) NOT NULL COMMENT '路径',
  `description` varchar(512) DEFAULT NULL COMMENT '描述',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='资源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ums_resource`
--

LOCK TABLES `ums_resource` WRITE;
/*!40000 ALTER TABLE `ums_resource` DISABLE KEYS */;
INSERT INTO `ums_resource` VALUES ('1','/admin/**',NULL,0,'2021-06-30 23:45:31','2021-06-30 23:45:32'),('2','/user/**',NULL,0,'2021-06-30 23:45:52','2021-06-30 23:45:53');
/*!40000 ALTER TABLE `ums_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ums_role`
--

DROP TABLE IF EXISTS `ums_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ums_role` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `description` varchar(512) DEFAULT NULL COMMENT '描述',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ums_role`
--

LOCK TABLES `ums_role` WRITE;
/*!40000 ALTER TABLE `ums_role` DISABLE KEYS */;
INSERT INTO `ums_role` VALUES ('1','admin',NULL,0,'2021-06-30 23:40:58','2021-06-30 23:40:59'),('2','user',NULL,0,'2021-06-30 23:41:16','2021-06-30 23:41:17');
/*!40000 ALTER TABLE `ums_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ums_role_resource_relation`
--

DROP TABLE IF EXISTS `ums_role_resource_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ums_role_resource_relation` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` varchar(32) NOT NULL COMMENT '角色ID',
  `resource_id` varchar(32) NOT NULL COMMENT '资源ID',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色和资源关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ums_role_resource_relation`
--

LOCK TABLES `ums_role_resource_relation` WRITE;
/*!40000 ALTER TABLE `ums_role_resource_relation` DISABLE KEYS */;
INSERT INTO `ums_role_resource_relation` VALUES (1,'1','1',0,'2021-06-30 23:46:37','2021-06-30 23:46:38'),(2,'1','2',0,'2021-06-30 23:46:39','2021-06-30 23:46:40'),(3,'2','2',0,'2021-06-30 23:46:42','2021-06-30 23:46:42');
/*!40000 ALTER TABLE `ums_role_resource_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ums_user`
--

DROP TABLE IF EXISTS `ums_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ums_user` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `username` varchar(64) NOT NULL COMMENT '账号',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `icon` varchar(256) DEFAULT NULL COMMENT '头像',
  `nick_name` varchar(128) DEFAULT NULL COMMENT '昵称',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `note` varchar(512) DEFAULT NULL COMMENT '备注',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ums_user`
--

LOCK TABLES `ums_user` WRITE;
/*!40000 ALTER TABLE `ums_user` DISABLE KEYS */;
INSERT INTO `ums_user` VALUES ('1','admin','$2a$10$.341NSzcxrYiChg8RYtL5OZjBjLfh9w9XRr/VZo8dw5MuPVNw7sv6',NULL,NULL,NULL,NULL,0,'2021-06-30 23:39:45','2021-06-30 23:39:49'),('2','wenjun','$2a$10$UCxFIQ7wAUuZGpCcEV4ct.b3Tx3lYqv1lJW06ee/xqPaFuEGm3oRm',NULL,NULL,NULL,NULL,0,'2021-06-30 23:40:28','2021-06-30 23:40:29');
/*!40000 ALTER TABLE `ums_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ums_user_role_relation`
--

DROP TABLE IF EXISTS `ums_user_role_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ums_user_role_relation` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `role_id` varchar(32) NOT NULL COMMENT '角色ID',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户和角色关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ums_user_role_relation`
--

LOCK TABLES `ums_user_role_relation` WRITE;
/*!40000 ALTER TABLE `ums_user_role_relation` DISABLE KEYS */;
INSERT INTO `ums_user_role_relation` VALUES (1,'1','1',0,'2021-06-30 23:43:05','2021-06-30 23:43:06'),(2,'2','2',0,'2021-06-30 23:43:23','2021-06-30 23:43:24');
/*!40000 ALTER TABLE `ums_user_role_relation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-01  2:24:29
