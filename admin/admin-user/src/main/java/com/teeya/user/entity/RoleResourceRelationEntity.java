package com.teeya.user.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
* Created by Mybatis Generator 2019/11/21
*/
@Table(name = "role_resource_relation")
@Data
public class RoleResourceRelationEntity implements Serializable {
    /**
     * 关系id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private String roleId;

    /**
     * 资源id
     */
    @Column(name = "resource_id")
    private String resourceId;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 更新时间
     */
    @Column(name = "updated_time")
    private Date updatedTime;

    /**
     * 创建人
     */
    @Column(name = "created_by")
    private String createdBy;

    /**
     * 更新人
     */
    @Column(name = "updated_by")
    private String updatedBy;

    /**
     * 是否已删除 1已删除 0未删除
     */
    private Integer deleted;

    /**
     * 备注
     */
    private String remark;

    private static final long serialVersionUID = 1L;
}