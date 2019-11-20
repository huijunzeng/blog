package com.teeya.user.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
* Created by Mybatis Generator 2019/11/21
*/
@Table(name = "user")
@Data
public class UserEntity implements Serializable {
    /**
     * 用户id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 用户更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 是否删除（1删除0未删除）
     */
    private Integer deleted;

    private static final long serialVersionUID = 1L;
}