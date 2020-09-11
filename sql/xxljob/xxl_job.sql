/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : 129.211.34.120:3306
 Source Schema         : xxl_job

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 11/09/2020 16:32:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for xxl_job_group
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_group`;
CREATE TABLE `xxl_job_group`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '执行器AppName',
  `title` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '执行器名称',
  `order` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  `address_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '执行器地址类型：0=自动注册、1=手动录入',
  `address_list` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行器地址列表，多地址逗号分隔',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_group
-- ----------------------------
INSERT INTO `xxl_job_group` VALUES (1, 'article-admin', '示例执行器', 1, 0, '192.168.10.2:9999');
INSERT INTO `xxl_job_group` VALUES (2, 'article-admin', '文章执行器', 2, 0, '192.168.10.2:9999');

-- ----------------------------
-- Table structure for xxl_job_info
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_info`;
CREATE TABLE `xxl_job_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_group` int(11) NOT NULL COMMENT '执行器主键ID',
  `job_cron` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '任务执行CRON',
  `job_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `add_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `author` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '作者',
  `alarm_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '报警邮件',
  `executor_route_strategy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行器路由策略',
  `executor_handler` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行器任务参数',
  `executor_block_strategy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '阻塞处理策略',
  `executor_timeout` int(11) NOT NULL DEFAULT 0 COMMENT '任务执行超时时间，单位秒',
  `executor_fail_retry_count` int(11) NOT NULL DEFAULT 0 COMMENT '失败重试次数',
  `glue_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'GLUE备注',
  `glue_updatetime` datetime(0) NULL DEFAULT NULL COMMENT 'GLUE更新时间',
  `child_jobid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '子任务ID，多个逗号分隔',
  `trigger_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '调度状态：0-停止，1-运行',
  `trigger_last_time` bigint(13) NOT NULL DEFAULT 0 COMMENT '上次调度时间',
  `trigger_next_time` bigint(13) NOT NULL DEFAULT 0 COMMENT '下次调度时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_info
-- ----------------------------
INSERT INTO `xxl_job_info` VALUES (1, 1, '0/5 * * * * ? *', '测试任务1', '2018-11-03 22:21:31', '2020-04-08 10:50:35', 'XXL', '', 'FIRST', 'demoJobHandler', '', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2018-11-03 22:21:31', '', 0, 0, 0);
INSERT INTO `xxl_job_info` VALUES (2, 2, '0/5 * * * * ? *', '文章定时发布任务', '2020-04-07 18:22:35', '2020-04-24 20:31:31', 'zjh', '', 'FIRST', 'articlePublishJobHandler', '', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2020-04-07 18:22:35', '', 0, 0, 0);

-- ----------------------------
-- Table structure for xxl_job_lock
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_lock`;
CREATE TABLE `xxl_job_lock`  (
  `lock_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '锁名称',
  PRIMARY KEY (`lock_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_lock
-- ----------------------------
INSERT INTO `xxl_job_lock` VALUES ('schedule_lock');

-- ----------------------------
-- Table structure for xxl_job_log
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_log`;
CREATE TABLE `xxl_job_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_group` int(11) NOT NULL COMMENT '执行器主键ID',
  `job_id` int(11) NOT NULL COMMENT '任务，主键ID',
  `executor_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行器地址，本次执行的地址',
  `executor_handler` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行器任务参数',
  `executor_sharding_param` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行器任务分片参数，格式如 1/2',
  `executor_fail_retry_count` int(11) NOT NULL DEFAULT 0 COMMENT '失败重试次数',
  `trigger_time` datetime(0) NULL DEFAULT NULL COMMENT '调度-时间',
  `trigger_code` int(11) NOT NULL COMMENT '调度-结果',
  `trigger_msg` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '调度-日志',
  `handle_time` datetime(0) NULL DEFAULT NULL COMMENT '执行-时间',
  `handle_code` int(11) NOT NULL COMMENT '执行-状态',
  `handle_msg` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '执行-日志',
  `alarm_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `I_trigger_time`(`trigger_time`) USING BTREE,
  INDEX `I_handle_code`(`handle_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2959 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_log_report
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_log_report`;
CREATE TABLE `xxl_job_log_report`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `trigger_day` datetime(0) NULL DEFAULT NULL COMMENT '调度-时间',
  `running_count` int(11) NOT NULL DEFAULT 0 COMMENT '运行中-日志数量',
  `suc_count` int(11) NOT NULL DEFAULT 0 COMMENT '执行成功-日志数量',
  `fail_count` int(11) NOT NULL DEFAULT 0 COMMENT '执行失败-日志数量',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `i_trigger_day`(`trigger_day`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 113 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_log_report
-- ----------------------------
INSERT INTO `xxl_job_log_report` VALUES (1, '2020-04-07 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (2, '2020-04-06 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (3, '2020-04-05 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (4, '2020-04-08 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (5, '2020-04-07 11:00:00', 0, 0, 6);
INSERT INTO `xxl_job_log_report` VALUES (6, '2020-04-06 11:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (7, '2020-04-05 11:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (8, '2020-04-09 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (9, '2020-04-10 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (10, '2020-04-11 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (11, '2020-04-12 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (12, '2020-04-13 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (13, '2020-04-14 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (14, '2020-04-15 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (15, '2020-04-16 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (16, '2020-04-17 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (17, '2020-04-18 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (18, '2020-04-19 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (19, '2020-04-20 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (20, '2020-04-21 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (21, '2020-04-22 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (22, '2020-04-23 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (23, '2020-04-24 00:00:00', 0, 16, 0);
INSERT INTO `xxl_job_log_report` VALUES (24, '2020-04-25 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (25, '2020-04-26 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (26, '2020-04-27 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (27, '2020-04-28 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (28, '2020-04-29 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (29, '2020-04-30 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (30, '2020-05-01 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (31, '2020-05-02 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (32, '2020-05-03 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (33, '2020-05-04 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (34, '2020-05-05 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (35, '2020-05-06 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (36, '2020-05-07 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (37, '2020-05-08 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (38, '2020-05-09 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (39, '2020-05-10 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (40, '2020-05-11 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (41, '2020-05-12 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (42, '2020-05-13 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (43, '2020-05-22 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (44, '2020-05-21 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (45, '2020-05-20 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (46, '2020-05-23 00:00:00', 0, 1, 0);
INSERT INTO `xxl_job_log_report` VALUES (47, '2020-05-24 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (48, '2020-05-25 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (49, '2020-05-26 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (50, '2020-05-27 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (51, '2020-05-28 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (52, '2020-05-29 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (53, '2020-05-30 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (54, '2020-05-31 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (55, '2020-06-01 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (56, '2020-06-02 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (57, '2020-06-03 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (58, '2020-06-04 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (59, '2020-06-05 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (60, '2020-06-06 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (61, '2020-06-07 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (62, '2020-06-08 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (63, '2020-06-09 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (64, '2020-06-10 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (65, '2020-06-11 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (66, '2020-06-12 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (67, '2020-06-13 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (68, '2020-06-14 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (69, '2020-06-15 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (70, '2020-06-16 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (71, '2020-06-17 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (72, '2020-06-18 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (73, '2020-06-19 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (74, '2020-06-20 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (75, '2020-06-21 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (76, '2020-06-22 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (77, '2020-06-23 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (78, '2020-06-24 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (79, '2020-06-25 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (80, '2020-06-26 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (81, '2020-06-27 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (82, '2020-06-28 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (83, '2020-06-29 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (84, '2020-06-30 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (85, '2020-07-01 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (86, '2020-07-02 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (87, '2020-07-03 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (88, '2020-07-04 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (89, '2020-07-05 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (90, '2020-07-06 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (91, '2020-07-07 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (92, '2020-07-08 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (93, '2020-07-09 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (94, '2020-07-10 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (95, '2020-07-11 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (96, '2020-07-12 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (97, '2020-07-13 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (98, '2020-07-14 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (99, '2020-07-15 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (100, '2020-07-16 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (101, '2020-07-17 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (102, '2020-07-18 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (103, '2020-07-19 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (104, '2020-07-20 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (105, '2020-07-21 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (106, '2020-07-22 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (107, '2020-07-23 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (108, '2020-07-24 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (109, '2020-07-25 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (110, '2020-07-26 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (111, '2020-07-27 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (112, '2020-07-28 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (113, '2020-07-29 00:00:00', 0, 0, 0);

-- ----------------------------
-- Table structure for xxl_job_logglue
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_logglue`;
CREATE TABLE `xxl_job_logglue`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_id` int(11) NOT NULL COMMENT '任务，主键ID',
  `glue_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'GLUE备注',
  `add_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_registry
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_registry`;
CREATE TABLE `xxl_job_registry`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `registry_group` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `registry_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `registry_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `i_g_k_v`(`registry_group`, `registry_key`, `registry_value`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 211 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_registry
-- ----------------------------
INSERT INTO `xxl_job_registry` VALUES (210, 'EXECUTOR', 'article-admin', '192.168.10.2:9999', '2020-09-11 16:33:05');

-- ----------------------------
-- Table structure for xxl_job_user
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_user`;
CREATE TABLE `xxl_job_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `role` tinyint(4) NOT NULL COMMENT '角色：0-普通用户、1-管理员',
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限：执行器ID列表，多个逗号分割',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `i_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_user
-- ----------------------------
INSERT INTO `xxl_job_user` VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 1, NULL);

SET FOREIGN_KEY_CHECKS = 1;
