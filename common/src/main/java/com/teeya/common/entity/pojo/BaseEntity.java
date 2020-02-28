package com.teeya.common.entity.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础类
 * @Author: ZJH
 * @Date: 2020/1/14 10:21
 */

@Data
@ApiModel(value = "实体类基础类")
public class BaseEntity implements Serializable {

    /**
     * id主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createdBy;

    /**
     * 更新人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatedBy;

    /**
     * 是否已删除 1已删除 0未删除
     * @TableLogic 逻辑删除，调用delete方法实际执行update
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @TableLogic
    private Integer deleted;

    /**
     * 备注
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String remark;

    /**
     * 版本  每次操作版本加1
     */
    @TableField(update = "%s+1", fill = FieldFill.INSERT_UPDATE)
    private long version;
}
