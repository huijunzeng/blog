package com.teeya.user.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.teeya.common.entity.pojo.BaseEntity;
import lombok.Data;

/**
* Created by Mybatis Generator 2019/11/21
*/
@TableName(value = "resource")
@Data
public class ResourceEntity extends BaseEntity {

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源类型
     */
    private String type;

    /**
     * 资源url
     */
    private String url;

}