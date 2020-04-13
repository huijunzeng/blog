package com.teeya.authentication.entity;

import com.teeya.common.web.entity.pojo.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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