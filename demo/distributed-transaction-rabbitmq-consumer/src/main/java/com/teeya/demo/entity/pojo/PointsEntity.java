package com.teeya.demo.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;

import java.io.Serializable;

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
@TableName("points")
public class PointsEntity implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 账号id
     */
    @TableField("account_id")
    private Long accountId;

    /**
     * 账号名称
     */
    @TableField("account_name")
    private String accountName;

    /**
     * 积分值
     */
    @TableField("point")
    private Integer point;


}
