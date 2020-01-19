package com.teeya.user.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.teeya.common.entity.pojo.BaseEntity;
import lombok.Data;

/**
* Created by Mybatis Generator 2019/11/21
*/
@TableName(value = "role_resource_relation")
@Data
public class RoleResourceRelationEntity extends BaseEntity {

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 资源id
     */
    private String resourceId;

}