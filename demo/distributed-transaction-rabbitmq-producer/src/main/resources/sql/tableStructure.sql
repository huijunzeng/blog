CREATE TABLE `accounts` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_estonian_ci NOT NULL COMMENT '账号名称',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `transactional_message` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `current_retry_times` int(11) DEFAULT NULL COMMENT '当前重试次数',
  `max_retry_times` int(11) DEFAULT NULL COMMENT '最大重试次数',
  `queue_name` varchar(255) DEFAULT NULL COMMENT '队列名',
  `exchange_name` varchar(255) DEFAULT NULL COMMENT '交换器名',
  `exchange_type` varchar(255) DEFAULT NULL COMMENT '交换器类型',
  `routing_key` varchar(255) DEFAULT NULL COMMENT '路由键',
  `message` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '消息内容',
  `status` int(255) DEFAULT NULL COMMENT '消息状态',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;