CREATE TABLE `points` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `account_id` bigint(20) NOT NULL COMMENT '账户id',
  `account_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账户名称',
  `point` int(255) NOT NULL COMMENT '积分值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;