package com.teeya.demo.entity.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author ZJH
 * @since 2020-05-21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("transaction_message")
public class TransactionMessageEntity implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 当前重试次数
     */
    @TableField("current_retry_times")
    private Integer currentRetryTimes;

    /**
     * 最大重试次数
     */
    @TableField("max_retry_times")
    private Integer maxRetryTimes;

    /**
     * 队列名
     */
    @TableField("queue_name")
    private String queueName;

    /**
     * 交换器名
     */
    @TableField("exchange_name")
    private String exchangeName;

    /**
     * 交换器类型
     */
    @TableField("exchange_type")
    private String exchangeType;

    /**
     * 路由键
     */
    @TableField("routing_key")
    private String routingKey;

    /**
     * 消息内容
     */
    @TableField("message")
    private String message;

    /**
     * 消息状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;


}
