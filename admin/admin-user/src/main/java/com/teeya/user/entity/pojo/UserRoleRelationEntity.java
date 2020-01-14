package com.teeya.user.entity.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import com.teeya.common.entity.BaseEntity;
import lombok.Data;

/**
* Created by Mybatis Generator 2019/11/21
*/
@TableName(value = "user_role_relation")
@Data
public class UserRoleRelationEntity extends BaseEntity {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 角色id
     */
    private String roleId;

}