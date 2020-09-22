/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : 129.211.34.120:3306
 Source Schema         : user

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 11/09/2020 16:31:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;

CREATE TABLE `resource` (
  `id` bigint(20) NOT NULL COMMENT '资源id',
  `name` varchar(50) NOT NULL COMMENT '资源名称',
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源类型(0菜单 1按钮)',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源url',
  `method` varchar(50) DEFAULT NULL COMMENT '资源请求方法',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `deleted` int(2) DEFAULT '0' COMMENT '是否已删除 1已删除 0未删除',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `version` bigint(15) DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES (1, 'admin-user', '1', '/admin-user', '2020-01-15 13:42:07', '2020-03-31 16:29:42', NULL, NULL, 0, '权限管理', 0);
INSERT INTO `resource` VALUES (2, 'user-manage', '1', '/admin-user/user', '2020-03-31 16:29:35', '2020-03-31 16:29:35', NULL, NULL, 0, '用户管理', 0);
INSERT INTO `resource` VALUES (3, 'role-manage', '1', '/admin-user/role', '2020-03-31 16:30:18', '2020-03-31 16:30:18', NULL, NULL, 0, '角色管理', 0);
INSERT INTO `resource` VALUES (4, 'resource-manage', '1', '/admin-user/resource', '2020-03-31 16:30:38', '2020-03-31 16:30:38', NULL, NULL, 0, '资源管理', 0);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(20) NOT NULL COMMENT '角色id',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色编码',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `created_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除 1已删除 0未删除',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `version` bigint(15) NULL DEFAULT 0 COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'R001', '超级管理员', '测试', '2020-01-08 09:28:33', '2020-01-08 09:28:33', NULL, NULL, 0, NULL, 0);

-- ----------------------------
-- Table structure for role_resource_relation
-- ----------------------------
DROP TABLE IF EXISTS `role_resource_relation`;
CREATE TABLE `role_resource_relation`  (
  `id` bigint(20) NOT NULL COMMENT '关系id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `resource_id` bigint(20) NOT NULL COMMENT '资源id',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `created_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除 1已删除 0未删除',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `version` bigint(15) NULL DEFAULT 0 COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台角色和资源关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_resource_relation
-- ----------------------------
INSERT INTO `role_resource_relation` VALUES (1, 1, 1, '2020-01-15 13:42:29', '2020-01-15 13:42:29', NULL, NULL, 0, NULL, 0);
INSERT INTO `role_resource_relation` VALUES (2, 1, 2, '2020-03-31 16:31:07', '2020-03-31 16:31:07', NULL, NULL, 0, NULL, 0);
INSERT INTO `role_resource_relation` VALUES (3, 1, 3, '2020-03-31 16:31:11', '2020-03-31 16:31:11', NULL, NULL, 0, NULL, 0);
INSERT INTO `role_resource_relation` VALUES (4, 1, 4, '2020-03-31 16:31:17', '2020-03-31 16:31:17', NULL, NULL, 0, NULL, 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL COMMENT '用户id',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码密文',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `created_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除 1已删除 0未删除',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `version` bigint(15) NULL DEFAULT 0 COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '$2a$10$vDAifcQnl.wo5vdKr2W.j.IdqqAehLJrARa4wvjrOeJgxBz2jq1he', NULL, NULL, '2019-11-24 23:11:18', '2020-03-14 15:13:35', NULL, NULL, 0, '', 0);
INSERT INTO `user` VALUES (1239425991833407490, 'root', '$2a$10$vDAifcQnl.wo5vdKr2W.j.IdqqAehLJrARa4wvjrOeJgxBz2jq1he', '18826233829@163.com', '18826233829', '2020-03-16 00:39:19', '2020-04-26 14:41:42', 'admin', 'admin', 0, NULL, 0);
INSERT INTO `user` VALUES (1252533747891310594, '测试1', '$2a$10$QDp97Ighmqb4vS/UHUqvHeyaHilo5wLf5SV/TxCjUwwQb3XufNiKa', NULL, '18826233388', '2020-04-21 04:44:51', '2020-04-21 22:20:26', 'admin', 'admin', 1, NULL, 0);
INSERT INTO `user` VALUES (1252534548856573953, '测试2', '$2a$10$TfS8ILDXKtpN9xL1L3frre1MfwvErS7iBiSi35.ujWHgGruFpr62a', NULL, '18826233389', '2020-04-21 04:48:02', '2020-04-21 22:26:18', 'admin', 'admin', 1, NULL, 0);
INSERT INTO `user` VALUES (1252584092235468802, '狗蛋1', '$2a$10$H3mkv7Z.5w9mIDAu/TsF5.X/PX41prW85w/1vykRHkc50tWQIhzRC', NULL, '13542314852', '2020-04-21 08:04:54', '2020-04-21 08:04:54', 'admin', 'admin', 0, NULL, 0);

-- ----------------------------
-- Table structure for user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `user_role_relation`;
CREATE TABLE `user_role_relation`  (
  `id` bigint(20) NOT NULL COMMENT '关系id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `created_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除 1已删除 0未删除',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `version` bigint(15) NULL DEFAULT 0 COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台用户和角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role_relation
-- ----------------------------
INSERT INTO `user_role_relation` VALUES (1, 1, 1, '2020-01-08 09:29:10', '2020-01-10 16:16:28', NULL, NULL, 0, NULL, 0);
INSERT INTO `user_role_relation` VALUES (2, 1239425991833407490, 1, '2020-04-26 14:45:19', '2020-04-26 14:45:19', NULL, NULL, 0, NULL, 0);
INSERT INTO `user_role_relation` VALUES (1252533748180717570, 1252533747891310594, 1, '2020-04-21 04:44:51', '2020-04-21 22:20:27', 'admin', 'admin', 1, NULL, 0);
INSERT INTO `user_role_relation` VALUES (1252534548982403074, 1252534548856573953, 1, '2020-04-21 04:48:02', '2020-04-21 22:26:19', 'admin', 'admin', 1, NULL, 0);
INSERT INTO `user_role_relation` VALUES (1252584092449378305, 1252584092235468802, 1, '2020-04-21 08:04:54', '2020-04-21 08:04:54', 'admin', 'admin', 0, NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
