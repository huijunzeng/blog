/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : 129.211.34.120:3306
 Source Schema         : distributed-transaction-rabbitmq-producer

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 11/09/2020 16:32:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for accounts
-- ----------------------------
DROP TABLE IF EXISTS `accounts`;
CREATE TABLE `accounts`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_estonian_ci NOT NULL COMMENT '账号名称',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of accounts
-- ----------------------------
INSERT INTO `accounts` VALUES (1266601033918914561, '', '');
INSERT INTO `accounts` VALUES (1266601148003983362, '', '');
INSERT INTO `accounts` VALUES (1266601183236136961, '', '');
INSERT INTO `accounts` VALUES (1266601315713228802, ' ', '');
INSERT INTO `accounts` VALUES (1266611946466123778, 'test', '12345678910');
INSERT INTO `accounts` VALUES (1266612025675554817, 'test', '12345678910');
INSERT INTO `accounts` VALUES (1269586053537222657, 'test', '18826233829');
INSERT INTO `accounts` VALUES (1269586705948610562, 'test', '18826233829');
INSERT INTO `accounts` VALUES (1269613946044755969, 'test', '18826233829');
INSERT INTO `accounts` VALUES (1269614649471463425, 'test', '18826233829');
INSERT INTO `accounts` VALUES (1269617063075266562, 'test', '18826233829');
INSERT INTO `accounts` VALUES (1269617534754172929, 'test', '18826233829');
INSERT INTO `accounts` VALUES (1269617923184472066, 'test', '18826233829');
INSERT INTO `accounts` VALUES (1269640002919247874, 'test', '18826233829');
INSERT INTO `accounts` VALUES (1269642172179714050, 'test', '18826233829');
INSERT INTO `accounts` VALUES (1269644769087229954, 'test', '18826233829');
INSERT INTO `accounts` VALUES (1269646307591491585, 'test', '18826233829');
INSERT INTO `accounts` VALUES (1269646527213621249, 'test', '18826233829');
INSERT INTO `accounts` VALUES (1269646744436625410, 'test', '18826233829');

-- ----------------------------
-- Table structure for transactional_message
-- ----------------------------
DROP TABLE IF EXISTS `transactional_message`;
CREATE TABLE `transactional_message`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `current_retry_times` int(11) NULL DEFAULT NULL COMMENT '当前重试次数',
  `max_retry_times` int(11) NULL DEFAULT NULL COMMENT '最大重试次数',
  `queue_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '队列名',
  `exchange_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交换器名',
  `exchange_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交换器类型',
  `routing_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由键',
  `message` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息内容',
  `status` int(255) NULL DEFAULT NULL COMMENT '消息状态',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of transactional_message
-- ----------------------------
INSERT INTO `transactional_message` VALUES (1266601037303717890, NULL, NULL, 'points.queue', 'points.exchange', 'topic', 'points.route.key', 'AccountSaveForm(name=, phone=)', 0, NULL, NULL);
INSERT INTO `transactional_message` VALUES (1266601148654100482, NULL, NULL, 'points.queue', 'points.exchange', 'topic', 'points.route.key', 'AccountSaveForm(name=, phone=)', 0, NULL, NULL);
INSERT INTO `transactional_message` VALUES (1266601183827533825, NULL, NULL, 'points.queue', 'points.exchange', 'topic', 'points.route.key', 'AccountSaveForm(name=, phone=)', 0, NULL, NULL);
INSERT INTO `transactional_message` VALUES (1266601316203962369, NULL, NULL, 'points.queue', 'points.exchange', 'topic', 'points.route.key', 'AccountSaveForm(name= , phone=)', 0, NULL, NULL);
INSERT INTO `transactional_message` VALUES (1266611967915794433, NULL, NULL, 'points.queue', 'points.exchange', 'topic', 'points.route.key', 'AccountSaveForm(name=test, phone=12345678910)', 0, NULL, NULL);
INSERT INTO `transactional_message` VALUES (1266612026313089026, NULL, NULL, 'points.queue', 'points.exchange', 'topic', 'points.route.key', 'AccountSaveForm(name=test, phone=12345678910)', 0, NULL, NULL);
INSERT INTO `transactional_message` VALUES (1269586056762642434, NULL, NULL, 'points.queue', 'points.exchange', 'topic', 'points.route.key', 'AccountSaveForm(name=test, phone=18826233829)', 0, NULL, NULL);
INSERT INTO `transactional_message` VALUES (1269586708855263233, NULL, NULL, 'points.queue', 'points.exchange', 'topic', 'points.route.key', 'AccountSaveForm(name=test, phone=18826233829)', 1, '2020-06-07 19:07:15', '2020-06-07 19:07:15');
INSERT INTO `transactional_message` VALUES (1269613949819629570, NULL, NULL, 'points.queue', 'points.exchange', 'topic', 'points.route.key', 'AccountSaveForm(name=test, phone=18826233829)', 1, '2020-06-07 20:55:30', '2020-06-07 20:55:30');
INSERT INTO `transactional_message` VALUES (1269614652726243329, NULL, NULL, 'points.queue', 'points.exchange', 'topic', 'points.route.key', 'AccountSaveForm(name=test, phone=18826233829)', 1, '2020-06-07 20:58:18', '2020-06-07 20:58:18');
INSERT INTO `transactional_message` VALUES (1269617066850140162, NULL, NULL, 'points.queue', 'points.exchange', 'topic', 'points.route.key', 'AccountSaveForm(name=test, phone=18826233829)', 1, '2020-06-07 21:07:53', '2020-06-07 21:07:53');
INSERT INTO `transactional_message` VALUES (1269617537690185730, NULL, NULL, 'points.queue', 'points.exchange', 'topic', 'points.route.key', 'AccountSaveForm(name=test, phone=18826233829)', 1, '2020-06-07 21:09:46', '2020-06-07 21:09:46');
INSERT INTO `transactional_message` VALUES (1269617923725537281, NULL, NULL, 'points.queue', 'points.exchange', 'topic', 'points.route.key', 'AccountSaveForm(name=test, phone=18826233829)', 1, '2020-06-07 21:11:17', '2020-06-07 21:11:17');
INSERT INTO `transactional_message` VALUES (1269640003871354881, NULL, NULL, 'points.queue', 'points.exchange', 'topic', 'points.route.key', 'AccountsEntity(id=1269640002919247874, name=test, phone=18826233829)', 1, '2020-06-07 22:39:02', '2020-06-07 22:39:02');
INSERT INTO `transactional_message` VALUES (1269642173060517889, NULL, NULL, 'points.queue', 'points.exchange', 'topic', 'points.route.key', '{\"id\":1269642172179714050,\"name\":\"test\",\"phone\":\"18826233829\"}', 1, '2020-06-07 22:47:39', '2020-06-07 22:47:39');
INSERT INTO `transactional_message` VALUES (1269644769800261634, NULL, NULL, 'points.queue', 'points.exchange', 'topic', 'points.route.key', '{\"id\":1269644769087229954,\"name\":\"test\",\"phone\":\"18826233829\"}', 1, NULL, '2020-06-07 22:57:58');
INSERT INTO `transactional_message` VALUES (1269646308610707457, NULL, NULL, 'points.queue', 'points.exchange', 'topic', 'points.route.key', '{\"id\":1269646307591491585,\"name\":\"test\",\"phone\":\"18826233829\"}', 1, NULL, '2020-06-07 23:04:05');
INSERT INTO `transactional_message` VALUES (1269646528127979522, NULL, NULL, 'points.queue', 'points.exchange', 'direct', 'points.route.key', '{\"id\":1269646527213621249,\"name\":\"test\",\"phone\":\"18826233829\"}', 1, NULL, '2020-06-07 10:04:56');
INSERT INTO `transactional_message` VALUES (1269646745254514689, NULL, NULL, 'points.queue', 'points.exchange', 'direct', 'points.route.key', '{\"id\":1269646744436625410,\"name\":\"test\",\"phone\":\"18826233829\"}', 1, '2020-06-07 23:05:49', '2020-06-07 10:05:48');

SET FOREIGN_KEY_CHECKS = 1;
