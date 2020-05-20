package com.teeya.demo.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.teeya.common.web.entity.pojo.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ZJH
 * @since 2020-05-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("points")
public class PointsEntity extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 积分值
     */
    @TableField("point")
    private Integer point;


}
