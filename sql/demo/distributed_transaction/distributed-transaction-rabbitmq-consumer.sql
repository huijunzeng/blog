/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : 129.211.34.120:3306
 Source Schema         : distributed-transaction-rabbitmq-consumer

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 11/09/2020 16:33:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for points
-- ----------------------------
DROP TABLE IF EXISTS `points`;
CREATE TABLE `points`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `account_id` bigint(20) NOT NULL COMMENT '账户id',
  `account_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账户名称',
  `point` int(255) NOT NULL COMMENT '积分值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of points
-- ----------------------------
INSERT INTO `points` VALUES (1269642886142574594, 1269642172179714050, 'test', 50);
INSERT INTO `points` VALUES (1269644776142094338, 1269644769087229954, 'test', 50);
INSERT INTO `points` VALUES (1269646311207006210, 1269646307591491585, 'test', 50);
INSERT INTO `points` VALUES (1269646530388750338, 1269646527213621249, 'test', 50);
INSERT INTO `points` VALUES (1269646748157014018, 1269646744436625410, 'test', 50);

SET FOREIGN_KEY_CHECKS = 1;
