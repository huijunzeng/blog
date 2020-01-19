package com.teeya.user.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.teeya.common.entity.pojo.BaseEntity;
import lombok.Data;

/**
* Created by Mybatis Generator 2019/11/21
*/
@TableName(value = "role")
@Data
public class RoleEntity extends BaseEntity {

    /**
     * 角色编码
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

}