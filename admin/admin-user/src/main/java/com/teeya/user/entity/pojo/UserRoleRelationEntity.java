package com.teeya.user.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.teeya.common.web.entity.pojo.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by Mybatis Generator 2019/11/21
*/
@TableName(value = "user_role_relation")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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