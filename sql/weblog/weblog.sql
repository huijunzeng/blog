/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : 129.211.34.120:3306
 Source Schema         : weblog

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 11/09/2020 16:32:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` bigint(20) NOT NULL COMMENT '文章id',
  `title` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章标题',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章内容',
  `visible` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否私密不可见 0否 1是',
  `reading_number` int(11) NULL DEFAULT 0 COMMENT '阅读数',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `created_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除 1已删除 0未删除',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `version` bigint(15) NULL DEFAULT 0 COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '博客文章表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (1, '1', '<p>hello，world！</p>', '1', 0, '2020-03-10 21:16:32', '2020-04-22 14:42:02', NULL, NULL, 0, NULL, 0);
INSERT INTO `article` VALUES (1253214538338144258, 'test', 'hello,test', '1', 0, '2020-04-23 01:50:04', '2020-04-23 01:50:04', 'admin', 'admin', 0, NULL, 0);
INSERT INTO `article` VALUES (1253219682664583169, '11', '**888**\n*saas*\n```\nhello,world\n```\n', '0', 0, '2020-04-23 02:10:31', '2020-04-23 02:10:31', 'admin', 'admin', 0, NULL, 0);
INSERT INTO `article` VALUES (1253220014987677697, '', '1**粗体**\ndada\n```java\nsasad\n```\n', '', 0, '2020-04-23 02:11:50', '2020-04-23 02:11:50', 'admin', 'admin', 0, NULL, 0);
INSERT INTO `article` VALUES (1253224924588281857, '66', '1', '0', 0, '2020-04-23 02:31:20', '2020-04-23 02:31:20', 'admin', 'admin', 0, NULL, 0);
INSERT INTO `article` VALUES (1253225796571500546, '88', '<p><img src=\"www.baidu.com02e9e35fbfb3445d813bb2c697167bba.jpg\" alt=\"TB2JItQlRDH8KJjSszcXXbDTFXa_!!2277536545.jpg\" /></p>\n<pre><code class=\"lang-\">888\n</code></pre>\n', '0', 0, '2020-04-23 02:34:48', '2020-04-23 02:34:48', 'admin', 'admin', 0, NULL, 0);
INSERT INTO `article` VALUES (1253238495745413121, '99', '<p>5555</p>\n', '0', 0, '2020-04-23 03:25:16', '2020-04-23 03:25:16', 'admin', 'admin', 0, NULL, 0);
INSERT INTO `article` VALUES (1253239348439027713, '99', '<p>5555</p>\n', '0', 0, '2020-04-23 03:28:39', '2020-04-23 03:28:39', 'admin', 'admin', 0, NULL, 0);
INSERT INTO `article` VALUES (1253240959261478913, '99', '<p>5555</p>\n', '0', 0, '2020-04-23 03:35:03', '2020-04-23 03:35:03', 'admin', 'admin', 0, NULL, 0);

-- ----------------------------
-- Table structure for article_classification_relation
-- ----------------------------
DROP TABLE IF EXISTS `article_classification_relation`;
CREATE TABLE `article_classification_relation`  (
  `id` bigint(20) NOT NULL COMMENT '关系id',
  `article_id` bigint(20) NOT NULL COMMENT '文章id',
  `classification_id` bigint(20) NOT NULL COMMENT '分类id',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `created_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除 1已删除 0未删除',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `version` bigint(15) NULL DEFAULT 0 COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章和分类关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_classification_relation
-- ----------------------------
INSERT INTO `article_classification_relation` VALUES (1, 1, 1, '2020-04-22 14:39:53', '2020-04-22 14:39:53', NULL, NULL, 0, NULL, 0);

-- ----------------------------
-- Table structure for article_label_relation
-- ----------------------------
DROP TABLE IF EXISTS `article_label_relation`;
CREATE TABLE `article_label_relation`  (
  `id` bigint(20) NOT NULL COMMENT '关系id',
  `article_id` bigint(20) NOT NULL COMMENT '文章id',
  `label_id` bigint(20) NOT NULL COMMENT '标签id',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `created_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除 1已删除 0未删除',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `version` bigint(15) NULL DEFAULT 0 COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章和标签关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_label_relation
-- ----------------------------
INSERT INTO `article_label_relation` VALUES (1, 1, 1, '2020-04-22 14:40:00', '2020-04-22 14:40:00', NULL, NULL, 0, NULL, 0);

-- ----------------------------
-- Table structure for classification
-- ----------------------------
DROP TABLE IF EXISTS `classification`;
CREATE TABLE `classification`  (
  `id` bigint(20) NOT NULL COMMENT '分类id',
  `name` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名称',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `created_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除 1已删除 0未删除',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `version` bigint(15) NULL DEFAULT 0 COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of classification
-- ----------------------------
INSERT INTO `classification` VALUES (1, 'java', '2020-04-22 14:40:14', '2020-04-22 14:40:14', NULL, NULL, 0, NULL, 0);
INSERT INTO `classification` VALUES (2, 'vue', '2020-04-23 16:28:21', '2020-04-23 16:28:21', NULL, NULL, 0, NULL, 0);

-- ----------------------------
-- Table structure for label
-- ----------------------------
DROP TABLE IF EXISTS `label`;
CREATE TABLE `label`  (
  `id` bigint(20) NOT NULL COMMENT '标签id',
  `name` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签名称',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `created_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除 1已删除 0未删除',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `version` bigint(15) NULL DEFAULT 0 COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of label
-- ----------------------------
INSERT INTO `label` VALUES (1, 'springcloud', '2020-04-22 14:38:52', '2020-04-22 14:38:52', NULL, NULL, 0, NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
